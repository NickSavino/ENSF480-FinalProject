package flightapp.gui.panel;

import flightapp.gui.navigation.NavigationController;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AttendantPanel extends JPanel {
    private JComboBox<String> flightSelector;
    private JList<String> passengerList;
    private DefaultListModel<String> passengerListModel;

    public AttendantPanel() {
        setLayout(new BorderLayout());

        // Flight selector - dropdown to select a flight
        flightSelector = new JComboBox<>();
        // Populate flightSelector with flight IDs or names here...

        flightSelector.addActionListener(e -> updatePassengerList());

        // Passenger list - to display passengers of the selected flight
        passengerListModel = new DefaultListModel<>();
        passengerList = new JList<>(passengerListModel);
        JScrollPane passengerScrollPane = new JScrollPane(passengerList);

        add(flightSelector, BorderLayout.NORTH);
        add(passengerScrollPane, BorderLayout.CENTER);
    }

    private void updatePassengerList() {
        // Fetch and display passengers for the selected flight
        String selectedFlight = (String) flightSelector.getSelectedItem();
        List<String> passengers = getPassengersForFlight(selectedFlight);

        passengerListModel.clear();
        for (String passenger : passengers) {
            passengerListModel.addElement(passenger);
        }
    }

    private List<String> getPassengersForFlight(String flight) {
        // TODO: Implement logic to fetch passengers based on the selected flight
        return List.of(); // Placeholder for fetched passenger data
    }
}