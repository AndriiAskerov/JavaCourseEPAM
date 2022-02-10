package ua.basics.lesson07.intreactive.mvc;

public class GameModel {
    private int min;
    private int max;
    private int attempts;
    private int rand;

    GameModel(int min, int max, int attempts) {
        this.min = min;
        this.max = max;
        this.rand = rand(min, max);
        this.attempts = attempts;
    }
    GameModel() {
        this(0, 100, 5);
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int getRand() {
        return rand;
    }

    public int getAttempts() {
        return attempts;
    }

    public void takeAwayOneAttempt() {
        attempts--;
    }

    private int rand(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}