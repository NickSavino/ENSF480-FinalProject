package flightapp.gui.panel;

import flightapp.domain.entity.Flight;
import flightapp.domain.entity.Seat;
import flightapp.gui.main.MainView;
import flightapp.gui.navigation.NavigationController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GuestPanel extends JPanel {

    private CardLayout cardLayout;
    private JPanel cardPanel;
    private NavigationController navigationController;
    private JButton browseFlightsButton;
    private JButton registerButton;
    private JButton loginButton;
    private MainView mainView;
    private DefaultListModel<String> flightsModel;

    private int totalCost = 0;

    public GuestPanel(MainView mainView) {
        this.mainView = mainView;

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Initialize and add sub-panels
        cardPanel.add(createMainMenuPanel(), "MainMenu");
        cardPanel.add(createFlightSelectionPanel(), "FlightSelection");

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
                String selectedFlight = flightList.getSelectedValue();
                mainView.getUserController().setSelectedFlightFromString(selectedFlight);
                updateSeatSelectionPanel();
                navigationController.navigateTo("SeatSelection");
            }
        });

        flightSelectionPanel.add(new JScrollPane(flightList), BorderLayout.CENTER);
        flightSelectionPanel.add(selectFlightButton, BorderLayout.SOUTH);

        return flightSelectionPanel;
    }

    private JPanel createSeatSelectionPanel() {
        JPanel seatSelectionPanel = new JPanel();

        Flight flight = mainView.getUserController().getSelectedFlight();
        System.out.println("Selected Flight: " + flight);
        if (flight == null) {

        }
        ArrayList<Seat> seatList = null;
        seatList = mainView.getUserController().getSelectedFlight().getSeatList();


        // Define the number of rows and columns for seats
        int rows = 0; // Amoount of rows on the aircraft
        int cols = 6; // Amount of seats per row

        for (int i = 0; i < seatList.size(); i++) {
            if (i % cols == 0) {
                rows++;
            }
        }
        seatSelectionPanel.setLayout(new GridLayout(rows, cols));
        // Use a list to store selected seats
        ArrayList<JToggleButton> selectedSeats = new ArrayList<>();
        ArrayList<Integer> selectedSeatIds = new ArrayList<>();

        for (int i = 1; i < rows * cols; i++) {
            JToggleButton seatButton = new JToggleButton("" + (i));
            seatButton.setOpaque(true);
            seatButton.setBorderPainted(false);


            // Customize the color of the buttons based on another condition
            if (seatList.get(i).getSeatType().equals("Comfort"))
            {
                seatButton.setBackground(Color.CYAN);
            }
            else if (seatList.get(i).getSeatType().equals("Business"))
            {
                seatButton.setBackground(Color.YELLOW);
            }
            else if (seatList.get(i).getSeatType().equals("Ordinary"))
            {
                seatButton.setBackground(Color.ORANGE);
            }
            else
            {
                seatButton.setForeground(Color.RED);
            }

            if (seatList.get(i).isBooked()) {
                System.out.println("IS BOOKED");
                seatButton.setEnabled(false);
                seatButton.setBackground(Color.DARK_GRAY);
            }
            else
            {
                seatButton.addActionListener(e -> {
                    if (seatButton.isSelected())
                    {
                        selectedSeats.add(seatButton);
                        selectedSeatIds.add(Integer.parseInt(seatButton.getText()));
                        System.out.println("Selected seat: " + seatButton.getText());
                    }
                    else
                    {
                        int index = selectedSeats.indexOf(seatButton);
                        String text = seatButton.getText();
                        if (index != -1)
                        {
                            selectedSeats.remove(index);
                            selectedSeatIds.remove(index);
                        }

                        System.out.println("Unselected seat " + text);
                    }
                });
            }

            seatSelectionPanel.add(seatButton);
        }

        JButton selectSeatButton = new JButton("Confirm Seats");
        selectSeatButton.addActionListener(e -> {
            mainView.getUserController().setSelectedSeats(selectedSeatIds);
            updatePaymentPanel();
            navigationController.navigateTo("Payment");
        });

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(selectSeatButton);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(seatSelectionPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        return mainPanel;
    }

    public void updateSeatSelectionPanel() {
        Flight selectedFlight = mainView.getUserController().getSelectedFlight();
        if (selectedFlight == null) {
            return;
        }
        JPanel seatSelectionPanel = createSeatSelectionPanel();

        cardPanel.add(seatSelectionPanel, "SeatSelection");
        cardPanel.revalidate();
        cardPanel.repaint();

    }


    private JPanel createPaymentPanel() {
        JPanel paymentPanel = new JPanel(new GridLayout(0, 1)); // Adjust layout as needed
        // Components for payment options
        JCheckBox loungeAccess = new JCheckBox("Lounge Access");
        JCheckBox cancellationInsurance = new JCheckBox("Cancellation Insurance");
        JCheckBox useCompanionTicket = new JCheckBox("Use Companion Ticket");

        JLabel costLabel = new JLabel();
        totalCost = mainView.getUserController().calculateTotalCost(false, false, false);
        costLabel.setText("Total Cost: $" + totalCost);

        paymentPanel.add(loungeAccess);
        paymentPanel.add(cancellationInsurance);

        // Add components for registered users
        if (mainView.getUserController().isCustomerLoggedIn()) {
            paymentPanel.add(useCompanionTicket);
            // Add more options for registered users
        }

        loungeAccess.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                totalCost = mainView.getUserController().calculateTotalCost(cancellationInsurance.isSelected(), loungeAccess.isSelected(), useCompanionTicket.isSelected());
                costLabel.setText("Total Cost: $" + totalCost);

            }
        });

        cancellationInsurance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                totalCost = mainView.getUserController().calculateTotalCost(cancellationInsurance.isSelected(), loungeAccess.isSelected(), useCompanionTicket.isSelected());
                costLabel.setText("Total Cost: $" + totalCost);

            }
        });

        useCompanionTicket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                totalCost = mainView.getUserController().calculateTotalCost(cancellationInsurance.isSelected(), loungeAccess.isSelected(), useCompanionTicket.isSelected());
                costLabel.setText("Total Cost: $" + totalCost);
            }
        });


        JPanel creditCardPanel = new JPanel();


        JLabel numberLabel = new JLabel("Credit Card Number:");
        JTextField creditCardField = new JTextField(16);
        JLabel securityCodeLabel = new JLabel("Security Code:");
        JTextField securityCodeField = new JTextField(3);

        creditCardPanel.add(numberLabel);
        creditCardPanel.add(creditCardField);
        creditCardPanel.add(securityCodeLabel);
        creditCardPanel.add(securityCodeField);

        JButton makePaymentButton = new JButton("Make Payment");
        // Add ActionListener to handle payment logic

        makePaymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean loungeAccessSelected = loungeAccess.isSelected();
                boolean cancellationInsuranceSelected = cancellationInsurance.isSelected();
                boolean useCompanionTicketSelected = mainView.getUserController().isCustomerLoggedIn() && useCompanionTicket.isSelected();
                String creditCardNumber = creditCardField.getText();
                int securityCode = Integer.parseInt(securityCodeField.getText());
                try {
                    mainView.getUserController().purchase(loungeAccessSelected, cancellationInsuranceSelected, useCompanionTicketSelected, creditCardNumber, securityCode);
                    JOptionPane.showMessageDialog(mainView, "Successfully Processed Purchase\n an E-mail will be sent to you shortly", "Successful Purchase", JOptionPane.INFORMATION_MESSAGE);
                    updatePaymentPanel();
                    updateSeatSelectionPanel();
                    navigationController.goHome("MainMenu");
                } catch (Exception Ex) {
                    JOptionPane.showMessageDialog(mainView, "Please confirm credit card information", "Error Confirming purchase", JOptionPane.WARNING_MESSAGE);
                }

            }
        });

        paymentPanel.add(creditCardPanel);
        paymentPanel.add(costLabel);
        paymentPanel.add(makePaymentButton); // Add other components as needed

        return paymentPanel;
    }

    public void updatePaymentPanel() {

        JPanel panel = createPaymentPanel();
        cardPanel.add(panel, "Payment");
        cardPanel.revalidate();
        cardPanel.repaint();

    }

    public void addtoFlightList(String flightInfo) {
        flightsModel.addElement(flightInfo);
    }

}