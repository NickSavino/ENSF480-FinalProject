package flightapp.domain.pattern;

import java.util.ArrayList;
import flightapp.domain.entity.*;

public interface BrowsePassengersStrategy {

    public ArrayList<Customer> browse(ArrayList<Customer> customers, int flightId);
}
