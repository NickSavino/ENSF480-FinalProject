package flightapp.controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import flightapp.DatabaseConnection;
import flightapp.domain.entity.*;
import flightapp.domain.pattern.*;


public class AirlineUserController {
    private EmployeeController employeeController;
    private FlightController flightController;
    private Airline airline;

    private RegisteredCustomer currentCustomer;
    private Employee currentEmployee;
    private boolean isCustomerLoggedIn = false;
    private boolean isEmployeeLoggedIn = false;

    public AirlineUserController() {
        // Initialize Airline object with database values
        this.airline = new Airline();
        initializeDataOnStartup();

        // Initialize controllers
        employeeController = new EmployeeController(airline);
        flightController = new FlightController(airline);
    }

    private void initializeDataOnStartup()
    {
        // TODO: Need to pull all data from the database to populate the airline object
        System.out.println("Initializing Data");
        try (Connection conn = DatabaseConnection.getConnection()) {
            populateEmployees();
            for ( Employee employee:
                    airline.getEmployees()) {
                System.out.println(employee.getEmployeeId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return;
    }
    private void populateFlights() throws SQLException // Nick
    {
        // Might be easier to call this after RegisteredCustomers is already initialized (for passengers) (I could be wrong)
        ResultSet rs = DatabaseController.queryFlights();
        while (rs.next()) {
        }
    }

    private void populateEmployees() throws SQLException // Nick
    {
        ResultSet rs = DatabaseController.queryEmployees();
        while (rs.next()) {
            int employeeId = rs.getInt("employeeId");
            int flightCrewId = rs.getInt("flightcrewID");
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

            airline.addEmployee(employeeId, flightCrewId, password, employeeType, firstName, lastName, houseNumber, street, city, province, country, email);
        }
    }

    private void populateAircrafts() // Bruce
    {

    }

    private void populateFlightCrews() // Bruce
    {
        // Might be easier to call this after Employees ArrayList is already initialized

    }

    private void populateLocations() // Bruce
    {

    }

    private void populatePurchases() // Liam
    {
        // Need to initialize Ticket object before populating Purchase object
        // Need to initialize Receipt object before populating Purchase object
        // Need to initialize CreditCard object before populating Purchase objecte

    }

    private void populateRegisteredCustomers() // Liam
    {
        // Need to check if customer is an airline member; if so then create CompanionVoucher object
        // Need to create a CreditCard after checking to ensure it is not null

    }

    public boolean customerLogin(String userId, String password)
    {
        LoginSingleton loginSingleton = LoginSingleton.getOnlyInstance();
        boolean validCustomer = loginSingleton.authenticateCustomer(userId, password);

        if (validCustomer)
        {
            ArrayList<RegisteredCustomer> customers = this.airline.getRegisteredCustomers();
            for (RegisteredCustomer customer : customers)
            {
                if (customer.getUsername().equals(userId))
                {
                    this.currentCustomer = customer;
                    break;
                }
            }
            this.currentEmployee = null;
            this.isCustomerLoggedIn = true;
            this.isEmployeeLoggedIn = false;
            this.flightController.setCustomer(this.currentCustomer);
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
            this.employeeController.setEmployee(this.currentEmployee);
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
        this.employeeController.setEmployee(null);
        this.flightController.setCustomer(null);
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
        //loginSingleton.addCustomer(newCustomer.getUsername(), newCustomer.getPassword());

        // Logging in the new customer
        //customerLogin(newCustomer.getUsername(), newCustomer.getPassword());
        this.flightController.setCustomer(newCustomer);

        DatabaseController.insertCustomer(newCustomer);
    }

    public void employeeSignup(String firstName, String lastName, String email, int age, String password, int houseNumber,
        String street, String city, String province, String country, String phoneNumber, String role)
    {
        Employee newEmployee = new Employee(firstName, lastName, houseNumber, street, city, province, country, email, password, role);
        this.airline.getEmployees().add(newEmployee);
        LoginSingleton loginSingleton = LoginSingleton.getOnlyInstance();
        loginSingleton.addEmployee(newEmployee.getEmployeeId(), newEmployee.getPassword());
        employeeLogin(newEmployee.getEmployeeId(), newEmployee.getPassword());
        this.employeeController.setEmployee(newEmployee);

        // TODO: Still need to add to database
    }

    public void upgradeToAirlineMember()
    {
        this.currentCustomer.becomeAirlineMember();
        // TODO: Still need to edit database
        
    }

    public void applyForAirlineCreditCard(String newCreditCardNumber, int newSecurityCode, RegisteredCustomer customer)
    {
        customer.setCreditCard(newCreditCardNumber, newSecurityCode);
    }
}
