package ua.advanced.lesson06.observer.bookExample.displays;

import ua.advanced.lesson06.observer.bookExample.interfaces.DisplayElement;
import ua.advanced.lesson06.observer.bookExample.interfaces.Observer;
import ua.advanced.lesson06.observer.bookExample.WeatherData;

public class ForecastDisplay implements DisplayElement, Observer {
    private float currentPressure = 29.92f;
    private float lastPressure;
    private WeatherData weatherData;

    public ForecastDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.print("Forecast: ");
        if (currentPressure > lastPressure) {
            System.out.println("Improving weather on the way!");
        } else if (currentPressure == lastPressure) {
            System.out.println("More of the same..");
        } else if (currentPressure < lastPressure) {
            System.out.println("Watch out for cooler, rainy weather!");
        }
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        setLastPressure(getCurrentPressure());
        setCurrentPressure(pressure);
        display();
    }

    public float getCurrentPressure() {
        return currentPressure;
    }

    public void setCurrentPressure(float currentPressure) {
        this.currentPressure = currentPressure;
    }

    public float getLastPressure() {
        return lastPressure;
    }

    public void setLastPressure(float lastPressure) {
        this.lastPressure = lastPressure;
    }
}
