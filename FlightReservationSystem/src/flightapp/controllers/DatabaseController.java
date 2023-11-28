package flightapp.controllers;


import flightapp.DatabaseConnection;
import flightapp.domain.entity.RegisteredCustomer;

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

    public static ResultSet queryAircrafts() {
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

    public static ResultSet queryLocations() {
        String query = "SELECT * FROM LOCATIONS";

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            return stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error Querying Locations", e);
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
}
