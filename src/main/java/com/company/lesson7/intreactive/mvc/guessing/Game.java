package com.company.lesson7.intreactive.mvc.guessing;

import java.util.Scanner;

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
        return (int) (min + (Math.random() * max));
    }
}