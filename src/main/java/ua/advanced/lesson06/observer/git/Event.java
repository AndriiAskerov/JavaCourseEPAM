package ua.advanced.lesson06.observer.git;

import java.util.List;
import java.util.StringJoiner;

public class Event {
    private List<Commit> commitList;
    private EventType eventType;
    private String branch;

    public Event(EventType eventType, List<Commit> commitList, String branch) {
        this.commitList = commitList;
        this.eventType = eventType;
        this.branch = branch;
    }

    public List<Commit> getCommitList() {
        return commitList;
    }

    public EventType getEventType() {
        return eventType;
    }

    public String getBranch() {
        return branch;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Event.class.getSimpleName() + "[", "]")
                .add(eventType.toString())
                .add(branch)
                .add(commitList.toString())
                .toString();
    }
}
