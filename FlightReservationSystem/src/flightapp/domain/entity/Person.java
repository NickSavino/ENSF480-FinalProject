package flightapp.domain.entity;

import flightapp.domain.valueobject.Name;
import flightapp.domain.valueobject.Address;

public abstract class Person {

    private Name name;

    private Address address;

    private String email;

    private int age;

    private String phoneNumber;

    public Person(String firstName, String lastName, String address, String email, int age, String phoneNumber) {
        this.name = new Name(firstName, lastName);
        this.address = new Address(address);
        this.email = email;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }
}
