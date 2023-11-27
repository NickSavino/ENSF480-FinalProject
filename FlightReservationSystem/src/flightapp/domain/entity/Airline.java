package flightapp.domain.entity;

import java.util.ArrayList;
import flightapp.domain.valueobject.*;

public class Airline {

    private ArrayList<Flight> flights;
    private ArrayList<Employee> employees;
    private ArrayList<Customer> customers;
    private ArrayList<Aircraft> aircrafts;
    private ArrayList<FlightCrew> flightCrew;
    private ArrayList<Location> locations;
    private ArrayList<Purchase> purchases;
    private ArrayList<FlightAttendant> flightAttendants;
    private ArrayList<AirlineAgent> airlineAgents;
    private ArrayList<NonAdmin> nonAdmins;
    private ArrayList<Admin> admins;
    private ArrayList<RegisteredCustomer> registeredCustomers;
    
    public Airline() {
        this.flights = new ArrayList<Flight>();
        this.employees = new ArrayList<Employee>();
        this.customers = new ArrayList<Customer>();
        this.registeredCustomers = new ArrayList<RegisteredCustomer>();
        this.aircrafts = new ArrayList<Aircraft>();
        this.flightCrew = new ArrayList<FlightCrew>();
        this.locations = new ArrayList<Location>();
        this.purchases = new ArrayList<Purchase>();
        this.flightAttendants = new ArrayList<FlightAttendant>();
        this.airlineAgents = new ArrayList<AirlineAgent>();
        this.nonAdmins = new ArrayList<NonAdmin>();
        this.admins = new ArrayList<Admin>();
    }

    public ArrayList<RegisteredCustomer> getRegisteredCustomers()
    {
        return this.registeredCustomers;
    }

    public ArrayList<Flight> getFlights() 
    {
        return this.flights;
    }

    public ArrayList<Aircraft> getAircrafts() 
    {
        return this.aircrafts;
    }

    public ArrayList<FlightCrew> getFlightCrew() 
    {
        return this.flightCrew;
    }

    public ArrayList<Customer> getCustomers()
    {
        return this.customers;
    }

    public ArrayList<Employee> getEmployees()
    {
        return this.employees;
    }

    public ArrayList<Location> getLocations()
    {
        return this.locations;
    }

    public ArrayList<Purchase> getPurchases()
    {
        return this.purchases;
    }

    public ArrayList<AirlineAgent> getAirlineAgents()
    {
        return this.airlineAgents;
    }

    public ArrayList<FlightAttendant> getFlightAttendants()
    {
        return this.flightAttendants;
    }

    public ArrayList<NonAdmin> getNonAdmins()
    {
        return this.nonAdmins;
    }

    public ArrayList<Customer> getCusotmers()
    {
        return this.customers;
    }

    public ArrayList<Admin> getAdmins()
    {
        return this.admins;
    }

}
