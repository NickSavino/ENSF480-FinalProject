package flightapp.domain.valueobject;

public class CreditCard {
    private int cardNumber;

    /*
     * Constructor for CreditCard
     * @param cardNumber
     * @throws IllegalArgumentException if card number is not 16 digits
     */
    public CreditCard(int cardNumber) {
        //card number should have 16 digits
        if (String.valueOf(cardNumber).length() != 16) {
            throw new IllegalArgumentException("Invalid card number");
        } else {
            this.cardNumber = cardNumber;
        }
    }
}
