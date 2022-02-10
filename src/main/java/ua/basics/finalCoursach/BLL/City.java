package ua.basics.finalCoursach.BLL;

import java.io.Serializable;

public class City extends Structure implements Serializable {
    private String country;
    private int population;
    private boolean isCapital;

    // CONSTRUCTORS --------------------------------------------------------------------------------
    public City() {}

    public City(String name, String id, String country, int population, boolean isCapital) {
        super(name, id);
        setCountry(country);
        setPopulation(population);
        setIsCapital(isCapital);
    }

    @Override
    public String toString() {
        return "Name      :\t" + getName() + "\n" +
                "Id        :\t" + getId() + "\n" +
                "Country   :\t" + getCountry() + "\n" +
                "Population:\t" + getPopulation() + "\n" +
                "Is capital:\t" + isCapital() + "\n";
    }

    // GETTERS & SETTERS ---------------------------------------------------------------------------
    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getPopulation() {
        return population;
    }

    public void setIsCapital(boolean isCapital) {
        this.isCapital = isCapital;
    }

    public boolean isCapital() {
        return isCapital;
    }
}
