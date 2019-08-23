package com.napier.sem;

import com.napier.sem.places.Country;
import com.napier.sem.utils.App;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if(args.length < 1) {
            System.err.println("Usage: set09623-g7 {ip address}");
            return;
        }

        System.out.println("Loading database, this may take a while.");

        String connectionString = String.format("%s:3306", args[0]);
        App app = new App();
        app.connect(connectionString, 10);

        app.loadDatabase();

        System.out.println("Finished loading database.");

        app.disconnect();

        Scanner menuIn = new Scanner(System.in);
        Scanner answerIn = new Scanner(System.in);
        int input = 0;
        do {
            System.out.println("Question ID [1-32, 0 to quit]: ");

            try {
                input = menuIn.nextInt();
            } catch(InputMismatchException e) {
                input = -1;
            }

            switch (input){
                case 0:
                    break;

                case 1: {
                    System.out.println("All the countries in the world organised by largest population to smallest.");
                    ArrayList<Country> countries = app.countriesInWorld();

                    for(Country c : countries) {
                        System.out.println(c);
                    }

                    break;
                }


                case 2: {
                    System.out.println("All the countries in a continent organised by largest population to smallest.");
                    System.out.print("Continent: ");
                    String search = answerIn.nextLine();

                    ArrayList<Country> countries = app.countriesInContinent(search);
                    for(Country c : countries) {
                        System.out.println(c);
                    }

                    break;
                }

                default:
                    System.err.println("ERROR: Unknown question ID");
                    menuIn.nextLine(); //Clear input buffer

            }


        }while(input != 0);


    }

}
