package flightapp.gui.panel;

import flightapp.gui.navigation.NavigationController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerPanel extends JPanel {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JList<String> flightList; // Placeholder for flight list
    private JButton selectFlightButton;
    private JButton selectSeatButton;
    private JButton makePaymentButton;

    private NavigationController navigationController;
    private JButton backButton;

    public CustomerPanel() {
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Initialize and add sub-panels
        cardPanel.add(createFlightSelectionPanel(), "FlightSelection");
        cardPanel.add(createSeatSelectionPanel(), "SeatSelection");
        cardPanel.add(createPaymentPanel(), "Payment");

        setLayout(new BorderLayout());
        add(cardPanel, BorderLayout.CENTER);

        backButton = new JButton("Back");
        backButton.addActionListener(e -> navigationController.goBack());
        add(backButton, BorderLayout.SOUTH);
        navigationController = new NavigationController(cardPanel, cardLayout, backButton);
        navigationController.initialize("FlightSelection");
        // Initialize to show main menu
        navigationController.navigateTo("FlightSelection");
    }

    private JPanel createFlightSelectionPanel() {
        JPanel flightSelectionPanel = new JPanel(new BorderLayout());

        // Dummy data for flights
        String[] flights = {"Flight 1", "Flight 2", "Flight 3"};
        flightList = new JList<>(flights);

        selectFlightButton = new JButton("Select Flight");
        selectFlightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Switch to seat selection panel
                navigationController.navigateTo("SeatSelection");
            }
        });

        flightSelectionPanel.add(new JScrollPane(flightList), BorderLayout.CENTER);
        flightSelectionPanel.add(selectFlightButton, BorderLayout.SOUTH);

        return flightSelectionPanel;
    }

    private JPanel createSeatSelectionPanel() {
        JPanel seatSelectionPanel = new JPanel(); // Layout as needed

        selectSeatButton = new JButton("Select Seat");
        selectSeatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Switch to payment panel
                navigationController.navigateTo("Payment");
            }
        });

        seatSelectionPanel.add(selectSeatButton); // Add other components as needed

        return seatSelectionPanel;
    }

    private JPanel createPaymentPanel() {
        JPanel paymentPanel = new JPanel(); // Layout as needed

        makePaymentButton = new JButton("Make Payment");
        // Add ActionListener to handle payment logic

        paymentPanel.add(makePaymentButton); // Add other components as needed

        return paymentPanel;
    }
}