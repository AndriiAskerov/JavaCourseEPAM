package ua.advanced.lesson06.observer.bookExample.interfaces;

public interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObserver();
}
