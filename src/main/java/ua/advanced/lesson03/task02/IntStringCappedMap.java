/*
package ua.advanced.lesson03.task02;

import java.util.*;

public class IntStringCappedMap extends LinkedHashMap<Integer, String> {
    private int capacity;
    private int currentMapLength;
    private int amountOfElements;

    private KV_Pair[] map = new KV_Pair[size()];

    class KV_Pair {
        private Integer key;
        private String value;

        KV_Pair(Integer key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    IntStringCappedMap() {
        this(10);
    }

    IntStringCappedMap(int capacity) {
        this.capacity = capacity;
        this.currentMapLength = 0;
        this.amountOfElements = 0;
    }

    @Override
    public AbstractSet<Map.Entry<Integer, String>> entrySet() {
        return new AbstractSet() {
            @Override
            public Iterator iterator() {
                return new Iterator() {
                    private int index = 0;

                    @Override
                    public boolean hasNext() {
                        return index < amountOfElements && super.i;
                    }

                    @Override
                    public Object next() {
                        return null;
                    }
                };
            }

            @Override
            public int size() {
                return amountOfElements;
            }
        };
    }

    @Override
    public String get(Object key) {
        return super.get(key);
    }

    @Override
    public String put(Integer key, String value) {
        if (value.length() > capacity) {
            throw new IllegalArgumentException();
        }

        if (currentMapLength + value.length() > capacity) {
            Iterator<Integer> iterator = keySet().iterator();
            while (currentMapLength + value.length() > capacity) {
                currentMapLength -= remove(iterator.next()).length();
            }
        }

        if (!super.containsKey(key)) {
            amountOfElements++;
        }

        String overwrittenWord = super.put(key, value); // TODO rework



        currentMapLength += value.length();
        return overwrittenWord;
    }

    @Override
    public int size() {
        return amountOfElements;
    }

    // TODO complete lesson03/task2
}
*/
