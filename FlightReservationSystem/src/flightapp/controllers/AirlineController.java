package flightapp.controllers;

import java.util.List;
import java.util.ArrayList;
import flightapp.domain.*;


public class AirlineController {
    private AdminController adminController;
    private AuthenticationController authenticationController;
    private FlightController flightController;
    private Airline airline;

    public AirlineController() {
        airline = new Airline();
        initializeDataOnStartup(airline);
        adminController = new AdminController(airline);
        authenticationController = new AuthenticationController(airline);
        flightController = new FlightController(airline);
    }

    private void initializeDataOnStartup(Airline airline)
    {
        // TODO: Need to pull all data from the database to populate the airline object
    }

    // TODO: Need to direct functionality into the proper controllers from here

    
}
