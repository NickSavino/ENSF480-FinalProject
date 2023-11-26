package flightapp.domain.entity;

import flightapp.domain.pattern.BrowseAll;
import flightapp.domain.pattern.BrowsePerFlight;

public class FlightAttendant extends NonAdmin {

    private String viewLevel = "FlightAttendant";

    private int assignedFlight;

    public FlightAttendant(String firstName, String lastName, String address, String email, int age, String phoneNumber, int employeeId, int assignedFlight) {
        super(firstName, lastName, address, email, age, phoneNumber, employeeId);
        this.assignedFlight = assignedFlight;
        setBrowseStrategy(new BrowsePerFlight());
    }

    public int getAssignedFlight() {
        return assignedFlight;
    }

    public void setAssignedFlight(int assignedFlight) {
        this.assignedFlight = assignedFlight;
    }
}
