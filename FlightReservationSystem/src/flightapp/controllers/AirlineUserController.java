package flightapp.controllers;

import java.sql.Connection;
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

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return;
    }

    private void populateFlights() // Nick
    {
        // Might be easier to call this after RegisteredCustomers is already initialized (for passengers) (I could be wrong)
        DatabaseController.queryFlights();
    }

    private void populateEmployees() // Nick
    {
        
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
                if (customer.getCustomerUsername().equals(userId))
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

    public void customerSignup(String username, String firstName, String lastName, int houseNumber, String street, String city, String province,
        String country, String email, int age, String phoneNumber, String password)
    {
        RegisteredCustomer newCustomer = new RegisteredCustomer(username, firstName, lastName, houseNumber, street, 
            city, province, country, email, password);
        this.airline.getRegisteredCustomers().add(newCustomer);
        LoginSingleton loginSingleton = LoginSingleton.getOnlyInstance();
        loginSingleton.addCustomer(newCustomer.getCustomerUsername(), newCustomer.getPassword());
        customerLogin(newCustomer.getCustomerUsername(), newCustomer.getPassword());
        this.flightController.setCustomer(newCustomer);

        // TODO: Still need to add to database
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
