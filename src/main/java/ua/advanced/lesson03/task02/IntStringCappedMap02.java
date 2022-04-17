package ua.advanced.lesson03.task02;

import java.util.*;

public class IntStringCappedMap02 extends LinkedHashMap<Integer, String> {
    Map<Integer, String> map = new LinkedHashMap();
    private int capacity;
    private int currentMapLength;

    IntStringCappedMap02() {
        this(10);
    }

    IntStringCappedMap02(int capacity) {
        this.capacity = capacity;
        this.currentMapLength = 0;
    }

    //-----------------------

    public AbstractSet entrySet() {
        return new AbstractSet() {
            private int index = 0;

            @Override
            public Iterator iterator() {
                return map.keySet().iterator();
            }

            @Override
            public int size() {
                return map.size();
            }
        };
    }

    //-----------------------

    public String get(Object key) {
        return map.get(key);
    }

    public String put(Integer key, String value) {
        if (value.length() > capacity) {
            throw new IllegalArgumentException();
        }

        if (currentMapLength + value.length() > capacity) {
            while (currentMapLength + value.length() > capacity) {
                Iterator<Integer> iterator = map.keySet().iterator();
                currentMapLength -= remove(iterator.next()).length();
            }
        }

        String overwrittenValue = map.put(key, value);
        currentMapLength += value.length();
        return overwrittenValue;
    }

    public String remove(Object key) {
        String removedValue = map.remove(key);
        currentMapLength += removedValue.length();
        return removedValue;
    }

    public int size() {
        return map.size();
    }

    public String toString() {
        return map.toString();
    }

    public static void main(String[] args) {
        /*IntStringCappedMap02 map1 = new IntStringCappedMap02();
        map1.put(1, "ab");
        map1.put(3, "cd");
        map1.put(4, "ef");
        map1.put(6, "gh");
        map1.put(7, "ij");
        System.out.println(map1);
        map1.put(9, "kl");
        System.out.println(map1);
        try {
            map1.put(9, "klala-lakla");
        } catch (IllegalArgumentException e) {
            System.out.println("An IllegalArgumentException has been handled!");
        }
        System.out.println(map1.put(9, "Ararat"));
        System.out.println(map1);*/

        IntStringCappedMap02 map = new IntStringCappedMap02(25);
        map.put(5, "Five");
        map.put(6, "Six");
        map.put(7, "Seven");
        map.put(8, "Eight");
        map.put(12, "Twelve");
        map.put(9, "Nine");
        map.put(1, "One");

        System.out.println(new TreeMap<>(map));
    }
}
