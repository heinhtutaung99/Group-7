package com.napier.sem.places;

import com.napier.sem.utils.Population;

import java.util.ArrayList;
/**
 Stores information about continent population and regions name
 */
public class Continent implements Population {
    //Instance variables
    private String name;
    private ArrayList<Region> regions;

    //Constructor
    public Continent(String name, ArrayList<Region> regions) {
        this.name = name;
        this.regions = regions;
    }

    /**
     * Getting name using Get Method
     */
    public String getName() { return name; }
    public ArrayList<Region> getRegions() { return regions; }

    /**
     * Getting population in regions using Get Method
     */
    public long getPopulation() {
        long continentPopulation = 0;
        for (Region region : regions) {
            continentPopulation += region.getPopulation();
        }
        return continentPopulation;
    }
}
