package flightapp.controllers;

import java.util.List;
import java.util.ArrayList;
import flightapp.domain.entity.*;
import flightapp.domain.valueobject.*;
import flightapp.domain.pattern.*;

public class FlightController {
    private Airline airline;
    
    private Flight selectedFlight = null;
    private ArrayList<Seat> selectedSeats;
    private boolean selectInsurance = false; 
    private CreditCard usingCreditCard = null;
    private boolean selectLounge = false;
    private CompanionVoucher selectedVoucher = null;

    public FlightController(Airline airline) {
        this.airline = airline;
    }

    public ArrayList<Flight> browseFlights(Location destination)
    {
        ArrayList<Flight> flightsToDestination = new ArrayList<Flight>();
        for (Flight flight : this.airline.getFlights())
        {
            if (flight.getDestination() == destination)
            {
                flightsToDestination.add(flight);
            }
        }
        return flightsToDestination;
    }

    public void selectFlight(int flightId)
    {
        for (Flight flight : this.airline.getFlights())
        {
            if (flight.getFlightId() == flightId)
            {
                this.selectedFlight = flight;
                return;
            }
        }
    }

    public ArrayList<ArrayList<Seat>> browseSeatMapGraphically()
    {
        ArrayList<ArrayList<Seat>> seatMap = new ArrayList<ArrayList<Seat>>();
        
        int rowCounter = 0;
        int seatInRowCounter = 0;
        for (int i = 0; i < this.selectedFlight.getAircraft().getNumberOfSeats(); i++)
        {
            if (seatInRowCounter == 6)
            {
                rowCounter++;
                seatInRowCounter = 1;
            }
            // Add Seat object at seatMap[rowCounter][seatInRowCounter]
            seatMap.get(rowCounter).add(this.selectedFlight.getSeatList().get(i));
            seatInRowCounter++;
        }
        return seatMap;
    }

    public void selectSeats(ArrayList<Seat> seats)
    {
        this.selectedSeats = seats;
    }

    public void selectInsurance(Date cancelByDate)
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

    public void sendPromotionalNews()
    {
        // TODO: Implement
    }
}
