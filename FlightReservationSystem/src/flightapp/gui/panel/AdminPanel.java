package flightapp.gui.panel;

import flightapp.gui.navigation.NavigationController;
import flightapp.main.MainApplication;

import javax.swing.*;
import java.awt.*;

public class AdminPanel extends JPanel {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JButton backButton;
    private JButton browseFlightsButton;
    private JButton browseCrewsButton;
    private JButton browseAircraftsButton;
    private JButton browseUsersButton;

    private NavigationController navigationController;
    private MainApplication mainApplication;

    public AdminPanel(MainApplication mainApp) {
        this.mainApplication = mainApp;
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);


        JPanel mainMenuPanel = createMainMenuPanel();
        JPanel flightsPanel = createListPanel("Flights");
        JPanel crewsPanel = createListPanel("Crews");
        JPanel aircraftsPanel = createListPanel("Aircrafts");
        JPanel usersPanel = createListPanel("Registered Users");

        // Add panels to the card layout
        cardPanel.add(mainMenuPanel, "MainMenu");
        cardPanel.add(flightsPanel, "Flights");
        cardPanel.add(crewsPanel, "Crews");
        cardPanel.add(aircraftsPanel, "Aircrafts");
        cardPanel.add(usersPanel, "Registered Users");

        setLayout(new BorderLayout());
        add(cardPanel, BorderLayout.CENTER);


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
        JList<String> list = new JList<>(); // Placeholder for actual data
        JScrollPane scrollPane = new JScrollPane(list);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton addButton = new JButton("Add");
        JButton removeButton = new JButton("Remove");

        // Add action listeners for Add and Remove
        addButton.addActionListener(e -> onAddItem(type));
        removeButton.addActionListener(e -> onRemoveItem(list, type));

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void onAddItem(String type) {
        // Show dialog to add item
        JOptionPane.showMessageDialog(this, "Add " + type);
    }

    private void onRemoveItem(JList<String> list, String type) {
        // Show confirmation dialog and remove item
        int confirmed = JOptionPane.showConfirmDialog(this, "Are you sure you want to remove the selected " + type + "?");
        if (confirmed == JOptionPane.YES_OPTION) {
            // Remove logic here
        }
    }
}