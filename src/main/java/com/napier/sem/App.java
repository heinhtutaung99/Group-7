package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;


public class App
{
    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public void connect()
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 20;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "pass");
                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect()
    {
        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }
    /**
    * Report one to generate all the countries in the world by decending order
    */
    public ArrayList<Country> getReportOne()
    {
        try {
            Statement stmt = con.createStatement();
            String strSelect = "";
            ResultSet rset = null;
            strSelect = "select code, country.name, continent, region, country.population, city.name from country, city where country.capital= city.id order by country.population DESC;\n";
            rset = stmt.executeQuery(strSelect);
            ArrayList<Country> countries = new ArrayList<Country>();

            while (rset.next()) {
                Country report = new Country();
                report.code = rset.getString(1);
                report.name = rset.getString(2);
                report.continent = rset.getString(3);
                report.region = rset.getString(4);
                report.population = rset.getInt(5);
                report.captical = rset.getString(6);
                countries.add(report);

            }
            return countries;
        }
        catch (SQLException e) {

            System.out.println(e.getMessage());
        }

        return null;
    }

/**
 * To format the country object of database to string
 */
    /
    public void printCountryReport(ArrayList<Country> countries)
    {
        // Print header
        System.out.println(String.format("%-10s %-15s %-20s %-8s %-15s %-15s", "Code", "Name", "Continent", "Region", "Population", "Capital"));
        // Loop over all employees in the list
        for (Country report : countries)
        {
            String cou_string =
                    String.format("%-10s %-20s %-20s %-20s %-20s %-20s",
                            report.code, report.name, report.continent, report.region, report.population, report.captical);
            System.out.println(cou_string);
        }
    }

    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();
        //Country arraylist
        ArrayList<Country> countries=a.getReportOne();
        a.printCountryReport(countries);



        // Disconnect from database
        a.disconnect();
    }
}