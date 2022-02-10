package ua.basics.lesson03;

import java.util.Arrays;

import static ua.basics.lesson03.task1.IsSorted;

public class task2 {
    public static void main(String[] args) {
        int[] arr1 = {5, 17, 24, 88, 33, 2};
        int[] arr2 = {15,  10,  3};

        System.out.println(Transform(arr1, SortOrder.ASC));
        System.out.println(Transform(arr2, SortOrder.ASC));
        System.out.println(Transform(arr2, SortOrder.DESC));
    }
    public static String Transform(int[] arr, SortOrder sortOrder) {
        int[] tmpArr = Arrays.copyOf(arr, arr.length);
        if (IsSorted(arr, sortOrder)) {
            for (int i = 0; i < arr.length; i++) {
                tmpArr[i] += i;
            }
        }
        return Arrays.toString(tmpArr);
    }
}