package flightapp.controllers;

import java.util.List;
import java.util.ArrayList;
import flightapp.domain.entity.*;
import flightapp.domain.valueobject.*;
import flightapp.domain.pattern.*;

public class UserAccountController {
    private Airline airline;
    private Customer currentCustomer;
    
    public UserAccountController(Airline airline) {
        this.airline = airline;
    }

    public void login(int customerId, String password)
    {
        // TODO: Implement
        // Need to authenticate via Singleton
    }

    public void signup(String firstName, String lastName, String email, int age, String password, String street, 
        String city, String province, String country, int newCustomerId)
    {
        // TODO: Implement
        // Remember to add all information to the Singleton instance
        // Need to make sure that newCustomerId does not already exist
    {

    public void becomeAirlineMember()
    {
        // TODO: Implement
    }

    public applyForAirlineCreditCard(int newCreditCardNumber, int newSecurityCode, RegisteredCustomer customer)
    {
        // TODO: Implement
    }

}
