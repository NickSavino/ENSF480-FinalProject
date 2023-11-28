package flightapp.domain.entity;

import flightapp.domain.valueobject.*;

public class RegisteredCustomer extends Customer {
    private String password;
    String username;
    private CreditCard creditCard = null;
    private boolean isAirlineMember = false;
    boolean hasCompanyCreditCard = false;
    private CompanionVoucher companionVoucher;

    public RegisteredCustomer(String username, String password, String creditCardNumber,
                              String creditCardSecurityCode, String firstName, String lastName,
                              int houseNumber, String street, String city, String province,
                              String country, String email) {
        super(firstName, lastName, houseNumber, street, city, province, country, email);

        this.creditCard = new CreditCard(creditCardNumber, Integer.parseInt(creditCardSecurityCode));
        this.password = password;
        this.username = username;
        super.status = "Registered";
    }

    public RegisteredCustomer(int customerId, String username, String firstName, String lastName, int houseNumber, String street, String city, String province, 
        String country, String email, String password, String creditCardNumber, int securityCode, String status, boolean hasCompanyCreditCard) {
        super(customerId, firstName, lastName, houseNumber, street, city, province, country, email);
        this.password = password;
        this.username = username;
        super.status = status;
        this.hasCompanyCreditCard = hasCompanyCreditCard;
        this.creditCard = new CreditCard(creditCardNumber, securityCode);
    }

    public RegisteredCustomer(int customerId, String username, String firstName, String lastName, int houseNumber, String street, String city, String province, 
        String country, String email, String password, String status, boolean hasCompanyCreditCard) {
        super(customerId, firstName, lastName, houseNumber, street, city, province, country, email);
        this.password = password;
        this.username = username;
        super.status = status;
        this.hasCompanyCreditCard = hasCompanyCreditCard;
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setCreditCard(String cardNumber, int securityCode)
    {
        this.creditCard = new CreditCard(cardNumber, securityCode);
    }

    public String getUsername()
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

    public String getCreditCardNumber() {
        return creditCard.getCreditCardNumber();
    }

    public int getCreditCardSecurityCode() {
        return creditCard.getSecurityCode();
    }



    public boolean hasCompanyCreditCard()
    {
        return this.hasCompanyCreditCard;
    }
}
