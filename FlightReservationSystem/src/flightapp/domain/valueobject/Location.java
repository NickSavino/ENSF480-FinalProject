package flightapp.domain.valueobject;

public class Location {

    private String name;
    private String locationId;

    public Location(String name, String locationId) {
        this.name = name;
        this.locationId = locationId;
    }

    public String getName()
    {
        return this.name;
    }

    public String getLocationId()
    {
        return this.locationId;
    }
}
