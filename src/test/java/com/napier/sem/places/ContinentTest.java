package com.napier.sem.places;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 * Declaring class for continent test
 */
class ContinentTest {
    /**
     * Getting continent name using Get method
     */
    @Test
    void getName() {
        //Arrange
        Continent continent = new Continent("Continent", null);

        //Assert
        assertEquals("Continent", continent.getName());
    }
    /**
     * Getting regions using Get method
     */
    @Test
    void getRegions() {
        // Arrange
        ArrayList<Region> regions = new ArrayList<>();
        regions.add(new Region("Region1", null));
        regions.add(new Region("Region2", null));
        regions.add(new Region("Region3", null));

        Continent continent = new Continent("Continent", regions);

        // Assert
        assertEquals(regions, continent.getRegions());
    }

    /**
     * Getting population using Get Method
     */
    @Test
    void getPopulation() {
        // Arrange
        ArrayList<Region> regions = new ArrayList<>();

        ArrayList<Country> countries = new ArrayList<>();

        countries.add(new Country(null, null, null, null, null, 1, null, null));
        countries.add(new Country(null, null, null, null, null, 2, null, null));
        countries.add(new Country(null, null, null, null, null, 3, null, null));


        regions.add(new Region("Region1", countries));
        regions.add(new Region("Region2", countries));
        regions.add(new Region("Region3", countries));

        Continent continent = new Continent("Continent", regions);

        assertEquals(18, continent.getPopulation());
    }
}