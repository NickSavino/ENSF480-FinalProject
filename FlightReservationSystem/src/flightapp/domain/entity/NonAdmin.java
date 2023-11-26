package flightapp.domain.entity;

import java.util.ArrayList;

import flightapp.domain.entity.Employee;
import flightapp.domain.pattern.BrowsePassengersStrategy;

public abstract class NonAdmin extends Employee {

    protected BrowsePassengersStrategy browseStrategy;

    public NonAdmin(String firstName, String lastName, String address, String email, int age, String phoneNumber, int employeeId) {
        super(firstName, lastName, address, email, age, phoneNumber, employeeId);
    }

    public ArrayList<Customer> performBrowse(ArrayList<Customer> customers, int flightId)
    {
        if (browseStrategy == null) 
        {
            return null;
        }
       return browseStrategy.browse(customers, flightId);
    }

    public void setBrowseStrategy(BrowsePassengersStrategy strategy) 
    {
        this.browseStrategy = strategy;
    }
}
