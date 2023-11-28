package flightapp.controllers;


import flightapp.DatabaseConnection;
import flightapp.domain.entity.*;
import flightapp.domain.valueobject.*;
import flightapp.domain.valueobject.Date;

import javax.xml.crypto.Data;
import java.sql.*;

public class DatabaseController {
    public static ResultSet queryFlights() {
        String query = "SELECT * FROM FLIGHTS";

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            return stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error Querying Flights", e);
        }
    }

    public static ResultSet queryAircrafts() 
    {
        String query = "SELECT * FROM AIRCRAFTS";

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            return stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error Querying Aircrafts", e);
        }
    }

    public static ResultSet queryFlightCrew() {
        String query = "SELECT * FROM FLIGHTCREW";

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            return stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error Querying Flight Crews", e);
        }
    }

    public static ResultSet queryLocations()
    {
        String query = "SELECT * FROM LOCATIONS";

        try
        {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            return stmt.executeQuery();
        } catch (SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException("Error Querying Locations", e);
        }
    }
    public static ResultSet queryEmployees() {
        String query = "SELECT * FROM EMPLOYEES";

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            return stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error Querying Employees", e);
        }
    }

    public static void insertCustomer(RegisteredCustomer customer) {
        // SQL INSERT statement
        String sql = "INSERT INTO customers (customerId, status, username, password, creditCardNumber, creditCardSecurityCode, " +
                "firstName, lastName, houseNumber, street, city, province, country, email) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = DatabaseConnection.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sql);

            // Set parameters
            stmt.setInt(1, customer.getCustomerId());
            stmt.setString(2, "Registered");
            stmt.setString(3, customer.getUsername());
            stmt.setString(4, customer.getPassword()); // Consider encrypting the password
            stmt.setString(5, customer.getCreditCardNumber());
            stmt.setInt(6, customer.getCreditCardSecurityCode());
            stmt.setString(7, customer.getName().getFirstName());
            stmt.setString(8, customer.getName().getLastName());
            stmt.setInt(9, customer.getAddress().getHouseNumber());
            stmt.setString(10, customer.getAddress().getStreet());
            stmt.setString(11, customer.getAddress().getCity());
            stmt.setString(12, customer.getAddress().getProvince());
            stmt.setString(13, customer.getAddress().getCountry());
            stmt.setString(14, customer.getEmail());

            // Execute the insert operation
            stmt.executeUpdate();

        } catch (SQLException e) {
            // Handle SQL Exceptions
            e.printStackTrace();
            throw new RuntimeException("Error insterting into table CUSTOMERS");
        }
    }


    public static ResultSet queryCustomers()
    {
        String query = "SELECT * FROM CUSTOMERS";
        try
        {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            return stmt.executeQuery();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException("Error Querying Customers", e);
        }
    }

    public static ResultSet queryPurchases()
    {
        String query = "SELECT * FROM PURCHASES";
        try
        {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            return stmt.executeQuery();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException("Error Querying Purchases", e);
        }
    }
    
    public static ResultSet queryFlightSeats()
    {
        String query = "SELECT * FROM FLIGHTSEATS";
        try
        {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            return stmt.executeQuery();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException("Error Querying Flight Seats", e);
        }
    }

    public static void addCrew(int flightCrewid, String flightCrewName, int assignFlightId)
    {

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO FLIGHTCREW (flightcrewID, assignflightID, crewName) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, flightCrewid);
                stmt.setInt(2, assignFlightId);
                stmt.setString(3, flightCrewName);
                stmt.executeUpdate();
                System.out.println("New flight crew added successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error adding new flight crew.");
        }
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

    public static void addAircraft(Aircraft newAircraft)
    {
        int aircraftId = newAircraft.getAircraftId();
        String aircraftModel = newAircraft.getModel();
        int amountOfOrdinarySeats = newAircraft.getNumberOfOrdinarySeats();
        int amountOfBusinessSeats = newAircraft.getNumberOfBusinessSeats();
        int amountOfComfortSeats = newAircraft.getNumberOfComfortSeats();
        int amountOfSeats = newAircraft.getNumberOfSeats();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO AIRCRAFT (aircraftId, aircraftModel, ordinarySeats, businessSeats, comfortSeats, totalSeats) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setInt(1, aircraftId);
                stmt.setString(2, aircraftModel);
                stmt.setInt(3, amountOfOrdinarySeats);
                stmt.setInt(4, amountOfBusinessSeats);
                stmt.setInt(5, amountOfComfortSeats);
                stmt.setInt(6, amountOfSeats);

                stmt.executeUpdate();
                System.out.println("Successfully added new aircraft.");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error adding new aircraft.", e);
        }
    }

    public static void removeFlight(int flightId)
    {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "DELETE FROM FLIGHTS WHERE flightId = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, flightId);
                stmt.executeUpdate();
                System.out.println("Successfully removed flight.");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error removing flight.", e);
        }
    }

    public static void removeFlightDestination(String destinationId)
    {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "DELETE FROM LOCATIONS WHERE locationId = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, destinationId);
                stmt.executeUpdate();
                System.out.println("Successfully removed location.");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error removing location.", e);
        }
    }

    public static void removeAircraft(int aircraftId)
    {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = String.format("DELETE FROM AIRCRAFTS WHERE aircraftId = ?");
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, aircraftId);
                stmt.executeUpdate();
                System.out.println("Successfully removed aircraft.");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error removing aircraft.", e);
        }
    }

    public static void removeCrew(int crewId)
    {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = String.format("DELETE FROM FLIGHTCREW WHERE flightCrewId = ?");
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, crewId);
                stmt.executeUpdate();
                System.out.println("Successfully removed flight crew.");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error removing flight crew.", e);
        }
    }

    public static void modifyFlightDuration(int flightId, int newFlightDuration)
    {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = String.format("UPDATE FLIGHTS SET flightDuration = ? WHERE flightId = ?");
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, newFlightDuration);
                stmt.setInt(2, flightId);
                stmt.executeUpdate();
                System.out.println("Successfully modified flight duration.");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error modifying flight duration.", e);
        }
    }

    public static void modifyFlightDate(int flightId, Date newDate)
    {
        int day = newDate.getDay();
        int month = newDate.getMonth();
        int year = newDate.getYear();
        int hour = newDate.getHour();
        int minutes = newDate.getMinutes();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = String.format("UPDATE FLIGHTS SET flightDepartureDay = ?, flightDepartureMonth = ?, flightDepartureYear = ?, flightDepartureHour = ?, flightDepartureMinute = ? WHERE flightId = ?");
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, day);
                stmt.setInt(2, month);
                stmt.setInt(3, year);
                stmt.setInt(4, hour);
                stmt.setInt(5, minutes);
                stmt.setInt(6, flightId);
                stmt.executeUpdate();
                System.out.println("Successfully modified flight duration.");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error modifying flight duration.", e);
        }
    }

    public static void deletePurchase(String purchaseId)
    {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = String.format("DELETE FROM PURCHASES WHERE purchaseId = ?");
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, purchaseId);
                stmt.executeUpdate();
                System.out.println("Successfully deleted purchase.");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error deleting purchase.", e);
        }
    }

    public static void addPurchase(Purchase newPurchase, int flightId, int customerId, boolean useCompanionVoucher)
    {
        CreditCard creditCard = newPurchase.getCreditCard();
        String purchaseId = newPurchase.getPurchaseId();
        boolean loungeAccess = newPurchase.getLoungeAccess();
        String creditCardNumber = creditCard.getCreditCardNumber();
        int creditCardSecurityCode = creditCard.getSecurityCode();
        int totalPurchaseCost = newPurchase.getTotalPurchaseCost();
        boolean ticketInsurance = newPurchase.getTicketInsurance();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO PURCHASES (purchaseId, loungeAccess, creditCardNumber, creditCardSecurityCode, totalPurchaseCost, ticketInsurance, flightId, customerId, useCompanionVoucher) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, purchaseId);
                stmt.setBoolean(2, loungeAccess);
                stmt.setString(3, creditCardNumber);
                stmt.setInt(4, creditCardSecurityCode);
                stmt.setInt(5, totalPurchaseCost);
                stmt.setBoolean(6, ticketInsurance);
                stmt.setInt(7, flightId);
                stmt.setInt(8, customerId);
                stmt.setBoolean(9, useCompanionVoucher);
                stmt.executeUpdate();
                System.out.println("Successfully added new purchase.");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error adding new purchase.", e);
        }        
    }
}
