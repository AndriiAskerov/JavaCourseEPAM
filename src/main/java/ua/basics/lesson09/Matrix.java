package ua.basics.lesson09;

import java.util.Arrays;

public class Matrix {
    int rows;
    int columns;
    double[][] matrix;

    Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.matrix = new double[rows][columns];
    }

    Matrix(double[][] twoDimensionalArray) {
        this.rows = twoDimensionalArray.length;
        this.columns = twoDimensionalArray[0].length;
        this.matrix = Arrays.copyOf(twoDimensionalArray, twoDimensionalArray.length);
    }

    public String getByIndex(int row, int column) {
        try {
            if (row < 0 || row >= matrix.length || column < 0 || column >= matrix[0].length)  {
                throw new IndexOutOfBounds("Invalid input. You've gone beyond the boundaries of the matrix");
            }
            return Double.toString(matrix[row][column]);
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }

    public Matrix addition(Matrix ma3x) throws MatricesOfDifferentSizesException {
        Matrix outputMa3x = new Matrix(rows, columns);
        if (rows != ma3x.rows || columns != ma3x.columns) {
            throw new MatricesOfDifferentSizesException("Matrices are incompatible. They are of different sizes");
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                outputMa3x.matrix[i][j] = this.matrix[i][j] + ma3x.matrix[i][j];
            }
        }
        return outputMa3x;
    }

    public Matrix deduction(Matrix ma3x) throws MatricesOfDifferentSizesException {
        Matrix outputMa3x = new Matrix(rows, columns);
        if (rows != ma3x.rows || columns != ma3x.columns) {
            throw new MatricesOfDifferentSizesException("Matrices are incompatible. They are of different sizes");
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                outputMa3x.matrix[i][j] = this.matrix[i][j] - ma3x.matrix[i][j];
            }
        }
        return outputMa3x;
    }

    public Matrix multiplication(Matrix ma3x) throws MatricesOfDifferentSizesException {
        Matrix outputMa3x = new Matrix(rows, columns);
        if (rows != ma3x.rows || columns != ma3x.columns) {
            throw new MatricesOfDifferentSizesException("Matrices are incompatible. They are of different sizes");
        }
        for (int i = 0; i < rows; ++i)
            for (int j = 0; j < columns; ++j)
                for (int k = 0; k < columns; ++k)
                    outputMa3x.matrix[i][j] += matrix[i][k] * ma3x.matrix[k][j];
        return outputMa3x;
    }

    public void setByIndex(int row, int column, double value) {
        matrix[row][column] = value;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public String getMatrix() {
        return Arrays.deepToString(matrix);
    }

    public class IndexOutOfBounds extends Exception {
        IndexOutOfBounds(String message) {
            super(message);
        }
    }

    public class MatricesOfDifferentSizesException extends Exception {
        MatricesOfDifferentSizesException(String message) {
            super(message);
        }
    }
}