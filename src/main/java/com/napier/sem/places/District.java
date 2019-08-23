package com.napier.sem.places;

import com.napier.sem.utils.Population;

import java.util.ArrayList;
/**
 Stores information about District population and city name
 */
public class District implements Population {

    private String name;
    private ArrayList<City> cities;

    public District(String name, ArrayList<City> cities){
        this.name = name;
        this.cities = cities;
    }

    public String getName() {
        return name;
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public long getPopulation() {
        long districtPopulation = 0;
        for (City city : cities) {
            districtPopulation += city.getPopulation();
        }
        return districtPopulation;
    }
}
