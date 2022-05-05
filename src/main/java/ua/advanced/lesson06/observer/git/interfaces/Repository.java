package ua.advanced.lesson06.observer.git.interfaces;

import ua.advanced.lesson06.observer.git.Commit;

public interface Repository {
    Commit commit(String branch, String dev, String message);

    void merge(String srcBranch, String targetBranch);

    void addWebHook(WebHook webHook);
}
