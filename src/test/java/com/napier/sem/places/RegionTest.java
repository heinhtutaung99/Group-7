package com.napier.sem.places;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 * Declaring class for Region test
 */
class RegionTest {

    /**
     * Getting region name using Get method
     */
    @Test
    void getName() {
        //Arrange
        Region region = new Region("Region", null);

        //Assert
        assertEquals("Region", region.getName());
    }

    /**
     * Getting countries using Get method
     */
    @Test
    void getCountries() { // Arrange
        ArrayList<Country> countries = new ArrayList<>();
        countries.add(new Country("ABC", "Country1", null, null, null, 1, null, null));
        countries.add(new Country("DEF", "Country2", null, null, null, 2, null, null));
        countries.add(new Country("GHI", "Country3", null, null, null, 3, null, null));

        Region region = new Region("Region", countries);

        // Assert
        assertEquals(countries, region.getCountries());
    }

    /**
     * Getting population using Get method
     */
    @Test
    void getPopulation() {
        ArrayList<Country> countries = new ArrayList<>();
        countries.add(new Country("ABC", "Country1", null, null, null, 1, null, null));
        countries.add(new Country("DEF", "Country2", null, null, null, 2, null, null));
        countries.add(new Country("GHI", "Country3", null, null, null, 3, null, null));

        Region region = new Region("Region", countries);

        // Assert
        assertEquals(6, region.getPopulation());
    }
}
