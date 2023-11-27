package flightapp.domain.entity;

import flightapp.domain.entity.Employee;

public class Admin extends Employee {
    private String viewLevel = "Admin";

    public Admin(String firstName, String lastName, int houseNumber, String street, String city, String province, 
        String country, String email, int age, String phoneNumber, String password) {
        super(firstName, lastName, houseNumber, street, city, province, country, email, age, phoneNumber, password);
    }
}
