package flightapp.domain.entity;

import flightapp.domain.valueobject.Date;
import flightapp.domain.valueobject.Receipt;

public class Purchase {

    private Date date;
    private boolean loungeAccess;
    private Ticket ticket;
    private Receipt receipt;

    public void confirmPurchase() {}
}
