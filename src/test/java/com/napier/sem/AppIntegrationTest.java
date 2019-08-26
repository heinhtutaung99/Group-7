package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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
}
