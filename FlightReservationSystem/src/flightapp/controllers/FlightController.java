package flightapp.controllers;

import java.util.ArrayList;

import flightapp.domain.entity.Aircraft;
import flightapp.domain.entity.Airline;
import flightapp.domain.entity.Flight;
import flightapp.domain.entity.Seat;
import flightapp.domain.valueobject.AirportLounge;
import flightapp.domain.valueobject.CompanionVoucher;
import flightapp.domain.valueobject.CreditCard;
import flightapp.domain.valueobject.Date;
import flightapp.domain.valueobject.Location;
import flightapp.domain.valueobject.TicketInsurance;

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
        return new ArrayList<Flight>();
    }

    public void selectFlight(int flightId)
    {
        // TODO: Implement
    }

    public ArrayList<Seat> browseSeatMapGraphically()
    {
        // TODO: Implement
        // Return an array of seats belonging to that flight
        return new ArrayList<Seat>();
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
        //sendReceiptAndTicket(Thread ticket, Receipt receipt);
        selectedSeats.clear();
        selectedFlight = null;
        selectedInsurance = null;
        usingCreditCard = null;
        selectedLounge = null;
        selectedVoucher = null;
    }

    //private void sendReceiptAndTicket(Ticket ticket, Receipt receipt)
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
