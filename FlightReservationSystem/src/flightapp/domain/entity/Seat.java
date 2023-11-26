package flightapp.domain.entity;

import java.util.Map;

public class Seat {

    private int seatId;
    private boolean isBooked;
    private String seatType;

    public Seat(int seatId, String seatType) {
        this.seatId = seatId;
        this.isBooked = false;
        this.seatType = seatType;
    }

    public void book() {}

    public void unbook() {}
}
