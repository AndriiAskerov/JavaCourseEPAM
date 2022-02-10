package ua.basics.lesson02;

import static ua.basics.lesson02.task3.matrixToString;

public class interactive {
    public static void main(String[] args) {
        int[][] matrix1 = {{31, 32, 33},
                          {11, 12, 13},
                          { 1,  2,  3},
                          {22, 23, 24}};
        int[][] matrix2 = {{2, 4, 3, 3, 7},
                           {5, 7, 8, 15, 4},
                           {2, 4, 3, 3, 2},
                           {5, 7, 81, 5, 9},
                           {2, 7, 2, 8, 5}};

        System.out.println(matrixToString(sortMatrix(matrix1)));
        System.out.println(matrixToString(sortMatrix(matrix2)));
    }

    private static int getValueOfArr(int[] arr) {
        int valueOfArr = 0;
        for (int i = 0; i < arr.length; i++) {
            valueOfArr += arr[i];
        }
        return valueOfArr;
    }
    private static int[][] sortMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length-1; j++) {
                int valueOfThisArr = getValueOfArr(matrix[j]);
                int valueOfNextArr = getValueOfArr(matrix[j+1]);
                if (valueOfThisArr > valueOfNextArr) {
                    int[] tmpArr = matrix[j];
                    matrix[j] = matrix[j+1];
                    matrix[j+1] = tmpArr;
                }
            }
        }
        int[][] outputArr = new int [matrix.length][];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length-1; j++) {
                outputArr[i] = matrix[i];
            }
        }
        return outputArr;
    }
}