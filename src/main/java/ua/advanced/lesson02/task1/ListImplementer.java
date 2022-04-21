package ua.advanced.lesson02.task1;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListImplementer implements List {

    private Link first;
    private Link last;
    private int size;

    ListImplementer() {
        first = null;
        last = null;
        size = 0;
    }

    static class Link {
        Object data;
        Link next;

        Link(Object data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    private boolean isEmpty() {
        return first == null;
    }

    @Override
    public void addFirst(Object element) {
        Link newLink = new Link(element);
        if (isEmpty())
            last = newLink;
        newLink.next = first;
        first = newLink;
        size++;
    }

    @Override
    public void addLast(Object element) {
        Link newLink = new Link(element);
        if (isEmpty())
            first = newLink;
        else
            last.next = newLink;
        last = newLink;
        size++;
    }

    @Override
    public void removeFirst() {
        if (isEmpty())
            return;
        first = first.next;
        size--;
    }

    @Override
    public void removeLast() {
        if (isEmpty())
            return;

        Link current = first;
        while (current.next != last) {
            current = current.next;
        }
        current.next = null;
        last = current;
        size--;
    }

    @Override
    public Object getFirst() {
        return first;
    }

    @Override
    public Object getLast() {
        return last;
    }

    @Override
    public Object search(Object element) {
        if (isEmpty())
            return null;

        Link current = first;
        while (!current.data.equals(element)) {
            if (current.next == null)
                return null;
            else
                current = current.next;
        }
        return current;
    }

    @Override
    public boolean remove(Object element) {
        if (isEmpty())
            return false;

        if (first.data.equals(element)) {
            removeFirst();
            return true;
        } else if (last.data.equals(element)) {
            removeLast();
            return true;
        }

        Link previous = null;
        Link current = first;
        while (current.next != null) {
            if (!current.data.equals(element)) {
                previous = current;
                current = current.next;
            } else {
                previous.next = current.next;
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        first = null;
        last = null;
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

    class IteratorImplementer implements Iterator<Object> {

        Link current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Object next() {
            if (hasNext()) {
                Object data = current.data;
                current = current.next;
                return data;
            }
            throw new NoSuchElementException();
        }
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("ListImplementer (first > last): ");
        Link current = first;
        while (current != null) {
            output.append(current.toString());
            if (current.next == null)
                output.append(";");
            else
                output.append(", ");
            current = current.next;
        }
        return output.toString();
    }

    public static void main(String[] args) {
        ListImplementer l1 = new ListImplementer();
        l1.addFirst(2);
        l1.addFirst(3);
        l1.addFirst(4);
        System.out.println(l1);

        l1.addLast(1);
        l1.addLast(0);
        System.out.println(l1);

        l1.removeFirst();
        System.out.println(l1);

        l1.removeLast();
        System.out.println(l1);

        System.out.println("First: " + l1.getFirst());
        System.out.println("Last: " + l1.getLast());

        System.out.println("Search: " + l1.search(2));

        int v1 = 2;
        int v2 = 1;
        System.out.println(v1 + " removed successfully?: " + l1.remove(v1));
        System.out.println(v1 + " removed successfully?: " + l1.remove(v1));
        System.out.println(l1);
        System.out.println(v2 + " removed successfully?: " + l1.remove(v2));
        System.out.println(l1);

        l1.addFirst(2);
        l1.addFirst(1);
        Iterator<Object> iterator = l1.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
