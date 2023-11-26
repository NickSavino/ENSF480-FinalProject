package flightapp.domain.entity;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FlightCrew {

    private ArrayList<Integer> assignedFlights;
    private int crewId;

    public FlightCrew(int crewId) {
        this.crewId = crewId;
        this.assignedFlights = new ArrayList<Integer>();
    }

    public void assignFlight(int flightId) {
        this.assignedFlights.add(flightId);
    }

    public ArrayList<Integer> getAssignedFlights() {
        return this.assignedFlights;
    }

    public int getCrewId() {
        return this.crewId;
    }


}
