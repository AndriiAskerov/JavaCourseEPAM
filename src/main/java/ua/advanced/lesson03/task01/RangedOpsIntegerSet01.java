package ua.advanced.lesson03.task01;

import java.util.Iterator;
import java.util.TreeSet;

public class RangedOpsIntegerSet01 extends TreeSet<Integer> {

    public boolean add(int start, int end) {
        boolean result = true;
        for (int i = start; i < end; i++) {
            boolean temp = add(i);
            if (!temp) {
                result = false;
            }
        }
        return result;
    }

    public boolean remove(int start, int end) {
        boolean result = true;
        for (int i = start; i < end; i++) {
            boolean temp = remove(i);
            if (!temp) {
                result = false;
            }
        }
        return result;
    }

    @Override
    public Iterator<Integer> iterator() {
        return super.iterator();
    }

    public static void main(String[] args) {
        RangedOpsIntegerSet01 set = new RangedOpsIntegerSet01();
        set.add(1, 5);
        for (Integer num : set) {
            System.out.print(num + ",");
        }

        System.out.println();

        set.add(1, 15);
        set.remove(3, 12);
        for (Integer num : set) {
            System.out.print(num + ",");
        }
    }
}
