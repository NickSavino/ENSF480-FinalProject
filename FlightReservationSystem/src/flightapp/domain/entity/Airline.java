package flightapp.domain.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import flightapp.controllers.DatabaseController;
import flightapp.domain.pattern.*;
import flightapp.domain.valueobject.*;

public class Airline {

    private ArrayList<Flight> flights; 
    private ArrayList<Employee> employees; 
    private ArrayList<Aircraft> aircrafts;
    private ArrayList<FlightCrew> flightCrew;
    private ArrayList<Location> locations;
    private ArrayList<Purchase> purchases; 
    private ArrayList<RegisteredCustomer> registeredCustomers;
    private PromotionalNews promotionalNews;
    
    public Airline() {
        this.flights = new ArrayList<Flight>();
        this.employees = new ArrayList<Employee>();
        this.registeredCustomers = new ArrayList<RegisteredCustomer>();
        this.aircrafts = new ArrayList<Aircraft>();
        this.flightCrew = new ArrayList<FlightCrew>();
        this.locations = new ArrayList<Location>();
        this.purchases = new ArrayList<Purchase>();
        this.promotionalNews = new PromotionalNews("Monthly Promotion", new ContentShort());
    }

    public ArrayList<RegisteredCustomer> getRegisteredCustomers()
    {
        return this.registeredCustomers;
    }

    public ArrayList<Flight> getFlights() 
    {
        return this.flights;
    }

    public Flight getFlightByID(int flightId) {
        for (Flight flight : flights) {
            if (flight.getFlightId() == flightId) {
                return flight;
            }
        }
        return null;
    }


    public PromotionalNews getPromotionalNews()
    {
        return this.promotionalNews;
    }

    public ArrayList<Aircraft> getAircrafts() 
    {
        return this.aircrafts;
    }

    public Aircraft getAircraftByID(int aircraftID) 
    {
        for (Aircraft aircraft : this.aircrafts) {
            if (aircraft.getAircraftId() == aircraftID)
                return aircraft;
        }
        return null;
    }

    public ArrayList<FlightCrew> getFlightCrew() 
    {
        return this.flightCrew;
    }

    public FlightCrew getFlightCrewByID(int flightCrewId) 
    {
        for (FlightCrew flightCrew : this.flightCrew) {
            if (flightCrew.getFlightCrewId() == flightCrewId)
                return flightCrew;
        }
        return null;
    }

    public ArrayList<Employee> getEmployees()
    {
        return this.employees;
    }

    public ArrayList<Location> getLocations()
    {
        return this.locations;
    }

    public void initializePurchase(String purchaseId, Flight flight, boolean buyInsurance, boolean buyAirportLoungeAccess, boolean useCompanionVoucher,
        String creditCardNumber, int securityCode, ArrayList<Seat> seats, Customer customer)
    {
        // Method to help on startup
        Purchase newPurchase = new Purchase(purchaseId, flight, buyInsurance, buyAirportLoungeAccess, useCompanionVoucher, creditCardNumber, securityCode, seats, customer);
        this.purchases.add(newPurchase);
        customer.addPurchase(newPurchase);
    }

    public Location getLocationByID(String locationId)
    {
        for (Location location : this.locations) {
            if (location.getLocationId().equals(locationId))
                return location;
        }
        return null;
    }

    public ArrayList<Purchase> getPurchases()
    {
        return this.purchases;
    }

    public void addFlight(int flightId, int aircraftId, String originId, 
                        String destinationId, int flightDuration,
                         int flightCrewId, int baseFlightCost, 
                         int flightDepartureDay, int flightDepartureMonth, 
                         int flightDepartureYear, int flightDepartureHour, int flightDepartureMinute) {

        
        Aircraft aircraft = getAircraftByID(aircraftId);

        Location origin = getLocationByID(originId);
        Location destination = getLocationByID(destinationId);
        FlightCrew flightCrew = getFlightCrewByID(flightCrewId);
        Date departureTime = new Date(flightDepartureDay, flightDepartureMonth,
        flightDepartureYear, flightDepartureHour, flightDepartureMinute);

        Flight flight = new Flight(aircraft, flightId, origin, destination, baseFlightCost, flightCrew, departureTime, flightDuration);
        this.flights.add(flight);
    }
    public int getNewFlightId() {
        HashSet<Integer> existingIds = new HashSet<>();
        for (Flight flight : flights) {
            existingIds.add(flight.getFlightId());
        }

        // Start the search from 100 (or whatever your base ID is).
        int newId = 100;
        while (existingIds.contains(newId)) {
            newId++;
        }

        return newId;
    }
    public void removeFlight(int flightId) {
        Iterator<Flight> iterator = flights.iterator();
        while (iterator.hasNext()) {
            Flight flight = iterator.next();
            if (flight.getFlightId() == flightId) {
                iterator.remove();
                break; // Assuming only one flight has this ID, we can break the loop after removing
            }
        }
    }

