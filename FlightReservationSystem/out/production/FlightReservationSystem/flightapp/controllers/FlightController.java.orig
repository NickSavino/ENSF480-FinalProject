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
    private RegisteredCustomer customer;

    public FlightController(Airline airline) {
        this.airline = airline;
    }

    public void setCustomer(RegisteredCustomer customer)
    {
        this.customer = customer;
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

    public void selectSeats(ArrayList<Integer> seatIds)
    {
        this.selectedSeats.clear();
        for (int seatId : seatIds)
        {
            for (Seat seat : this.selectedFlight.getSeatList())
            {
                if (seat.getSeatId() == seatId && !seat.isBooked())
                {
                    this.selectedSeats.add(seat);
                    seat.book();
                }
            }
        }
    }

    public void purchase(boolean buyInsurance, boolean buyAirportLoungeAccess, boolean useCompanionVoucher, 
        String creditCardNumber, int creditCardSecurityCode)
    {  
        // REQUIRES: Purchase parameters
        // RETURNS: Total cost of the purchase
        CreditCard creditCard = new CreditCard(creditCardNumber, creditCardSecurityCode);

        // Mark companion voucher as used or don't use it at all if unavailable
        if (useCompanionVoucher && this.selectedSeats.size() > 1)
        {
            for (RegisteredCustomer member : this.airline.getRegisteredCustomers())
            {
                if (member.getCustomerId() == this.customer.getCustomerId())
                {
                    if (member.getCompanionVoucher().isUsable())
                    {
                        member.getCompanionVoucher().use();
                        break;
                    }
                    else
                    {
                        useCompanionVoucher = false;
                    }
                }
            }
        }
        if (this.selectedSeats.size() < 2)
        {
            useCompanionVoucher = false;
        }

        Purchase currentPurchase = new Purchase(this.selectedFlight, buyInsurance, buyAirportLoungeAccess, useCompanionVoucher, creditCard, this.selectedSeats, this.customer);
        this.airline.getPurchases().add(currentPurchase);
        sendReceiptAndTicket(currentPurchase.getTickets(), currentPurchase.getReceipt());
        selectedSeats.clear();
        this.customer.addPurchase(currentPurchase);
        // TODO: Need to update database
        
    }

    private void sendReceiptAndTicket(ArrayList<Ticket> ticket, Receipt receipt)
    {
        // TODO: Implement
        // Need to populate the proper information inside of a new Receipt and Payment
        return;
    }

    public void refundPurchase(String purchaseId)
    {
        Purchase currentPurchase = null;
        for (Purchase purchase : this.airline.getPurchases())
        {
            if (purchase.getPurchaseId() == purchaseId)
            {
                currentPurchase = purchase;
                break;
            }
        }
        if (currentPurchase != null)
        {
            for (Ticket ticket : currentPurchase.getTickets())
            {
                int flightId = ticket.getFlightNumber();
                for (Flight flight : this.airline.getFlights())
                {
                    if (flight.getFlightId() == flightId)
                    {
                        this.selectedFlight = flight;
                        break;
                    }
                }
                for (Seat seat : this.selectedFlight.getSeatList())
                {
                    seat.unbook();
                }
            }
        }
        // Need to find purchase and refund; update database
    }

    public String sendPromotionalNews()
    {
        String promotionalNews = "New flights to Hawaii! Find your new vacation destination today for cheap! Variety of options provided and high-class flying!";
        return promotionalNews;
    }
}
