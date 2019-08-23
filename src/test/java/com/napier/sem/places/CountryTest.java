package com.napier.sem.places;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
/**
 * Declaring class for country test
 */
class CountryTest {
    /**
     * Getting country code using Get Method
     */
    @Test
    void getCountryCode() {
        // Arrange
        Country country = new Country("ABC", "Country", null, null, null,
                12345, null, null);

        // Assert
        assertEquals("ABC", country.getCountryCode());
    }
    /**
     * Getting country name using Get Method
     */
    @Test
    void getName() {
        // Arrange
        Country country = new Country("ABC", "Country", null, null, null,
                12345, null, null);

        // Assert
        assertEquals("Country", country.getName());
    }

    /**
     * Getting districts using Get method
     */
    @Test
    void getDistricts() {
        // Arrange
        ArrayList<District> districts = new ArrayList<>();
        districts.add(new District("District1", null));
        districts.add(new District("District2", null));
        districts.add(new District("District3", null));

        Country country = new Country("ABC", "Country", districts, null, null,
                12345, null, null);

        // Assert
        assertEquals(districts, country.getDistricts());
    }

    /**
     * Getting population using Get method
     */
    @Test
    void getPopulation() {
        // Arrange
        Country country = new Country("ABC", "Country", null, null, null,
                12345, null, null);

        assertEquals(12345, country.getPopulation());
    }

    /**
     * Getting cities population using Get method
     */
    @Test
    void getCitiesPopulation() {
        // Arrange
        ArrayList<City> cities1 = new ArrayList<>();
        cities1.add(new City("City1", 100,true, null, null));
        cities1.add(new City("City2", 50,false, null, null));

        ArrayList<City> cities2 = new ArrayList<>();
        cities2.add(new City("City3", 100,false, null, null));
        cities2.add(new City("City4", 50,false, null, null));

        ArrayList<District> districts = new ArrayList<>();
        districts.add(new District("District1", cities1));
        districts.add(new District("District2", cities2));

        Country country = new Country("ABC", "Country", districts, null, null,
                12345, null, null);

        assertEquals(300, country.getCitiesPopulation());
    }
    /**
     * Getting Capital name using Get method
     */
    @Test
    void getCapital() {
        // Arrange
        City capital = new City("City1",100,true, null, null);

        Country country = new Country("ABC", "Country", null, capital, null,
                12345, null, null);

        // Assert
        assertEquals(capital, country.getCapital());
    }
    /**
     * Getting Language using Get method
     */
    @Test
    void getLanguages() {
        // Arrange
        ArrayList<Language> languages = new ArrayList<>();
        languages.add(new Language("Language1", 20.5, true));
        languages.add(new Language("Language2", 39.5, true));
        languages.add(new Language("Language3", 40, false));


        Country country = new Country("ABC", "Country", null, null, languages,
                12345, null, null);

        // Assert
        assertEquals(languages, country.getLanguages());
    }

    /**
     * Getting official language using Get method
     */
    @Test
    void getOfficialLanguages() {
        // Arrange
        ArrayList<Language> languages = new ArrayList<>();
        languages.add(new Language("Language1", 20.5, true));
        languages.add(new Language("Language2", 39.5, true));
        languages.add(new Language("Language3", 40, false));

        ArrayList<Language> expected = new ArrayList<>();
        expected.add(new Language("Language1", 20.5, true));
        expected.add(new Language("Language2", 39.5, true));

        Country country = new Country("ABC", "Country", null, null, languages,
                12345, null, null);

        // Assert
        assertEquals(expected, country.getOfficialLanguages());
    }

    /**
     * Getting empty official language using Get method
     */
    @Test
    void getEmptyOfficialLanguages() {
        // Arrange
        ArrayList<Language> languages = new ArrayList<>();
        languages.add(new Language("Language1", 20.5, false));
        languages.add(new Language("Language2", 39.5, false));
        languages.add(new Language("Language3", 40, false));

        Country country = new Country("ABC", "Country", null, null, languages,
                12345, null, null);

        // Assert
        assertNull(country.getOfficialLanguages());
    }
    /**
     * Getting continent using Get method
     */
    @Test
    void getContinent() {
        // Arrange
        Country country = new Country("ABC", "Country", null, null, null,
                12345, "Continent", null);

        // Assert
        assertEquals("Continent", country.getContinent());
    }

    /**
     * Getting region using Get method
     */
    @Test
    void getRegion() {
        // Arrange
        Country country = new Country("ABC", "Country", null, null, null,
                12345, null, "Region");

        // Assert
        assertEquals("Region", country.getRegion());
    }

    @Test
    void toStringTest() {
        // Arrange
        Country country = new Country("ABC", "Country", null,
                new City("City", 100, true, "Country", "District"),
                null, 12345, "Continent", "Region");

        // Assert
        assertEquals("Country{countryCode='ABC', name='Country', continent='Continent', region='Region', population=12345, capital=City{name='City', population=100, country='Country', district='District'}}", country.toString());
    }
}
