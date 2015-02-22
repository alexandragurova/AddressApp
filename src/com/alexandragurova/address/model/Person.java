package com.alexandragurova.address.model;

import java.util.Calendar;

/**
 * Created by Gurova on 18.02.2015.
 */

public class Person {
    private String firstName;
    private  String lastName;
    private String street;
    private int postalCode;
    private String city;
    private Calendar birthday;

    /**
     * Default constructor.
     */
    public Person() {
    }

    /**
     * Constructor with initial data.
     *
     * @param firstName
     * @param lastName
     */
    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;

        this.street = "Sesame Street";
        this.postalCode = 1234;
        this.city = "Muppet City";
        this.birthday = Calendar.getInstance();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }
}
