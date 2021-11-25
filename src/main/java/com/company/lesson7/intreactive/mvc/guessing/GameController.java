package com.company.lesson7.intreactive.mvc.guessing;

import java.util.Scanner;

public class GameController {
    Game model;
    GameView view;
    int lowerBound;
    int greaterBound;
    int amountOfAttempts;
    int userAnswer;
    boolean isGreater;
    int[] attemptsLog;
    Scanner in = new Scanner(System.in);


    GameController(Game model, GameView view) {
        this.model = model;
        this.view = view;
        this.lowerBound = model.getMin();
        this.greaterBound = model.getMax();
        this.amountOfAttempts = model.getAttempts();
        this.attemptsLog = new int[model.getAttempts()];
    }

    public void startTheGame() {
        for (int i = amountOfAttempts; i > 0; i--) {
            if (i < amountOfAttempts) { // якщо не перший цикл, то...
                view.printLog(attemptsLog);
            }
            view.status(model.getAttempts(), lowerBound, greaterBound);
            userAnswer = in.nextInt();
            if (userAnswer == model.getRand()) {
                int attempt = 1 + amountOfAttempts-i;
                view.win(attempt);
                attemptsLog[amountOfAttempts-i] = userAnswer;
                view.printLog(attemptsLog);
                break;
            }
            else if (userAnswer < model.getRand()) {
                isGreater = false;
                view.guessedWrong(isGreater, userAnswer);
                if (lowerBound < userAnswer) {
                    lowerBound = userAnswer;
                }
            }
            else if (userAnswer > model.getRand()) {
                isGreater = true;
                view.guessedWrong(isGreater, userAnswer);
                if (greaterBound > userAnswer) {
                    greaterBound = userAnswer;
                }
            }
            attemptsLog[amountOfAttempts-i] = userAnswer;
            model.takeAwayOneAttempt();
        }
        if (model.getAttempts() == 0) {
            view.loss(model.getRand());
        }
    }
}