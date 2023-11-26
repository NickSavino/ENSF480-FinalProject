package flightapp.domain.entity;

import flightapp.domain.valueobject.CompanionVoucher;

public class AirlineMember extends RegisteredCustomer {

    private CompanionVoucher companionVoucher;

    public AirlineMember(String firstName, String lastName, String address, String email, int age, String phoneNumber, int customerId) {
        super(firstName, lastName, address, email, age,  phoneNumber, customerId);
    }

    public CompanionVoucher getCompanionVoucher() {
        return companionVoucher;
    }
}
