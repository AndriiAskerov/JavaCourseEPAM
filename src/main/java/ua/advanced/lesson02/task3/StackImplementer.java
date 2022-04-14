package ua.advanced.lesson02.task3;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackImplementer implements Stack {

    private Object[] storage;
    private int top;

    public StackImplementer() {
        this(5);
    }

    public StackImplementer(int capacity) {
        if (capacity < 0) {
            storage = new Object[-capacity];
        } else {
            storage = new Object[capacity];
        }
        top = -1;
    }

    @Override
    public void clear() {
        top = -1; // can cause memory leak
    }

    @Override
    public int size() {
        return storage.length;
    }

    public int elementsInTheStack() {
        return top + 1;
    }

    @Override
    public Iterator<Object> iterator() {
        return new IteratorImplementer();
    }

    private class IteratorImplementer implements Iterator<Object> {

        private int index;

        public IteratorImplementer() {
            index = top;
        }

        @Override
        public boolean hasNext() {
            return index >= 0;
        }

        @Override
        public Object next() {
            if (hasNext()) {
                return storage[index--];
            } else {
                throw new NoSuchElementException();
            }
        }

        @Override
        public void remove() {
            top--;
            index--; // can cause memory leak
        }
    }

    @Override
    public void push(Object element) {
        if (!isFull()) {
            storage[++top] = element;
        } else {
            throw new RuntimeException("Cannot push: stack is full");
        }
    }

    private boolean isFull() {
        return top == storage.length - 1;
    }

    @Override
    public Object pop() {
        if (!isEmpty()) {
            return storage[top--];
        } else {
            throw new RuntimeException("Cannot pop: stack is empty");
        }
    }

    private boolean isEmpty() {
        return top == -1;
    }

    @Override
    public Object top() {
        if (!isEmpty()) {
            return storage[top];
        } else {
            throw new RuntimeException("Cannot peek: stack is empty");
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
            if (top < i || storage[i] == null) {
                b.append(String.valueOf('*'));
            } else {
                b.append(String.valueOf(storage[i]));
            }
            if (i == iMax)
                return b.append(']').toString();
            b.append(", ");
        }
    }

    public static void main(String[] args) {

        StackImplementer stack1 = new StackImplementer(); // we can specify the capacity of the stack (3 is default value)

        // "In case the three elements A, B, C were added to the container..."
        firstDemo(stack1);
        System.out.println("___ End of the 1st demonstration\n");

        // Demonstrate all the methods (including inherited ones)
        secondDemo(stack1);
        System.out.println("___ End of the  2nd demonstration\n");

        // Demonstration of the Iterator
        thirdDemo(stack1);
        System.out.println("___ End of the  3nd demonstration");
    }

    private static void firstDemo(StackImplementer stack1) {
        //  --- 1)
        stack1.push("A");
        stack1.push("B");
        stack1.push("C");
        System.out.println(stack1);
        //  --- 2)
        for (Iterator<Object> i = stack1.iterator(); i.hasNext(); ) {
            System.out.print(" " + i.next() + " ");
        }
        System.out.println();
    }

    private static void secondDemo(StackImplementer stack1) {
        System.out.println("Container.java ------ |"); // --- Container.java
        System.out.println(stack1); // toString() method
        System.out.println("Size! of the stack: " + stack1.size());
        System.out.println("Amount of elements: " + stack1.elementsInTheStack());
        Iterator<Object> objectIterator01 = stack1.iterator();
        stack1.clear();
        System.out.println(stack1 + " Stack after \"clear()\"");
        System.out.println("Amount of elements: " + stack1.elementsInTheStack());
        System.out.println("Stack.java ---------- |"); // --- Stack.java
        stack1.push("a");
        stack1.push("b");
        stack1.push("c");
        System.out.println(stack1);
        System.out.println(stack1 + " Popping \"" + stack1.pop() + "\"");
        System.out.println(stack1 + " Now TOP is: " + stack1.top());
        System.out.println("Amount of elements: " + stack1.elementsInTheStack());
        System.out.println(stack1 + " Let's push something to ensure that \"" + stack1.top() + "\" is TOP");
        stack1.push("P");
        System.out.println(stack1 + " << result of the push()");
        System.out.println("Amount of elements: " + stack1.elementsInTheStack());
    }

    private static void thirdDemo(StackImplementer stack1) {
        System.out.println(stack1);
        for (Iterator<Object> i = stack1.iterator(); i.hasNext(); ) {
            System.out.print(" " + i.next() + " ");
        }
        System.out.println(", where the TOP is \"" + stack1.top() + "\"");
        for (Iterator<Object> i = stack1.iterator(); i.hasNext(); ) {
            System.out.println(stack1.top());
            i.remove();
        }
        try {
            System.out.println(stack1.top());
        } catch (RuntimeException exception) {
            System.out.println("RuntimeException has been handled. Stack is empty, so we cannot peek.");
        }
    }
}
