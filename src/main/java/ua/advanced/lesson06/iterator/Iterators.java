package ua.advanced.lesson06.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

class Iterators {

    public static Iterator<Integer> intArrayTwoTimesIterator(int[] array) {
        return new MyIterator(2, array);
    }

    public static Iterator<Integer> intArrayThreeTimesIterator(int[] array) {
        return new MyIterator(3, array);
    }

    public static Iterator<Integer> intArrayFiveTimesIterator(int[] array) {
        return new MyIterator(5, array);
    }

    public static Iterable<String> table(String[] columns, int[] rows) {
        return new MyTable(columns, rows);
    }
}
