package flightapp.domain;

import java.util.ArrayList;

public class Flight {

    private Aircraft aircraft;
    private int flightId;
    private ArrayList<Seat> seatMap;
    private Location origin;
    private Location destination;

    private FlightCrew flightCrew;

    private Date date;


}
