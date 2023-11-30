package flightapp.gui.panel;

import flightapp.gui.form.*;
import flightapp.gui.navigation.NavigationController;
import flightapp.gui.main.MainView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class AdminPanel extends JPanel implements AdminFormCallback {

    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JButton backButton;
    private JButton browseFlightsButton;
    private JButton browseCrewsButton;
    private JButton browseAircraftsButton;
    private JButton browseUsersButton;
    JPanel mainMenuPanel;
    JPanel flightsPanel;
    JPanel crewsPanel;
    JPanel aircraftsPanel;
    JPanel usersPanel;
    private NavigationController navigationController;
    private MainView mainView;
    DefaultListModel<String> flightsModel;
    DefaultListModel<String> crewsModel;
    DefaultListModel<String> aircraftsModel;
    DefaultListModel<String> usersModel;
    DefaultComboBoxModel<String> destinationsModel;
    private JComboBox destinationsComboBox;


    public AdminPanel(MainView mainApp) {
        this.mainView = mainApp;
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        mainMenuPanel = createMainMenuPanel();
        flightsPanel = createListPanel("Flight");
        crewsPanel = createListPanel("Crew");
        aircraftsPanel = createListPanel("Aircraft");
        usersPanel = createListPanel("Registered Users");

        // Add panels to the card layout
        cardPanel.add(mainMenuPanel, "MainMenu");
        cardPanel.add(flightsPanel, "Flight");
        cardPanel.add(crewsPanel, "Crew");
        cardPanel.add(aircraftsPanel, "Aircraft");
        cardPanel.add(usersPanel, "Registered Users");

        setLayout(new BorderLayout());
        add(cardPanel, BorderLayout.CENTER);

        //Create Back button
        backButton = new JButton("Back");
        backButton.addActionListener(e -> navigationController.goBack());
        add(backButton, BorderLayout.SOUTH);
        navigationController = new NavigationController(cardPanel, cardLayout, backButton);
        navigationController.initialize("MainMenu");
        // Initialize to show main menu
        navigationController.navigateTo("MainMenu");
    }

    private JPanel createMainMenuPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 1)); // 4 buttons, one per row

        browseFlightsButton = new JButton("Browse Flights");
        browseCrewsButton = new JButton("Browse Crews");
        browseAircraftsButton = new JButton("Browse Aircrafts");
        browseUsersButton = new JButton("Browse Registered Users");

        // Add action listeners
        browseFlightsButton.addActionListener(e -> navigationController.navigateTo("Flight"));
        browseCrewsButton.addActionListener(e -> navigationController.navigateTo("Crew"));
        browseAircraftsButton.addActionListener(e -> navigationController.navigateTo("Aircraft"));
        browseUsersButton.addActionListener(e -> navigationController.navigateTo("Registered Users"));

        panel.add(browseFlightsButton);
        panel.add(browseCrewsButton);
        panel.add(browseAircraftsButton);
        panel.add(browseUsersButton);
        panel.add(createDestinationManagementPanel());

        return panel;
    }

    private DefaultListModel<String> populateModel(String type) {
        //fetch and add data to the model based on the 'type'
        if ("Flight".equals(type)) {
            flightsModel = new DefaultListModel<>();
            for (String flight : mainView.getUserController().getFlightsString()) {
                flightsModel.addElement(flight);
            }
            return flightsModel;
        } else if ("Crew".equals(type)) {
            crewsModel = new DefaultListModel<>();
            for (String crew : mainView.getUserController().getFlightCrewsString()) {
                crewsModel.addElement(crew);
            }
            return crewsModel;
        } else if ("Aircraft".equals(type)) {
            aircraftsModel = new DefaultListModel<>();
            for (String aircraft : mainView.getUserController().getAircraftsString()) {
                aircraftsModel.addElement(aircraft);
            }
            return aircraftsModel;
        } else if ("Registered Users".equals(type)) {
            usersModel = new DefaultListModel<>();
            for (String user : mainView.getUserController().getRegisteredUsersString()) {
                usersModel.addElement(user);
            }
            return usersModel;
        }

        return new DefaultListModel<>();
    }

    private JPanel createDestinationManagementPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2)); // One row, two columns

        JButton addDestinationButton = new JButton("Add Destination");
        JButton removeDestinationButton = new JButton("Remove Destination");

        addDestinationButton.addActionListener(e -> onAddDestination());
        removeDestinationButton.addActionListener(e -> onRemoveDestination());

        panel.add(addDestinationButton);
        panel.add(removeDestinationButton);

        // Initialize destinations model and combo box for removal
        destinationsModel = new DefaultComboBoxModel<>();
        ArrayList<String> destinations = mainView.getUserController().getLocationsString();
        for (String destination : destinations) {
            destinationsModel.addElement(destination);
        }
        destinationsComboBox = new JComboBox<>(destinationsModel);

        return panel;
    }

    // Method to handle the addition of a destination
    private void onAddDestination() {
        JTextField locationField = new JTextField();
        JTextField codeField = new JTextField();
        final JComponent[] inputs = new JComponent[] {
                new JLabel("Location"),
                locationField,
                new JLabel("Code"),
                codeField
        };
        int result = JOptionPane.showConfirmDialog(this, inputs, "Add Destination", JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String location = locationField.getText();
            String code = codeField.getText();
            destinationsModel.addElement(code);
            destinationsComboBox.revalidate();
            destinationsComboBox.repaint();
            mainView.getUserController().addDestination(location, code);
        }
    }



    // Method to handle the removal of a destination
    private void onRemoveDestination() {
        if (destinationsComboBox.getItemCount() > 0) {
            destinationsComboBox.setSelectedIndex(0); // Select first item by default

            Object[] options = {"Remove", "Cancel"};
            int result = JOptionPane.showOptionDialog(
                    this,
                    destinationsComboBox,
                    "Remove Destination",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    options,
                    options[1] // Default button (Cancel)
            );

            if (result == 0) { // The index of "Remove" option is 0
                String selectedDestination = (String) destinationsComboBox.getSelectedItem();
                destinationsModel.removeElement(selectedDestination);
                mainView.getUserController().removeDestination(selectedDestination);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No destinations available to remove.", "Remove Destination", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private JPanel createListPanel(String type) {
        JPanel panel = new JPanel(new BorderLayout());

        // Create a new list model and list for each type
        // Populate the model with data specific to 'type'
        DefaultListModel<String> model = populateModel(type);
        JList<String> list = new JList<>(model);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        JScrollPane scrollPane = new JScrollPane(list);

        if (!type.equals("Registered Users")) {
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JButton addButton = new JButton("Add");
            JButton removeButton = new JButton("Remove");

            // Add action listeners for Add and Remove
            addButton.addActionListener(e -> onAddItem(model, type));
            removeButton.addActionListener(e -> onRemoveItem(list, model, type));

            buttonPanel.add(addButton);
            //Add Modify Button for Flights Panel
            if (type.equals("Flight")) {
                JButton modifyDestinationButton = new JButton("Modify");
                modifyDestinationButton.addActionListener(e -> onModifyFlight(list, model));
                buttonPanel.add(modifyDestinationButton);

            }
            buttonPanel.add(removeButton);



            panel.add(buttonPanel, BorderLayout.SOUTH);
        }

        panel.add(scrollPane, BorderLayout.CENTER);


        return panel;
    }

    private void onAddItem(DefaultListModel<String> model, String type) {
        // Show dialog to add item
        if (type != null) {
            if (type.equals("Flight")) {
                AddFlightForm flightForm = new AddFlightForm(this.mainView, this, this.mainView);
                flightForm.setVisible(true);
            }
            else if (type.equals("Crew")) {
                AddCrewForm crewForm = new AddCrewForm(this.mainView, this);
                crewForm.setVisible(true);
            }
            else if (type.equals("Aircraft")) {
                AddAircraftForm aircraftForm = new AddAircraftForm(this.mainView, this);
                aircraftForm.setVisible(true);
            }
        }
    }

    private void onModifyFlight(JList<String> list, DefaultListModel<String> model) {
        int flightID = extractIdFromSelectedItem(model.getElementAt(list.getSelectedIndex()));
        int selectedIndex = list.getSelectedIndex();
        ModifyFlightForm modifyFlightForm = new ModifyFlightForm(this.mainView, this, this.mainView, flightID, selectedIndex);
        modifyFlightForm.setVisible(true);
    }
    @Override
    public void onFlightModified(int selectedIndex, int flightId, int aircraftId, String originId, String destinationId, int flightDuration, int flightCrewId, int baseFlightCost,
                                 int day, int month, int year, int hour, int minute) {
        System.out.println("Modified Callback Called");
        mainView.getUserController().modifyFlight(flightId, aircraftId, originId, destinationId,  flightDuration, flightCrewId,
        baseFlightCost, day,  month,  year,  hour,  minute);
        flightsModel.remove(selectedIndex);
        String flightString = String.format("%s to %s | Departure: %02d/%02d/%d %02d:%02d - %d", originId, destinationId,
                day, month, year, hour, minute, flightId);
        flightsModel.addElement(flightString);
    }

    @Override
    public void onFlightAdded(int aircraftId, String originId, String destinationId, int flightDuration, int flightCrewId,
                              int baseFlightCost, int departureDay, int departureMonth, int departureYear, int departureHour, int departureMinute) {

        int flightId = mainView.getUserController().getAirline().getNewFlightId();


        String flightString = String.format("%s to %s | Departure: %02d/%02d/%d %02d:%02d - %d", originId, destinationId,
                departureDay, departureMonth, departureYear, departureHour, departureMinute, flightId);
        flightsModel.addElement(flightString);

        mainView.getCustomerPanel().addtoFlightList(flightString);
        mainView.getGuestPanel().addtoFlightList(flightString);
        mainView.getUserController().addFlight(flightId, aircraftId, originId, destinationId, flightDuration, flightCrewId, baseFlightCost, departureDay,
                departureMonth, departureYear, departureHour, departureMinute);


    }

    @Override
    public void onCrewAdded(int crewId, String crewName, int assignedFlightId) {
        crewsModel.addElement(crewName + " - " + Integer.toString(crewId));
        mainView.getUserController().addCrew(crewId, crewName, assignedFlightId);
    }

    @Override
    public void onAircraftAdded(int aircraftId, String model, int comfortSeats, int businessSeats, int ordinarySeats) {

        mainView.getUserController().addAircraft(aircraftId, model, comfortSeats, businessSeats, ordinarySeats);
        aircraftsModel.addElement(mainView.getUserController().getAirline().getAircraftByID(aircraftId).toString());

    }

    private void onRemoveItem(JList<String> list, DefaultListModel<String> model, String type) {
        // Show confirmation dialog and remove item
        int selectedIndex = list.getSelectedIndex();
        if (selectedIndex != -1) {
            String selectedItem = model.getElementAt(selectedIndex);
            int confirmed = JOptionPane.showConfirmDialog(this, "Are you sure you want to remove the selected " + type + "?");
            if (confirmed == JOptionPane.YES_OPTION) {
                // Extract ID from the selected item
                int id = extractIdFromSelectedItem(selectedItem);

                // Perform removal based on type and id
                if (type.equals("Flight")) {
                    mainView.getUserController().removeFlight(id);
                } else if (type.equals("Crew")) {
                    mainView.getUserController().removeCrew(id);
                } else if (type.equals("Aircraft")) {
                    mainView.getUserController().removeAircraft(id);
                }

                // Update model and UI
                model.remove(selectedIndex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a " + type + " to remove.", "No " + type + " Selected", JOptionPane.WARNING_MESSAGE);
        }
    }

    private int extractIdFromSelectedItem(String selectedItem) {
        // Assuming the format is "Name - ID"
        String[] parts = selectedItem.split(" - ");
        return Integer.parseInt(parts[1]);
    }
}