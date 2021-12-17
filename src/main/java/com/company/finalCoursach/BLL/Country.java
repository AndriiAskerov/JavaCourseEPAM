package com.company.finalCoursach.BLL;

import java.io.Serializable;
import java.util.ArrayList;

public class Country extends Structure implements Serializable {
    public ArrayList<City> cities = new ArrayList<>();
    private String capital;

    // CONSTRUCTORS --------------------------------------------------------------------------------
    public Country() {
    }

    public Country(String name, String id) {
        super(name, id);
    }

    // GETTERS & SETTERS ---------------------------------------------------------------------------
    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getCapital() {
        return capital;
    }

    // OTHER METHODS -------------------------------------------------------------------------------
    @Override
    public String toString() {
        return "Name      :\t" + getName() + "\n" +
                "Id        :\t" + getId() + "\n" +
                "Capital   :\t" + getCapital() + "\n";
    }
}
