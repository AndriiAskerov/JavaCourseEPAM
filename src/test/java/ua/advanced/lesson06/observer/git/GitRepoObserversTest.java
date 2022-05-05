package ua.advanced.lesson06.observer.git;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.advanced.lesson06.observer.git.interfaces.Repository;
import ua.advanced.lesson06.observer.git.interfaces.WebHook;

import static org.junit.jupiter.api.Assertions.*;

class GitRepoObserversTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void readmeCase() {
        Repository repo = GitRepoObservers.newRepository();

        WebHook commitMasterWebHook = GitRepoObservers.commitToBranchWebHook("master");
        WebHook commitReadmeWebHook = GitRepoObservers.commitToBranchWebHook("development");
        WebHook mergeMasterBranch = GitRepoObservers.mergeToBranchWebHook("master");

        repo.addWebHook(mergeMasterBranch);
        repo.addWebHook(commitMasterWebHook);
        repo.addWebHook(commitReadmeWebHook);

        repo.commit("development", "Serj Verhust","Added task01");
        repo.commit("development", "John Silver","rework method 'addFirst()' in task01.LinkedList");
        repo.commit("development", "Serj Verhust","Added tests to task01");

        repo.merge("development", "master");

        assertEquals("[]",
                commitMasterWebHook.eventsLog().toString());
        assertEquals("[" +
                        "Event[" +
                            "Commit, development, [" +
                            "Commit" + "[Serj Verhust, Added task01]]], " +
                        "Event[" +
                            "Commit, development, [" +
                            "Commit" + "[John Silver, rework method 'addFirst()' in task01.LinkedList]]], " +
                        "Event[" +
                            "Commit, development, [" +
                            "Commit[Serj Verhust, Added tests to task01]]]]",
                commitReadmeWebHook.eventsLog().toString()
        );
        assertEquals(
                "[" +
                        "Event[" +
                            "Merge, master, [" +
                            "Commit" + "[Serj Verhust, Added task01], " +
                            "Commit" + "[John Silver, rework method 'addFirst()' in task01.LinkedList], " +
                            "Commit" + "[Serj Verhust, Added tests to task01]]]]",
                mergeMasterBranch.eventsLog().toString()
        );

    }

    @Test
    void newRepository() {
    }

    @Test
    void commitToBranchWebHook() {
    }

    @Test
    void mergeToBranchWebHook() {
    }
}