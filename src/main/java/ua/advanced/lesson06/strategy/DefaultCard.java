package ua.advanced.lesson06.strategy;

import ua.advanced.lesson06.strategy.interfaces.Card;

public class DefaultCard implements Card {
    private String name;

    public DefaultCard(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
