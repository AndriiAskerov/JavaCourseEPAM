package ua.advanced.lesson06.strategy;

import ua.advanced.lesson06.strategy.dealing_strategies.Bridge;
import ua.advanced.lesson06.strategy.dealing_strategies.ClassicPoker;
import ua.advanced.lesson06.strategy.dealing_strategies.Fool;
import ua.advanced.lesson06.strategy.dealing_strategies.TexasHoldem;
import ua.advanced.lesson06.strategy.interfaces.CardDealingStrategy;

public class CardDealingStrategies {
    public static CardDealingStrategy texasHoldemCardDealingStrategy() {
        return new TexasHoldem();
    }

    public static CardDealingStrategy classicPokerCardDealingStrategy() {
        return new ClassicPoker();
    }

    public static CardDealingStrategy bridgeCardDealingStrategy() {
        return new Bridge();
    }

    public static CardDealingStrategy foolCardDealingStrategy() {
        return new Fool();
    }
}
