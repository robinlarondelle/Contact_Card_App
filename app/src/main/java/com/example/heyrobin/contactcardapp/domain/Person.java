package com.example.heyrobin.contactcardapp.domain;


import java.io.Serializable;

/**
 * Created by HeyRobin on 7-3-2018.
 */

public class Person implements Serializable {

    //Variables
    public String firstName;
    public String lastName;
    public String fullName;
    public String email;
    public String imageUrl;
    public String streetName;
    public String city;
    public String country;
    public String phoneNumber;

    public Person(String firstName,
                  String lastName,
                  String email,
                  String imageUrl,
                  String streetName,
                  String city,
                  String country,
                  String phoneNumber) {


        this.firstName      = capitalize(firstName);
        this.lastName       = capitalize(lastName);
        this.fullName       = this.firstName + " " + this.lastName;
        this.email          = email;
        this.imageUrl       = imageUrl;
        this.streetName     = capitalize(streetName);
        this.city           = capitalize(city);
        this.country        = capitalize(country);
        this.phoneNumber    = phoneNumber;
    }


    //create capitalized words
    private String capitalize(final String line) {
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }


}
