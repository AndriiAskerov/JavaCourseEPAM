package ua.advanced.lesson06.observer.git;

import ua.advanced.lesson06.observer.git.interfaces.Repository;
import ua.advanced.lesson06.observer.git.interfaces.WebHook;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DefaultRepository implements Repository {
    private List<WebHook> webHookList;

    public DefaultRepository() {
        webHookList = new ArrayList<>();
    }

    @Override
    public Commit commit(String branch, String developer, String message) {
        // TODO commit implementation
        Commit commit = new Commit(developer, message);
        List<Commit> commitsLog = new ArrayList<>();
        commitsLog.add(commit);
        Event event = new Event(EventType.Commit, commitsLog, branch);
        List<WebHook> webHooksList = new ArrayList<>();
        for(WebHook currentWebHook : webHookList){
            if(currentWebHook.eventType() == EventType.Commit && currentWebHook.branch() == branch) {
                webHooksList.add(currentWebHook);
                currentWebHook.onEvent(event);
            }
        }
        return commit;
    }

    @Override
    public void merge(String srcBranch, String targetBranch) {
        List<Commit> unionCommits = new ArrayList<>();
        WebHook sourceWebHook = null;
        WebHook targetWebHook = null;
        for(WebHook currentWebHook : webHookList){
            if(currentWebHook.eventType() == EventType.Commit){
                if(currentWebHook.branch() == srcBranch) {
                    sourceWebHook = currentWebHook;
                } else if(currentWebHook.branch() == targetBranch){
                    targetWebHook = currentWebHook;
                }
            }
        }
        if(sourceWebHook == null || targetWebHook == null){
            return;
        }
        for(Event currentEvent : sourceWebHook.eventsLog()){
            for(Commit currentCommit : currentEvent.getCommitList()){
                unionCommits.add(currentCommit);
            }
        }

        Event event = new Event(EventType.Merge, unionCommits, targetBranch);
        WebHook foundWebHook = null;
        for(WebHook currentWebHook : webHookList){
            if(currentWebHook.eventType() == EventType.Merge && Objects.equals(currentWebHook.branch(), targetBranch)){
                foundWebHook = currentWebHook;
            }
        }
        if(foundWebHook == null)
            return;
        for(Event currentEvent : foundWebHook.eventsLog()){
            if(currentEvent.getEventType() == EventType.Merge && currentEvent.getBranch() == targetBranch)
                return;
        }
        foundWebHook.onEvent(event);
    }

    @Override
    public void addWebHook(WebHook webHook) {
        webHookList.add(webHook);
    }
}
