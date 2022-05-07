package ua.advanced.lesson06.factory.factories;

import ua.advanced.lesson06.factory.interfaces.Character;
import ua.advanced.lesson06.factory.interfaces.EpicCrisis;
import ua.advanced.lesson06.factory.interfaces.Plot;
import ua.advanced.lesson06.factory.interfaces.PlotFactory;
import ua.advanced.lesson06.factory.plots.MarvelPlot;

public class MarvelPlotFactory implements PlotFactory {

    private MarvelPlot marvelPlot;

    public MarvelPlotFactory(Character[] heroes, EpicCrisis epicCrisis, Character villain) {
        marvelPlot = new MarvelPlot(heroes, epicCrisis, villain);
    }

    @Override
    public Plot plot() {
        return marvelPlot;
    }
}
