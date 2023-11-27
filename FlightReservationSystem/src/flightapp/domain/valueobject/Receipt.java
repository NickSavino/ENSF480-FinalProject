package flightapp.domain.valueobject;

public class Receipt {
    private String creditCardNumber;
    private int price;
    private String itemsPurchased;

    public Receipt(String itemsPurchased, int price, String creditCardNumber) {
        this.itemsPurchased = itemsPurchased;
        this.price = price;
        this.creditCardNumber = creditCardNumber;
    }
}
