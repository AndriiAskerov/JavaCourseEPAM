package ua.advanced.lesson06.strategy;

import ua.advanced.lesson06.strategy.interfaces.Card;
import ua.advanced.lesson06.strategy.interfaces.Deck;

import java.util.LinkedList;
import java.util.List;

public class DefaultDeck implements Deck {
    private LinkedList<Card> cards;
    private int size;

    public DefaultDeck(int size) {
        cards = new LinkedList<>();
        this.size = size;

        // заповення створеного списку картами
        for (int i = 0; i < size; i++) {
            cards.push(new DefaultCard(String.valueOf(i)));
        }
    }

    @Override
    public Card dealCard() {
        if (cards.size() == 0) {
            return null;
        }

        Card tmp = cards.pop();
        size--;
        return tmp;
    }

    @Override
    public List<Card> restCards() {
        LinkedList<Card> tmpList = new LinkedList<>(cards);
        cards = new LinkedList<>();
        size = 0;
        return null;
    }

    @Override
    public int size() {
        return size;
    }
}
