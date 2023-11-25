package flightapp.gui.panel;

import flightapp.gui.dialog.AddFlightDialog;
import flightapp.gui.dialog.DialogCallback;
import flightapp.gui.navigation.NavigationController;
import flightapp.main.MainApplication;

import javax.swing.*;
import java.awt.*;
import java.util.Dictionary;
import java.util.Vector;

public class AdminPanel extends JPanel implements DialogCallback {
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
    private MainApplication mainApplication;

    JList<String> flightsList;
    Vector<String> flightsData;

    public AdminPanel(MainApplication mainApp) {
        this.mainApplication = mainApp;
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);


        flightsData = new Vector<>();
        flightsData.add("Hello");
        flightsData.add("Hi");
        flightsData.add("Hey");
        flightsList = new JList<>(flightsData);

        mainMenuPanel = createMainMenuPanel();
        flightsPanel = createListPanel("Flights");
        crewsPanel = createListPanel("Crews");
        aircraftsPanel = createListPanel("Aircrafts");
        usersPanel = createListPanel("Registered Users");

        // Add panels to the card layout
        cardPanel.add(mainMenuPanel, "MainMenu");
        cardPanel.add(flightsPanel, "Flights");
        cardPanel.add(crewsPanel, "Crews");
        cardPanel.add(aircraftsPanel, "Aircrafts");
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
        JPanel panel = new JPanel(new GridLayout(4, 1)); // 4 buttons, one per row

        browseFlightsButton = new JButton("Browse Flights");
        browseCrewsButton = new JButton("Browse Crews");
        browseAircraftsButton = new JButton("Browse Aircrafts");
        browseUsersButton = new JButton("Browse Registered Users");

        // Add action listeners
        browseFlightsButton.addActionListener(e -> navigationController.navigateTo("Flights"));
        browseCrewsButton.addActionListener(e -> navigationController.navigateTo("Crews"));
        browseAircraftsButton.addActionListener(e -> navigationController.navigateTo("Aircrafts"));
        browseUsersButton.addActionListener(e -> navigationController.navigateTo("Registered Users"));

        panel.add(browseFlightsButton);
        panel.add(browseCrewsButton);
        panel.add(browseAircraftsButton);
        panel.add(browseUsersButton);


        return panel;
    }

    private JPanel createListPanel(String type) {
        JPanel panel = new JPanel(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane();
        flightsList.setVisible(true);
        scrollPane.add(flightsList);
        scrollPane.repaint();
        scrollPane.revalidate();

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton addButton = new JButton("Add");
        JButton removeButton = new JButton("Remove");

        // Add action listeners for Add and Remove
        addButton.addActionListener(e -> onAddItem(type));
        removeButton.addActionListener(e -> onRemoveItem(flightsList, type));

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void onAddItem(String type) {
        // Show dialog to add item
        System.out.println(type);
        if (type != null) {
            if (type.equals("Flights")) {
                AddFlightDialog flightDialog = new AddFlightDialog(this.mainApplication, this);
                flightDialog.setVisible(true);
            }
        }
    }

    @Override
    public void onFlightAdded(String flightInfo) {

        flightsData.add(flightInfo);
        flightsList.setListData(flightsData);
        flightsList.revalidate();
        flightsList.repaint();
    }




    private void onRemoveItem(JList<String> list, String type) {
        // Show confirmation dialog and remove item
        int confirmed = JOptionPane.showConfirmDialog(this, "Are you sure you want to remove the selected " + type + "?");
        if (confirmed == JOptionPane.YES_OPTION) {
            // Remove logic here
        }
    }
}