    public void addEmployee(int employeeId,
                            String password, String employeeType,
                            String firstName, String lastName,
                            int houseNumber, String street,
                            String city, String province,
                            String country, String email) {
        this.employees.add(new Employee(employeeId, password, employeeType, firstName, lastName, houseNumber, street, city, province, country, email));
    }

    public void addAircraft(int aircraftID, String model, int amountOfOrdinarySeats, int amountOfBusinessSeats,
            int amountOfComfortSeats) {
        this.aircrafts.add(new Aircraft(aircraftID, model, amountOfOrdinarySeats, amountOfBusinessSeats, amountOfComfortSeats));
    }

    public void addFlightCrew(int flightCrewId, int flightId, String crewName) {
        this.flightCrew.add(new FlightCrew(flightCrewId, flightId, crewName));

    }
    public void removeFlightCrew(int flightCrewId) {
        Iterator<FlightCrew> iterator = flightCrew.iterator();
        while (iterator.hasNext()) {
            FlightCrew crew = iterator.next();
            if (crew.getFlightCrewId() == flightCrewId) {
                iterator.remove();
            }
        }
    }

    public void addLocation(String name, String locationId) {
        this.locations.add(new Location(name, locationId));
    }

    public void addRegisteredCustomer(int customerId, String username, String firstName, String lastName, int houseNumber, String street, String city, 
        String province, String country, String email, String password, String creditCardNumber, int securityCode, String status, boolean hasCompanyCreditCard)
    {
        this.registeredCustomers.add(new RegisteredCustomer(customerId, username, firstName, lastName, houseNumber, street, city, province, country, email, password, creditCardNumber, securityCode, status, hasCompanyCreditCard));
    }

    public void addRegisteredCustomer(int customerId, String username, String firstName, String lastName, int houseNumber, String street, String city, 
        String province, String country, String email, String password, String status, boolean hasCompanyCreditCard)
    {
        this.registeredCustomers.add(new RegisteredCustomer(customerId, username, firstName, lastName, houseNumber, street, city, province, country, email, password, status, hasCompanyCreditCard));
    }

    public Customer getCustomerByID(int customerId) {

        for (Customer customer : registeredCustomers) {
            if (customer.getCustomerId() == customerId) {
                return customer;
            }
        }
        return null;
    }

    public void addSeatToFlight(int flightId, int seatId, String seatType, boolean isBooked) {
        Flight flight = getFlightByID(flightId);
        flight.modifySeat(seatId, seatType, isBooked);
    }

    //removes purchase and updates seat on flight
    public void removePurchaseByID(String purchaseId) {
        Iterator<Purchase> iterator = purchases.iterator();
        while (iterator.hasNext()) {
            Purchase purchase = iterator.next();
            if (purchase.getPurchaseId().equals(purchaseId)) {
                Customer customer = getCustomerByID(purchase.getCustomerId());
                customer.removePurchase(purchase);
                purchases.remove(purchase);

                Flight flight = getFlightByID(purchase.getFlightId());
                for (Ticket ticket : purchase.getTickets()) {
                    Seat seat = flight.getSeat(ticket.getSeatNumber());
                    addSeatToFlight(flight.getFlightId(), seat.getSeatId(), seat.getSeatType(), false);
                    DatabaseController.updateSeat(flight.getFlightId(), seat.getSeatId(), false);
                }
                break;
            }
        }
    }

    public ArrayList<String> getLocationIds()
    {
        ArrayList<String> locationIds = new ArrayList<String>();
        for (Location location : this.locations) {
            locationIds.add(location.getLocationId());
        }
        return locationIds;
    }

    public ArrayList<Integer> getAircraftIds()
    {
        ArrayList<Integer> aircraftIds = new ArrayList<Integer>();
        for (Aircraft aircraft : this.aircrafts) {
            aircraftIds.add(aircraft.getAircraftId());
        }
        return aircraftIds;
    }

    public ArrayList<Integer> getFlightCrewIds()
    {
        ArrayList<Integer> flightCrewIds = new ArrayList<Integer>();
        for (FlightCrew flightCrew : this.flightCrew)
        {
            flightCrewIds.add(flightCrew.getFlightCrewId());
        }
        return flightCrewIds;
    }
}
