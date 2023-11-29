package flightapp.domain.entity;

import java.util.Map;

public class Seat {

    private int seatId;
    private boolean isBooked;
    private String seatType; // "Business", "Comfort", "Ordinary

    public Seat(int seatId, String seatType) {
        this.seatId = seatId;
        this.isBooked = false;
        this.seatType = seatType;
    }

    public void book()
    {
        this.isBooked = true;
    }

    public void unbook()
    {
        this.isBooked = false;
    }

    public void setBooked(boolean isBooked)
    {
        this.isBooked = isBooked;
    }

    public int getSeatId()
    {
        return this.seatId;
    }

    public String getSeatType()
    {
        return this.seatType;
    }

    public void setSeatType(String seatType)
    {
        this.seatType = seatType;
    }

    public boolean isBooked()
    {
        return this.isBooked;
    }
}
