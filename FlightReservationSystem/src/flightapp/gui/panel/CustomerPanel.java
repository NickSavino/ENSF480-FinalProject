package flightapp.gui.panel;

import com.sun.tools.javac.Main;
import flightapp.gui.main.MainView;
import flightapp.gui.navigation.NavigationController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerPanel extends JPanel {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JButton selectFlightButton;
    private JButton selectSeatButton;
    private JButton makePaymentButton;

    private NavigationController navigationController;
    private JButton backButton;

    private MainView mainView;

    private DefaultListModel<String> flightsModel;

    public CustomerPanel(MainView mainView) {
        this.mainView = mainView;
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Initialize and add sub-panels
        cardPanel.add(createMainMenuPanel(), "MainMenu");
        cardPanel.add(createFlightSelectionPanel(), "FlightSelection");
        cardPanel.add(createSeatSelectionPanel(), "SeatSelection");
        cardPanel.add(createPaymentPanel(), "Payment");

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
        JPanel mainMenuPanel = new JPanel();
        mainMenuPanel.setLayout(new GridLayout(3, 1)); // 3 options, one per row

        JButton browseFlightsButton = new JButton("Browse Flights");
        JButton becomeMemberButton = new JButton("Become Member");
        JButton applyCreditCardButton = new JButton("Apply for Company Credit Card");

        // Set up action listeners for each button
        browseFlightsButton.addActionListener(e -> navigationController.navigateTo("FlightSelection"));
        //becomeMemberButton.addActionListener(e -> navigationController.navigateTo("SeatSe"));
        //applyCreditCardButton.addActionListener(e -> );

        mainMenuPanel.add(browseFlightsButton);
        mainMenuPanel.add(becomeMemberButton);
        mainMenuPanel.add(applyCreditCardButton);

        return mainMenuPanel;
    }

    private JPanel createFlightSelectionPanel() {
        JPanel flightSelectionPanel = new JPanel(new BorderLayout());

        // Dummy data for flights
        flightsModel = new DefaultListModel<>();
        for (String flight : mainView.getUserController().getFlightsString()) {
            flightsModel.addElement(flight);
        }
        JList<String> list = new JList<>(flightsModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        selectFlightButton = new JButton("Select Flight");
        selectFlightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Switch to seat selection panel
                navigationController.navigateTo("SeatSelection");
            }
        });

        flightSelectionPanel.add(new JScrollPane(list), BorderLayout.CENTER);
        flightSelectionPanel.add(selectFlightButton, BorderLayout.SOUTH);

        return flightSelectionPanel;
    }

    private JPanel createSeatSelectionPanel() {
        JPanel seatSelectionPanel = new JPanel();
        // Define the number of rows and columns for seats
        int rows = 5; // For example, 5 rows
        int cols = 4; // For example, 4 seats per row

        seatSelectionPanel.setLayout(new GridLayout(rows, cols));

        ButtonGroup seatGroup = new ButtonGroup(); // To allow only one seat to be selected at a time

        for (int i = 0; i < rows * cols; i++) {
            JToggleButton seatButton = new JToggleButton("" + (i + 1));
            seatButton.addActionListener(e -> {
                // Handle seat selection
                // use seatButton.getText() to get the seat number
            });

            seatGroup.add(seatButton);
            seatSelectionPanel.add(seatButton);
        }

        selectSeatButton = new JButton("Confirm Seat");
        selectSeatButton.addActionListener(e -> {
            // Confirm the selected seat and move to the next step
            navigationController.navigateTo("Payment");
        });

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(selectSeatButton);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(seatSelectionPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        return mainPanel;
    }

    private JPanel createPaymentPanel() {
        JPanel paymentPanel = new JPanel(new GridLayout(0, 1)); // Adjust layout as needed

        // Components for payment options
        JCheckBox loungeAccess = new JCheckBox("Lounge Access");
        JCheckBox cancellationInsurance = new JCheckBox("Cancellation Insurance");
        JComboBox<String> seatClass = new JComboBox<>(new String[] {"Economy", "Business", "First Class"});

        paymentPanel.add(loungeAccess);
        paymentPanel.add(cancellationInsurance);
        paymentPanel.add(new JLabel("Seat Class:"));
        paymentPanel.add(seatClass);

        // Add components for registered users
        if (userIsRegistered()) {
            JCheckBox useCompanionTicket = new JCheckBox("Use Companion Ticket");
            paymentPanel.add(useCompanionTicket);
            // Add more options for registered users
        }

        makePaymentButton = new JButton("Make Payment");
        // Add ActionListener to handle payment logic

        paymentPanel.add(makePaymentButton); // Add other components as needed

        return paymentPanel;
    }

    private boolean userIsRegistered() {
        // Implement logic to determine if the current user is a registered customer
        return false; // Placeholder
    }
}