package flightapp.domain.entity;

import flightapp.domain.valueobject.Name;
import flightapp.domain.valueobject.Address;

public abstract class Person {
    private Name name;
    private Address address;
    private String email;
    private int age;
    private String phoneNumber;

    public Person(String firstName, String lastName, int houseNumber, String street, String city, 
        String province, String country, String email, int age, String phoneNumber) {
        this.name = new Name(firstName, lastName);
        this.address = new Address(houseNumber, street, city, province, country);
        this.email = email;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }
}
