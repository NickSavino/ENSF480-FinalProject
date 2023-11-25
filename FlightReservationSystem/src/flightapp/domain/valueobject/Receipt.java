package flightapp.domain.valueobject;

public class Receipt {

    private int receiptNumber;
    private int price;

    public Receipt(int receiptNumber, int price) {
        this.receiptNumber = receiptNumber;
        this.price = price;
    }
}
