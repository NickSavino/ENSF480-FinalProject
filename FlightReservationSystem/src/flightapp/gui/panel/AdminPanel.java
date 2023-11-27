package flightapp.gui.panel;

import flightapp.gui.form.AddAircraftForm;
import flightapp.gui.form.AddCrewForm;
import flightapp.gui.form.AddFlightForm;
import flightapp.gui.form.FormCallback;
import flightapp.gui.navigation.NavigationController;
import flightapp.gui.main.MainView;

import javax.swing.*;
import java.awt.*;


public class AdminPanel extends JPanel implements FormCallback {

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
            flightsModel.addElement("Flight 1");
            flightsModel.addElement("Flight 2");
            // Add more flight items...
            return flightsModel;
        } else if ("Crew".equals(type)) {
            crewsModel = new DefaultListModel<>();
            return crewsModel;
        } else if ("Aircraft".equals(type)) {
            aircraftsModel = new DefaultListModel<>();
            return aircraftsModel;
        } else if ("Registered User".equals(type)) {
            usersModel = new DefaultListModel<>();
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
        destinationsComboBox = new JComboBox<>(destinationsModel);
        // Populate destinationsModel with data...

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
            // Validate and add the destination...
            destinationsModel.addElement(location + " (" + code + ")");
        }
    }

    // Method to handle the removal of a destination
    private void onRemoveDestination() {
        if (destinationsComboBox.getItemCount() > 0) {
            destinationsComboBox.setSelectedIndex(0); // Select first item by default
            int result = JOptionPane.showConfirmDialog(this, destinationsComboBox, "Remove Destination", JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                String selectedDestination = (String) destinationsComboBox.getSelectedItem();
                // Validate and remove the destination...
                destinationsModel.removeElement(selectedDestination);
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

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton addButton = new JButton("Add");
        JButton removeButton = new JButton("Remove");

        // Add action listeners for Add and Remove
        addButton.addActionListener(e -> onAddItem(model, type));
        removeButton.addActionListener(e -> onRemoveItem(list, model, type));

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void onAddItem(DefaultListModel<String> model, String type) {
        // Show dialog to add item
        System.out.println(type);
        if (type != null) {
            if (type.equals("Flight")) {
                AddFlightForm flightForm = new AddFlightForm(this.mainView, this);
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

    @Override
    public void onFlightAdded(String flightInfo) {
        flightsModel.addElement(flightInfo);
    }

    @Override
    public void onCrewAdded(String crewName, String assignedFlight) {
        crewsModel.addElement(crewName);
    }

    @Override
    public void onAircraftAdded(String model, int seats) {
        aircraftsModel.addElement(model);
    }

    private void onRemoveItem(JList<String> list, DefaultListModel<String> model, String type) {
        // Show confirmation dialog and remove item
        int selectedIndex = list.getSelectedIndex();

        if (selectedIndex != -1) {
            int confirmed = JOptionPane.showConfirmDialog(this, "Are you sure you want to remove the selected " + type + "?");
            if (confirmed == JOptionPane.YES_OPTION) {
                model.remove(selectedIndex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a " + type + " to remove.", "No " + type + " Selected", JOptionPane.WARNING_MESSAGE);
        }
    }
}