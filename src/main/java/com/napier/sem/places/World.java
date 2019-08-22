package com.napier.sem.places;

import com.napier.sem.utils.Population;

import java.util.ArrayList;

public class World implements Population {

    private World() {}

    private static World instance = null;

    public static World getInstance() {
        if (instance  == null) {
            instance = new World();
        }
        return instance;
    }

    private ArrayList<Continent> continents;

    public ArrayList<Continent> getContinents() {
        return continents;
    }

    public void setContinents(ArrayList<Continent> continents) {
        this.continents = continents;
    }

    public long getPopulation() {
        long worldPopulation = 0;
        for (Continent continent : continents) {
            worldPopulation += continent.getPopulation();
        }
        return worldPopulation;
    }

}

