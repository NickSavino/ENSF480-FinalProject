package flightapp.domain.entity;

import flightapp.domain.entity.Customer;

public class UnregisteredCustomer extends Customer {

    private String viewLevel = "Guest";


    /*
     * @param firstName
     * @param lastName
     * @param address
     * @param email
     * @param age
     * @param phoneNumber
     * 
     * @param customerId - Will be 0 as a guest
     */
    public UnregisteredCustomer(String firstName, String lastName, String address, String email, int age, String phoneNumber, int customerId) {
        super(firstName, lastName, address, email, age, phoneNumber, customerId);

    }
}
