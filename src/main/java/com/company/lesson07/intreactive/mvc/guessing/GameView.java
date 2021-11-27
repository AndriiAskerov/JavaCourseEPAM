package com.company.lesson07.intreactive.mvc.guessing;

import java.util.Arrays;

public class GameView {
    public void status(int attempts, int lowerBound, int greaterBound) {
        System.out.println("You have " + attempts + " attempts. Go ahead and guess the number in between " + lowerBound + " and " + greaterBound + ":");
    }

    public void printLog(int[] log) {
        System.out.println(Arrays.toString(log) + "\n");
    }

    public void printError() {
        System.out.println("Error! Incorrect user input. Please, enter the integer number");
    }

    public void win(int attempt) {
        System.out.println("Congratulations! You have guessed the right number on the " +  attempt + " try.");
    }

    public void guessedWrong(boolean isGreater, int userAnswer) {
        if (!isGreater) {
            System.out.println("Guessed wrong! Your number (" + userAnswer + ") is LESS");
        } else {
            System.out.println("Guessed wrong! Your number (" + userAnswer + ") is GREATER");
        }
    }

    public void loss(int rand) {
        System.out.println("What a pity, you did not guess correctly. Secret number is: " + rand + ". Unlucky in game, lucky in love.");
    }
}