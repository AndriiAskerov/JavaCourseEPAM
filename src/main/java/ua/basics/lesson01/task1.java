package ua.basics.lesson01;

import java.util.Scanner;

public class task1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the number: ");
        int n = in.nextInt();

        if (n > 0) System.out.println(Math.pow(n, 2));
        else if (n < 0) System.out.println(Math.abs(n));
        else System.out.println(0);

        in.close();
    }
}