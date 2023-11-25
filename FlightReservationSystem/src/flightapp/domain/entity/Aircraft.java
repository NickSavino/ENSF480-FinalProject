package flightapp.domain.entity;

public class Aircraft {

    private String model;
    private boolean isFlying;
    private int aircraftId;
    private String condition;

    Aircraft(String model, int id, String condition) {

        this.model = model;
        this.isFlying = false;
        this.aircraftId = id;
        this.condition = condition;
    }
}
