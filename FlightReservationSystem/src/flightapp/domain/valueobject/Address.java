package flightapp.domain.valueobject;

public class Address {

    private int houseNumber;
    private String street;
    private String city;
    private String province;
    private String country;

    public Address(int houseNumber, String street, String city, String province, String country) {
        this.houseNumber = houseNumber;
        this.street = street;
        this.city = city;
        this.province = province;
        this.country = country;
    }

    public String getAddress() {
        return String.format("%s %s %s %s %s", houseNumber, street, city, province, country);
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getProvince() {
        return province;
    }

    public String getStreet() {
        return street;
    }
}
