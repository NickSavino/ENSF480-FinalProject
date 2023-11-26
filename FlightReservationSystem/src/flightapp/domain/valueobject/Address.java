package flightapp.domain.valueobject;

public class Address {

    private String houseNumber;
    private String street;
    private String city;
    private String province;
    private String country;


    /*
     *  Constructor
     *  @param address
     * 
     * should take address in as 123SampleStreet_SampleCity_SampleProvince_SampleCountry
     * analyzes string and splits into data members
     * 
     */
    public Address(String address) {
        this.houseNumber = houseNumber;
        this.street = street;
        this.city = city;
        this.province = province;
        this.country = country;
    }

    public String getAddress() {
        return String.format("%s %s %s %s %s", houseNumber, street, city, province, country);
    }
}
