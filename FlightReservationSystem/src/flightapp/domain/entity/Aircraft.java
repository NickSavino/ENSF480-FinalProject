package flightapp.domain.entity;

public class Aircraft {

    private String model;
    private int aircraftId;
    private int amountOfOrdinarySeats;
    private int amountOfBusinessSeats;
    private int amountOfComfortSeats;
    private int amountOfSeats;

    public Aircraft(String model, int id, int amountOfOrdinarySeats,
        int amountOfBusinessSeats, int amountOfComfortSeats) {

        this.model = model;
        this.aircraftId = id;
        this.amountOfOrdinarySeats = amountOfOrdinarySeats;
        this.amountOfBusinessSeats = amountOfBusinessSeats;
        this.amountOfComfortSeats = amountOfComfortSeats;
        this.amountOfSeats = amountOfOrdinarySeats + amountOfBusinessSeats + amountOfComfortSeats;
    }

    public Aircraft(int id, String model, int amountOfOrdinarySeats,
        int amountOfBusinessSeats, int amountOfComfortSeats) {

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
}
