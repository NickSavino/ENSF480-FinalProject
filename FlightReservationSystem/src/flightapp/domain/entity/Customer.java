package flightapp.domain.entity;

import java.util.ArrayList;

public class Customer extends Person {

    private String viewLevel = "Guest";

    private int customerId;
    private String status = "Unregistered";

    private ArrayList<Purchase> purchases;

    
    public Customer(String firstName, String lastName, String address, String email, int age, String phoneNumber, int customerId) {
        super(firstName, lastName, address, email, age, phoneNumber);
        this.customerId = customerId;
        this.purchases = new ArrayList<Purchase>();
    }

    public void addPurchase(Purchase purchase) {
        this.purchases.add(purchase);
    }

    public ArrayList<Purchase> getPurchases() {
        return this.purchases;
    }

    public int getCustomerId() 
    {
        return this.customerId;
    }

    public String getStatus()
    {
        return this.status;
    }

}
