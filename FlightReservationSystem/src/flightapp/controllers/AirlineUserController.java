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
            // populatePurchases();
            populateRegisteredCustomers();
            for (RegisteredCustomer customer : airline.getRegisteredCustomers())
            {
                System.out.println(customer.hasCompanyCreditCard());
                System.out.println(customer.getCustomerId());
                System.out.println(customer.getCustomerUsername());
                System.out.println(customer.getStatus());
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

    private void populatePurchases() throws SQLException // Liam
    {
        // Need to initialize Ticket object before populating Purchase object
        // Need to initialize Receipt object before populating Purchase object
        // Need to initialize CreditCard object before populating Purchase object
        // Need to make sure we're adding purchases to the corresponding Customer object
        

        // Need to initialize Flight object before populating Purchase object
        // ArrayList<Seat> seats = new ArrayList<Seat>();
        // ResultSet rsFlightSeats = DatabaseController.queryFlightSeats();
        // while (rsFlightSeats.next())
        // {

        // }


        // ResultSet rs = DatabaseController.queryPurchases();
        
        // while (rs.next())
        // {
        //     String purchaseId = rs.getString("purchaseId");
        //     boolean loungeAccess = rs.getBoolean("loungeAccess");
        //     String creditCardNumber = rs.getString("creditCardNumber");
        //     int creditCardSecurityCode = rs.getInt("creditCardSecurityCode");
        //     int totalPurchaseCost = rs.getInt("totalPurchaseCost");
        //     boolean ticketInsurance = rs.getBoolean("ticketInsurance");
        //     String itemsPurchased = rs.getString("itemsPurchased");
        //     String ticketId = rs.getString("ticketId");
        //     int customerId = rs.getInt("customerId");
        //     int flightId = rs.getInt("flightId");
            
        //     Flight currentFlight = null;
        //     for (Flight flight : airline.getFlights())
        //     {
        //         if (flight.getFlightId() == (flightId))
        //         {
        //             currentFlight = flight;
        //             break;
        //         }
        //     }

        // }

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
