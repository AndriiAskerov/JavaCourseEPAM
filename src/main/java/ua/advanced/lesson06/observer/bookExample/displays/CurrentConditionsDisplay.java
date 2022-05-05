package ua.advanced.lesson06.observer.bookExample.displays;

import ua.advanced.lesson06.observer.bookExample.interfaces.DisplayElement;
import ua.advanced.lesson06.observer.bookExample.interfaces.Observer;
import ua.advanced.lesson06.observer.bookExample.WeatherData;

public class CurrentConditionsDisplay implements DisplayElement, Observer {
    private float temperature;
    private float humidity;
    private WeatherData weatherData;

    public CurrentConditionsDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        this.weatherData.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.println("Current conditions: " + temperature
                + "F degrees and " + humidity + "% humidity");
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        setTemperature(temperature);
        setHumidity(humidity);
        display(); // существуют и более элегантные способы проектирования отображения данных..
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }
}
