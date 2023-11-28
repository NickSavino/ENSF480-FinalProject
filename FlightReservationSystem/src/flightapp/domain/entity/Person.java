package flightapp.domain.entity;

import flightapp.domain.valueobject.Name;
import flightapp.domain.valueobject.Address;

public abstract class Person {
    private Name name;
    private Address address;
    private String email;



    public Person(String firstName, String lastName, int houseNumber, String street, String city, 
        String province, String country, String email) {
        this.name = new Name(firstName, lastName);
        this.address = new Address(houseNumber, street, city, province, country);
        this.email = email;
    }

    public Name getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }
}
