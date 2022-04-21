package ua.advanced.lesson03.task02;

import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class IntStringCappedMap {
    private Pair[] map; // масив пар ключ значення
    private int size;
    private int charactersInMap;
    private final int DEFAULT_CAPACITY = 16;
    private int capacity;

    // конструктори
    IntStringCappedMap() {
        this(40);
    }

    IntStringCappedMap(Integer capacity) {
        map = new Pair[DEFAULT_CAPACITY];
        size = 0;
        charactersInMap = 0;
        this.capacity = capacity;
    }

    // пара ключ-значення
    static class Pair {

        private Integer key;
        private String value;
        Pair(Integer key, String value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "K:" + key +
                    ", V:'" + value + '\'';
        }

    }

    // методи
    public AbstractSet entrySet() {
        return new AbstractSet() {
            private int index = 0;

            @Override
            public Iterator iterator() {
                return new Iterator() {
                    @Override
                    public boolean hasNext() {
                        return index < size && map[index] != null;
                    }

                    @Override
                    public Pair next() {
                        return map[index++];
                    }
                };
            }

            @Override
            public int size() {
                return size;
            }
        };
    }

    private String get(Integer key) {
        for (int i = 0; i < size; i++) {
            if (map[i].key == key) {
                return map[i].value;
            }
        }
        throw new NoSuchElementException("The element wasn't found");
    }

    public boolean put(Integer key, String value) {
        if (value.length() > map.length) {
            throw new IllegalArgumentException();
        }



        for (int i = 0; i < size; i++) {
            if (map[i].key == key) { // якщо елемент з таким ключем вже існує
                return false;
            }
        }

        if (charactersInMap + value.length() > map.length) {
            int deletedElements = 0;

            // шукаємо кількість елементів, що необхідно видалити
            while (charactersInMap + value.length() > map.length) {
                charactersInMap -= map[deletedElements].value.length();
                deletedElements++;
            }

            // видалення* елементів
            map = Arrays.copyOfRange(map, deletedElements, map.length);

            // попередня операція зменшила розмір масиву на кількість видалених елементів
            map = Arrays.copyOf(map, map.length + deletedElements);
            size -= deletedElements; // фіксуємо кількість об'єктів нового масиву (map)
        }

        Pair newPair = new Pair(key, value);
        charactersInMap += value.length();
        map[size++] = newPair;
        return true;
    }

    public String remove(Integer key) {
        if (size == 0)
            throw new NoSuchElementException("Map is empty");

        for (int i = 0; i < size; i++) {
            if (map[i].key == key) {
                String output = map[i].value;
                System.arraycopy(map, i + 1, map, i, --size);
                return output;
            }
        }

        throw new NoSuchElementException();
    }

    @Override
    public String toString() {
        return "IntStringCappedMap{" +
                "map=" + Arrays.toString(map) +
                '}';
    }

    public static void main(String[] args) {
        IntStringCappedMap m1 = new IntStringCappedMap();
        m1.put(1, "ab0");
        m1.put(2, "ab1");
        m1.put(3, "ab2");
        m1.put(4, "ab3");
        m1.put(4, "ab4");
        m1.put(4, "ab5");
        m1.put(7, "abF");
        System.out.println(m1 + "\n");

        m1.put(4, "padasdas4544444"); // елемент не буде додано
        System.out.println(m1 + "\n");

        m1.put(10, "padasdas4544445"); // додавання елемента викликає видалення старих
        System.out.println(m1 + "\n");

        m1.put(11, "pa4");
        m1.put(12, "pa5");
        m1.put(13, "pa6");
        System.out.println(m1 + "\n");

        m1.remove(12);
        System.out.println(m1 + "\n");

        m1.put(4, "asd");
        System.out.println(m1 + "\n");

        try {
            m1.remove(7);
        } catch (NoSuchElementException e) {
            System.out.println("NoSuchElementException has been handled!");
        }
        System.out.println(m1);
    }
}
