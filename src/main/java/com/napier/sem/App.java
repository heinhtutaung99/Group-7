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
    public void connect(String location)
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://" + location + "/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "pass");
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
     * Report two to generate all the city in the world by decending order
     * @return
     */
//    public ArrayList<City> getReport7()
//    {
//        try {
//            Statement stmt = con.createStatement();
//            String strSelect = "";
//            ResultSet rset = null;
//            strSelect = "select name,countrycode,district,population from city order by population DESC;\n";
//            rset = stmt.executeQuery(strSelect);
//            if (rset == null) {
//                System.out.println("no statement execute queries");
//            }
//            ArrayList<City> cities = new ArrayList<City>();
//            while (rset.next()) {
//                City report = new City();
//                report.name = rset.getString(1);
//                report.countrycode = rset.getString(2);
//                report.district = rset.getString(3);
//                report.population = rset.getInt(4);
//                cities.add(report);
//            }
//            return cities;
//        }
//        catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        return null;
//    }
    /**
     * Report two to generate all the city in the world by decending order
     * @return
     */
    public ArrayList<City> getReport8()
    {
        try {
            Statement stmt = con.createStatement();
            String strSelect = "";
            ResultSet rset = null;
            strSelect = "select city.name,city.CountryCode,city.district,city.population from country, city where country.capital= city.id order by country.region DESC;\n";
            rset = stmt.executeQuery(strSelect);
            if (rset == null) {
                System.out.println("no statement execute queries");
            }
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next()) {
                City report = new City();
                report.setName( rset.getString(1));
                Country c1 = getCountrybyCode(rset.getString(2));
                report.setCountry(c1);
                report.setDistrict(rset.getString(3));
                report.setPopulation(rset.getInt(4));
                cities.add(report);
            }
            return cities;
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Country getCountrybyCode(String ccode)
    {
        Country c = new Country();
        try {
            Statement stmt = con.createStatement();
            String strSelect = "";
            ResultSet rset = null;
            strSelect = "select name from country where country.Code = '+ccode+' ";
            rset = stmt.executeQuery(strSelect);
            c.name = rset.getString(1);

        }catch(SQLException e)
        {

            System.out.println(e);

        }
        return c;
    }
/**
 * To format the country object of database to string
 */
    public void printCountryReport(ArrayList<Country> countries)
    {
        // Print header
        System.out.println(String.format("%-10s %-35s %-20s %-15s %-15s %-15s", "Code", "Name", "Continent", "Region", "Population", "Capital"));
        // Loop over all employees in the list
        for (Country report : countries)
        {
            String cou_string =
                    String.format("%-10s %-35s %-20s %-15s %-15s %-15s",
                            report.code, report.name, report.continent, report.region, report.population, report.captical);
            System.out.println(cou_string);
        }
    }
    /**
     * To format the city object of database to string
     */
    public void printCityReport(ArrayList<City> cities)
    {
        if (cities == null){
            System.out.println("No Cities Data");
            return;
        }
        // Print header
        System.out.println(String.format("%-30s %-20s %-20s %-20s", "Name", "CountryCode", "District", "Population"));
        // Loop over all employees in the list
        for (City report_city : cities)
        {
            if (report_city == null)
                continue;
            String cit_string =
                    String.format("%-30s %-20s %-20s %-20s",
                            report_city.getName(), report_city.getCountry().name, report_city.getDistrict(), report_city.getPopulation());
            System.out.println(cit_string);
        }
    }
    /**
     * Java main method
     */
    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();
        // Connect to database
        a.connect("localhost:33060");
        //Country arraylist
        //ArrayList<Country> countries=a.getReportOne();
        //a.printCountryReport(countries);
        //ArrayList<City> cities=a.getReport7();
        //a.printCityReport(cities);
        ArrayList<City> cities=a.getReport8();
        a.printCityReport(cities);
        // Disconnect from database
        a.disconnect();
        //city
    }
}