package flightapp.controllers;

import java.util.List;
import java.util.ArrayList;
import flightapp.domain.*;

public class FlightController {
    private Airline airline;

    public FlightController(Airline airline) {
        this.airline = airline;
    }
}
