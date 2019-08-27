package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppIntegrationTest {

    static App app;
    static DatabaseHandler db;

    @BeforeAll
    static void init()
    {
        app = new App();
        db = DatabaseHandler.Instance();
        db.connect("localhost:33060");
    }



    @Test
    void testReportOne(){
        Country r = (Country) db.getReportOne();
        int size =r.get_reportsItems().size();
        assertTrue(size >0);

    }

    @Test
    void testReportTwo(){
        Country r = (Country) db.getReportTwo("Africa");
        Country.CountryReportItem i = r.get_reportsItems().get(0);
        assertEquals(111506000, i.get_population());
    }

    @Test
    void testReportThree(){
        Country r = (Country) db.getReportThree("South America");

        Country.CountryReportItem i = r.get_reportsItems().get(0);
        assertEquals("Brazil", i.get_name());
    }

    @Test
    void testReportFour(){
        Country r = (Country) db.getReportFour(6);
        Country.CountryReportItem item = r.get_reportsItems().get(3);

        assertEquals("Jakarta", item.get_capital());
    }

    @Test
    void testReportFive(){
        Country r = (Country) db.getReportFive("Europe", 30);
        assertEquals(30, r.get_reportsItems().size());
    }


    @Test
    void testReportSix(){
        Country r = (Country) db.getReportSix("Eastern Asia", 2);
        Country.CountryReportItem item = r.get_reportsItems().get(1);
        assertEquals("Tokyo", item.get_capital());
    }
}
