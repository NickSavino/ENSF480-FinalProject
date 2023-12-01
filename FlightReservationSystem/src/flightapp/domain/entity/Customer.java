package flightapp.domain.entity;

import java.util.UUID;
import java.util.ArrayList;

public class Customer extends Person {
    private int customerId;
    protected String status = "Unregistered"; // Other statuses are: "Registered", "Airline Member
    private ArrayList<Purchase> purchases;

    public Customer(String firstName, String lastName, int houseNumber, String street, String city, 
        String province, String country, String email)
    {
        super(firstName, lastName, houseNumber, street, city, province, country, email);
        this.customerId = UUID.randomUUID().hashCode();
        this.purchases = new ArrayList<Purchase>();
    }

    public Customer(int customerId, String firstName, String lastName, int houseNumber, String street, String city, 
        String province, String country, String email)
    {
        super(firstName, lastName, houseNumber, street, city, province, country, email);
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

    public void removePurchase(Purchase purchase) {
        purchases.remove(purchase);
    }
}
