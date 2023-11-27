package flightapp.domain.entity;

import flightapp.domain.valueobject.*;

public class RegisteredCustomer extends Customer {

    private String viewLevel = "User";
    private String password;
    private CreditCard creditCard = null;
    private boolean isAirlineMember = false;
    private CompanionVoucher companionVoucher;

    public RegisteredCustomer(String firstName, String lastName, int houseNumber, String street, String city, String province, 
        String country, String email, int age, String phoneNumber, String password) {
        super(firstName, lastName, houseNumber, street, city, province, country, email, age, phoneNumber);
        this.password = password;
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setCreditCard(String cardNumber, int securityCode)
    {
        this.creditCard = new CreditCard(cardNumber, securityCode);
    }

    public void becomeAirlineMember()
    {
        this.isAirlineMember = true;
        this.companionVoucher = new CompanionVoucher();
    }

    public CompanionVoucher getCompanionVoucher()
    {
        return this.companionVoucher;
    }
}
