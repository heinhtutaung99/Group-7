package com.napier.sem.places;

public class Language {
    /**
     Stores information about language, percentage and country code.
     */
    private String languageName;
    private double percentage;
    private boolean isOfficial;

    public Language(String languageName, double percentage, boolean isOfficial) {
        this.languageName = languageName;
        this.percentage = percentage;
        this.isOfficial = isOfficial;
    }
    /**
     * Getting Language Name with Get Method
     */
    public String getLanguageName() {
        return languageName;
    }
    /**
     * Getting Percentage with Get Method
     */
    public double getPercentage() {
        return percentage;
    }

    public boolean isOfficial() {
        return isOfficial;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Language language = (Language) o;
        return Double.compare(language.getPercentage(), getPercentage()) == 0 &&
                isOfficial() == language.isOfficial() &&
                getLanguageName().equals(language.getLanguageName());
    }

    @Override
    public String toString() {
        return "Language{" +
                "languageName='" + languageName + "', " +
                "percentage='" + percentage + "', " +
                "isOfficial='" + isOfficial + "'" +
                "}";
    }
}
