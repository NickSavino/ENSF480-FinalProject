package flightapp.domain.entity;

import flightapp.domain.pattern.*;
public class FlightAttendant extends NonAdmin {

    private String viewLevel = "FlightAttendant";

    private int assignedFlightCrew;

    public FlightAttendant(String firstName, String lastName, String address, String email, int age, String phoneNumber, int employeeId, int assignedFlightCrew) {
        super(firstName, lastName, address, email, age, phoneNumber, employeeId);
        this.assignedFlightCrew = assignedFlightCrew;
        setBrowseStrategy(new BrowsePerFlight());
    }
}
