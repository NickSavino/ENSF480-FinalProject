package flightapp.domain.entity;

import flightapp.domain.valueobject.*;
import java.util.Random;

import java.util.UUID;

public class RegisteredCustomer extends Customer {
    private String password;
    String username;
    private CreditCard creditCard = null;
    private boolean isAirlineMember = false;
    boolean hasCompanyCreditCard = false;
    private CompanionVoucher companionVoucher;

    public RegisteredCustomer() {
        super("Guest", "Guest", 0, "null", "null", "null", "null", "email");
        super.status = "Guest";
    }
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

    public String grantCompanyCreditCard()
    {
        this.hasCompanyCreditCard = true;
        String cardNumber = "";
        String securityCode = "";

        Random random = new Random();
        StringBuilder creditCardNumber = new StringBuilder();
        StringBuilder creditCardSecurityNumber = new StringBuilder();
        for (int i = 0; i < 16; i++)
        {
            int value = random.nextInt(10);
            creditCardNumber.append(value);
        }
        for (int i = 0; i < 3; i++)
        {
            int value = random.nextInt(10);
            creditCardSecurityNumber.append(value);
        }
        cardNumber = creditCardNumber.toString();
        securityCode = creditCardSecurityNumber.toString();
        this.creditCard = new CreditCard(cardNumber, Integer.parseInt(securityCode));
        return cardNumber + " " + securityCode;
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

    public String getCustomerUsername()
    {
        return this.username;
    }

    public boolean hasCompanyCreditCard()
    {
        return this.hasCompanyCreditCard;
    }

    public boolean isAirlineMember()
    {
        return this.isAirlineMember;
    }

    public String toString() {
        return username + " - " + getEmail() + " - " + getName().getFirstName() + " " + getName().getLastName();
    }
}
