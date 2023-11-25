package flightapp.domain.entity;

public abstract class Employee extends Person {
    int employeeId;

    public Employee(String firstName, String lastName, String address, String email, int age, String phoneNumber, int employeeId) {
        super(firstName, lastName, address, email, age, phoneNumber);
        this.employeeId = employeeId;
    }
}
