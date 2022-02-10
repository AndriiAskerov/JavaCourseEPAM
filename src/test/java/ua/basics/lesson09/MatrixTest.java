package ua.basics.lesson09;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.basics.lesson09.Matrix;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {
    static int i = 0;
    Matrix m1;
    Matrix m2;
    Matrix m3;
    Matrix m4;
    Matrix tmp;
    double[][] twoDimArray1;
    double[][] twoDimArray2;
    String outOfBound;
    String sizeMismatches;

    @BeforeEach
    void setUp() {
        m1 = new Matrix(4, 4);
        m2 = new Matrix(3, 3);
        twoDimArray1 = new double[][]{
                {2.1, 4, 3, 3},
                {5.4, 7, 8, 5},
                {2.2, 4, 3, 3},
                {5.7, 7, 8, 5}
        };
        twoDimArray2 = new double[][]{
                {6.1, 3, 7.1, 3},
                {1.4, 4, 8.6, 5},
                {3.2, 2, 3.9, 3},
                {8.7, 9, 8.4, 5}
        };
        m3 = new Matrix(twoDimArray1);
        m4 = new Matrix(twoDimArray2);
        outOfBound = "Invalid input. You've gone beyond the boundaries of the matrix";
        sizeMismatches = "Matrices are incompatible. They are of different sizes";
        System.out.println("Test_" + ++i + ": ");
    }

    @AfterEach
    void tearDown() {
        System.out.println("End of test_" + i + ".\n");
    }

    @Test
    void getByIndex() {
        assertEquals(outOfBound, m1.getByIndex(4, 1));
        System.out.println("m1 passes");
        assertEquals("0.0", m2.getByIndex(0, 2));
        System.out.println("m2 passes");
        assertEquals(outOfBound, m3.getByIndex(5, 0));
        System.out.println("m3 passes");
        assertEquals("3.9", m4.getByIndex(2, 2));
        System.out.println("m4 passes");
    }

    @Test
    void addition() throws Matrix.MatricesOfDifferentSizesException {
        //m1.addition(m2);
        //System.out.println("subtest \"throwingExceptions\" passes");
        assertEquals("[[2.1, 4.0, 3.0, 3.0], [5.4, 7.0, 8.0, 5.0], [2.2, 4.0, 3.0, 3.0], [5.7, 7.0, 8.0, 5.0]]", m1.addition(m3).getMatrix());
        System.out.println("subtest1 passes");
        assertEquals("[[8.2, 7.0, 10.1, 6.0], [6.800000000000001, 11.0, 16.6, 10.0], [5.4, 6.0, 6.9, 6.0], [14.399999999999999, 16.0, 16.4, 10.0]]", m3.addition(m4).getMatrix());
        System.out.println("subtest2 passes");
    }

    @Test
    void deduction() throws Matrix.MatricesOfDifferentSizesException {
        assertEquals("[[-2.1, -4.0, -3.0, -3.0], [-5.4, -7.0, -8.0, -5.0], [-2.2, -4.0, -3.0, -3.0], [-5.7, -7.0, -8.0, -5.0]]", m1.deduction(m3).getMatrix());
        System.out.println("subtest1 passes");
        assertEquals("[[-3.9999999999999996, 1.0, -4.1, 0.0], [4.0, 3.0, -0.5999999999999996, 0.0], [-1.0, 2.0, -0.8999999999999999, 0.0], [-2.999999999999999, -2.0, -0.40000000000000036, 0.0]]", m3.deduction(m4).getMatrix());
        System.out.println("subtest2 passes");
    }

    @Test
    void multiplication() throws Matrix.MatricesOfDifferentSizesException {
        assertEquals("[[0.0, 0.0, 0.0, 0.0], [0.0, 0.0, 0.0, 0.0], [0.0, 0.0, 0.0, 0.0], [0.0, 0.0, 0.0, 0.0]]", m1.multiplication(m3).getMatrix());
        System.out.println("subtest1 passes");
        assertEquals("[[54.11, 55.3, 86.21000000000001, 50.3], [111.84, 105.2, 171.73999999999998, 100.2], [54.72, 55.6, 86.92, 50.6], [113.66999999999999, 106.1, 173.86999999999998, 101.1]]", m3.multiplication(m4).getMatrix());
        System.out.println("subtest2 passes");
    }

    @Test
    void setByIndex() {
        double v1 = 50.4;
        double v2 = 17.1;
        double v3 = 38.75;

        m1.setByIndex(3, 1, v1);
        System.out.println(m1.getByIndex(3,1));
        m2.setByIndex(2, 2, v2);
        System.out.println(m2.getByIndex(2,2));
        m3.setByIndex(0, 0, v3);
        System.out.println(m3.getByIndex(0,0));
    }
}