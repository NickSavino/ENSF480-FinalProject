package flightapp.domain.entity;

import flightapp.domain.pattern.BrowseAll;
import flightapp.domain.pattern.*;

public class AirlineAgent extends NonAdmin {
    private String viewLevel = "AirlineAgent";

    public AirlineAgent(String firstName, String lastName, int houseNumber, String street, String city, String province,
        String country, String email, int age, String phoneNumber, String password) {
        super(firstName, lastName, houseNumber, street, city, province, country, email, age, phoneNumber, password);
        setBrowseStrategy(new BrowseAll());
    }
}
