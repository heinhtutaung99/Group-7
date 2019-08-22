package com.napier.sem.places;

import com.napier.sem.utils.Population;

import java.util.ArrayList;

public class Continent implements Population {
    //Instance variables
    private String name;
    private ArrayList<Region> regions;

    //Constructor
    public Continent(String name, ArrayList<Region> regions) {
        this.name = name;
        this.regions = regions;
    }

    //Getters
    public String getName() { return name; }
    public ArrayList<Region> getRegions() { return regions; }

    public long getPopulation() {
        long continentPopulation = 0;
        for (Region region : regions) {
            continentPopulation += region.getPopulation();
        }
        return continentPopulation;
    }
}
