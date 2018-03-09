package com.example.heyrobin.contactcardapp.domain;

/**
 * Created by HeyRobin on 7-3-2018.
 */

public class Person {

    public String name;
    public int age;
    public String emailadress;

    public Person(String name, int age, String emailadress) {
        this.name = name;
        this.age = age;
        this.emailadress = emailadress;
    }

    @Override
    public String toString() {
        return name;
    }

}
