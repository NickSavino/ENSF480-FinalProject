package flightapp.domain.entity;

import flightapp.domain.valueobject.Date;

public class Ticket {

    private Date departure;
    private Date arrival;

    private int flightNumber;

    private int gate;

    private int seatNumber;

    private int ticketNumber;

    public Ticket(Date departure, Date arrival, int flightNumber, int gate, int seatNumber, int ticketNumber) {
        this.departure = departure;
        this.arrival = arrival;
        this.flightNumber = flightNumber;
        this.gate = gate;
        this.seatNumber = seatNumber;
        this.ticketNumber = ticketNumber;
    }
}
