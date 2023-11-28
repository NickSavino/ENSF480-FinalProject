package flightapp.gui.form;

public interface FormCallback {
    void onFlightAdded(String flightInfo);
    void onCrewAdded(String crewName, String assignedFlight);

    void onAircraftAdded(String model, int seats);

}
