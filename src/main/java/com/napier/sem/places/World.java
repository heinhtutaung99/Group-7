package com.napier.sem.places;

import com.napier.sem.utils.Population;

import java.util.ArrayList;
/**
 Stores information about the attributes of the world
 */
public class World implements Population {

    private World() {}

    private static World instance = null;
    /**
     *  Getting information with Get Method
     * */
    public static World getInstance() {
        if (instance  == null) {
            instance = new World();
        }
        return instance;
    }

    private ArrayList<Continent> continents;
    /**
     * Getting Continents with Get Method
     */
    public ArrayList<Continent> getContinents() {
        return continents;
    }

    public void setContinents(ArrayList<Continent> continents) {
        this.continents = continents;
    }
    /**
     * Getting population with Get Method
     */
    public long getPopulation() {
        long worldPopulation = 0;
        for (Continent continent : continents) {
            worldPopulation += continent.getPopulation();
        }
        return worldPopulation;
    }

}

