package ua.advanced.lesson02.task3;

import ua.advanced.lesson01.task02.Container;

public interface Stack extends Container {

    // Pushes the specified element onto the top.
    void push(Object element);

    // Removes and returns the top element.
    Object pop();

    // Returns the top element.
    Object top(); // peek()*

}
