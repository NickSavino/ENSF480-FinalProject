package flightapp.controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import flightapp.DatabaseConnection;
import flightapp.domain.entity.*;
import flightapp.domain.pattern.*;
import flightapp.domain.valueobject.*;

import javax.xml.crypto.Data;



public class AirlineUserController {
    private Airline airline;

    private RegisteredCustomer currentCustomer;
    private Employee currentEmployee;
    private boolean isCustomerLoggedIn = false;
    private boolean isEmployeeLoggedIn = false;
    private boolean initializationComplete = false;
    private Flight selectedFlight;

    public AirlineUserController() {
        // Initialize Airline object with database values
        this.airline = new Airline();
        initializeDataOnStartup();

        
        // flightController.sendEmail("TESTING EMAIL SENDING WITH THIS DUMMY MESSAGE");
        
    }

    private void initializeDataOnStartup()
    {
        // TODO: Need to pull all data from the database to populate the airline object
        System.out.println("Initializing Data");
        try (Connection conn = DatabaseConnection.getConnection()) {
            populateEmployees();
            populateRegisteredCustomers();

            populateFlightCrews();

            populateAircrafts();

            populateLocations();

            populateFlights();

            populatePurchases();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        initializationComplete = true;
        System.out.println("Initialization Complete");

    }

    public boolean getInitalizationStatus() {
        return initializationComplete;
    }

    private void populateFlights() throws SQLException // Nick
    {
        // Might be easier to call this after RegisteredCustomers is already initialized (for passengers) (I could be wrong)
        ResultSet rs = DatabaseController.queryFlights();
        while (rs.next()) {
            int flightId = rs.getInt("flightId");
            int aircraftId = rs.getInt("aircraftId");
            String originId = rs.getString("originId");
            String destinationId = rs.getString("destinationId");
            int flightDuration = rs.getInt("flightDuration");
            int flightCrewId = rs.getInt("flightCrewId");
            int baseFlightCost = rs.getInt("baseFlightCost");
            int flightDepartureDay = rs.getInt("flightDepartureDay");
            int flightDepartureMonth = rs.getInt("flightDepartureMonth");
            int flightDepartureYear = rs.getInt("flightDepartureYear");
            int flightDepartureHour = rs.getInt("flightDepartureHour");
            int flightDepartureMinute = rs.getInt("flightDepartureMinute");
            airline.addFlight(flightId, aircraftId, originId, destinationId,
            flightDuration, flightCrewId, baseFlightCost, flightDepartureDay, 
            flightDepartureMonth, flightDepartureYear, flightDepartureHour, flightDepartureMinute);    
        }
    }

    private void populateEmployees() throws SQLException // Nick
    {
        ResultSet rs = DatabaseController.queryEmployees();
        while (rs.next()) {
            int employeeId = rs.getInt("employeeId");
            String password = rs.getString("password");
            String employeeType = rs.getString("employeeType");
            String firstName = rs.getString("firstName");
            String lastName = rs.getString("lastName");
            int houseNumber = rs.getInt("houseNumber");
            String street = rs.getString("street");
            String city = rs.getString("city");
            String province = rs.getString("province");
            String country = rs.getString("country");
            String email = rs.getString("email");

            LoginSingleton loginSingleton = LoginSingleton.getOnlyInstance();
            loginSingleton.addEmployee(employeeId, password);

            airline.addEmployee(employeeId, password, employeeType, firstName, lastName, houseNumber, street, city, province, country, email);
        }
    }

    private void populateAircrafts() throws SQLException // Bruce
    {
        ResultSet rs = DatabaseController.queryAircrafts();
        while (rs.next()) {
            int aircraftID = rs.getInt("aircraftID");
            String model = rs.getString("aircraftModel");
            int amountOfOrdinarySeats = rs.getInt("ordinarySeats");
            int amountOfBusinessSeats = rs.getInt("businessSeats");
            int amountOfComfortSeats = rs.getInt("comfortSeats");

            airline.addAircraft(aircraftID, model, amountOfOrdinarySeats, amountOfBusinessSeats, amountOfComfortSeats);
        }

    }

    private void populateFlightCrews() throws SQLException // Bruce
    {
        ResultSet rs = DatabaseController.queryFlightCrew();
        while (rs.next()) {
            int flightCrewId = rs.getInt("flightCrewID");
            int assignflightId = rs.getInt("assignflightID");
            String crewName = rs.getString("crewName");

            airline.addFlightCrew(flightCrewId, assignflightId, crewName);
            }     
        }

    private void populateLocations() throws SQLException// Bruce
    {
        ResultSet rs = DatabaseController.queryLocations();
        while (rs.next()) {
            String locationID = rs.getString("locationID");
            String name = rs.getString("locationName");

            airline.addLocation(name, locationID);
        }
    }

    private void populatePurchases() throws SQLException // Liam
    {
        ResultSet rs = DatabaseController.queryPurchases();
        
        while (rs.next())
        {
            String purchaseId = rs.getString("purchaseId");
            boolean loungeAccess = rs.getBoolean("loungeAccess");
            String creditCardNumber = rs.getString("creditCardNumber");
            int creditCardSecurityCode = rs.getInt("creditCardSecurityCode");
            int totalPurchaseCost = rs.getInt("totalPurchaseCost");
            boolean ticketInsurance = rs.getBoolean("ticketInsurance");
            int customerId = rs.getInt("customerId");
            int flightId = rs.getInt("flightId");
            boolean useCompanionVoucher = rs.getBoolean("useCompanionVoucher");
            Flight currentFlight = null;
            ArrayList<Seat> seats = null;
            for (Flight flight : airline.getFlights())
            {
                if (flight.getFlightId() == (flightId))
                {
                    currentFlight = flight;
                    seats = flight.getSeatList();
                    break;
                }
            }
            RegisteredCustomer currentCustomer = null;
            for (RegisteredCustomer customer :  airline.getRegisteredCustomers())
            {
                if (customer.getCustomerId() == customerId)
                {
                    currentCustomer = customer;
                    break;
                }
            }
            
            if (currentFlight != null && currentCustomer != null)
            {
                airline.initializePurchase(currentFlight, ticketInsurance, loungeAccess, useCompanionVoucher, creditCardNumber, creditCardSecurityCode, seats, currentCustomer);
            }
        }

    }

    private void populateRegisteredCustomers() throws SQLException // Liam
    {
        ResultSet rs = DatabaseController.queryCustomers();
        while (rs.next())
        {
            int customerId = rs.getInt("customerId");
            String status = rs.getString("status");
            String username = rs.getString("username");
            String password = rs.getString("password");
            String creditCardNumber = rs.getString("creditCardNumber");
            int creditCardSecurityCode = rs.getInt("creditCardSecurityCode");
            boolean isAirlineMember = rs.getBoolean("isAirlineMember");
            boolean companionVoucherUsable = rs.getBoolean("companionVoucherUsable");
            String firstName = rs.getString("firstName");
            String lastName = rs.getString("lastName");
            int houseNumber = rs.getInt("houseNumber");
            String street = rs.getString("street");
            String city = rs.getString("city");
            String country = rs.getString("country");
            String province = rs.getString("province");
            String email = rs.getString("email");
            boolean hasCompanyCreditCard = rs.getBoolean("hasCompanyCreditCard");

            // TODO: Still need to populate all purchases
            if (creditCardNumber.equals(""))
            {
                airline.addRegisteredCustomer(customerId, username, firstName, lastName, houseNumber, street, city, province, country, email, password, status, hasCompanyCreditCard);
            }
            else
            {
                airline.addRegisteredCustomer(customerId, username, firstName, lastName, houseNumber, street, city, province, country, email, password, creditCardNumber, creditCardSecurityCode, status, hasCompanyCreditCard);
            }

            //Add Customer credentials to LoginSingleton Hashmap
            LoginSingleton loginSingleton = LoginSingleton.getOnlyInstance();
            loginSingleton.addCustomer(username, password);

            if (isAirlineMember)
            {
                for (RegisteredCustomer customer : airline.getRegisteredCustomers())
                {
                    if (customer.getCustomerId() == customerId)
                    {
                        customer.becomeAirlineMember();
                        if (!companionVoucherUsable)
                        {
                            customer.getCompanionVoucher().use();
                        }
                        break;
                    }
                }
            }
        }
    }

    public boolean customerLogin(String username, String password)
    {
        LoginSingleton loginSingleton = LoginSingleton.getOnlyInstance();
        boolean validCustomer = loginSingleton.authenticateCustomer(username, password);

        if (validCustomer)
        {
            ArrayList<RegisteredCustomer> customers = this.airline.getRegisteredCustomers();
            for (RegisteredCustomer customer : customers)
            {
                if (customer.getUsername().equals(username))
                {
                    this.currentCustomer = customer;
                    break;
                }
            }
            this.currentEmployee = null;
            this.isCustomerLoggedIn = true;
            this.isEmployeeLoggedIn = false;
        }
        return validCustomer;
    }

    public boolean employeeLogin(int userId, String password)
    {
        LoginSingleton loginSingleton = LoginSingleton.getOnlyInstance();
        boolean validEmployee = loginSingleton.authenticateEmployee(userId, password);

        if (validEmployee)
        {
            ArrayList<Employee> employees = this.airline.getEmployees();
            for (Employee employee : employees)
            {
                if (employee.getEmployeeId() == userId)
                {
                    this.currentEmployee = employee;
                    break;
                }
            }
            this.currentCustomer = null;
            this.isCustomerLoggedIn = false;
            this.isEmployeeLoggedIn = true;
        }
        return validEmployee;
    }

    public boolean isCustomerLoggedIn()
    {
        return this.isCustomerLoggedIn;
    }

    public boolean isEmployeeLoggedIn()
    {
        return this.isEmployeeLoggedIn;
    }

    public void logout()
    {
        this.isCustomerLoggedIn = false;
        this.isEmployeeLoggedIn = false;
        this.currentCustomer = null;
        this.currentEmployee = null;
    }

    public void customerSignup(String username, String password, String creditCardNumber,
                               String creditCardSecurityCode, String firstName, String lastName,
                               int houseNumber, String street, String city, String province,
                               String country, String email) {

        // Constructing new customer object with the additional fields
        RegisteredCustomer newCustomer = new RegisteredCustomer(username, password, creditCardNumber,
                creditCardSecurityCode,
                firstName, lastName, houseNumber, street,
                city, province, country, email);

        // Adding new customer to the airline's customer list
        this.airline.getRegisteredCustomers().add(newCustomer);

        // Adding customer credentials to the LoginSingleton
        LoginSingleton loginSingleton = LoginSingleton.getOnlyInstance();
        loginSingleton.addCustomer(newCustomer.getUsername(), newCustomer.getPassword());

        // Logging in the new customer
        customerLogin(newCustomer.getUsername(), newCustomer.getPassword());

        DatabaseController.insertCustomer(newCustomer);
    }

    public void employeeSignup(String firstName, String lastName, String email, String password, int houseNumber,
        String street, String city, String province, String country, String role)
    {
        Employee newEmployee = new Employee(firstName, lastName, houseNumber, street, city, province, country, email, password, role);
        this.airline.getEmployees().add(newEmployee);
        LoginSingleton loginSingleton = LoginSingleton.getOnlyInstance();
        loginSingleton.addEmployee(newEmployee.getEmployeeId(), newEmployee.getPassword());
        employeeLogin(newEmployee.getEmployeeId(), newEmployee.getPassword());
        DatabaseController.insertEmployee(newEmployee);
    }

    public void upgradeToAirlineMember()
    {
        this.currentCustomer.becomeAirlineMember();
        DatabaseController.becomeAirlineMember(this.currentCustomer.getCustomerId());
        
    }


    public void applyForAirlineCreditCard(String newCreditCardNumber, int newSecurityCode, RegisteredCustomer customer)
    {
        customer.setCreditCard(newCreditCardNumber, newSecurityCode);
        DatabaseController.createAirlineCreditCard(newCreditCardNumber, newSecurityCode, customer.getCustomerId());
    }

    public Airline getAirline() {
        return airline;
    }

    public ArrayList<String> getFlightsString() {

        ArrayList<String> flightStrings = new ArrayList<>();
        for (Flight flight : this.airline.getFlights()) {
            flightStrings.add(flight.toString());
        }
        return flightStrings;
    }

    public void setSelectedFlightFromString(String string)
    {
        String[] tokens = string.split(" ");
        int flightId = -1;
        try
        {
            flightId = Integer.parseInt(tokens[tokens.length - 1]);
        }
        catch (NumberFormatException e)
        {
            System.out.println(e);
        }
        for (Flight flight : airline.getFlights())
        {
            if (flight.getFlightId() == flightId)
            {
                this.selectedFlight = flight;
                break;
            }
        }
    }
    
    public Flight getSelectedFlight()
    {
        return this.selectedFlight;
    }

    public ArrayList<String> getAircraftsString() {

        ArrayList<String> aircraftStrings = new ArrayList<>();
        for (Aircraft aircraft : airline.getAircrafts()) {
            aircraftStrings.add(aircraft.toString());
        }
        return aircraftStrings;
    }

    public ArrayList<String> getLocationsString() {

        ArrayList<String> locationStrings = new ArrayList<>();
        for (Location location : airline.getLocations()) {
            locationStrings.add(location.getLocationId());
        }
        return locationStrings;
    }

    public ArrayList<String> getFlightCrewsString() {

        ArrayList<String> flightCrewStrings = new ArrayList<>();
        for (FlightCrew flightCrew : airline.getFlightCrew()) {
            flightCrewStrings.add(flightCrew.toString());
        }
        return flightCrewStrings;
    }

    public ArrayList<String> getRegisteredUsersString() {
        
        ArrayList<String> registeredUserStrings = new ArrayList<>();
        for (RegisteredCustomer registeredCustomer : airline.getRegisteredCustomers()) {
            registeredUserStrings.add(registeredCustomer.toString());
        }
        return registeredUserStrings;
    }

    public String getEmployeeType() {
        if (currentEmployee != null)
            return currentEmployee.getEmployeeType();
        else
            return null;
    }

    public void removeDestination(String locationId) {
        airline.getLocations().remove(locationId);
        DatabaseController.removeFlightDestination(locationId);
    }

    public void addDestination(String name, String locationId) {
        airline.addLocation(name, locationId);
        DatabaseController.addFlightDestination(airline.getLocationByID(locationId));
    }

    public void addAircraft(int aircraftId, String model, int amountOfOrdinarySeats,
                            int amountOfBusinessSeats, int amountOfComfortSeats) {
        int amountOfSeats = amountOfBusinessSeats + amountOfComfortSeats + amountOfOrdinarySeats;
        airline.addAircraft(aircraftId, model, amountOfOrdinarySeats, amountOfBusinessSeats, amountOfComfortSeats);
        DatabaseController.addAircraft(airline.getAircraftByID(aircraftId));
    }

    public void removeAircraft(int aircraftId) {
        DatabaseController.removeAircraft(aircraftId);
    }

    public void addFlight(int flightId, int aircraftId, String originId, String destinationId, int flightDuration, int flightCrewId,
                          int baseFlightCost, int departureDay, int departureMonth, int departureYear, int departureHour, int departureMinute) {

        airline.addFlight(flightId, aircraftId, originId, destinationId, flightDuration, flightCrewId, baseFlightCost, departureDay, departureMonth, departureYear, departureHour, departureMinute);
        DatabaseController.addFlight(flightId, aircraftId, originId, destinationId, flightDuration, flightCrewId, baseFlightCost, departureDay, departureMonth, departureYear, departureHour, departureMinute);
    }


    public void removeFlight(int flightId) {
        airline.removeFlight(flightId);
        DatabaseController.removeFlight(flightId);
    }

    public void addCrew(int crewId, String crewName, int assignedFlightId) {
        airline.addFlightCrew(crewId, assignedFlightId, crewName);
        DatabaseController.addCrew(crewId, crewName, assignedFlightId);
    }

    public void removeCrew(int crewId) {
        airline.removeFlightCrew(crewId);
        DatabaseController.removeCrew(crewId);
    }
}
