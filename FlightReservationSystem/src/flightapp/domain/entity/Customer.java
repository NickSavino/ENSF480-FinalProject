package flightapp.domain.entity;

import java.util.UUID;
import java.util.ArrayList;

public class Customer extends Person {

    private String viewLevel = "Guest";

    private int customerId;
    private String status = "Unregistered";

    private ArrayList<Purchase> purchases;

    
    public Customer(String firstName, String lastName, int houseNumber, String street, String city, 
        String province, String country, String email, int age, String phoneNumber)
    {
        super(firstName, lastName, houseNumber, street, city, province, country, email, age, phoneNumber);
        this.customerId = UUID.randomUUID().hashCode();
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
