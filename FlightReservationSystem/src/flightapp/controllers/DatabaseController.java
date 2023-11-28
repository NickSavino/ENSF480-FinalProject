package flightapp.controllers;


import flightapp.DatabaseConnection;

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
}
