package flightapp.gui.form;

public interface RegistrationCallback {
    void onRegistrationComplete(String username, String password, String creditCardNumber,
                                String creditCardSecurityCode, String firstName, String lastName,
                                int houseNumber, String street, String city,
                                String province, String country, String email);

}
