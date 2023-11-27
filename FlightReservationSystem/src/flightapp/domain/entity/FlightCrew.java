package flightapp.domain.entity;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FlightCrew {
    private int flightCrewId;
    private ArrayList<FlightAttendant> flightAttendants;

    public FlightCrew(int crewId, ArrayList<FlightAttendant> flightAttendants) {
        this.flightCrewId = crewId;
        this.flightAttendants = flightAttendants;
    }

    public int getFlightCrewId() {
        return this.flightCrewId;
    }


}
