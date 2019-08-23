package com.napier.sem.places;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
/**
 * Declaring class for the city test
 */
class CityTest {
    /**
     * Getting city name using Get Method
     */
    @Test
    void getName() {
        //Arrange
        City city = new City("City", 100, false, null, null);

        //Assert
        assertEquals("City", city.getName());
    }
    /**
     * Getting population in the city using Get Method
     */
    @Test
    void getPopulation() {
        //Arrange
        City city = new City("City", 100, false, null, null);

        //Assert
        assertEquals(100, city.getPopulation());
    }

    @Test
    void isCapital() {
        //Arrange
        City city = new City("City", 100, true, null, null);

        //Assert
        assertTrue(city.isCapital());
    }
    /**
     * Getting Country using Get Method
     */
    @Test
    void getCountry() {
        //Arrange
        City city = new City("City", 100, false, "Country", null);

        //Assert
        assertEquals("Country", city.getCountry());
    }

    /**
     * Getting District using Get Method
     */
    @Test
    void getDistrict() {
        //Arrange
        City city = new City("City", 100, false, null, "District");

        //Assert
        assertEquals("District", city.getDistrict());
    }

    @Test
    void toStringTest() {
        //Arrange
        City city = new City("City", 100, true, "Country", "District");

        //Assert
        assertEquals("City{name='City', population=100, country='Country', district='District'}", city.toString());
    }
}
