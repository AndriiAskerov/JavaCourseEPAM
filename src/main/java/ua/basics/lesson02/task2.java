package ua.basics.lesson02;

import static ua.basics.lesson02.task1.printArray;

public class task2 {
    public static void main(String[] args) {
        int[] arr1 = {4, 100, 3, 4};
        int[] arr2 = {5, 50, 50, 4, 5};
        int[] arr3 = {5, 350, 350, 4, 350};
        int[] arr4 = {10, 10, 10, 10, 10};

        System.out.print("1. " + distanceBetweenMaxValues(arr1) + "         Array: ");
        printArray(arr1);
        System.out.print("2. " + distanceBetweenMaxValues(arr2) + "         Array: ");
        printArray(arr2);
        System.out.print("3. " + distanceBetweenMaxValues(arr3) + "         Array: ");
        printArray(arr3);
        System.out.print("4. " + distanceBetweenMaxValues(arr4) + "         Array: ");
        printArray(arr4);
    }

    public static String distanceBetweenMaxValues(int[] arr1) {
        int maxValue = -1; // program works only with integer numbers | "-1" to fix the issue, when the first num is zero
        int firstEntry = 0;
        int lastEntry = 0;
        int distance = 0;

        for (int i = 0; i < arr1.length; i++) {
            if (maxValue > arr1[i]) continue;
            else if (maxValue < arr1[i]) {
                maxValue = arr1[i];
                firstEntry = lastEntry = i;
            }
            else if (maxValue == arr1[i]) {
                lastEntry = i;
                distance = lastEntry - firstEntry;
            }
        }
        return "Distance between max numbers is: " + distance + "\n" +
                "First entrance: " + firstEntry + "\n" +
                " Last entrance: " + lastEntry + "\n" +
                "         Value: " + maxValue + "\n";
    }
}