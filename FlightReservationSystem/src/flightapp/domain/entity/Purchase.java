package flightapp.domain.entity;

import flightapp.domain.valueobject.Date;
import flightapp.domain.valueobject.Receipt;

public class Purchase {

    private Date date;
    private boolean loungeAccess;
    private Ticket ticket;
    private Receipt receipt;

    public Purchase(Date date, boolean loungeAccess, Ticket ticket, Receipt receipt) {
        this.date = date;
        this.loungeAccess = loungeAccess;
        this.ticket = ticket;
        this.receipt = receipt;
    }

    public Date getDate() {
        return date;
    }


    public void confirmPurchase() {}

    public void cancelPurchase() {}
}
