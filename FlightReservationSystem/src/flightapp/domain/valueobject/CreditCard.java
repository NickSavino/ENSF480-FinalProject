package flightapp.domain.valueobject;

public class CreditCard {
    private String cardNumber;
    private int securityCode;
    
    public CreditCard(String cardNumber, int securityCode) {
        //card number should have 16 digits
        if (String.valueOf(cardNumber).length() != 16) 
        {
            throw new IllegalArgumentException("Invalid card number");
        } 
        else 
        {
            this.cardNumber = cardNumber;
        }
        this.securityCode = securityCode;
    }

    public String getCreditCardNumber() {
        return this.cardNumber;
    }
}
