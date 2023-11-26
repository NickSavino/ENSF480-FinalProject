package flightapp.domain.entity;

import flightapp.domain.entity.Employee;
import flightapp.domain.pattern.BrowsePassengersStrategy;

public abstract class NonAdmin extends Employee {

    private BrowsePassengersStrategy browseStrategy;

    public NonAdmin(String firstName, String lastName, String address, String email, int age, String phoneNumber, int employeeId) {
        super(firstName, lastName, address, email, age, phoneNumber, employeeId);
    }

    public void performBrowse() {}

    public void setBrowseStrategy(BrowsePassengersStrategy strategy) {

        this.browseStrategy = strategy;
    }
}
