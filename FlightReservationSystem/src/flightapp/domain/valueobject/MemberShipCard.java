package flightapp.domain.valueobject;

public class MemberShipCard {

    private String cardNumber;
    private int securityCode;

    public MemberShipCard(String cardNumber, int securityCode) {
        this.cardNumber = cardNumber;
        this.securityCode = securityCode;
    }
}
