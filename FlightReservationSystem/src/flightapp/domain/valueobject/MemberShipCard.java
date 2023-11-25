package flightapp.domain.valueobject;

public class MemberShipCard {

    private String cardNumber;

    /*
     * Constructor for MemberShipCard
     * @param cardNumber
     * @throws IllegalArgumentException if card number is not 16 digits
     */
    public MemberShipCard(String cardNumber) {
        //card number should have 16 digits
        if (String.valueOf(cardNumber).length() != 16) {
            throw new IllegalArgumentException("Invalid card number");
        } else {
            this.cardNumber = cardNumber;
        }
    }
}
