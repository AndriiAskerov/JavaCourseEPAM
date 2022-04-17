package ua.advanced.lesson03.task01;

import java.util.Iterator;
import java.util.TreeSet;

public class RangedOpsIntegerSet extends TreeSet<Integer> {

    // task methods
    public boolean add(Integer fromInclusive, Integer toExclusive) {
        boolean isActuallyAdded = false;
        int times = toExclusive - fromInclusive;
        for (int i = 0; i < times; i++) {
            if (add(fromInclusive++)) {
                isActuallyAdded = true;
            }
        }
        return isActuallyAdded;
    }

    public boolean remove(Integer fromInclusive, Integer toExclusive) {
        boolean isActuallyRemoved = false;
        int times = toExclusive - fromInclusive;
        for (int i = 0; i < times; i++) {
            if (remove(fromInclusive++)) {
                isActuallyRemoved = true;
            }
        }
        return isActuallyRemoved;
    }

    // demonstration
    public static void main(String[] args) {
        RangedOpsIntegerSet set = new RangedOpsIntegerSet();

        System.out.println(set.add(3, 5));
        print(set);

        set.clear();
        print(set);

        System.out.println(set.add(1, 5));
        print(set);

        System.out.println(set.add(1, 15));
        print(set);

        System.out.println(set.remove(3, 12));
        print(set);

        System.out.println(set.remove(20, 40));
        print(set);

        System.out.println(set.add(1, 4));
        print(set);
    }

    // iterator based output of the set content
    private static void print(RangedOpsIntegerSet set) {
        Iterator<Integer> iterator = set.iterator();
        if (iterator.hasNext()) {
            while (iterator.hasNext()) {
                System.out.print(iterator.next() + " ");
            }
        } else {
            System.out.print("The Set is empty! ");
        }
        System.out.println("| Size: " + set.size() + "\n");
    }
}
