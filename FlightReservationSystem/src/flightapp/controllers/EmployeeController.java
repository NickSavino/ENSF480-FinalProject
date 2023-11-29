package flightapp.controllers;

import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import flightapp.DatabaseConnection;
import flightapp.domain.entity.*;
import flightapp.domain.valueobject.*;
import flightapp.domain.pattern.*;

public class EmployeeController {
    private Airline airline;
    private Employee employee;

    public EmployeeController(Airline airline) {
        this.airline = airline;
    }

    public void modifyFlightDate(int flightId, int day, int month, int year, int hour, int min)
    {
        // Update data associated with flight object
        Date newDate = new Date(day, month, year, hour, min);
        for (Flight flight : this.airline.getFlights())
        {
            if (flight.getFlightId() == flightId)
            {
                flight.setDate(newDate);
                break;
            }
        }
        DatabaseController.modifyFlightDate(flightId, newDate);
    }

    public void modifyFlightDuration(int flightId, int newFlightDuration)
    {
        // Update the duration of the flight
        for (Flight flight : this.airline.getFlights())
        {
            if (flight.getFlightId() == flightId)
            {
                flight.setDuration(newFlightDuration);
                break;
            }
        }
        DatabaseController.modifyFlightDuration(flightId, newFlightDuration);
    }

    public ArrayList<RegisteredCustomer> retrieveRegisteredUsers()
    {
        return this.airline.getRegisteredCustomers();
    }

    // This method is for airline agents and flight attendants
    public ArrayList<Customer> browsePassengers(int flightId)
    {
        ArrayList<Customer> passengers = null;
        for (Flight flight : this.airline.getFlights())
        {
            if (flight.getFlightId() == flightId)
            {
                passengers = flight.getPassengers();
                break;
            }
        }
        return passengers;
    }
}
