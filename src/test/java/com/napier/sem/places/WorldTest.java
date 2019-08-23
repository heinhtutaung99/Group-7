package com.napier.sem.places;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 * Declaring class for World test
 */
class WorldTest {

    /**
     * Getting continents name using Get method
     */
    @Test
    void getContinents() {
        // Arrange
        ArrayList<Continent> continents = new ArrayList<>();
        continents.add(new Continent("Continent1", null));
        continents.add(new Continent("Continent2", null));
        continents.add(new Continent("Continent3", null));

        World world = World.getInstance();
        world.setContinents(continents);

        // Assert
        assertEquals(continents, world.getContinents());
    }

    /**
     * Getting population using Get method
     */
    @Test
    void getPopulation() {
        // Arrange
        ArrayList<Country> countries = new ArrayList<>();
        countries.add(new Country("ABC", "Country1", null, null, null,
                12345, null, null));
        countries.add(new Country("DEF", "Country2", null, null, null,
                12345, null, null));

        ArrayList<Region> regions = new ArrayList<>();
        regions.add(new Region("Region", countries));

        ArrayList<Continent> continents = new ArrayList<>();
        continents.add(new Continent("Continent", regions));

        World world = World.getInstance();
        world.setContinents(continents);

        // Assert
        assertEquals(12345*2, world.getPopulation());
    }
}
