package flightapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/airline";
    private static final String USER = "user";
    private static final String PASS = "password";

    public static void initialize() {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Database connection successfully established.");
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}