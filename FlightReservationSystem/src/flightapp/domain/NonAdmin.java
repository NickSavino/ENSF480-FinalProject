package flightapp.domain;

public abstract class NonAdmin extends Employee {

    private BrowsePassengersStrategy browseStrategy;


    public void performBrowse() {}

    public void setBrowseStrategy(BrowsePassengersStrategy strategy) {

        this.browseStrategy = strategy;
    }
}
