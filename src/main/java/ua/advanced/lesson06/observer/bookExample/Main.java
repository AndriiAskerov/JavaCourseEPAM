package ua.advanced.lesson06.observer.bookExample;

import ua.advanced.lesson06.observer.bookExample.displays.CurrentConditionsDisplay;
import ua.advanced.lesson06.observer.bookExample.displays.ForecastDisplay;
import ua.advanced.lesson06.observer.bookExample.displays.HeatIndexDisplay;
import ua.advanced.lesson06.observer.bookExample.displays.StatisticsDisplay;

public class Main {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();

        CurrentConditionsDisplay ccd = new CurrentConditionsDisplay(weatherData);
        ForecastDisplay fd = new ForecastDisplay(weatherData);
        HeatIndexDisplay hid = new HeatIndexDisplay(weatherData);
        StatisticsDisplay sd = new StatisticsDisplay(weatherData);

        weatherData.setMeasurements(80, 65, 30.4f);
        System.out.println();
        weatherData.setMeasurements(82, 70, 29.2f);
        System.out.println();
        weatherData.setMeasurements(78, 90, 29.2f);
        System.out.println();
    }
}
