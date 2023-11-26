package flightapp.domain.entity;

import java.util.Map;

public class Seat {

    private String seatId;

    private boolean isBooked;

    private Map<String, Integer> seatType;

    public Seat(String seatId, boolean isBooked, Map<String, Integer> seatType) {
        this.seatId = seatId;
        this.isBooked = isBooked;
        this.seatType = seatType;
    }

    public void book() {}

    public void unbook() {}
}
