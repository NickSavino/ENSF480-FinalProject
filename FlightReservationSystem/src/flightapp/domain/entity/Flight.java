package flightapp.domain.entity;

import flightapp.domain.valueobject.Location;
import flightapp.domain.valueobject.Date;

import java.util.ArrayList;

public class Flight {

    private Aircraft aircraft;
    private int flightId;
    private ArrayList<Seat> seatMap;
    private Location origin;
    private Location destination;

    private FlightCrew flightCrew;

    private Date date;

    public Flight(Aircraft aircraft, int flightId, ArrayList<Seat> seatMap, Location origin, Location destination, FlightCrew flightCrew, Date date) {
        this.aircraft = aircraft;
        this.flightId = flightId;
        this.seatMap = seatMap;
        this.origin = origin;
        this.destination = destination;
        this.flightCrew = flightCrew;
        this.date = date;
    }


}
