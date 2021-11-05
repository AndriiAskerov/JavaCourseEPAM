package com.task;

public class Main {
    public static void main(String[] args) {
        System.out.println(func(34));
        System.out.println(func(128));
        System.out.println(func(255));
    }

    public static String func(int num) {
        int tmp, counter = 0;
        String output = "";
        while (num > 0) {
            tmp = num % 2;
            if (tmp == 1) counter++;
            output = tmp + output;
            num = num / 2;
        }
        while (output.length() < 8) {
            output = " " + output;
        }
        return (output + ": total amount of \"1\" is: " + counter);
    }
}