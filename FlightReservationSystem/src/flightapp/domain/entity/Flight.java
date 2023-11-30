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
    private int baseFlightCost;
    private ArrayList<Customer> passengers;
    private Date departureTime;

    public Flight(Aircraft aircraft, int flightId, Location origin,
        Location destination, int baseFlightCost, FlightCrew flightCrew, Date date, int flightDuration)
    {
        this.aircraft = aircraft;
        this.flightId = flightId;
        this.flightDuration = flightDuration;
        this.origin = origin;
        this.destination = destination;
        this.flightCrew = flightCrew;
        this.departureTime = date;
        this.baseFlightCost = baseFlightCost;
        this.passengers = new ArrayList<Customer>();
        this.seatList = new ArrayList<>();

        int numberOfSeats = aircraft.getNumberOfSeats();
        int numberOfBusinessSeats = aircraft.getNumberOfBusinessSeats();
        int numberOfComfortSeats = aircraft.getNumberOfComfortSeats();

        for (int i = 1; i <= numberOfSeats; i++) {
            if (i < numberOfComfortSeats) {
                seatList.add(new Seat(i, "Comfort"));
            } else if (i < numberOfComfortSeats + numberOfBusinessSeats) {
                seatList.add(new Seat(i, "Business"));
            } else {
                seatList.add(new Seat(i, "Ordinary"));
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
        return this.departureTime;
    }

    public int getFlightId()
    {
        return this.flightId;
    }

    public void setFlightCrew(FlightCrew newCrew)
    {
        this.flightCrew = newCrew;
    }

    public FlightCrew getFlightCrew()
    {
        return this.flightCrew;
    }

    public void setDate(Date newDate)
    {
        this.departureTime = newDate;
    }

    public void setDuration(int newDuration)
    {
        this.flightDuration = newDuration;
    }

    public int getDuration()
    {
        return this.flightDuration;
    }

    public Aircraft getAircraft()
    {
        return this.aircraft;
    }

    public ArrayList<Seat> getSeatList()
    {
        return this.seatList;
    }

    public int getBaseFlightCost()
    {
        return this.baseFlightCost;
    }

    public ArrayList<Customer> getPassengers()
    {
        return this.passengers;
    }

    public void addPassenger(Customer passenger)
    {
        this.passengers.add(passenger);
    }

    public void setAircraft(Aircraft aircraft)
    {
        this.aircraft = aircraft;
    }

    public void setDestination(Location destination)
    {
        this.destination = destination;
    }

    public void setOrigin(Location origin)
    {
        this.origin = origin;
    }

    public Seat getSeat(int seatId) {
        for (Seat seat : seatList) {
            if (seat.getSeatId() == seatId) {
                return seat;
            }
        }
        return null;
    }

    public void modifySeat(int seatId, String seatType, boolean isBooked) {

        Seat seat = getSeat(seatId);

        if (seat != null) {
            seat.setSeatType(seatType);
            seat.setBooked(isBooked);
        }

    }
    public String toString() {
        String flightString = String.format("%s to %s | Departure: %d/%d/%d %02d:%02d - %d", origin.getLocationId(), destination.getLocationId(),
                departureTime.getDay(), departureTime.getMonth(), departureTime.getYear(), departureTime.getHour(), departureTime.getMinutes(), flightId);
        return flightString;
    }
}
