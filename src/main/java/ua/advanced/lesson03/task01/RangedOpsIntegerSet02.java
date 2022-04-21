package ua.advanced.lesson03.task01;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RangedOpsIntegerSet02 implements Iterable<Integer> {
    private int size;
    private int[] set;
    private final int DEFAULT_CAPACITY = 16;

    RangedOpsIntegerSet02() {
        set = new int[DEFAULT_CAPACITY];
        size = 0;
    }

    private boolean isUnique(int value) {
        for (int i = 0; i < size; i++) {
            if (set[i] == value)
                return false;
        }
        return true;
    }

    private void ensureCapacity() {
        if (size == set.length)
            set = Arrays.copyOf(set, set.length * 2);
    }

    public boolean add(int value) {
        if (!isUnique(value))
            return false;

        ensureCapacity();

        set[size++] = value;
        return true;
    }

    public boolean add(int fromInclusive, int toExclusive) {
        boolean isActuallyAdded = false;
        int times = toExclusive - fromInclusive;
        for (int i = 0; i < times; i++) {
            if (add(fromInclusive++)) {
                isActuallyAdded = true;
            }
        }
        return isActuallyAdded;
    }

    private boolean isEmpty() {
        return size == 0;
    }

    public boolean remove(int value) {
        if (isEmpty())
            return false;

        for (int i = 0; i < size; i++) {
            if (set[i] == value) {
                System.arraycopy(set, i + 1, set, i, size - i);
                size--;
                return true;
            }
        }
        return false;
    }

    public boolean remove(int fromInclusive, int toExclusive) {
        boolean isActuallyRemoved = false;
        int times = toExclusive - fromInclusive;
        for (int i = 0; i < times; i++) {
            if (remove(fromInclusive++)) {
                isActuallyRemoved = true;
            }
        }
        return isActuallyRemoved;
    }

    public Iterator<Integer> iterator() {
        return new IteratorImplementer();
    }

    class IteratorImplementer implements Iterator<Integer> {

        int index = 0;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public Integer next() {
            if (hasNext())
                return set[index++];
            throw new NoSuchElementException();
        }
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return Arrays.toString(set);
    }

    public static void main(String[] args) {

        System.out.println("1-st DEMO:");
        RangedOpsIntegerSet02 set = new RangedOpsIntegerSet02();
        set.add(1, 5);
        pritOut(set);

        /*Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }*/

        System.out.println("2-nd DEMO:");
        set = new RangedOpsIntegerSet02();
        set.add(1, 5);
        set.add(9, 11);
        pritOut(set);

        System.out.println("3-rd DEMO:");
        set = new RangedOpsIntegerSet02();
        set.add(1, 15);
        set.remove(3, 12);
        pritOut(set);
    }

    private static void pritOut(RangedOpsIntegerSet02 set) {
        StringBuilder str = new StringBuilder("Output: ");
        for(Integer el : set){
            str.append(el + " ");
        }
        str.append("\n");
        System.out.println(str);
    }
}
