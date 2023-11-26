package flightapp.domain.pattern;

import java.util.ArrayList;
import flightapp.domain.entity.*;

public class BrowseAll implements  BrowsePassengersStrategy{

    @Override
    public ArrayList<Customer> browse(ArrayList<Customer> customers, int flightId) {
        return customers;
    }
}
