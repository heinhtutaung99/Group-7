package com.napier.sem.places;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
/**
 * Declaring class for Language test
 */
class LanguageTest {

    /**
     * Getting language name using Get method
     */
    @Test
    void getLanguageName() {
        //Arrange
        Language language = new Language("Language", 99.9, true);

        //Assert
        assertEquals("Language", language.getLanguageName());
    }

    /**
     * Getting percentage using Get method
     */
    @Test
    void getPercentage() {
        //Arrange
        Language language = new Language("Language", 99.9, true);

        //Assert
        assertEquals(99.9, language.getPercentage());
    }


    @Test
    void isOfficial() {
        //Arrange
        Language language = new Language("Language", 99.9, true);

        //Assert
        assertTrue(language.isOfficial());
    }

    @Test
    void equals() {
        //Arrange
        Language language1 = new Language("Language", 99.9, true);
        Language language2 = new Language("Language", 99.9, true);

        //Assert
        assertEquals(language1, language2);
    }
}
