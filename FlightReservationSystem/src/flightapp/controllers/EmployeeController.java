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

    public ArrayList<Employee> browseCrew(int flightId)
    {
        // TODO: How does it make sense that we're returning a bunch of list flights for a single flight? Isn't there only one?
        ArrayList<Employee> flightCrew = new ArrayList<Employee>();
        for (Flight flight : this.airline.getFlights())
        {
            if (flight.getFlightId() == flightId)
            {
                FlightCrew foundFlightCrew = flight.getFlightCrew();
                for (Employee employee : foundFlightCrew.getFlightAttendants())
                {
                    flightCrew.add(employee);
                }
            }
        }
        return flightCrew;
    }

    public ArrayList<Aircraft> browseAircrafts()
    {
        return this.airline.getAircrafts();
    }

    public void addCrew(int newCrewId, ArrayList<Integer> employeeIds)
    {
        // Add a new crew object to the airline list of flight crews (don't do anything if crewId already exists)
        for (FlightCrew crew : this.airline.getFlightCrew())
        {
            if (crew.getFlightCrewId() == newCrewId)
            {
                return;
            }
        }

        ArrayList<Employee> flightAttendants = new ArrayList<Employee>();
        
        for (Integer id : employeeIds)
        {
            for (Employee employee : this.airline.getEmployees())
            {
                if (employee.getEmployeeId() == id)
                {
                    flightAttendants.add(employee);
                }
            }
        }

        FlightCrew newCrew = new FlightCrew(newCrewId, flightAttendants);
        this.airline.getFlightCrew().add(newCrew);

        // TODO: Need to implement update to database
    }

    public static void addCrew(FlightCrew newCrew)
    {
        int crewId = newCrew.getFlightCrewId();
        String flightCrewName = newCrew.getCrewName();
        int assignflightID = newCrew.getAssignFlightId();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = String.format("INSERT INTO FLIGHTCREW (flightcrewID, assignflightID, crewName) VALUES (%d, '%d', %s)", crewId, assignflightID, flightCrewName);
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.executeUpdate();
                System.out.println("Successfully added new flight crew.");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error adding new flight crew.", e);
        }
    }

    public void removeCrew(int crewId)
    {
        // Remove crew from flight
        for (Flight flight : this.airline.getFlights())
        {
            if (flight.getFlightCrew().getFlightCrewId() == crewId)
            {
                flight.setFlightCrew(null);
                break;
            }
        }
        // TODO: Need to update database
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

        Aircraft newAircraft = new Aircraft(aircraftModel, newAircraftId, amountOfOrdinarySeats,
            amountOfBusinessSeats, amountOfComfortSeats);
        this.airline.getAircrafts().add(newAircraft);
        
        // TODO: Need to update database

    }

    public static void addAircraft(Aircraft newAircraft)
    {
        int aircraftId = newAircraft.getAircraftId();
        String aircraftModel = newAircraft.getModel();
        int amountOfOrdinarySeats = newAircraft.getNumberOfOrdinarySeats();
        int amountOfBusinessSeats = newAircraft.getNumberOfBusinessSeats();
        int amountOfComfortSeats = newAircraft.getNumberOfComfortSeats();
        int amountOfSeats = newAircraft.getNumberOfSeats();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = String.format("INSERT INTO AIRCRAFT (aircraftId, aircraftModel, ordinarySeats, businessSeats, comfortSeats, totalSeats) VALUES (%d, '%s', %d, %d, %d, %d)", aircraftId, aircraftModel, amountOfOrdinarySeats, amountOfBusinessSeats, amountOfComfortSeats, amountOfSeats);
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.executeUpdate();
                System.out.println("Successfully added new aircraft.");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error adding new aircraft.", e);
        }
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
        // TODO: Need to update database
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

        // TODO: Need to update database
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

        // TODO: Need to update database
    }

    public static void addFlightDestination(Location newLocation)
    {
        String locationId = newLocation.getLocationId();
        String locationName = newLocation.getName();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = String.format("INSERT INTO LOCATION (locationId, locationName) VALUES ('%s', '%s')", locationId, locationName);
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.executeUpdate();
                System.out.println("Successfully added new location.");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error adding new location.", e);
        }
    }

    public void addFlight(int flightId, int aircraftId, String originId, String destinationId, 
        int year, int day, int month, int crewId, int flightDuration, int baseFlightCost)
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
        }
        // TODO: Need to update database
    }

    public static void addFlight(Flight newFlight)
    {
        int flightId = newFlight.getFlightId();
        int aircraftId = newFlight.getAircraft().getAircraftId();
        String originId = newFlight.getOrigin().getLocationId();
        String destinationId = newFlight.getDestination().getLocationId();
        int flightDuration = newFlight.getDuration();
        int flightCrewId = newFlight.getFlightCrew().getFlightCrewId();
        int baseFlightCost = newFlight.getBaseFlightCost();
        int flightDepartureMonth = newFlight.getDate().getMonth();
        int flightDepartureDay = newFlight.getDate().getDay();
        int flightDepartureYear = newFlight.getDate().getYear();
        int flightDepartureHour = newFlight.getDate().getHour();
        int flightDepartureMinute = newFlight.getDate().getMinutes();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = String.format("INSERT INTO FLIGHT (flightId, aircraftId, originId, destinationId, flightDuration, flightCrewId, baseFlightCost, flightDepartureMonth, flightDepartureDay, flightDepartureYear, flightDepartureHour, flightDepartureMinute) VALUES (%d, %d, '%s', '%s', %d, %d, %d, %d, %d, %d)", flightId, aircraftId, originId, destinationId, flightDuration, flightCrewId, baseFlightCost, flightDepartureMonth, flightDepartureDay, flightDepartureYear, flightDepartureHour, flightDepartureMinute);
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.executeUpdate();
                System.out.println("Successfully added new flight.");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error adding new flight.", e);
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
        // TODO: Need to update database
    }

    public void modifyFlightDate(int flightId, int day, int month, int year)
    {
        // Update data associated with flight object
        Date newDate = new Date(day, month, year);
        for (Flight flight : this.airline.getFlights())
        {
            if (flight.getFlightId() == flightId)
            {
                flight.setDate(newDate);
                break;
            }
        }
        // TODO: Need to update database
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
        // TODO: Need to update database
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
