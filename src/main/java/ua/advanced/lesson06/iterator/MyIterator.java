package ua.advanced.lesson06.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyIterator implements Iterator<Integer> {

    private int index;
    private int counter;
    private final int TIMES;
    private int[] arr;

    public MyIterator(int times, int[] arr) {
        index = 0;
        counter = 0;
        TIMES = times;
        this.arr = arr;
    }

    @Override
    public boolean hasNext() {
        return index < arr.length;
    }

    @Override
    public Integer next() {
        if (!hasNext())
            throw new NoSuchElementException();

        if (counter++ == TIMES - 1) {
            counter = 0;
            return arr[index++];
        }
        return arr[index];
    }
}
