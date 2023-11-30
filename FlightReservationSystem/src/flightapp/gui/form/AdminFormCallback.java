package flightapp.gui.form;

public interface AdminFormCallback {
    void onFlightAdded(int aircraftId, String originId, String destinationId, int flightDuration, int flightCrewId,
                       int baseFlightCost, int departureDay, int departureMonth, int departureYear, int departureHour, int departureMinute);
    void onCrewAdded(int crewId, String crewName, int assignedFlightId);

    void onAircraftAdded(int aircraftId, String model, int comfortSeats, int businessSeats, int ordinarySeats);

    void onFlightModified(int selectedIndex, int flightId, int aircraftId, String originId, String destinationId, int flightDuration, int flightCrewId, int baseFlightCost,
                          int day, int month, int year, int hour, int minute);
}
