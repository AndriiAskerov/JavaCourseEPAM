package ua.advanced.lesson06.factory.factories;

import ua.advanced.lesson06.factory.interfaces.Character;
import ua.advanced.lesson06.factory.interfaces.Plot;
import ua.advanced.lesson06.factory.interfaces.PlotFactory;
import ua.advanced.lesson06.factory.plots.ClassicDisneyPlot;

public class ClassicDisneyPlotFactory implements PlotFactory {

    private ClassicDisneyPlot classicDisneyPlot;

    public ClassicDisneyPlotFactory(Character hero, Character beloved, Character villain) {
        classicDisneyPlot = new ClassicDisneyPlot(hero, beloved, villain);
    }

    @Override
    public Plot plot() {
        return classicDisneyPlot;
    }
}
