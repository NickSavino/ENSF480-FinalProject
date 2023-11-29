package flightapp.domain.entity;

import flightapp.domain.valueobject.Date;

public class Ticket {

    private Date departure;
    private int flightNumber;
    private int seatNumber;
    private String ticketId;

    public Ticket(Date departure, int flightNumber, int seatNumber, String ticketId) {
        this.departure = departure;
        this.flightNumber = flightNumber;
        this.seatNumber = seatNumber;
        this.ticketId = ticketId;
    }

    public int getFlightNumber()
    {
        return this.flightNumber;
    }

    public int getSeatNumber()
    {
        return this.seatNumber;
    }

    public String getTicketId()
    {
        return this.ticketId;
    }
}
