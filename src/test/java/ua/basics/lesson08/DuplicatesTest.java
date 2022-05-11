package ua.basics.lesson08;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.basics.lesson08.Duplicates;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class DuplicatesTest {
    Duplicates d;
    String expected;

    @BeforeEach
    void setUp() throws FileNotFoundException {
        d = new Duplicates("resources/basics/lesson08_duplicates.txt");
    }

    @AfterEach
    void tearDown() {
        System.out.println("The end of the test.");
    }

    @Test
    void getDuplicates() {
        expected = "1. ADNAP ==> true\n" +
                "2. AGNUBO ==> true\n" +
                "3. KIHZE ==> true\n";
        assertEquals(expected, d.getDuplicates(3));
    }
}