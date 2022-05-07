package ua.advanced.lesson06.factory.plots;

import ua.advanced.lesson06.factory.interfaces.Character;
import ua.advanced.lesson06.factory.interfaces.Plot;

public class ClassicDisneyPlot implements Plot {

    private Character hero;
    private Character beloved;
    private Character villain;

    public ClassicDisneyPlot(Character hero, Character beloved, Character villain) {
        this.hero = hero;
        this.beloved = beloved;
        this.villain = villain;
    }

    @Override
    public String toString() {
        return new StringBuilder().append(hero.name()).append(" is great. ").append(hero.name()).append(" and ").append(beloved.name()).append(" love each other. ").append(villain.name()).append(" interferes with their happiness but loses in the end.").toString();
    }
}
