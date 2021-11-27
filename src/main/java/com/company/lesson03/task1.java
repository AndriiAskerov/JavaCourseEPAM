package com.company.lesson03;

public class task1 {

    public static void main(String[] args) {
        int[] arr1 = {3, 7, 12, 73, 38, 52, 56, 83, 1, 5, 83, 2, 74, 93, 99};
        int[] arr2 = {5, 6, 7, 9, 40, 58};
        int[] arr3 = {9, 6, 3, 2, 1};

        System.out.println(IsSorted(arr1, SortOrder.ASC));
        System.out.println(IsSorted(arr2, SortOrder.ASC));
        System.out.println(IsSorted(arr3, SortOrder.DESC));
    }
    public static boolean IsSorted(int[] arr, SortOrder sortOrder) {
        if (sortOrder == SortOrder.ASC) {
            for (int i = 0; i < arr.length-1; i++) {
                if (arr[i] > arr[i+1]) return false;
            }
        }
        else if (sortOrder == SortOrder.DESC) {
            for (int i = 0; i < arr.length-1; i++) {
                if (arr[i] < arr[i+1]) return false;
            }
        }
        return true;
    }
}