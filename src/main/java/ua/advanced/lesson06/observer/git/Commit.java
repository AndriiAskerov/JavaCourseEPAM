package ua.advanced.lesson06.observer.git;

import java.util.StringJoiner;

public class Commit {
    private String developer;
    private String message;

    public Commit (String developer, String message) {
        this.developer = developer;
        this.message = message;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Commit.class.getSimpleName() + "[", "]")
                .add(developer)
                .add(message)
                .toString();
    }
}
