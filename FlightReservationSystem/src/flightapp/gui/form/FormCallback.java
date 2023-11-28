package flightapp.gui.form;

public interface FormCallback {
    void onFlightAdded(String flightInfo);
    void onCrewAdded(int crewId, String crewName, int assignedFlightId);

    void onAircraftAdded(String model, int seats);

}
