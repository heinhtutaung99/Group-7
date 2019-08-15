package com.napier.sem;

public class Country {
    String code;
    String name;
    String continent;
    String region;
    float surfArea;
    int IndepYear;
    long population;
    float lifeExp;
    float gnp;
    float gnpold;
    String localName;
    String GovForm;
    String HeadofState;
    String captical;
    int code2;

    public City cty;

    @Override
    public String toString() {
        return "Country{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", continent='" + continent + '\'' +
                ", region='" + region + '\'' +
                ", population=" + population +
                ", captical='" + captical + '\'' +
                '}';
    }
}
