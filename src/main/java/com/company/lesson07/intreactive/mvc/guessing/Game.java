package com.company.lesson07.intreactive.mvc.guessing;

public class Game {
    private int min;
    private int max;
    private int attempts;
    private int rand;

    Game(int min, int max, int attempts) {
        this.min = min;
        this.max = max;
        this.rand = rand(min, max);
        this.attempts = attempts;
    }
    Game() {
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