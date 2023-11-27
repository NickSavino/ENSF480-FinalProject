package flightapp.domain.entity;
import java.util.UUID;

public abstract class Employee extends Person {
    int employeeId;
    String password;

    public Employee(String firstName, String lastName, int houseNumber, String street, String city, String province, 
        String country, String email, int age, String phoneNumber, String password) 
    {
        super(firstName, lastName, houseNumber, street, city, province, country, email, age, phoneNumber);
        this.employeeId = UUID.randomUUID().hashCode();
        this.password = password;
    }

    public int getEmployeeId() 
    {
        return this.employeeId;
    }

    public String getPassword()
    {
        return this.password;
    }
}
