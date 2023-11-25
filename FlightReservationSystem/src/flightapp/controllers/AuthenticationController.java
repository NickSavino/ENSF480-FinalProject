package flightapp.controllers;

import java.util.List;

import javax.naming.AuthenticationNotSupportedException;

import java.util.ArrayList;
import flightapp.domain.*;

public class AuthenticationController {
    private Airline airline;
    
    public AuthenticationController(Airline airline) {
        this.airline = airline;
    }
}
