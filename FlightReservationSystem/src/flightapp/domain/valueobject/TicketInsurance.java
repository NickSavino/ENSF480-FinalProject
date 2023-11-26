package flightapp.domain.valueobject;

import flightapp.domain.valueobject.Date;

public class TicketInsurance {

    private Date cancelByDate;

    public TicketInsurance(Date cancelByDate) {
        this.cancelByDate = cancelByDate;
    }

    public Date getCancelByDate() {
        return cancelByDate;
    }
}
