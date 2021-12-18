package com.company.lesson08;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class FrequencyTest {
    Frequency f;
    static int i = 0;
    String expected;

    @BeforeEach
    void setUp() throws FileNotFoundException {
        f = new Frequency("resources/lesson08_frequency.txt");
        System.out.println("Test_" + ++i + ": ");
    }

    @AfterEach
    void tearDown() {
        System.out.println("End of test_" + i + ".\n");
    }

    @Test
    void getFrequency() {
        expected = "1. panda ==> 21\n" +
                "2. obunga ==> 13\n" +
                "3. doom ==> 13\n";
        assertEquals(expected, f.getFrequency(3));
    }
}