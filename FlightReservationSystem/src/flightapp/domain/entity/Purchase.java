package flightapp.domain.entity;
import java.util.ArrayList;
import flightapp.domain.valueobject.Receipt;
import java.util.UUID;
import flightapp.domain.valueobject.*;

public class Purchase {

    private boolean loungeAccess;
    private String purchaseId;
    private boolean ticketInsurance;
    private ArrayList<Ticket> tickets;
    private Receipt receipt;
    private int totalPurchaseCost = 0;
    CreditCard creditCard;

    public Purchase(Flight flight, boolean buyInsurance, boolean buyAirportLoungeAccess, boolean useCompanionVoucher,
        String creditCardNumber, int creditCardSecurityCode, ArrayList<Seat> seats, Customer customer) 
    {
        this.loungeAccess = buyAirportLoungeAccess;
        this.ticketInsurance = buyInsurance;
        this.tickets = new ArrayList<Ticket>();
        this.creditCard = new CreditCard(creditCardNumber, creditCardSecurityCode);
        this.purchaseId = UUID.randomUUID().toString();

        int baseFlightCost = flight.getBaseFlightCost();

        for (Seat seat : seats)
        {
            this.tickets.add(new Ticket(flight.getDate(), flight.getFlightId(), seat.getSeatId(), UUID.randomUUID().toString()));
            this.totalPurchaseCost += baseFlightCost;
            if (seat.getSeatType().equals("Business"))
            {
                this.totalPurchaseCost *= 1.5;
            }
            else if (seat.getSeatType().equals("Comfort"))
            {
                this.totalPurchaseCost *= 2;
            }
            else
            {
                this.totalPurchaseCost *= 1;
            }
        }

        if (loungeAccess)
        {
            if (customer.getStatus().equals("Airline Member"))
            {
                this.totalPurchaseCost += 25 * seats.size();
            }
            else
            {
                this.totalPurchaseCost += 50 * seats.size();
            }
        }

        if (ticketInsurance)
        {
            this.totalPurchaseCost += 20 * seats.size();
        }

        if (useCompanionVoucher)
        {
            this.totalPurchaseCost -= flight.getBaseFlightCost();
            String seatType = seats.get(0).getSeatType();
            if (seatType.equals("Business"))
            {
                this.totalPurchaseCost -= 200;
            }
            else if (seatType.equals("Comfort"))
            {
                this.totalPurchaseCost -= 140;
            }
            else
            {
                this.totalPurchaseCost -= 100;
            }
        }

        this.receipt = new Receipt(UUID.randomUUID().toString(), this.totalPurchaseCost, this.creditCard.getCreditCardNumber());
    }

    public int getTotalPurchaseCost()
    {
        return this.totalPurchaseCost;
    }

    public ArrayList<Ticket> getTickets()
    {
        return this.tickets;
    }

    public Receipt getReceipt()
    {
        return this.receipt;
    }

    public String getPurchaseId()
    {
        return this.purchaseId;
    }

    public boolean getLoungeAccess()
    {
        return this.loungeAccess;
    }

    public CreditCard getCreditCard()
    {
        return this.creditCard;
    }

    public boolean getTicketInsurance()
    {
        return this.ticketInsurance;    
    }
}
