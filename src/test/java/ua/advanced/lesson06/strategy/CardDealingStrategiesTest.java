package ua.advanced.lesson06.strategy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.advanced.lesson06.strategy.interfaces.CardDealingStrategy;
import ua.advanced.lesson06.strategy.interfaces.Deck;

import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class CardDealingStrategiesTest {

    static int i = 0;

    @BeforeEach
    void setUp() {
        System.out.println("Test_" + ++i + ": ");
    }

    @AfterEach
    void tearDown() {
    }

    private void test(final CardDealingStrategy strategy, final int deckSize, final int players, final String expected) {
        Deck deck = new DefaultDeck(deckSize);
        assertEquals(deckSize, deck.size());
        assertEquals(expected, new TreeMap(strategy.deal(deck, players)).toString()
        );
        assertEquals(0, deck.size());
    }

    @Test
    void texasHoldemCardDealingStrategy() {
        CardDealingStrategy strategy = CardDealingStrategies.texasHoldemCardDealingStrategy();

        test(strategy, 36, 2,
                "{" +
                        "Community=[31, 30, 29, 28, 27], " +
                        "Player-1=[35, 33], " +
                        "Player-2=[34, 32], " +
                        "Remaining=[26, 25, 24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0]}");
        System.out.println("\tTest_01: passes");

        test(strategy, 36, 3,
                "{" +
                        "Community=[29, 28, 27, 26, 25], " +
                        "Player-1=[35, 32], " +
                        "Player-2=[34, 31], " +
                        "Player-3=[33, 30], " +
                        "Remaining=[24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0]}");
        System.out.println("\tTest_02: passes");

        test(strategy, 52, 4,
                "{" +
                        "Community=[43, 42, 41, 40, 39], " +
                        "Player-1=[51, 47], " +
                        "Player-2=[50, 46], " +
                        "Player-3=[49, 45], " +
                        "Player-4=[48, 44], " +
                        "Remaining=[38, 37, 36, 35, 34, 33, 32, 31, 30, 29, 28, 27, 26, 25, 24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0]}");
        System.out.println("\tTest_03: passes");
    }

    @Test
    void classicPokerCardDealingStrategy() {
        CardDealingStrategy strategy = CardDealingStrategies.classicPokerCardDealingStrategy();

        test(strategy, 36, 2,
                "{" +
                        "Player-1=[35, 33, 31, 29, 27], " +
                        "Player-2=[34, 32, 30, 28, 26], " +
                        "Remaining=[25, 24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0]}");
        System.out.println("\tTest_01: passes");

        test(strategy, 36, 3,
                "{" +
                        "Player-1=[35, 32, 29, 26, 23], " +
                        "Player-2=[34, 31, 28, 25, 22], " +
                        "Player-3=[33, 30, 27, 24, 21], " +
                        "Remaining=[20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0]}");
        System.out.println("\tTest_02: passes");

        test(strategy, 52, 8,
                "{" +
                        "Player-1=[51, 43, 35, 27, 19], " +
                        "Player-2=[50, 42, 34, 26, 18], " +
                        "Player-3=[49, 41, 33, 25, 17], " +
                        "Player-4=[48, 40, 32, 24, 16], " +
                        "Player-5=[47, 39, 31, 23, 15], " +
                        "Player-6=[46, 38, 30, 22, 14], " +
                        "Player-7=[45, 37, 29, 21, 13], " +
                        "Player-8=[44, 36, 28, 20, 12], " +
                        "Remaining=[11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0]}");
        System.out.println("\tTest_03: passes");
    }

    @Test
    void foolCardDealingStrategy() {
        CardDealingStrategy strategy = CardDealingStrategies.foolCardDealingStrategy();

        test(strategy, 36, 2,
                "{Player-1=[35, 33, 31, 29, 27, 25], Player-2=[34, 32, 30, 28, 26, 24], Remaining=[22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0], Trump=[23]}");
        System.out.println("\tTest_01: passes");

        test(strategy, 36, 3,
                "{Player-1=[35, 32, 29, 26, 23, 20], Player-2=[34, 31, 28, 25, 22, 19], Player-3=[33, 30, 27, 24, 21, 18], Remaining=[16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0], Trump=[17]}");
        System.out.println("\tTest_02: passes");

        test(strategy, 52, 4,
                "{Player-1=[51, 47, 43, 39, 35, 31], Player-2=[50, 46, 42, 38, 34, 30], Player-3=[49, 45, 41, 37, 33, 29], Player-4=[48, 44, 40, 36, 32, 28], Remaining=[26, 25, 24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0], Trump=[27]}");
        System.out.println("\tTest_03: passes");
    }

    @Test
    void bridgeCardDealingStrategy() {
        CardDealingStrategy strategy = CardDealingStrategies.bridgeCardDealingStrategy();

        test(strategy, 36, 2,
                "{Player-1=[35, 33, 31, 29, 27, 25, 23, 21, 19, 17, 15, 13, 11], Player-2=[34, 32, 30, 28, 26, 24, 22, 20, 18, 16, 14, 12, 10], Remaining=[9, 8, 7, 6, 5, 4, 3, 2, 1, 0]}");
        System.out.println("\tTest_01: passes");

        test(strategy, 36, 3,
                "{Player-1=[35, 32, 29, 26, 23, 20, 17, 14, 11, 8, 5, 2, null], Player-2=[34, 31, 28, 25, 22, 19, 16, 13, 10, 7, 4, 1, null], Player-3=[33, 30, 27, 24, 21, 18, 15, 12, 9, 6, 3, 0, null], Remaining=[]}");
        System.out.println("\tTest_02: passes");

        test(strategy, 52, 4,
                "{Player-1=[51, 47, 43, 39, 35, 31, 27, 23, 19, 15, 11, 7, 3], Player-2=[50, 46, 42, 38, 34, 30, 26, 22, 18, 14, 10, 6, 2], Player-3=[49, 45, 41, 37, 33, 29, 25, 21, 17, 13, 9, 5, 1], Player-4=[48, 44, 40, 36, 32, 28, 24, 20, 16, 12, 8, 4, 0], Remaining=[]}");
        System.out.println("\tTest_03: passes");
    }
}