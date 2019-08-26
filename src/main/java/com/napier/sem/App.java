package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;


public class App
{

    private static DatabaseHandler db = DatabaseHandler.Instance(); // Instance of database
    private static App app;

    /**
     * Main method
     *
     * @param args args passed into main
     */
    public static void main(String[] args) {

        // Connect to database
        app = new App();

        // Connect to database
        if (args.length < 1) {
            db.connect("localhost:3306");
        } else {
            db.connect(args[0]);
        }

    }
}