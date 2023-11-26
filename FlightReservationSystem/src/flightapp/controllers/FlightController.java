package flightapp.controllers;

import java.util.List;
import java.util.ArrayList;
import flightapp.domain.entity.*;
import flightapp.domain.valueobject.*;
import flightapp.domain.pattern.*;

public class FlightController {
    private Airline airline;
    
    private Flight selectedFlight;
    private ArrayList<Seat> selectedSeats;
    private TicketInsurance selectedInsurance = null; 
    private CreditCard usingCreditCard = null;
    private AirportLounge selectedLounge = null;
    private CompanionVoucher selectedVoucher = null;

    public FlightController(Airline airline) {
        this.airline = airline;
    }

    public ArrayList<Flight> browseFlights(Location destination)
    {
        // TODO: Implement
    }

    public void selectFlight(int flightId)
    {
        // TODO: Implement
    }

    public ArrayList<Seat> browseSeatMapGraphically()
    {
        // TODO: Implement
        // Return an array of seats belonging to that flight
    }

    public void selectSeats(ArrayList<Seat> seats)
    {
        this.selectedSeats = seats;
    }

    public void selectInsurance (Date cancelByDate)
    {
        // TODO: Implement
        // Need to make a new TicketInsurance object (add more valuable attributes to this class) and populate class information

    }

    public void setCreditCard(String cardNumber, int securityCode)
    {
        // TODO: Implement
        // Need to make a new credit card object and populate it within this class
    }

    public void setAirportLounge(int chooseLounge)
    {
        // TODO: Implement
    }

    public void setCompanionVoucher(int chooseVoucher)
    {
        // TODO: Implement
    }

    public void purchase()
    {
        // TODO: Implement
        // Need to calculate the total cost (baseCost + seatCost)
        // Need to create new ticket and receipt objects
        // Need to create a new purchase object and send it to the database
        sendReceiptAndTicket(Ticket ticket, Receipt receipt);
        selectedSeats.clear();
        selectedFlight = null;
        selectedInsurance = null;
        usingCreditCard = null;
        selectedLounge = null;
        selectedVoucher = null;
    }

    private void sendReceiptAndTicket(Ticket ticket, Receipt receipt)
    {
        // TODO: Implement
        // Need to populate the proper information inside of a new Receipt and Payment 
    }

    public void refundPurchase(int purchaseId)
    {
        // TODO: Implement
        // Need to find purchase and refund; update database
    }
    
    // This method is for airline agents and flight attendants
    public void browsePassengers(int flightId)
    {
        // TODO: Implement
        // Make sure to make use of design pattern inside of domain model for this
    }

    public void sendMonthlyNews()
    {
        // TODO: Implement
    }
}
