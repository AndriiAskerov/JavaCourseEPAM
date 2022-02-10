package ua.basics.lesson02;

import java.util.Arrays;

public class task3 {
    public static void main(String[] args) {
        int[][] matrix1 = {{2, 4, 3, 3},
                {5, 7, 8, 5},
                {2, 4, 3, 3},
                {5, 7, 8, 5}};
        int[][] matrix2 = {{2, 4, 3, 3, 7},
                {5, 7, 8, 5, 4},
                {2, 4, 3, 3, 2},
                {5, 7, 8, 5, 9},
                {2, 7, 2, 8, 5}};

        performTask(matrix1);
        performTask(matrix2);
    }

    public static void performTask(int[][] matrix1) {
        changeMatrix(matrix1);
        System.out.println(matrixToString(matrix1) + "\n");
    }

    public static void changeMatrix(int[][] matrix1) {
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix1[i].length; j++) {
                if (i == j) continue;
                else if (j > i) matrix1[i][j] = 1;
                else if (j < i) matrix1[i][j] = 0;
            }
        }
    }
    public static String matrixToString(int[][] matrix) {
        String[] tmpArr = Arrays.deepToString(matrix).split("\\], \\[");
        tmpArr[0] = tmpArr[0] + "],\n";
        for (int i = 0; i < tmpArr.length-2; i++) {
            tmpArr[1+i] = " [" + tmpArr[1+i] + "],\n";
        }
        tmpArr[tmpArr.length-1] = " [" + tmpArr[tmpArr.length-1];

        String output = "";
        for (int i = 0; i < tmpArr.length; i++) {
            output += tmpArr[i];
        }
        return output;
    }
}