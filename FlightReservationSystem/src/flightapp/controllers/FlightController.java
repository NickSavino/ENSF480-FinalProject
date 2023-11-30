package flightapp.controllers;

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
    
    public void selectFlight(int flightId) // Selecting flight and saving it locally as a variable in this class
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
            if (seatInRowCounter == 6) // Making rows of 6 (can be customized later)
            {
                rowCounter++;
                seatInRowCounter = 1;
            }
            // Add Seat object at seatMap[rowCounter][seatInRowCounter]
            seatMap.get(rowCounter).add(this.selectedFlight.getSeatList().get(i)); // Adding in a graphical format
            seatInRowCounter++;
        }
        return seatMap;
    }

    public void selectSeats(ArrayList<Integer> seatIds) // Selecting seats and saving it locally
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

        // Mark companion voucher as used or don't use it at all if unavailable
        if (useCompanionVoucher && this.selectedSeats.size() > 1 && this.customer.getStatus().equals("Airline Member"))
        {
            for (RegisteredCustomer member : this.airline.getRegisteredCustomers())
            {
                if (member.getCustomerId() == this.customer.getCustomerId())
                {
                    if (member.getCompanionVoucher().isUsable())
                    {
                        member.getCompanionVoucher().use();
                    }
                    else
                    {
                        useCompanionVoucher = false;
                    }
                    break;
                }
            }
        }
        else
        {
            useCompanionVoucher = false;
        }

        Purchase currentPurchase = new Purchase(this.selectedFlight, buyInsurance, buyAirportLoungeAccess, useCompanionVoucher, creditCardNumber, creditCardSecurityCode, this.selectedSeats, this.customer);
        this.airline.getPurchases().add(currentPurchase);
        sendReceiptAndTicket(currentPurchase);
        selectedSeats.clear();
        this.customer.addPurchase(currentPurchase);
        for (Flight flight : this.airline.getFlights())
        {
            if (flight.getFlightId() == this.selectedFlight.getFlightId())
            {
                flight.addPassenger(this.customer);
                break;
            }
        }
        DatabaseController.addPurchase(currentPurchase, this.selectedFlight.getFlightId(), this.customer.getCustomerId(), useCompanionVoucher);
        
    }

    private void sendReceiptAndTicket(Purchase purchase)
    {
        String purchaseId = purchase.getPurchaseId();
        Receipt receipt = purchase.getReceipt();
        ArrayList<Ticket> tickets = purchase.getTickets();

        // Format the receipt information
        String receiptInfo = String.format("Receipt ID: %s\nTotal Price: $%d\nCredit Card Number: %s",
                receipt.getReceiptId(), receipt.getPrice(), receipt.getCreditCardNumber());

        // Format the ticket information
        StringBuilder ticketInfo = new StringBuilder("Tickets:\n");
        for (Ticket ticket : tickets) {
            ticketInfo.append(String.format("Flight Number: %d, Seat Number: %d, Ticket ID: %s\n",
                    ticket.getFlightNumber(), ticket.getSeatNumber(), ticket.getTicketId()));
        }

        // Combine receipt and ticket information
        String email = String.format("Dear Customer,\n\nThank you for your purchase (ID: %s).\n\n%s\n\n%s",
                purchaseId, receiptInfo, ticketInfo.toString());

        sendEmail(email);
    }

    public void sendEmail(String content) {
        String receivingEmail = "liam.d.mah@gmail.com";
        String senderEmail = "ensf480helperemail@gmail.com";
        String host = "smtp.gmail.com";
    
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
    
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("ensf480helperemail@gmail.com", "aikm cotj umyp wnvz");
            }
        });
    
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(receivingEmail));
            message.setSubject("ENSF480 Airline Ticket and Receipt");
            message.setText(content);
            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
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
        DatabaseController.deletePurchase(purchaseId);
    }


}
