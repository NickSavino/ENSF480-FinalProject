package flightapp.domain.entity;

import java.util.ArrayList;

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

    public void addEmployee(int employeeId,
                            String password, String employeeType,
                            String firstName, String lastName,
                            int houseNumber, String street,
                            String city, String province,
                            String country, String email) {
        this.employees.add(new Employee(employeeId, password, employeeType, firstName, lastName, houseNumber, street, city, province, country, email));
    }

    public void addAircraft(int aircraftID, String model, int amountOfOrdinarySeats, int amountOfBusinessSeats,
            int amountOfComfortSeats, int amountOfSeats) {
        this.aircrafts.add(new Aircraft(aircraftID, model, amountOfOrdinarySeats, amountOfBusinessSeats, amountOfComfortSeats, amountOfSeats));
    }

    public void addFlightCrew(int flightcrewId, int flightId, String crewName) {
        this.flightCrew.add(new FlightCrew(flightcrewId, flightId, crewName));
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

    public void addPurchase(Flight flight, boolean buyInsurance, boolean buyAirportLoungeAccess, boolean useCompanionVoucher,
        String creditCardNumber, int securityCode, ArrayList<Seat> seats, Customer customer)
    {
        Purchase newPurchase = new Purchase(flight, buyInsurance, buyAirportLoungeAccess, useCompanionVoucher, creditCardNumber, securityCode, seats, customer);
        this.purchases.add(newPurchase);
        customer.addPurchase(newPurchase);
    }
}
