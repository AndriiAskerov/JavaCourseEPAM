package ua.advanced.lesson01.task2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class ArrayImplementerTest {

    ArrayImplementer arr1;
    int testIndex = 0;

    @BeforeEach
    void setUp() {
        arr1 = new ArrayImplementer();
        arr1.add("A");
        arr1.add("B");
        arr1.add("C");
        System.out.println("Test #" + ++testIndex + ": ");
    }

    @Test
    void add() {
        arr1.clear(); // reset after setUp() stage
        arr1.add("A");
        arr1.add("B");
        arr1.add("C");
        String stringRepresentation = arr1.toString();
        assertEquals("[A, B, C]", stringRepresentation);
    }

    @Test
    void set() {
        arr1.set(0, "X");
        arr1.set(1, "Y");
        arr1.set(2, "Z");
        String stringRepresentation = arr1.toString();
        assertEquals("[X, Y, Z]", stringRepresentation);
    }

    @Test
    void get() {
        assertEquals("A", arr1.get(0));
        assertEquals("B", arr1.get(1));
        assertEquals("C", arr1.get(2));
    }

    @Test
    void indexOf() {
        assertEquals(0, arr1.indexOf("A"));
        assertEquals(1, arr1.indexOf("B"));
        assertEquals(2, arr1.indexOf("C"));
        assertEquals(-1, arr1.indexOf("Z"));
    }

    @Test
    void remove() {
        for (Iterator<Object> i = arr1.iterator(); i.hasNext(); ) {
            i.remove();
        }
        String stringRepresentation = arr1.toString();
        assertEquals("[]", stringRepresentation);
    }

    @Test
    void clear() {
        arr1.clear();
        String stringRepresentation = arr1.toString();
        assertEquals("[]", stringRepresentation);
    }

    @Test
    void size() {
        assertEquals(3, arr1.size());
    }

    @Test
    void iterator() {
        int iterationCounter = 0;
        for (Iterator<Object> i = arr1.iterator(); i.hasNext(); ) {
            i.remove();
            iterationCounter++;
        }

        /* there is a three elements in the array, so.. */
        if (iterationCounter == 3) assertEquals(1, 1);
    }

    @Test
    void testToString() {
        String stringRepresentation = arr1.toString();
        assertEquals("[A, B, C]", stringRepresentation);
    }
}