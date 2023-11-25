package flightapp.domain.entity;

import flightapp.domain.entity.Employee;
import flightapp.domain.pattern.BrowsePassengersStrategy;

public abstract class NonAdmin extends Employee {

    private BrowsePassengersStrategy browseStrategy;


    public void performBrowse() {}

    public void setBrowseStrategy(BrowsePassengersStrategy strategy) {

        this.browseStrategy = strategy;
    }
}
