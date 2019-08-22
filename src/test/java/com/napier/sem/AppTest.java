package com.napier.sem;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import org.junit.jupiter.api.TestInstance;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest
{
    static App app;
    @BeforeAll
    static void init()
    {
        app = new App();
    }
    @Test
    void printCityTestNull()
    {
        app.printCityReport(null);
    }
    @Test
    void printCitiesTestContainsNull()
    {
        ArrayList<City> cities = new ArrayList<City>();
        cities.add(null);
        app.printCityReport(cities);
    }
    @Test
    void printCities()
    {
        ArrayList<City> cities = new ArrayList<City>();
        City citydata = new City();
        citydata.setName("Yangon");
        citydata.setDistrict("Yangon");
        citydata.setPopulation(102030403);
        cities.add(citydata);
        app.printCityReport(cities);
    }
}

