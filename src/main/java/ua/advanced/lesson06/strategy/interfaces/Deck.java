package ua.advanced.lesson06.strategy.interfaces;

import java.util.List;

public interface Deck {
    Card dealCard();
    List<Card> restCards();
    int size();
}
