package ua.advanced.lesson06.observer.git;

import ua.advanced.lesson06.observer.git.interfaces.Repository;
import ua.advanced.lesson06.observer.git.interfaces.WebHook;

public class GitRepoObservers {
    public static Repository newRepository() {
        return new DefaultRepository();
    }

    public static WebHook commitToBranchWebHook(String targetBranch) {
        return new DefaultWebHook(targetBranch, EventType.Commit);
    }

    public static WebHook mergeToBranchWebHook(String targetBranch) {
        return new DefaultWebHook(targetBranch, EventType.Merge);
    }
}
