package flightapp.domain.entity;

import flightapp.domain.entity.Employee;

public class Admin extends Employee {
    private String viewLevel = "Admin";

    public Admin(String firstName, String lastName, String address, String email, int age, String phoneNumber, int employeeId) {
        super(firstName, lastName, address, email, age, phoneNumber, employeeId);
    }
}
