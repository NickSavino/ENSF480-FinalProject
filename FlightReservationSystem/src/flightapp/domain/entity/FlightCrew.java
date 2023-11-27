package flightapp.domain.entity;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FlightCrew {
    private int flightCrewId;
    private ArrayList<Employee> flightAttendants;

    public FlightCrew(int crewId, ArrayList<Employee> flightAttendants) {
        this.flightCrewId = crewId;
        this.flightAttendants = flightAttendants;
    }

    public int getFlightCrewId() {
        return this.flightCrewId;
    }

    public ArrayList<Employee> getFlightAttendants()
    {
        return this.flightAttendants;
    }
}
