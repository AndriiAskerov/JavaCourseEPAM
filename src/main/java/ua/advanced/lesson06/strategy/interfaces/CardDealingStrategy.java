package ua.advanced.lesson06.strategy.interfaces;

import java.util.List;
import java.util.Map;

public interface CardDealingStrategy {
    Map<String, List<Card>> deal(Deck deck, int playersAmount);
}
