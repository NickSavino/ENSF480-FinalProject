package flightapp.controllers;

import java.util.List;
import java.util.ArrayList;
import flightapp.domain.entity.*;
import flightapp.domain.valueobject.*;
import flightapp.domain.pattern.*;

public class UserAccountController {
    private Airline airline;
    private Customer currentCustomer;
    private Employee currentEmployee;
    private boolean isCustomerLoggedIn = false;
    private boolean isEmployeeLoggedIn = false;
    
    public UserAccountController(Airline airline) {
        this.airline = airline;
    }

    public boolean customerLogin(int userId, String password)
    {
        LoginSingleton loginSingleton = LoginSingleton.getOnlyInstance();
        boolean validCustomer = loginSingleton.authenticateCustomer(userId, password);

        if (validCustomer)
        {
            ArrayList<Customer> customers = this.airline.getCustomers();
            for (Customer customer : customers)
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
            return;
        }

        // TODO: Still need to add to database
    }

    public void becomeAirlineMember()
    {
        RegisteredCustomer currentRegCustomer = null;
        for (Customer customer : this.airline.getCustomers())
        {
            if (customer.getCustomerId() == this.currentCustomer.getCustomerId())
            {
                currentRegCustomer = (RegisteredCustomer) customer;
                break;
            }
        }
        if (currentRegCustomer != null)
        {
            currentRegCustomer.becomeAirlineMember();
        }
    }

    public void applyForAirlineCreditCard(String newCreditCardNumber, int newSecurityCode, RegisteredCustomer customer)
    {
        customer.setCreditCard(newCreditCardNumber, newSecurityCode);
    }

}
