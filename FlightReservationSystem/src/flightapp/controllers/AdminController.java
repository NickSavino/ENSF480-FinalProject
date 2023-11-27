package flightapp.controllers;

import java.util.ArrayList;
import flightapp.domain.entity.*;
import flightapp.domain.valueobject.Date;
import flightapp.domain.valueobject.Location;

public class AdminController {
    private Airline airline;

    public AdminController(Airline airline) {
        this.airline = airline;
    }

    public ArrayList<Flight> browseFlights(Location origin, Location destination, Date date)
    {
        // TODO: Implement
        return new ArrayList<Flight>();
    }

    public ArrayList<FlightCrew> browseCrews(int flightId)
    {
        // TODO: Implement
        return new ArrayList<FlightCrew>();
    }

    public ArrayList<Aircraft> browseAircrafts()
    {
        // TODO: Implement
        return new ArrayList<Aircraft>();
    }

    public void addCrew(int flightId, FlightCrew flightCrew)
    {
        // TODO: Implement
        // Need to make sure flightId exists; edit database
    }

    public void removeCrew(int flightId)
    {
        // TODO: Implement
        // Need to ensure flightId exists with a crew, if not then throws exception; edit database
    }

    public void addAircraft(Aircraft aircraft)
    {
        // TODO: Implement
    }

    public void removeAircraft(int flightId)
    {
        // TODO: Implement
        // Need to ensure flightId exists with an aircraft, if not then throws exception; edit database
    }

    public void removeFlightDestination(String destinationId)
    {
        // TODO: Implement
        // Need to see if locationId exists, if not then throws exception; edit database
    }

    public void addFlightDestination(String destinationId, String locationName)
    {
        // TODO: Implement
        // Need to ensure destinationId is unique; edit database
    }

    public void addFlight(int flightId, int aircraftId, int originId, String destinationId, Date date, int crewId)
    {
        // TODO: Implement
        // Need to ensure flightId is unique, Aircraft & FlightCrew isn't being used at date; edit database
    }

    public void removeFlight(int flightId)
    {
        // TODO: Implement
        // Need to ensure that no passengers are on flightId?
    }

    public void modifyFlightDate(int flightId, Date newDate)
    {
        // TODO: Implement
    }

    public void modifyFlightDuration(int flightId, int newFlightDuration)
    {
        // TODO: Implement
        // Need to check to make sure that new flight id doesn't already exist
    }

    public ArrayList<RegisteredCustomer> retrieveRegisteredUsers()
    {
        // TODO:

        return new ArrayList<RegisteredCustomer>();
    }
    
}
