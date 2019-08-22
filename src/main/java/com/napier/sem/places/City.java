package com.napier.sem.places;

import com.napier.sem.utils.Population;

public class City implements Population {

    private String name;
    private int population;
    private boolean isCapital;
    private String country;
    private String district;

    public City(String name, int population, boolean isCapital, String country, String district) {
        this.name = name;
        this.population = population;
        this.isCapital = isCapital;
        this.country = country;
        this.district = district;
    }



    public String getName() {
        return name;
    }

    public long getPopulation() {
        return (long)population;
    }

    public boolean isCapital() {
        return isCapital;
    }

    public String getCountry() {
        return country;
    }



    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", population=" + population +
                ", country='" + country + '\'' +
                ", district='" + district + '\'' +
                '}';
    }
}
