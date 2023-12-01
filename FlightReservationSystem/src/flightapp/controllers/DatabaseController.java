package flightapp.controllers;


import flightapp.DatabaseConnection;
import flightapp.domain.entity.*;
import flightapp.domain.valueobject.*;
import flightapp.domain.valueobject.Date;

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

    // Queries seats for a specific flight
    public static ResultSet querySeats(int flightId) {
        String query = "SELECT * FROM FLIGHTSEATS WHERE flightId = ?";

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, flightId);
            return stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error Querying Seats", e);
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
        String sql = "INSERT INTO customers (customerId, status, username, password, " +
                "firstName, lastName, houseNumber, street, city, province, country, email, creditCardNumber, creditCardSecurityCode, hasCompanyCreditCard) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = DatabaseConnection.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sql);

            // Set parameters
            stmt.setInt(1, customer.getCustomerId());
            stmt.setString(2, "Registered");
            stmt.setString(3, customer.getUsername());
            stmt.setString(4, customer.getPassword()); // Consider encrypting the password
            stmt.setString(5, customer.getName().getFirstName());
            stmt.setString(6, customer.getName().getLastName());
            stmt.setInt(7, customer.getAddress().getHouseNumber());
            stmt.setString(8, customer.getAddress().getStreet());
            stmt.setString(9, customer.getAddress().getCity());
            stmt.setString(10, customer.getAddress().getProvince());
            stmt.setString(11, customer.getAddress().getCountry());
            stmt.setString(12, customer.getEmail());
            stmt.setString(13, "null");
            stmt.setInt(14, -1);
            stmt.setBoolean(15, false);

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

    public static void removePurchase(String purchaseId) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "DELETE FROM PURCHASES WHERE purchaseId = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, purchaseId);
                stmt.executeUpdate();
                System.out.println("Successfully removed Purchase.");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error removing purchase.", e);
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

    public static void addFlight(int flightId, int aircraftId, String originId, String destinationId, int flightDuration, int flightCrewId,
                                 int baseFlightCost, int departureDay, int departureMonth, int departureYear, int departureHour, int departureMinute)
    {
        System.out.println("Adding flight departure month: " + departureMonth);
        System.out.println("Adding flight departure day: " + departureDay);

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO FLIGHTS (flightId, aircraftId, originId, destinationId, flightDuration, flightCrewId, baseFlightCost, flightDepartureDay, flightDepartureMonth, flightDepartureYear, flightDepartureHour, flightDepartureMinute) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, flightId);
                stmt.setInt(2, aircraftId);
                stmt.setString(3, originId);
                stmt.setString(4, destinationId);
                stmt.setInt(5, flightDuration);
                stmt.setInt(6, flightCrewId);
                stmt.setInt(7, baseFlightCost);
                stmt.setInt(8, departureDay);
                stmt.setInt(9, departureMonth);
                stmt.setInt(10, departureYear);
                stmt.setInt(11, departureHour);
                stmt.setInt(12, departureMinute);
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
            String query = "INSERT INTO LOCATIONS (locationId, locationName) VALUES (?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, locationId);
                stmt.setString(2, locationName);
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
            String query = "INSERT INTO AIRCRAFTS (aircraftId, aircraftModel, ordinarySeats, businessSeats, comfortSeats, totalSeats) VALUES (?, ?, ?, ?, ?, ?)";
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

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "DELETE FROM FLIGHTSEATS WHERE flightId = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, flightId);
                stmt.executeUpdate();
                System.out.println("Successfully removed seats belonging to the flight.");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Eror removing seats belonging to flight.");
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
            String query = "DELETE FROM AIRCRAFTS WHERE aircraftId = ?";
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
            String query = "DELETE FROM FLIGHTCREW WHERE flightCrewId = ?";
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
            String query = "UPDATE FLIGHTS SET flightDuration = ? WHERE flightId = ?";
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
        int minutes = newDate.getMinute();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "UPDATE FLIGHTS SET flightDepartureDay = ?, flightDepartureMonth = ?, flightDepartureYear = ?, flightDepartureHour = ?, flightDepartureMinute = ? WHERE flightId = ?";
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
            String query = "DELETE FROM PURCHASES WHERE purchaseId = ?";
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

    public static void insertEmployee(Employee employee) {
        // SQL INSERT statement
        String sql = "INSERT INTO employees (employeeId, password, employeeType, firstName, lastName, houseNumber, street, city, province, country, email) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = DatabaseConnection.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sql);

            // Set parameters
            stmt.setInt(1, employee.getEmployeeId());
            stmt.setString(2, employee.getPassword()); // Consider encrypting the password
            stmt.setString(3, employee.getEmployeeType());
            stmt.setString(4, employee.getName().getFirstName());
            stmt.setString(5, employee.getName().getLastName());
            stmt.setInt(6, employee.getAddress().getHouseNumber());
            stmt.setString(7, employee.getAddress().getStreet());
            stmt.setString(8, employee.getAddress().getCity());
            stmt.setString(9, employee.getAddress().getProvince());
            stmt.setString(10, employee.getAddress().getCountry());
            stmt.setString(11, employee.getEmail());

            // Execute the insert operation
            stmt.executeUpdate();

        } catch (SQLException e) {
            // Handle SQL Exceptions
            e.printStackTrace();
            throw new RuntimeException("Error insterting into table EMPLOYEES");
        }
    }

    public static void becomeAirlineMember(int employeeId)
    {
        String sql = "UPDATE CUSTOMERS SET status = 'Airline Member' WHERE customerId = ?";
        
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, employeeId);
            stmt.executeUpdate();
            System.out.println("Successfully made customer an airline member.");
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error making customer an airline member.", e);
        }
    }

    public static void createAirlineCreditCard(String newCreditCardNumber, int newSecurityCode, int customerId)
    {
        String sql = "UPDATE CUSTOMERS SET creditCardNumber = ?, hasCompanyCreditCard = true, creditCardSecurityCode = ? WHERE customerId = ?";
        
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, newCreditCardNumber);
            stmt.setInt(2, newSecurityCode);
            stmt.setInt(3, customerId);
            stmt.executeUpdate();
            System.out.println("Successfully created airline credit card.");
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error creating airline credit card.", e);
        }
    }

    public static void addSeatToFlight(int flightId, int seatId, String seatType, boolean isBooked)
    {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO FLIGHTSEATS (flightId, seatId, seatType, isBooked) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, flightId);
                stmt.setInt(2, seatId);
                stmt.setString(3, seatType);
                stmt.setBoolean(4, isBooked);
                stmt.executeUpdate();
                System.out.println("Successfully added new seat.");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error adding new seat.", e);
        }
    }

    public static void updateSeat(int flightId, int seatId, boolean isBooked) {
        // SQL UPDATE statement
        String sql = "UPDATE FLIGHTSEATS SET isBooked = ? WHERE flightId = ? AND seatId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set the parameters for the prepared statement
            stmt.setBoolean(1, isBooked);
            stmt.setInt(2, flightId);
            stmt.setInt(3, seatId);

            // Execute the update operation
            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Seat booking status updated successfully.");
            } else {
                System.out.println("No rows affected. It may mean that the seat or flight does not exist.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error updating seat booking status.", e);
        }
    }

    public static void updateFlight(int flightId, int aircraftId, String originId, String destinationId, int flightDuration, int flightCrewId, int baseFlightCost,
                                    int day, int month, int year, int hour, int minute) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "UPDATE flights SET aircraftId = ?, originId = ?, destinationId = ?, flightDuration = ?, " +
                    "flightCrewId = ?, baseFlightCost = ?, flightDepartureMonth = ?, flightDepartureDay = ?, " +
                    "flightDepartureYear = ?, flightDepartureHour = ?, flightDepartureMinute = ? WHERE flightId = ?";

            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, aircraftId);
                pstmt.setString(2, originId);
                pstmt.setString(3, destinationId);
                pstmt.setInt(4, flightDuration);
                pstmt.setInt(5, flightCrewId);
                pstmt.setInt(6, baseFlightCost);
                pstmt.setInt(7, day);
                pstmt.setInt(8, month);
                pstmt.setInt(9, year);
                pstmt.setInt(10, hour);
                pstmt.setInt(11, minute);
                pstmt.setInt(12, flightId);

                int updatedRows = pstmt.executeUpdate();
                if (updatedRows > 0) {
                    System.out.println("Flight successfully updated.");
                } else {
                    System.out.println("No flight was updated.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions such as SQL issues
        }
    }

    public static void giveAirlineCreditCard(int customerId, String creditCardNumber, int securityCode)
    {
        String sql = "UPDATE CUSTOMERS SET creditCardNumber = ?, creditCardSecurityCode = ?, hasCompanyCreditCard = true WHERE customerId = ?";
        
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, creditCardNumber);
            stmt.setInt(2, securityCode);
            stmt.setInt(3, customerId);
            stmt.executeUpdate();
            System.out.println("Successfully gave customer an airline credit card.");
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error giving customer an airline credit card.", e);
        }
    }
}
