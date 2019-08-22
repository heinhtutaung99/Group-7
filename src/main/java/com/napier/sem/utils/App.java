package com.napier.sem.utils;

import com.napier.sem.places.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

public class App {
    private Connection connection = null;
    private World world = null;

    /**
     * Creates a connection to a mysql database
     *
     * @param connectionString Where to connect to, usually db:3306 or localhost:3306
     * @param retries The number of times the connection should be attempted
     */
    public void connect(String connectionString, int retries) {
        for(int i = 0; i < retries; ++i) {
            try {
                TimeUnit.SECONDS.sleep(5);
                connection = DriverManager.getConnection(String.format("jdbc:mysql://%s/world", connectionString), "root", "pass");
                break;
            } catch(InterruptedException | SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Close the connection to the database
     */
    public void disconnect() {
        if(connection != null) {
            try {
                connection.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void loadDatabase() {
        world = World.getInstance();
        world.setContinents(loadContinents());
    }



    public ArrayList<Country> countriesInWorld() {
        ArrayList<Country> countries = new ArrayList<>();

        for(Continent continent : world.getContinents()) {
            for(Region region : continent.getRegions()) {
                countries.addAll(region.getCountries());
            }
        }

        countries.sort(Comparator.comparingLong(Country::getPopulation).reversed());

        return countries;
    }

    private ArrayList<Continent> loadContinents() {
        ArrayList<Continent> continents = new ArrayList<>();
        String query = "SELECT DISTINCT Continent FROM country;";

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while(rs.next()) {
                String continentName = rs.getString("Continent");
                //Call the function to load all regions for a continent
                continents.add(new Continent(continentName, loadRegions(continentName)));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return continents;
    }

    private ArrayList<Region> loadRegions(String continentName) {
        ArrayList<Region> regions = new ArrayList<>();
        String query = "SELECT DISTINCT Region FROM country WHERE Continent = ?;";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, continentName);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                String regionName = rs.getString("Region");
                //Call function to load countries for a region
                regions.add(new Region(regionName, loadCountries(regionName)));
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return regions;
    }


    private ArrayList<Country> loadCountries(String regionName) {
        ArrayList<Country> countries = new ArrayList<>();
        String query = "SELECT Code, Name, Capital, Population, Continent FROM country WHERE Region = ?;";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, regionName);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                //Put this in a variable to save writing this statement in multiple places
                String countryCode = rs.getString("Code");
                String countryName = rs.getString("Name");
                //Find the capital city
                City capital = loadCapital(countryCode);
                //Call function to load districts and to load languages
                countries.add(new Country(countryCode, countryName, loadDistricts(countryCode, rs.getString("Name"), capital),
                        capital, loadLanguages(countryCode), rs.getLong("Population"),
                        rs.getString("Continent"), regionName));
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return countries;
    }



    private ArrayList<Language> loadLanguages(String countryCode) {
        ArrayList<Language> languages = new ArrayList<>();
        String query = "SELECT Language, IsOfficial, Percentage FROM countrylanguage WHERE CountryCode = ?;";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, countryCode);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                //Convert the database data into a boolean
                boolean isOfficial = rs.getString("isOfficial").equals("T");
                languages.add(new Language(rs.getString("Language"), rs.getDouble("Percentage"), isOfficial));
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return languages;
    }
    private City loadCapital(String countryCode) {
        String query = "SELECT city.Name, city.Population, District, country.Name FROM country LEFT JOIN city ON Capital = ID AND Code = CountryCode WHERE Code = ?;";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, countryCode);
            ResultSet rs = ps.executeQuery();

            //Get first result
            rs.next();

            //Without this we get City objects of {null, 0, true} for countries without a capital
            if(rs.getString("city.Name") == null) {
                return null;
            }

            String districtName = rs.getString("District");
            //If it's a strange value set it to something which makes more sense for the user
            if(districtName.equals("") || districtName.equals("–")) {
                districtName = "None";
            }

            return new City(rs.getString("city.Name"), rs.getInt("city.Population"), true,
                    rs.getString("country.Name"), districtName);

        } catch(SQLException e) {
            e.printStackTrace();
        }

        //Return null for any countries without a capital
        return null;
    }


    private ArrayList<District> loadDistricts(String countryCode, String countryName, City capital) {
        ArrayList<District> districts = new ArrayList<>();

        //EDGE CASE: If there is no capital there will not be any districts so return early
        if(capital == null) {
            return districts;
        }

        String query = "SELECT DISTINCT District FROM city WHERE CountryCode = ?;";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, countryCode);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                //Get original district name for next query
                String districtNameOrig = rs.getString("District");
                String districtName = districtNameOrig;

                //If it's a strange value set it to something which makes more sense for the user
                if(districtNameOrig.equals("") || districtNameOrig.equals("–")) {
                    districtName = "None";
                }
                //Load cities into new district object
                districts.add(new District(districtName, loadCities(districtNameOrig, countryName, countryCode, capital)));
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return districts;
    }

    private ArrayList<City> loadCities(String districtName, String countryName, String countryCode, City capital) {
        ArrayList<City> cities = new ArrayList<>();
        String query = "SELECT Name, Population FROM city WHERE District = ? AND CountryCode = ?;";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, districtName);
            ps.setString(2, countryCode);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                //We don't want to double include the capital
                if(rs.getString("Name").equals(capital.getName())
                        && rs.getInt("Population") == capital.getPopulation()) {
                    cities.add(capital);
                } else {
                    cities.add(new City(rs.getString("Name"), rs.getInt("Population"), false,
                            countryName, districtName));
                }
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return cities;
    }



}