package ua.advanced.lesson06.observer.git;

import ua.advanced.lesson06.observer.git.interfaces.WebHook;

import java.util.ArrayList;
import java.util.List;

public class DefaultWebHook implements WebHook {
    private String branch;
    private EventType eventType;
    private List<Event> eventsLog;

    public DefaultWebHook(String branch, EventType eventType) {
        this.branch = branch;
        this.eventType = eventType;
        eventsLog = new ArrayList<>();
    }

    @Override
    public String branch() {
        return branch;
    }

    @Override
    public EventType eventType() {
        return eventType;
    }

    @Override
    public List<Event> eventsLog() {
        return eventsLog;
    }

    @Override
    public void onEvent(Event event) {
        eventsLog.add(event);
    }
}
