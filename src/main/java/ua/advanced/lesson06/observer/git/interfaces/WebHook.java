package ua.advanced.lesson06.observer.git.interfaces;

import ua.advanced.lesson06.observer.git.Event;
import ua.advanced.lesson06.observer.git.EventType;

import java.util.List;

public interface WebHook {
    String branch();
    EventType eventType();
    List<Event> eventsLog();
    void onEvent(Event event);
}
