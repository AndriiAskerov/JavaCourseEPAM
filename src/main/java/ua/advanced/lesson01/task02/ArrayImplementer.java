package ua.advanced.lesson01.task02;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayImplementer implements Array {

    private Object[] storage;
    private int size;

    public ArrayImplementer() {
        storage = new Object[0];
        size = 0;
    }

    @Override
    public void add(Object element) {
        scaleUp();
        storage[size++] = element;
    }

    // TODO see scaleDown() function
    private void scaleUp() {
        Object[] largerStorage = new Object[storage.length + 1];
        System.arraycopy(storage, 0, largerStorage, 0, storage.length);
        storage = largerStorage;
    }

    @Override
    public void set(int index, Object element) {
        if (index >= 0 && index < storage.length) {
            storage[index] = element;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public Object get(int index) {
        if (index >= 0 && index < storage.length) {
            return storage[index];
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public int indexOf(Object element) {
        for (int i = 0; i < size; i++) {
            if (storage[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void remove(int index) {
        if (index >= 0 && index < storage.length) {
            for (int i = index; i < storage.length - 1; i++) {
                storage[i] = storage[i + 1];
            }
            scaleDown();
            size--;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    private void scaleDown() {
        Object[] smallerStorage = new Object[storage.length - 1];
        System.arraycopy(storage, 0, smallerStorage, 0, storage.length - 1);
        storage = smallerStorage;
    } // TODO unify scale* methods

    @Override
    public void clear() {
        storage = new Object[0];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Object> iterator() {
        return new IteratorImplementer();
    }

    private class IteratorImplementer implements Iterator<Object> {

        private int index;

        public IteratorImplementer() {
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < storage.length;
        }

        @Override
        public Object next() {
            if (hasNext()) {
                return storage[index++];
            } else {
                throw new NoSuchElementException();
            }
        }

        @Override
        public void remove() {
            if (index >= 0 && index < storage.length) {
                for (int i = index; i < storage.length - 1; i++) {
                    storage[i] = storage[i + 1];
                }
                scaleDown();
                size--;
            } else {
                throw new ArrayIndexOutOfBoundsException();
            }
        }
    }

    public String toString() {
        if (storage == null)
            return "null";

        int iMax = storage.length - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append(String.valueOf(storage[i]));
            if (i == iMax)
                return b.append(']').toString();
            b.append(", ");
        }
    }

    public static void main(String[] args) {
        ArrayImplementer arr1 = new ArrayImplementer();

        // "In case the three elements A, B, C were added to the container..."
        arr1.add("A");
        arr1.add("B");
        arr1.add("C");
        System.out.println(arr1);
        for (Iterator<Object> i = arr1.iterator(); i.hasNext(); ) {
            System.out.println(i.next());
        }

        // Demonstrate all the "MyArray" methods (including inherited ones)
        System.out.println("\nDemonstration #1:\n" + arr1);     // toString()
        System.out.println(arr1.size());                        // size()
        Iterator<Object> objectIterator01 = arr1.iterator();    // iterator()
        arr1.clear();                                           // clear()
        System.out.println(arr1);                               // ...

        arr1.add("A"); // we can use any type that extends Object
        arr1.add(7.4); // ...
        arr1.set(0, 'c');
        arr1.get(0);
        System.out.println(arr1 + " Index of element \"7.4\" is " + arr1.indexOf(7.4));
        arr1.remove(1);
        System.out.println(arr1);

        // preparing array to demonstration
        arr1.clear();
        arr1.add("A");
        arr1.add("B");
        arr1.add("C");

        // Demonstrate all the "MyIterator" methods
        System.out.println("\nDemonstration #2:\n" + arr1);
        int jListIndex = 0;
        for (Iterator<Object> i = arr1.iterator(); i.hasNext(); ) {
            i.next();
            System.out.println(++jListIndex + ". Has next? (" + i.hasNext() + ")");
        }
        for (Iterator<Object> i = arr1.iterator(); i.hasNext(); ) {
            i.next();
        }
        for (Iterator<Object> i = arr1.iterator(); i.hasNext(); ) {
            System.out.println(arr1 + " Let's remove " + arr1.get(0));
            i.remove();
        }
        System.out.println(arr1 + " Now, array size is " + arr1.size());
    }
}