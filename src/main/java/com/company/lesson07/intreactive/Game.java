package com.company.lesson07.intreactive;

import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        int min = 0;
        int max = 100;
        int rand = rand(min, max);
        int userAnswer;
        int attempts = 3;
        int lowerBound = min;
        int greaterBound = max;
        Scanner in = new Scanner(System.in);

        while (attempts > 0) {
            System.out.println("You have " + attempts + " attempts. Go ahead and guess the number in between " + lowerBound + " and " + greaterBound + ":");
            userAnswer = in.nextInt();
            if (userAnswer == rand) {
                System.out.println("Congratulations! You have guessed the number.");
                break;
            }
            else if (userAnswer < rand) {
                System.out.println("Guessed wrong! Your number (" + userAnswer + ") is LESS");
                lowerBound = userAnswer;
            }
            else if (userAnswer > rand) {
                System.out.println("Guessed wrong! Your number (" + userAnswer + ") is GREATER");
                greaterBound = userAnswer;
            }
            attempts--;
        }
        if (attempts == 0) {
            System.out.println("What a pity, you did not guess correctly. Secret number is: " + rand + ". Unlucky in game, lucky in love.");
        }
    }
    private static int rand(int min, int max) {
        return (int) (min + (Math.random() * max));
    }
}