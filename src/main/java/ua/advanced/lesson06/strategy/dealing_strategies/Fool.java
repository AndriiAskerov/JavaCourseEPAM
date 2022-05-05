package ua.advanced.lesson06.strategy.dealing_strategies;

import ua.advanced.lesson06.strategy.interfaces.Card;
import ua.advanced.lesson06.strategy.interfaces.CardDealingStrategy;
import ua.advanced.lesson06.strategy.interfaces.Deck;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Fool implements CardDealingStrategy {
    private Map<String, List<Card>> stacks; // "гральний стіл" - карта гравців (рук)
    private final int CARDS_PER_PLAYER = 6;

    @Override
    public Map<String, List<Card>> deal(Deck deck, int playersAmount) {

        stacks = new HashMap<>();

        // додавання гравців (рук) до "грального столу"
        for (int i = 1; i < (playersAmount + 1); i++) {
            stacks.put("Player-" + i, new LinkedList<>());
        }

        // роздача карти на руку
        for (int cardsInHand = 0; cardsInHand < CARDS_PER_PLAYER; cardsInHand++) {
            // кожному гравцю за столом
            for (int playerN = 1; playerN < (playersAmount + 1); playerN++) {
                stacks.get("Player-" + playerN).add(deck.dealCard());
            }
        }

        // реєструється карта-козир
        stacks.put("Trump", new LinkedList<>());
        stacks.get("Trump").add(deck.dealCard());

        // реєструються карти, що лишилися
        stacks.put("Remaining", new LinkedList<>());
        while (deck.size() != 0) {
            stacks.get("Remaining").add(deck.dealCard());
        }

        return stacks;
    }
}
