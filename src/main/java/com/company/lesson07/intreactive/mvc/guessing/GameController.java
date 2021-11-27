package com.company.lesson07.intreactive.mvc.guessing;

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
            Scanner in = new Scanner(System.in);
            if (i < amountOfAttempts) { // якщо не перший цикл, то...
                view.printLog(attemptsLog);
            }
            view.status(model.getAttempts(), lowerBound, greaterBound);

//            try {
//                userAnswer = in.nextInt();
//            } catch (Exception e) {
//                view.printError();
//                i++;
//                continue;
//            }

            try {
                userAnswer = in.nextInt();
            } catch (Exception e) {
                String tmp = "" + userAnswer;
                while (tmp.matches("\\d+")) {
                    view.printError();
                    in = new Scanner(System.in);
                    try {
                        userAnswer = in.nextInt();
                        break;
                    } catch (Exception eInner) {
                        continue;
                    }
                }
            }

            if (userAnswer == model.getRand()) {
                int attempt = 1 + amountOfAttempts - i;
                view.win(attempt);
                attemptsLog[amountOfAttempts - i] = userAnswer;
                view.printLog(attemptsLog);
                break;
            } else if (userAnswer < model.getRand()) {
                isGreater = false;
                view.guessedWrong(isGreater, userAnswer);
                if (lowerBound < userAnswer) {
                    lowerBound = userAnswer;
                }
            } else if (userAnswer > model.getRand()) {
                isGreater = true;
                view.guessedWrong(isGreater, userAnswer);
                if (greaterBound > userAnswer) {
                    greaterBound = userAnswer;
                }
            }
            attemptsLog[amountOfAttempts - i] = userAnswer;
            model.takeAwayOneAttempt();
        }
        if (model.getAttempts() == 0) {
            view.loss(model.getRand());
        }
    }
}