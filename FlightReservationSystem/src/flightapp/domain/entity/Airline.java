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
    
    public Airline() {

        this.flights = new ArrayList<Flight>();
        this.employees = new ArrayList<Employee>();
        this.customers = new ArrayList<Customer>();
        this.aircrafts = new ArrayList<Aircraft>();
        this.flightCrew = new ArrayList<FlightCrew>();
        this.locations = new ArrayList<Location>();
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

}
