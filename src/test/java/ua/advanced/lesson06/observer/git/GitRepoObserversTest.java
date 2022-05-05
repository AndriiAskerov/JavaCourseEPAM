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
        WebHook commitReadmeWebHook = GitRepoObservers.commitToBranchWebHook("dev-readme");
        WebHook mergeMasterBranch = GitRepoObservers.mergeToBranchWebHook("master");

        repo.addWebHook(mergeMasterBranch);
        repo.addWebHook(commitMasterWebHook);
        repo.addWebHook(commitReadmeWebHook);

        repo.commit("dev-readme", "Johnny Mnemonic","as");
        repo.commit("dev-readme", "Johnny Mnemonic","a");
        repo.commit("dev-readme", "Johnny Mnemonic","s");

        repo.merge("dev-readme", "master");

        assertEquals("[]",
                commitMasterWebHook.eventsLog().toString());
        assertEquals("[" +
                        "Event" +
                            "[Commit, dev-readme, " +
                            "[Commit" + "[Johnny Mnemonic, as]]], " +
                        "Event" +
                            "[Commit, dev-readme, " +
                            "[Commit" + "[Johnny Mnemonic, a]]], " +
                        "Event" +
                            "[Commit, dev-readme, " +
                            "[Commit" + "[Johnny Mnemonic, s]]]]",
                commitReadmeWebHook.eventsLog().toString()
        );
        assertEquals(
                "[Event" +
                            "[Merge, master, " +
                            "[Commit" + "[Johnny Mnemonic, as], " +
                             "Commit" + "[Johnny Mnemonic, a], " +
                             "Commit" + "[Johnny Mnemonic, s]]]]",
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