package com.napier.sem.places;

import com.napier.sem.utils.Population;

import java.util.ArrayList;
/**
 Stores information about region population and countries
 */
public class Region implements Population {
    //Instance variables
    private String name;
    private ArrayList<Country> countries;

    //Constructor
    public Region(String name, ArrayList<Country> countries) {
        this.name = name;
        this.countries = countries;
    }

    //Getters
    public String getName() { return name; }
    public ArrayList<Country> getCountries() { return countries; }

    public long getPopulation() {
        long regionPopulation = 0;
        for (Country country : countries) {
            regionPopulation += country.getPopulation();
        }
        return regionPopulation;
    }
}
