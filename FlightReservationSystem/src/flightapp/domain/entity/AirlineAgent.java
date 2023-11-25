package flightapp.domain.entity;

import flightapp.domain.pattern.BrowseAll;

public class AirlineAgent extends NonAdmin {
    private String viewLevel = "AirlineAgent";

    
    public AirlineAgent(String firstName, String lastName, String address, String email, int age, String phoneNumber, int employeeId) {
        super(firstName, lastName, address, email, age, phoneNumber, employeeId);

        setBrowseStrategy(new BrowseAll());
    }

    

    
}
