package ua.basics.lesson01;

import java.util.Scanner;

public class task3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter an integer four-digit number: ");
        int a = input.nextInt();
        System.out.println(func(a));

        input.close();
    }

    public static String func(int number) {
        int tmp, result = 0;
        final int SIZE = 4;
        int[] array = new int[SIZE];

        for (int i = 0; i < SIZE; i++) {
            tmp = (int) (number % Math.pow(10, (i + 1)));
            tmp /= Math.pow(10, i);
            if (tmp % 2 == 1) {
                array[i] = tmp;
            }
        }

        for(int i = 0; i < SIZE; i++) {
            result += array[i];
        }
        return ("Sum of the odd numbers is: " + result);
    }
}
