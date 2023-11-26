package flightapp.domain.valueobject;

public class Receipt {

    private String receiptId;
    private String creditCardNumber;
    private int price;

    public Receipt(String receiptId, int price, String creditCardNumber) {
        this.receiptId = receiptId;
        this.price = price;
        this.creditCardNumber = creditCardNumber;
    }
}
