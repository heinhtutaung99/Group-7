package com.napier.sem.utils;

import com.napier.sem.places.City;
import com.napier.sem.places.Country;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
/**
 * Declaring class for App test
 */
class AppTest {
    private static App app;

    /**
     * A generic method for getting the SHA1 hash of a list
     *
     * @param list The list the hash should be made from
     * @param <T> The object type of the list
     * @return A SHA1 hash of all the elements in the list combined together
     */
    <T> String listToSHA1(ArrayList<T> list) {
        StringBuilder hashBuffer = new StringBuilder();
        for(T item : list) {
            hashBuffer.append(item.toString());
        }

        String sha1 = "";
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.update(hashBuffer.toString().getBytes(StandardCharsets.UTF_8));
            sha1 = Base64.getEncoder().encodeToString(crypt.digest());

        } catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return sha1;
    }


    @BeforeAll
    static void init() {
        app = new App();
        app.connect("localhost:3306", 10); //This needs to be localhost and not db for some reason
        app.loadDatabase();
        app.disconnect();
    }

    @Test
    void countriesInWorld() {
        //Act
        ArrayList<Country> actual = app.countriesInWorld();

        //Assert
        String sha1 = listToSHA1(actual);
        assertEquals(239, actual.size());
        assertEquals("8/o7+3uFB368QRAhQIW5SAtTv3c=", sha1);
    }


}
