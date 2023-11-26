package flightapp.controllers;

import java.util.List;
import java.util.ArrayList;
import flightapp.domain.entity.*;
import flightapp.domain.valueobject.*;
import flightapp.domain.pattern.*;

public class AdminController {
    private Airline airline;

    public AdminController(Airline airline) {
        this.airline = airline;
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

    public ArrayList<FlightCrew> browseCrews(int flightId)
    {
        // TODO: How does it make sense that we're returning a bunch of list flights for a single flight? Isn't there only one?
        ArrayList<Flight> flights = this.airline.getFlights();
        ArrayList<FlightCrew> flightCrews = new ArrayList<FlightCrew>();
        for (Flight flight : flights)
        {
            if (flight.getFlightId() == flightId)
            {
                flightCrews.add(flight.getFlightCrew());
            }
        }
        return flightCrews;
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

        ArrayList<FlightAttendant> flightAttendants = new ArrayList<FlightAttendant>();
        for (Integer id : employeeIds)
        {
            for (Employee employee : this.airline.getEmployees())
            {
                if (employee.getEmployeeId() == id && employee instanceof FlightAttendant)
                {
                    flightAttendants.add((FlightAttendant)employee);
                }
            }
        }

        FlightCrew newCrew = new FlightCrew(newCrewId, flightAttendants);
        this.airline.getFlightCrew().add(newCrew);

        // TODO: Need to implement update to database
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

    public void addAircraft(String aircraftModel, int newAircraftId, String condition, int amountOfOrdinarySeats,
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

        Aircraft newAircraft = new Aircraft(aircraftModel, newAircraftId, condition, amountOfOrdinarySeats,
            amountOfBusinessSeats, amountOfComfortSeats);
        this.airline.getAircrafts().add(newAircraft);
        
        // TODO: Need to update database
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

    public void addFlight(int flightId, int aircraftId, int originId, String destinationId, 
        int year, int day, int month, int crewId, int flightDuration)
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
            }
        }

        // Find flightCrew object
        FlightCrew flightCrew = null;
        for (FlightCrew crew : this.airline.getFlightCrew())
        {
            if (crew.getFlightCrewId() == crewId)
            {
                flightCrew = crew;
            }
        }

        // Initialize time of departure
        Date timeOfDeparture = new Date(day, month, year);

        if (origin != null && destination != null && aircraft != null && flightCrew != null)
        {
            Flight newFlight = new Flight(aircraft, flightId, origin, destination, flightCrew, timeOfDeparture, flightDuration);
            this.airline.getFlights().add(newFlight);
        }
        // TODO: Need to update database
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
        for (Flight flight : this.airline.getFlights())
        {
            if (flight.getFlightId() == flightId)
            {
                flight.setDate(new Date(day, month, year));
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
        ArrayList<Customer> customers = this.airline.getCustomers();
        ArrayList<RegisteredCustomer> registeredCustomers = new ArrayList<RegisteredCustomer>();
        for (Customer customer : customers)
        {
            if (customer instanceof RegisteredCustomer)
            {
                registeredCustomers.add((RegisteredCustomer)customer);
            }
        }
        return registeredCustomers;
    }
}
