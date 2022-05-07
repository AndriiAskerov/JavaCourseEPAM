package ua.advanced.lesson06.factory;

import ua.advanced.lesson06.factory.factories.ClassicDisneyPlotFactory;
import ua.advanced.lesson06.factory.factories.ContemporaryDisneyPlotFactory;
import ua.advanced.lesson06.factory.factories.MarvelPlotFactory;
import ua.advanced.lesson06.factory.interfaces.Character;
import ua.advanced.lesson06.factory.interfaces.EpicCrisis;
import ua.advanced.lesson06.factory.interfaces.PlotFactory;

class PlotFactories {

    public PlotFactory classicDisneyPlotFactory(Character hero, Character beloved, Character villain) {
        return new ClassicDisneyPlotFactory(hero, beloved, villain);
    }

    public PlotFactory contemporaryDisneyPlotFactory(Character hero, EpicCrisis epicCrisis, Character funnyFriend) {
        return new ContemporaryDisneyPlotFactory(hero, epicCrisis, funnyFriend);
    }

    public PlotFactory marvelPlotFactory(Character[] heroes, EpicCrisis epicCrisis, Character villain) {
        return new MarvelPlotFactory(heroes, epicCrisis, villain);
    }
}
