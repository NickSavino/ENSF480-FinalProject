package flightapp.domain.entity;

import flightapp.domain.valueobject.Location;
import flightapp.domain.valueobject.Date;

import java.util.ArrayList;

public class Flight {

    private Aircraft aircraft;
    private int flightId;
    private ArrayList<Seat> seatList;
    private Location origin;
    private Location destination;
    private int flightDuration;
    private FlightCrew flightCrew;

    private Date date;

    public Flight(Aircraft aircraft, int flightId, Location origin, 
        Location destination, FlightCrew flightCrew, Date date, int flightDuration) 
    {
        this.aircraft = aircraft;
        this.flightId = flightId;
        this.flightDuration = flightDuration;
        this.origin = origin;
        this.destination = destination;
        this.flightCrew = flightCrew;
        this.date = date;

        int numberOfSeats = aircraft.getNumberOfSeats();
        int numberOfOrdinarySeats = aircraft.getNumberOfOrdinarySeats();
        int numberOfBusinessSeats = aircraft.getNumberOfBusinessSeats();
        int numberOfComfortSeats = aircraft.getNumberOfComfortSeats();

        for (int i = 0; i < numberOfSeats; i++)
        {
            if (i < numberOfBusinessSeats)
            {
                this.seatList.add(new Seat(i + 1, "Business"));
            }
            else if (i < numberOfBusinessSeats + numberOfComfortSeats)
            {
                this.seatList.add(new Seat(i + 1, "Comfort"));
            }
            else
            {
                this.seatList.add(new Seat(i + 1, "Ordinary"));
            }
        }

    }

    public Location getOrigin() 
    {
        return this.origin;
    }

    public Location getDestination() 
    {
        return this.destination;
    }

    public Date getDate() 
    {
        return this.date;
    }

    public FlightCrew getFlightCrew()
    {
        return this.flightCrew;
    }

    public int getFlightId()
    {
        return this.flightId;
    }

    public void setFlightCrew(FlightCrew newCrew)
    {
        this.flightCrew = newCrew;
    }

    public void setDate(Date newDate)
    {
        this.date = newDate;
    }

    public void setDuration(int newDuration)
    {
        this.flightDuration = newDuration;
    }

    public Aircraft getAircraft()
    {
        return this.aircraft;
    }

    public ArrayList<Seat> getSeatList()
    {
        return this.seatList;
    }

}
