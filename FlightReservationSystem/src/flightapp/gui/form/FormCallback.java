package flightapp.gui.form;

public interface FormCallback {
    void onFlightAdded(int aircraftId, String originId, String destinationId, int flightDuration, int flightCrewId,
                       int baseFlightCost, int departureDay, int departureMonth, int departureYear, int departureHour, int departureMinute);
    void onCrewAdded(int crewId, String crewName, int assignedFlightId);

    void onAircraftAdded(int aircraftId, String model, int comfortSeats, int businessSeats, int ordinarySeats);

}
