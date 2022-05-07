package ua.advanced.lesson06.factory.plots;

import ua.advanced.lesson06.factory.interfaces.Character;
import ua.advanced.lesson06.factory.interfaces.EpicCrisis;
import ua.advanced.lesson06.factory.interfaces.Plot;

public class MarvelPlot implements Plot {

    private Character[] heroes;
    private EpicCrisis epicCrisis;
    private Character villain;

    public MarvelPlot(Character[] heroes, EpicCrisis epicCrisis, Character villain) {
        this.heroes = heroes;
        this.epicCrisis = epicCrisis;
        this.villain = villain;
    }

    @Override
    public String toString() {
        StringBuilder heroesOutput = new StringBuilder();
        for (int i = 0; i < heroes.length; i++) {
            heroesOutput.append("brave ").append(heroes[i].name());
            if (i < heroes.length - 1)
                heroesOutput.append(", ");
        }
        return new StringBuilder().append(epicCrisis.name()).append(" threatens the world. But ").append(heroesOutput).append(" on guard. So, no way that intrigues of ").append(villain.name()).append(" overcome the willpower of inflexible heroes").toString();
    }
}
