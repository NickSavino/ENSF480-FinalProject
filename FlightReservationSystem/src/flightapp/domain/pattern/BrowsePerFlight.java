package flightapp.domain.pattern;

import java.util.ArrayList;
import flightapp.domain.entity.*;
public class BrowsePerFlight implements BrowsePassengersStrategy {

    @Override
    public ArrayList<Customer> browse(ArrayList<Customer> customers, int flightId)
    {
        ArrayList<Customer> result = new ArrayList<Customer>();

        for (Customer customer : customers) {
            for (Purchase purchase : customer.getPurchases()) {
                for (Ticket ticket : purchase.getTickets())
                {
                    if (ticket.getFlightNumber() == flightId) {
                        result.add(customer);
                    }
                }
            }
        }
        return result;
    }
}
