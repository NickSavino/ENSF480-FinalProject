package flightapp.domain.valueobject;

public class CreditCard {
    private String cardNumber;
    private int securityCode;
    
    public CreditCard(String cardNumber, int securityCode) {
        //card number should have 16 digits
        this.cardNumber = cardNumber;
        this.securityCode = securityCode;
    }

    public String getCreditCardNumber() {
        return this.cardNumber;
    }

    public int getSecurityCode() { return this.securityCode; }
}
