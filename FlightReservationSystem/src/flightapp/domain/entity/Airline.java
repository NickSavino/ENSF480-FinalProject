package flightapp.domain.entity;

import java.util.ArrayList;
import flightapp.domain.valueobject.*;

public class Airline {

    private ArrayList<Flight> flights; // Person 1 - Nick
    private ArrayList<Employee> employees; // Person 1
    private ArrayList<Aircraft> aircrafts; // Person 3 - Bruce
    private ArrayList<FlightCrew> flightCrew; // Person 3
    private ArrayList<Location> locations; // Person 3
    private ArrayList<Purchase> purchases; // Person 2 - Liam
    private ArrayList<RegisteredCustomer> registeredCustomers; // Person 2
    
    public Airline() {
        this.flights = new ArrayList<Flight>();
        this.employees = new ArrayList<Employee>();
        this.registeredCustomers = new ArrayList<RegisteredCustomer>();
        this.aircrafts = new ArrayList<Aircraft>();
        this.flightCrew = new ArrayList<FlightCrew>();
        this.locations = new ArrayList<Location>();
        this.purchases = new ArrayList<Purchase>();
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
}
