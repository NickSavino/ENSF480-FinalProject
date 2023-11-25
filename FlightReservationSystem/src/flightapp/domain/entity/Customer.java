package flightapp.domain.entity;

import java.util.ArrayList;

public class Customer extends Person {

    private int customerId;

    private ArrayList<Purchase> purchases;

    
    public Customer(String firstName, String lastName, String address, String email, int age, String phoneNumber, int customerId) {
        super(firstName, lastName, address, email, age, phoneNumber);
        this.customerId = customerId;
        this.purchases = new ArrayList<Purchase>();
    }


}
