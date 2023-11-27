package flightapp.domain.entity;

import flightapp.domain.valueobject.*;

public class RegisteredCustomer extends Customer {
    private String password;
    String username;
    private CreditCard creditCard = null;
    private boolean isAirlineMember = false;
    private CompanionVoucher companionVoucher;

    public RegisteredCustomer(String username, String firstName, String lastName, int houseNumber, String street, String city, String province, 
        String country, String email, int age, String password) {
        super(firstName, lastName, houseNumber, street, city, province, country, email);
        this.password = password;
        this.username = username;
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setCreditCard(String cardNumber, int securityCode)
    {
        this.creditCard = new CreditCard(cardNumber, securityCode);
    }

    public String getCustomerUsername()
    {
        return this.username;
    }

    public void becomeAirlineMember()
    {
        this.isAirlineMember = true;
        super.status = "Airline Member";
        this.companionVoucher = new CompanionVoucher();
    }

    public CompanionVoucher getCompanionVoucher()
    {
        return this.companionVoucher;
    }
}
