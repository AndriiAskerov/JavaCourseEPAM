package com.company.lesson05;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayRectanglesTest {
    static int i = 0;
    ArrayRectangles rectangles1;
    ArrayRectangles rectangles2;

    Rectangle rectangle1 = new Rectangle(2, 2);
    Rectangle rectangle2 = new Rectangle(9, 9);

    @BeforeEach
    void setUp() {
        rectangles1 = new ArrayRectangles(3);
        rectangles2 = new ArrayRectangles(rectangle1, rectangle2);

        System.out.println("Test_" + ++i + ": ");
    }

    @AfterEach
    void tearDown() { System.out.println("End of test_" + i + ".\n"); }

    @Test
    void addRectangle() {
        assertTrue(rectangles1.addRectangle(rectangle1));
        System.out.println("rectangles1: passes");
        assertFalse(rectangles2.addRectangle(rectangle2));
        System.out.println("rectangles2: passes");
    }

    @Test
    void numberOfTheRectangleWithMaxArea() {
        assertEquals(-1, rectangles1.numberOfTheRectangleWithMaxArea());
        System.out.println("rectangles1: passes");
        assertEquals(1, rectangles2.numberOfTheRectangleWithMaxArea());
        System.out.println("rectangles2: passes");
    }

    @Test
    void numberOfTheRectangleWithMinPerimeter() {
        assertEquals(-1, rectangles1.numberOfTheRectangleWithMinPerimeter());
        System.out.println("rectangles1: passes");
        assertEquals(1, rectangles2.numberOfTheRectangleWithMinPerimeter());
        System.out.println("rectangles2: passes");
    }

    @Test
    void numberOfSquares() {
        assertEquals(0, rectangles1.numberOfSquares());
        System.out.println("rectangles1: passes");
        assertEquals(2, rectangles2.numberOfSquares());
        System.out.println("rectangles2: passes");
    }
}