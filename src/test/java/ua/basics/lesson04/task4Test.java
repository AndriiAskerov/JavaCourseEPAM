package ua.basics.lesson04;

import org.junit.jupiter.api.Test;
import ua.basics.lesson04.task4;

import static org.junit.jupiter.api.Assertions.*;

class task4Test {

    @Test
    void geometricProgression() {
        float expected = 175F;
        float actual = task4.GeometricProgression(100F, 0.5F, 20F);
        assertEquals(expected, actual);
        System.out.println("Test was successfully passed! (" + expected + " = " + actual + ")");
    }
}