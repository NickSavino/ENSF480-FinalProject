package flightapp.domain.entity;

import java.util.ArrayList;

public class Airline {

    ArrayList<Flight> flights;
    ArrayList<Employee> employees;
    ArrayList<Customer> customers;
    public Airline(ArrayList<Flight> flights, ArrayList<Employee> employees, ArrayList<Customer> customers) {

        this.flights = flights;
        this.employees = employees;
        this.customers = customers;
    }

}
