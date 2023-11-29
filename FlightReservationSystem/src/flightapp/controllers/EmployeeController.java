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

    public void setEmployee(Employee employee)
    {
        this.employee = employee;
    }

    public ArrayList<Flight> browseFlights(Location origin, Location destination, Date date)
    {
        ArrayList<Flight> flights = this.airline.getFlights();
        ArrayList<Flight> filteredFlights = new ArrayList<Flight>();
        for (Flight flight : flights)
        {
            if (flight.getOrigin() == origin && flight.getDestination() == destination && flight.getDate() == date)
            {
                filteredFlights.add(flight);
            }
        }
        return filteredFlights;
    }

    public FlightCrew browseCrew(int flightId)
    {
        FlightCrew foundFlightCrew = null;
        for (FlightCrew flightCrew : this.airline.getFlightCrew())
        {
            if (flightCrew.getAssignFlightId() == flightId)
            {
                foundFlightCrew = flightCrew;
            }
        }
        return foundFlightCrew;
    }

    public ArrayList<Aircraft> browseAircrafts()
    {
        return this.airline.getAircrafts();
    }

    public void addCrew(int newCrewId, String crewName, int assignFlightId)
    {
        // Add a new crew object to the airline list of flight crews (don't do anything if crewId already exists)
        for (FlightCrew crew : this.airline.getFlightCrew())
        {
            if (crew.getFlightCrewId() == newCrewId)
            {
                return;
            }
        }

        FlightCrew newCrew = new FlightCrew(newCrewId, assignFlightId, crewName);
        this.airline.getFlightCrew().add(newCrew);
        DatabaseController.addCrew(newCrewId, crewName, assignFlightId);
    }
    

    public void removeCrew(int crewId)
    {
        // Remove crew from flight
        for (FlightCrew flightCrews : this.airline.getFlightCrew())
        {
            if (flightCrews.getFlightCrewId() == crewId)
            {
                this.airline.getFlightCrew().remove(flightCrews);
                break;
            }
        }
        for (Flight flight : this.airline.getFlights())
        {
            if (flight.getFlightCrew().getFlightCrewId() == crewId)
            {
                flight.setFlightCrew(null);
                break;
            }
        }
        DatabaseController.removeCrew(crewId);
    }

    public void addAircraft(String aircraftModel, int newAircraftId, int amountOfOrdinarySeats,
        int amountOfBusinessSeats, int amountOfComfortSeats)
    {
        // Add aircraft (do nothing if aircraft id already exists in airline)
        for (Aircraft aircraft : this.airline.getAircrafts())
        {
            if (aircraft.getAircraftId() == newAircraftId)
            {
                return;
            }
        }

        Aircraft newAircraft = new Aircraft(newAircraftId, aircraftModel, amountOfOrdinarySeats,
            amountOfBusinessSeats, amountOfComfortSeats);
        this.airline.getAircrafts().add(newAircraft);
        DatabaseController.addAircraft(newAircraft);
    }

    public void removeAircraft(int aircraftId)
    {
        // Remove aircraft list information
        for (Aircraft aircraft : this.airline.getAircrafts())
        {
            if (aircraft.getAircraftId() == aircraftId)
            {
                this.airline.getAircrafts().remove(aircraft);
                break;
            }
        }
        for (Flight flight : this.airline.getFlights())
        {
            if (flight.getAircraft().getAircraftId() == aircraftId)
            {
                flight.setAircraft(null);
                break;
            }
        }
        DatabaseController.removeAircraft(aircraftId);
    }

    public void removeFlightDestination(String destinationId)
    {
        // Remove location from airline location list
        for (Location location : this.airline.getLocations())
        {
            if (location.getLocationId() == destinationId)
            {
                this.airline.getLocations().remove(location);
                break;
            }
        }
        for (Flight flight : this.airline.getFlights())
        {
            if (flight.getDestination().getLocationId() == destinationId)
            {
                flight.setDestination(null);
                break;
            }
            if (flight.getOrigin().getLocationId() == destinationId)
            {
                flight.setOrigin(null);
                break;
            }
        }
        DatabaseController.removeFlightDestination(destinationId);
    }

    public void addFlightDestination(String destinationId, String locationName)
    {
        // Add location to airline location list (don't do anything if destinationId already exists)
        for (Location location : this.airline.getLocations())
        {
            if (location.getLocationId() == destinationId)
            {
                return;
            }
        }

        Location newLocation = new Location(destinationId, locationName);
        this.airline.getLocations().add(newLocation);
        DatabaseController.addFlightDestination(newLocation);
    }

    public void addFlight(int flightId, int aircraftId, String originId, String destinationId, 
        int year, int day, int month, int min, int sec, int crewId, int flightDuration, int baseFlightCost)
    {
        // Add flight to airline flight list (Don't do anything if flightId already exists)
        for (Flight flight : this.airline.getFlights())
        {
            if (flight.getFlightId() == flightId)
            {
                return;
            }
        }

        // Fnd location and origin objects
        Location origin = null;
        Location destination = null;
        for (Location location : this.airline.getLocations())
        {
            if (location.getLocationId().equals(originId))
            {
                origin = location;
            }
            if (location.getLocationId().equals(destinationId))
            {
                destination = location;
            }
        }

        // Find aircraft object
        Aircraft aircraft = null;
        for (Aircraft aircraftObj : this.airline.getAircrafts())
        {
            if (aircraftObj.getAircraftId() == aircraftId)
            {
                aircraft = aircraftObj;
                break;
            }
        }

        // Find flightCrew object
        FlightCrew flightCrew = null;
        for (FlightCrew crew : this.airline.getFlightCrew())
        {
            if (crew.getFlightCrewId() == crewId)
            {
                flightCrew = crew;
                break;
            }
        }

        // Initialize time of departure
        Date timeOfDeparture = new Date(day, month, year);

        if (origin != null && destination != null && aircraft != null && flightCrew != null)
        {
            Flight newFlight = new Flight(aircraft, flightId, origin, destination, baseFlightCost, flightCrew, timeOfDeparture, flightDuration);
            this.airline.getFlights().add(newFlight);
            DatabaseController.addFlight(newFlight);
        }
    }

    public void removeFlight(int flightId)
    {
        // Need to remove flight from airline flight list
        for (Flight flight : this.airline.getFlights())
        {
            if (flight.getFlightId() == flightId)
            {
                this.airline.getFlights().remove(flight);
                break;
            }
        }
        DatabaseController.removeFlight(flightId);
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
