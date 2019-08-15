package com.napier.sem;

public class City {
    String name;
    String countrycode;
    String district;
    int population;
    public City cty;

    @Override
    public String toString() {
        return "City{" +
                ", name='" + name + '\'' +
                "countrycode='" + countrycode + '\'' +
                ", district='" + district + '\'' +
                ", population=" + population +'\'' +
                '}';
    }
}