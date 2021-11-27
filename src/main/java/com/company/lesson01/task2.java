package com.company.lesson01;

import java.util.Scanner;
import java.util.Arrays;

public class task2 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a three-digit integer number in between 100 and 999: ");
        int a = input.nextInt();
        System.out.println(func(a));

        input.close();
    }
    public static int func(int number) {
        int tmp = 0;
        String result = "";
        int[] array = new int[3];

        for (int i = 0; i < 3; i++) {
            tmp = (int) (number % Math.pow(10, (i + 1)));
            tmp /= Math.pow(10, i);
            array[i] = tmp;
        }

        Arrays.sort(array);
        for(int i = (3 - 1); i >= 0; i--) {
            result += array[i];
        }
        return (Integer.parseInt(result));
    }
}