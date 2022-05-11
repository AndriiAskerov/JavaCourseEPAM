package ua.basics.lesson08;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.basics.lesson08.Length;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class LengthTest {
    Length l;
    static int i = 0;
    String expected;

    @BeforeEach
    void setUp() throws FileNotFoundException {
        l = new Length("resources/basics/lesson08_length.txt");
        System.out.println("Test_" + ++i + ": ");
    }

    @AfterEach
    void tearDown() {
        System.out.println("End of test_" + i + ".\n");
    }

    @Test
    void getLongest() {
        expected = "1. obungaaaaaa ==> 11\n" +
                "2. pandaumus ==> 9\n" +
                "3. obungum ==> 7\n" +
                "4. pandani ==> 7\n" +
                "5. applium ==> 7\n";
        assertEquals(expected, l.getLongest(5));
    }
}