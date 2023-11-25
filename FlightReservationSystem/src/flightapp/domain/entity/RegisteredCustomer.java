package flightapp.domain.entity;

public class RegisteredCustomer extends Customer {

    private String viewLevel = "User";

    /*
     * @param firstName
     * @param lastName
     * @param address
     * @param email
     * @param age
     * @param phoneNumber
     * @param customerId
     */
    public RegisteredCustomer(String firstName, String lastName, String address, String email, int age, String phoneNumber, int customerId) {
        super(firstName, lastName, address, email, age, phoneNumber, customerId);
    }

}
