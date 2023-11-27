package flightapp.controllers;

import java.util.List;
import java.util.ArrayList;
import flightapp.domain.entity.*;
import flightapp.domain.valueobject.*;
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
        airline = new Airline();
        initializeDataOnStartup(airline);

        // Initialize controllers
        employeeController = new EmployeeController(airline);
        flightController = new FlightController(airline);
    }

    private void initializeDataOnStartup(Airline airline)
    {
        // TODO: Need to pull all data from the database to populate the airline object
        return;
    }

    // TODO: Need to direct functionality into the proper controllers from here

    public boolean customerLogin(int userId, String password)
    {
        LoginSingleton loginSingleton = LoginSingleton.getOnlyInstance();
        boolean validCustomer = loginSingleton.authenticateCustomer(userId, password);

        if (validCustomer)
        {
            ArrayList<RegisteredCustomer> customers = this.airline.getRegisteredCustomers();
            for (RegisteredCustomer customer : customers)
            {
                if (customer.getCustomerId() == userId)
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

    public void customerSignup(String firstName, String lastName, int houseNumber, String street, String city, String province,
        String country, String email, int age, String phoneNumber, String password)
    {
        RegisteredCustomer newCustomer = new RegisteredCustomer(firstName, lastName, houseNumber, street, 
            city, province, country, email, age, phoneNumber, password);
        this.airline.getCustomers().add(newCustomer);
        LoginSingleton loginSingleton = LoginSingleton.getOnlyInstance();
        loginSingleton.addCustomer(newCustomer.getCustomerId(), newCustomer.getPassword());
        customerLogin(newCustomer.getCustomerId(), newCustomer.getPassword());

        // TODO: Still need to add to database
    }

    public void employeeSignup(String firstName, String lastName, String email, int age, String password, int houseNumber,
        String street, String city, String province, String country, String phoneNumber, String role)
    {
        if (role.equals("AirlineAgent"))
        {
            AirlineAgent newEmployee = new AirlineAgent(firstName, lastName, houseNumber, street, city, province, country, email, age, phoneNumber, password);
            this.airline.getEmployees().add(newEmployee);
            this.airline.getAirlineAgents().add(newEmployee);
            LoginSingleton loginSingleton = LoginSingleton.getOnlyInstance();
            loginSingleton.addEmployee(newEmployee.getEmployeeId(), newEmployee.getPassword());
            employeeLogin(newEmployee.getEmployeeId(), newEmployee.getPassword());
            this.employeeController.setEmployee(newEmployee);
            return;
        }
        else if (role.equals("FlightAttendant"))
        {
            FlightAttendant newEmployee = new FlightAttendant(firstName, lastName, houseNumber, street, city, province, country, email, age, phoneNumber, password);
            this.airline.getEmployees().add(newEmployee);
            this.airline.getFlightAttendants().add(newEmployee);
            LoginSingleton loginSingleton = LoginSingleton.getOnlyInstance();
            loginSingleton.addEmployee(newEmployee.getEmployeeId(), newEmployee.getPassword());
            employeeLogin(newEmployee.getEmployeeId(), newEmployee.getPassword());
            this.employeeController.setEmployee(newEmployee);
            return;
        }
        else if (role.equals("Admin"))
        {
            Admin newEmployee = new Admin(firstName, lastName, houseNumber, street, city, province, country, email, age, phoneNumber, password);
            this.airline.getEmployees().add(newEmployee);
            this.airline.getAdmins().add(newEmployee);
            LoginSingleton loginSingleton = LoginSingleton.getOnlyInstance();
            loginSingleton.addEmployee(newEmployee.getEmployeeId(), newEmployee.getPassword());
            employeeLogin(newEmployee.getEmployeeId(), newEmployee.getPassword());
            this.employeeController.setEmployee(newEmployee);
            return;
        }

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
