package flightapp.domain.valueobject;

public class Receipt {
    private String creditCardNumber;
    private int price;
    private String receiptId;

    public Receipt(String receiptId, int price, String creditCardNumber) {
        this.receiptId = receiptId;
        this.price = price;
        this.creditCardNumber = creditCardNumber;
    }

    public String getReceiptId()
    {
        return this.receiptId;
    }

    public int getPrice()
    {
        return this.price;
    }
    
    public String getCreditCardNumber()
    {
        return this.creditCardNumber;
    }
}
