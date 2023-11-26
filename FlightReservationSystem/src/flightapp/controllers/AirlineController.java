package flightapp.controllers;

import java.util.List;
import java.util.ArrayList;
import flightapp.domain.entity.*;
import flightapp.domain.valueobject.*;
import flightapp.domain.pattern.*;


public class AirlineController {
    private AdminController adminController;
    private UserAccountController userAccountController;
    private FlightController flightController;
    private Airline airline;

    public AirlineController() {
        // Initialize Airline object with database values
        airline = new Airline();
        initializeDataOnStartup(airline);

        // Initialize controllers
        adminController = new AdminController(airline);
        userAccountController = new UserAccountController(airline);
        flightController = new FlightController(airline);
    }

    private void initializeDataOnStartup(Airline airline)
    {
        // TODO: Need to pull all data from the database to populate the airline object

        // Need to popui
    }

    // TODO: Need to direct functionality into the proper controllers from here

    
}
