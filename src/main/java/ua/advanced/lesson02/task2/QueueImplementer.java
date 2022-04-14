package ua.advanced.lesson02.task2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class QueueImplementer implements Queue {

    private Object[] storage;
    private int top; // the first element of the queue (front)
    private int rear; // the last element of the queue
    private int elementsInTheQueue;

    public QueueImplementer() {
        this(5);
    }

    public QueueImplementer(int capacity) {
        storage = new Object[capacity];
        top = 0;
        rear = -1;
        elementsInTheQueue = 0;
    }

    @Override
    public void clear() {
        top = 0;
        rear = -1;
        elementsInTheQueue = 0;
    }

    @Override
    public int size() {
        return storage.length;
    }

    public int getNumberOfElements() {
        return elementsInTheQueue;
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
            if (top < rear || top == rear) {
                return index <= rear;
            } else if (top > rear && elementsInTheQueue > 0) {
                return index >= top || index <= rear;
            }
            return false; // case when TOP and REAR are equal
        }

        @Override
        public Object next() {
            if (hasNext()) {
                if (index == storage.length - 1) {
                    Object tmp = storage[index];
                    index = 0;
                    return tmp;
                }
                return storage[index++];
            } else {
                throw new NoSuchElementException();
            }
        }

        @Override
        public void remove() {
            if (index == storage.length - 1) {
                top = 0;
                index = 0;
            } else {
                top++;
                index++;
            }
            elementsInTheQueue--;
        }
    }

    @Override
    public void enqueue(Object element) {
        if (rear == storage.length - 1) {
            rear = -1;
        }
        storage[++rear] = element;
        elementsInTheQueue++;
    } // insert()

    private boolean isFull() {
        return elementsInTheQueue == storage.length;
    }

    @Override
    public Object dequeue() {
        Object tmp = storage[top++];
        if (top == storage.length) {
            top = 0;
        }
        elementsInTheQueue--;
        return tmp;
    } // remove()

    private boolean isEmpty() {
        return elementsInTheQueue == 0;
    }

    @Override
    public Object top() {
        if (!isEmpty()) {
            return storage[top];
        } else {
            throw new RuntimeException("Cannot peek TOP: queue is empty");
        }
    } // peekFront()

    public Object rear() {
        if (!isEmpty()) {
            return storage[rear];
        } else {
            throw new RuntimeException("Cannot peek REAR: queue is empty");
        }
    } // peekRear()

    public String toString() {
        if (storage == null)
            return "null";

        int iMax = storage.length - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            if (elementsInTheQueue == 0) {
                b.append(String.valueOf('*'));
            } else if (top < rear || top == rear) {
                if (i < top || i > rear) {
                    b.append(String.valueOf('*'));
                } else {
                    b.append(String.valueOf(storage[i]));
                }
            } else if (top > rear) {
                if (i < top && i > rear) {
                    b.append(String.valueOf('*'));
                } else {
                    b.append(String.valueOf(storage[i]));
                }
            }

            if (i == iMax)
                return b.append(']').toString();
            b.append(", ");
        }
    }

    public static void main(String[] args) {

        QueueImplementer queue1 = new QueueImplementer();

        // "In case the three elements A, B, C were added to the container..."
        firstDemo(queue1);
        System.out.println("___ End of the 1st demonstration\n");

        // Demonstrate all the methods (including inherited ones)
        secondDemo(queue1);
        System.out.println("___ End of the 2nd demonstration\n");

        // Demonstration of the Iterator
        thirdDemo(queue1);
        System.out.println("___ End of the 3rd demonstration\n");
    }

    private static void firstDemo(QueueImplementer queue1) {
        //  --- 1)
        queue1.enqueue("A");
        queue1.enqueue("B");
        queue1.enqueue("C");
        System.out.println(queue1);
        //  --- 2)
        for (Iterator<Object> i = queue1.iterator(); i.hasNext(); ) {
            System.out.print(" " + i.next() + " ");
        }
        System.out.println();
        System.out.println("Now I'll dequeue \"" + queue1.dequeue() + "\" and \"" + queue1.dequeue() + "\"");
        System.out.println(queue1 + ", So now both TOP and REAR are \"" + queue1.top() + "\"");
        queue1.enqueue("X");
        queue1.enqueue("Y");
        queue1.enqueue("Z");
        System.out.println(queue1 + ", I've added some values to demonstrate how iterator works");
        for (Iterator<Object> i = queue1.iterator(); i.hasNext(); ) {
            System.out.print(" " + i.next() + " ");
        }
        System.out.println(", It's a complete circular queue!");
    }

    private static void secondDemo(QueueImplementer queue1) {
        System.out.println("Container.java ------ |"); // --- Container.java
        System.out.println(queue1 + ", it's toString() representation");
        System.out.println("Size! of the queue: " + queue1.size());
        System.out.println("Amount of elements: " + queue1.getNumberOfElements());
        Iterator<Object> objectIterator01 = queue1.iterator();
        queue1.clear();
        System.out.println(queue1 + " Queue after \"clear()\"");
        System.out.println("Amount of elements: " + queue1.getNumberOfElements());
        System.out.println("Queue.java ---------- |"); // --- Queue.java
        queue1.enqueue("a");
        queue1.enqueue("b");
        queue1.enqueue("c");
        System.out.println(queue1 + " Queueing \"a\", \"b\" and \"c\"");
        System.out.println(queue1 + " Dequeuing \"" + queue1.dequeue() + "\"");
        System.out.println(queue1 + " Now TOP is: " + queue1.top());
        System.out.println("Amount of elements: " + queue1.getNumberOfElements());
        System.out.println(queue1 + " Let's enqueue something to ensure that \"" + queue1.rear() + "\" is REAR");
        queue1.enqueue("P");
        System.out.println(queue1 + " << result of the enqueue()");
        System.out.println("Amount of elements: " + queue1.getNumberOfElements());
    }

    private static void thirdDemo(QueueImplementer queue1) {
        System.out.println(queue1 + "Let's check the iterator.next() method");
        for (Iterator<Object> i = queue1.iterator(); i.hasNext(); ) {
            System.out.print(" " + i.next() + " ");
        }
        System.out.println(", where the TOP is \"" + queue1.top() + "\" and REAR is \"" + queue1.rear() + "\"");
        System.out.println("Now let's remove:");
        for (Iterator<Object> i = queue1.iterator(); i.hasNext(); ) {
            System.out.println(" " + queue1.top());
            i.remove();
        }
        System.out.println(queue1 + " << result of iterator.remove()");
        System.out.println("Amount of elements: " + queue1.getNumberOfElements());
        try {
            System.out.println(queue1.top());
        } catch (RuntimeException exception) {
            System.out.println("RuntimeException has been handled. Queue is empty, so we cannot peek [ cannot use queue1.top() ].");
        }
    }
}
