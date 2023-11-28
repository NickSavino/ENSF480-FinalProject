package flightapp.domain.entity;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FlightCrew {
    private int flightCrewId;
    private int assignflightId;
    private String crewName;
    private ArrayList<Employee> flightAttendants;

    public FlightCrew(int crewId, ArrayList<Employee> flightAttendants) {
        this.flightCrewId = crewId;
        this.flightAttendants = flightAttendants;
    }

    public FlightCrew(int crewId, int flightId, String crewName) {
        this.flightCrewId = crewId;
        this.assignflightId = flightId;
        this.crewName = crewName;
    }

    public int getFlightCrewId() {
        return this.flightCrewId;
    }

    public ArrayList<Employee> getFlightAttendants()
    {
        return this.flightAttendants;
    }

    public String toString() {
        return crewName + "-" + flightCrewId;
    }
}
