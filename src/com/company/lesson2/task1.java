package com.company.lesson2;

public class task1 {
    public static void main(String[] args) {
        int[] arr1 = {10, 5, 3, 4};
        int[] arr2 = {100, 2, 3, 4, 5};
        int[] arr3 = {100, 2, 3, 45, 33, 8, 4, 54};

        printArray(reverseIfEven(arr1));
        printArray(reverseIfEven(arr2));
        printArray(reverseIfEven(arr3));
    }

    public static int[] reverseIfEven(int[] arr) {
        int[] tmpArr = arr;
        for (int i = 0; i < arr.length/2; i++) {
            if (arr[i] % 2 == 0 && arr[(arr.length-1)-i] % 2 == 0) {
                int tmp = arr[i];
                arr[i] = arr[(arr.length-1)-i];
                arr[(arr.length-1)-i] = tmp;
            }
        }
        return tmpArr;
    }
    public static void printArray(int[] arr) {
        String output = "[";
        for (int i = 0; i < arr.length-1; i++) {
            output += arr[i] + ", ";
        }
        output += arr[arr.length-1] + "]";
        System.out.println(output + "\n");
    }
}