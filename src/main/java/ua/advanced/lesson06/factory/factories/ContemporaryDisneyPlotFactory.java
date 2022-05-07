package ua.advanced.lesson06.factory.factories;

import ua.advanced.lesson06.factory.interfaces.Character;
import ua.advanced.lesson06.factory.interfaces.EpicCrisis;
import ua.advanced.lesson06.factory.interfaces.Plot;
import ua.advanced.lesson06.factory.interfaces.PlotFactory;
import ua.advanced.lesson06.factory.plots.ContemporaryDisneyPlot;

public class ContemporaryDisneyPlotFactory implements PlotFactory {

    private ContemporaryDisneyPlot contemporaryDisneyPlotFactory;

    public ContemporaryDisneyPlotFactory(Character hero, EpicCrisis epicCrisis, Character funnyFriend) {
        contemporaryDisneyPlotFactory = new ContemporaryDisneyPlot(hero, epicCrisis, funnyFriend);
    }

    @Override
    public Plot plot() {
        return contemporaryDisneyPlotFactory;
    }
}
