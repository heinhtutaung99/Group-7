package com.napier.sem.places;

import com.napier.sem.utils.Population;

import java.util.ArrayList;
/**
 Stores information about country population, district and continent.
 */
public class Country implements Population {

    private String countryCode;
    private String name;
    private ArrayList<District> districts;
    private City capital;
    private ArrayList<Language> languages;
    private long population;
    private String continent;
    private String region;

    public Country(String countryCode, String name, ArrayList<District> districts, City capital,
                   ArrayList<Language> languages, long population, String continent, String region) {
        this.countryCode = countryCode;
        this.name = name;
        this.districts = districts;
        this.capital = capital;
        this.languages = languages;
        this.population = population;
        this.continent = continent;
        this.region = region;
    }

    /**
     * Getting Countrycode using Get Method
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Getting Name using Get Method
     */
    public String getName() {
        return name;
    }

    /**
     * Getting Districts using Get Method
     */
    public ArrayList<District> getDistricts() {
        return districts;
    }

    /**
     * Getting Capital using Get Method
     */
    public City getCapital() {
        return capital;
    }

    /**
     * Getting Language using Get Method
     */
    public ArrayList<Language> getLanguages() {
        return languages;
    }

    public ArrayList<Language> getOfficialLanguages() {
        ArrayList<Language> officialLanguages = new ArrayList<>();
        for(Language language : languages) {
            if(language.isOfficial()) {
                officialLanguages.add(language);
            }
        }

        if(officialLanguages.isEmpty()) {
            return null;
        }

        return officialLanguages;
    }
    /**
     * Getting population in district using Get Method
     */
    public long getPopulation() {
        return population;
    }

    public long getCitiesPopulation() {
        long countryPopulation = 0;
        for (District district : districts) {
            countryPopulation += district.getPopulation();
        }
        return countryPopulation;
    }

    /**
     * Getting Continent using Get Method
     */
    public String getContinent() {
        return continent;
    }

    /**
     * Getting Region using Get Method
     */
    public String getRegion() {
        return region;
    }

    @Override
    public String toString() {
        return "Country{" +
                "countryCode='" + countryCode + '\'' +
                ", name='" + name + '\'' +
                ", continent='" + continent + '\'' +
                ", region='" + region + '\'' +
                ", population=" + population +
                ", capital=" + capital +
                '}';
    }
}
