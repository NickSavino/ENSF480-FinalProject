package flightapp.domain.entity;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FlightCrew {
    private int flightCrewId;
    private int assignflightId;
    private String crewName;

    public FlightCrew(int crewId, int flightId, String crewName) {
        this.flightCrewId = crewId;
        this.assignflightId = flightId;
        this.crewName = crewName;
    }

    public int getFlightCrewId() {
        return this.flightCrewId;
    }

    public String getCrewName() {
        return this.crewName;
    }

    public int getAssignFlightId()
    {
        return this.assignflightId;
    }

    public String toString() {
        return crewName + " - " + flightCrewId;
    }
}
