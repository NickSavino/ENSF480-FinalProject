package flightapp.domain.entity;

import java.util.ArrayList;

public class Aircraft {

    private String model;
    private int aircraftId;
    private int amountOfOrdinarySeats;
    private int amountOfBusinessSeats;
    private int amountOfComfortSeats;
    private int amountOfSeats;
    private ArrayList<Seat> seats;

    public Aircraft(String model, int id, int amountOfOrdinarySeats,
        int amountOfBusinessSeats, int amountOfComfortSeats) {

        this.model = model;
        this.aircraftId = id;
        this.amountOfOrdinarySeats = amountOfOrdinarySeats;
        this.amountOfBusinessSeats = amountOfBusinessSeats;
        this.amountOfComfortSeats = amountOfComfortSeats;
        this.amountOfSeats = amountOfOrdinarySeats + amountOfBusinessSeats + amountOfComfortSeats;

        seats = new ArrayList<>();
        for (int i = 0; i < amountOfSeats; i++) {
            if (i < amountOfComfortSeats) {
                seats.add(new Seat(i, "Comfort"));
            } else if (i < amountOfComfortSeats + amountOfBusinessSeats) {
                seats.add(new Seat(i, "Business"));
            } else {
                seats.add(new Seat(i, "Ordinary"));
            }
        }
    }

    public Aircraft(int id, String model, int amountOfOrdinarySeats,
        int amountOfBusinessSeats, int amountOfComfortSeats, int amountOfSeats) {

        this.model = model;
        this.aircraftId = id;
        this.amountOfOrdinarySeats = amountOfOrdinarySeats;
        this.amountOfBusinessSeats = amountOfBusinessSeats;
        this.amountOfComfortSeats = amountOfComfortSeats;
        this.amountOfSeats = amountOfOrdinarySeats + amountOfBusinessSeats + amountOfComfortSeats;
    }

    public String getModel() {
        return this.model;
    }

    public int getAircraftId() 
    {
        return this.aircraftId;
    }

    public int getNumberOfSeats() 
    {
        return this.amountOfSeats;
    }

    public int getNumberOfOrdinarySeats() 
    {
        return this.amountOfOrdinarySeats;
    }
    
    public int getNumberOfBusinessSeats() 
    {
        return this.amountOfBusinessSeats;
    }

    public int getNumberOfComfortSeats() 
    {
        return this.amountOfComfortSeats;
    }

    public String toString() {
        return model + " - " + aircraftId;
    }
}
