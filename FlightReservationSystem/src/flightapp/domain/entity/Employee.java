package flightapp.domain.entity;
import java.util.UUID;

public class Employee extends Person {
    private int employeeId;
    private String password;
    private String employeeType; // "Flight Attendant", "Admin", or "Airline Agent"

    public Employee(String firstName, String lastName, int houseNumber, String street, String city, String province, 
        String country, String email, String password, String role) 
    {
        super(firstName, lastName, houseNumber, street, city, province, country, email);
        this.employeeId = UUID.randomUUID().hashCode();
        this.password = password;
        this.employeeType = role;
    }

    public int getEmployeeId() 
    {
        return this.employeeId;
    }

    public String getPassword()
    {
        return this.password;
    }

    public String getEmployeeType()
    {
        return this.employeeType;
    }
}
