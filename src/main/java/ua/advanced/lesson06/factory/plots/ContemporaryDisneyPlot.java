package ua.advanced.lesson06.factory.plots;

import ua.advanced.lesson06.factory.interfaces.Character;
import ua.advanced.lesson06.factory.interfaces.EpicCrisis;
import ua.advanced.lesson06.factory.interfaces.Plot;

public class ContemporaryDisneyPlot implements Plot {

    private Character hero;
    private EpicCrisis epicCrisis;
    private Character funnyFriend;

    public ContemporaryDisneyPlot(Character hero, EpicCrisis epicCrisis, Character funnyFriend) {
        this.hero = hero;
        this.epicCrisis = epicCrisis;
        this.funnyFriend = funnyFriend;
    }

    @Override
    public String toString() {
        return new StringBuilder().append(hero.name()).append(" feels a bit awkward and uncomfortable. But personal issues fades, when a big trouble comes - ").append(epicCrisis.name()).append(". ").append(hero.name()).append(" stands up against it, but with no success at first. But putting self together and help of friends, including spectacular funny ").append(funnyFriend.name()).append(" restore the spirit and ").append(hero.name()).append(" overcomes the crisis and gains gratitude and respect").toString();
    }
}
