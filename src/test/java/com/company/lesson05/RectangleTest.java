package com.company.lesson05;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {
    static int i = 0;
    Rectangle rectangle1;
    Rectangle rectangle2;
    Rectangle rectangle3;
    Rectangle rectangle4;

    @BeforeEach
    void setUp() {
        rectangle1 = new Rectangle(2, 2);
        rectangle2 = new Rectangle( 10);
        rectangle3 = new Rectangle(3);
        rectangle4 = new Rectangle();

        System.out.println("Test_" + ++i + ": ");
    }

    @AfterEach
    void tearDown() {
        System.out.println("End of test_" + i + ".\n");
    }

    @Test
    void getSideA() {
        assertEquals(2, rectangle1.getSideA(), 0.01);
        System.out.println("rectangle1: passes");
        assertEquals(10, rectangle2.getSideA(), 0.01);
        System.out.println("rectangle2: passes");
        assertEquals(3, rectangle3.getSideA(), 0.01);
        System.out.println("rectangle3: passes");
        assertEquals(4, rectangle4.getSideA(), 0.01);
        System.out.println("rectangle4: passes");
    }

    @Test
    void getSideB() {
        assertEquals(2, rectangle1.getSideB(), 0.01);
        System.out.println("rectangle1: passes");
        assertEquals(5, rectangle2.getSideB(), 0.01);
        System.out.println("rectangle2: passes");
        assertEquals(5, rectangle3.getSideB(), 0.01);
        System.out.println("rectangle3: passes");
        assertEquals(3, rectangle4.getSideB(), 0.01);
        System.out.println("rectangle4: passes");
    }

    @Test
    void perimeter() {
        assertEquals(8, rectangle1.perimeter(), 0.01);
        System.out.println("rectangle1: passes");
        assertEquals(30, rectangle2.perimeter(), 0.01);
        System.out.println("rectangle2: passes");
        assertEquals(16, rectangle3.perimeter(), 0.01);
        System.out.println("rectangle3: passes");
        assertEquals(14, rectangle4.perimeter(), 0.01);
        System.out.println("rectangle4: passes");
    }

    @Test
    void area() {
        assertEquals(4, rectangle1.area(), 0.01);
        System.out.println("rectangle1: passes");
        assertEquals(50, rectangle2.area(), 0.01);
        System.out.println("rectangle2: passes");
        assertEquals(15, rectangle3.area(), 0.01);
        System.out.println("rectangle3: passes");
        assertEquals(12, rectangle4.area(), 0.01);
        System.out.println("rectangle4: passes");
    }

    @Test
    void isSquare() {
        assertTrue(rectangle1.isSquare());
        System.out.println("rectangle1: passes");
        assertFalse(rectangle2.isSquare());
        System.out.println("rectangle2: passes");
        assertFalse(rectangle3.isSquare());
        System.out.println("rectangle3: passes");
        assertFalse(rectangle4.isSquare());
        System.out.println("rectangle4: passes");
    }

    @Test
    void replaceSides() {
        rectangle1.replaceSides();
        assertEquals(2, rectangle1.getSideA(), 0.01);
        System.out.println("rectangle1: passes");
        rectangle2.replaceSides();
        assertEquals(5, rectangle2.getSideA(), 0.01);
        System.out.println("rectangle2: passes");
        rectangle3.replaceSides();
        assertEquals(5, rectangle3.getSideA(), 0.01);
        System.out.println("rectangle3: passes");
        rectangle4.replaceSides();
        assertEquals(3, rectangle4.getSideA(), 0.01);
        System.out.println("rectangle4: passes");
    }
}