package flightapp.gui.panel;

import flightapp.gui.main.MainView;
import flightapp.gui.navigation.NavigationController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuestPanel extends JPanel {

    private CardLayout cardLayout;
    private JPanel cardPanel;
    private NavigationController navigationController;
    private JButton browseFlightsButton;
    private JButton registerButton;
    private JButton loginButton;
    private MainView mainView;
    private DefaultListModel<String> flightsModel;

    public GuestPanel(MainView mainView) {
        this.mainView = mainView;

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Initialize and add sub-panels
        cardPanel.add(createMainMenuPanel(), "MainMenu");
        cardPanel.add(createFlightSelectionPanel(), "FlightSelection");
        cardPanel.add(createSeatSelectionPanel(), "SeatSelection");

        setLayout(new BorderLayout());
        add(cardPanel, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
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

        browseFlightsButton = new JButton("Browse Flights");
        registerButton = new JButton("Register");
        loginButton = new JButton("Login");

        browseFlightsButton.addActionListener(e -> navigationController.navigateTo("FlightSelection"));
        registerButton.addActionListener(e -> mainView.onRegister());
        loginButton.addActionListener(e -> showLoginDialog());

        add(browseFlightsButton);
        add(registerButton);
        add(loginButton);


        // Set up action listeners for each button
        browseFlightsButton.addActionListener(e -> navigationController.navigateTo("FlightSelection"));
        //becomeMemberButton.addActionListener(e -> navigationController.navigateTo("SeatSe"));
        //applyCreditCardButton.addActionListener(e -> );

        mainMenuPanel.add(browseFlightsButton);
        mainMenuPanel.add(registerButton);
        mainMenuPanel.add(loginButton);

        return mainMenuPanel;
    }

    private void showLoginDialog() {
        // Create components
        JTextField usernameField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);

        // Construct the dialog components array
        final JComponent[] inputs = new JComponent[] {
                new JLabel("Username"),
                usernameField,
                new JLabel("Password"),
                passwordField
        };

        // Show the dialog and get user response
        int result = JOptionPane.showConfirmDialog(this, inputs, "Login", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        // Handle the user response
        if (result == JOptionPane.OK_OPTION) {
            try {
                int employeeID = Integer.parseInt(usernameField.getText());
                String password = new String(passwordField.getPassword());
                mainView.onLogin(employeeID, password);

            } catch (Exception e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                mainView.onLogin(username, password);
            }
        }
    }
    private JPanel createFlightSelectionPanel() {
        JPanel flightSelectionPanel = new JPanel(new BorderLayout());

        flightsModel = new DefaultListModel<>();
        for (String flight : mainView.getUserController().getFlightsString()) {
            flightsModel.addElement(flight);
        }
        JList<String> flightList = new JList<>(flightsModel);
        flightList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JButton selectFlightButton = new JButton("Select Flight");
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

        JButton selectSeatButton = new JButton("Confirm Seat");
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

        JButton makePaymentButton = new JButton("Make Payment");
        // Add ActionListener to handle payment logic

        paymentPanel.add(makePaymentButton); // Add other components as needed

        return paymentPanel;
    }

    private boolean userIsRegistered() {
        // Implement logic to determine if the current user is a registered customer
        return false; // Placeholder
    }

}