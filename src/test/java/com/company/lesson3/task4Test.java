package com.company.lesson3;

import static org.junit.jupiter.api.Assertions.*;

class task4Test {

    @org.junit.jupiter.api.Test
    void geometricProgression() {
        float expected = 175F;
        float actual = task4.GeometricProgression(100F, 0.5F, 20F);
        assertEquals(expected, actual);
    }
}