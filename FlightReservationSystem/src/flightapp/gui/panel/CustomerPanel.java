package flightapp.gui.panel;

import com.sun.mail.imap.protocol.Item;
import com.sun.tools.javac.Main;

import flightapp.controllers.FlightController;
import flightapp.domain.entity.*;
import flightapp.gui.main.MainView;
import flightapp.gui.navigation.NavigationController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CustomerPanel extends JPanel {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JButton selectFlightButton;
    private JButton selectSeatButton;
    private JButton makePaymentButton;

    Map<JToggleButton, Color> buttonColorMap = new HashMap<>();
    private NavigationController navigationController;
    private JButton backButton;

    private MainView mainView;

    private int totalCost = 0;

    private DefaultListModel<String> flightsModel;

    public CustomerPanel(MainView mainView) {
        this.mainView = mainView;
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Initialize and add sub-panels
        cardPanel.add(createMainMenuPanel(), "MainMenu");
        cardPanel.add(createFlightSelectionPanel(), "FlightSelection");

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
        becomeMemberButton.addActionListener(e -> {
            becomeMemberButton.setEnabled(true);
            if (mainView.getUserController().getCurrentCustomer().getStatus().equals("Registered"))
            {
                mainView.getUserController().makeCurrentCustomerAirlineMember();
                JOptionPane.showMessageDialog(mainMenuPanel, "You are now an Airline Member", "Airline Member", JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(mainMenuPanel, "Error. You are already an Airline Member!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        applyCreditCardButton.addActionListener(e -> {
            if (mainView.getUserController().getCurrentCustomer().hasCompanyCreditCard())
            {
                JOptionPane.showMessageDialog(mainMenuPanel, "Error. You already have a company credit card!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            boolean validInput = false;
            int creditScore = 0;
            while (!validInput)
            {
                String userStringInput = JOptionPane.showInputDialog(mainMenuPanel, "Enter your credit score:", "Airline Credit Card", JOptionPane.QUESTION_MESSAGE);
                if (userStringInput == null)
                {
                    return;
                }
                try
                {
                    creditScore = Integer.parseInt(userStringInput);
                    validInput = true;
                }
                catch (NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(mainMenuPanel, "Please enter a valid credit score", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            if (creditScore >= 500)
            {
                String creditCardInfo = mainView.getUserController().giveAirlineCreditCard();
                JOptionPane.showMessageDialog(mainMenuPanel, "Success! You now have an Airline Credit Card.\n\nNew credit card info:\n" + creditCardInfo, 
                    "Airline Credit Card", JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(mainMenuPanel, "Error. You do not have a high enough credit score.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

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
                // Need to find corresponding flight and set it as active in AirlineUserController
                String selectedFlight = list.getSelectedValue();
                mainView.getUserController().setSelectedFlightFromString(selectedFlight);
                updateSeatSelectionPanel();
                navigationController.navigateTo("SeatSelection");
            }
        });

        flightSelectionPanel.add(new JScrollPane(list), BorderLayout.CENTER);
        flightSelectionPanel.add(selectFlightButton, BorderLayout.SOUTH);

        return flightSelectionPanel;
    }



    private JPanel createSeatSelectionPanel() {
        JPanel seatSelectionPanel = new JPanel();

        Flight flight = mainView.getUserController().getSelectedFlight();
        if (flight == null) {
            seatSelectionPanel.add(new JLabel("Flight not selected"));
            return seatSelectionPanel;
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



        ActionListener seatActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JToggleButton button = (JToggleButton) e.getSource();
                if (button.isSelected()) {
                    selectedSeats.add(button);
                    selectedSeatIds.add(Integer.parseInt(button.getText()));
                } else {
                    Color color = buttonColorMap.get(button);
                    button.setBackground(color);
                    selectedSeats.remove(button);
                    selectedSeatIds.remove(Integer.valueOf(Integer.parseInt(button.getText())));
                }
            }
        };

        for (int i = 0; i < rows * cols; i++) {
            JToggleButton seatButton = new JToggleButton("" + (i + 1));
            seatButton.setOpaque(true);
            seatButton.setBorderPainted(false);
            Color seatColor = new Color(0, 0, 0);
            // Customize the color of the buttons based on another condition
            switch (seatList.get(i).getSeatType()) {
                case "Comfort" :
                    seatColor = (Color.CYAN);
                    break;
                case "Business" :
                    seatColor = (Color.YELLOW);
                    break;
                case "Ordinary" :
                    seatColor = (Color.ORANGE);
                    break;
                default:
                    seatColor = Color.RED;
            }
            seatButton.setBackground(seatColor);
            buttonColorMap.put(seatButton, seatColor);
            seatButton.setModel(new JToggleButton.ToggleButtonModel());
            if (seatList.get(i).isBooked()) {
                JButton disabledButton = new JButton("" + (i + 1));
                disabledButton.setEnabled(false);
                disabledButton.setBackground(Color.RED);
                seatSelectionPanel.add(disabledButton);
            } else {
               seatButton.addActionListener(seatActionListener);
               seatSelectionPanel.add(seatButton);
            }
        }

        //Create Scroll pane to store seat grid
        JScrollPane scrollPane = new JScrollPane(seatSelectionPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        selectSeatButton = new JButton("Confirm Seats");
        selectSeatButton.addActionListener(e -> {
            if (selectedSeats.size() == 0) {
                JOptionPane.showMessageDialog(this, "Please Select Your Seats.", "No Seats Selected", JOptionPane.WARNING_MESSAGE);
                return;
            }
            mainView.getUserController().setSelectedSeats(selectedSeatIds);
            updatePaymentPanel();
            navigationController.navigateTo("Payment");
        });

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(selectSeatButton);

        JPanel legendPanel = new JPanel();
        legendPanel.add(createLegendLabel("Comfort", Color.CYAN));
        legendPanel.add(createLegendLabel("Business", Color.YELLOW));
        legendPanel.add(createLegendLabel("Ordinary", Color.ORANGE));
        legendPanel.add(createLegendLabel("Selected", Color.GREEN));
        legendPanel.add(createLegendLabel("Unavailable", Color.RED));


        JPanel mainPanel = new JPanel(new BorderLayout());

        mainPanel.add(legendPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        return mainPanel;
    }

    private JLabel createLegendLabel(String text, Color color) {
        JLabel label = new JLabel(text);
        label.setOpaque(true);
        label.setBackground(color);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        label.setPreferredSize(new Dimension(100, 20)); // Set preferred size for uniformity
        return label;
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
        JPanel paymentPanel = new JPanel(new GridLayout(0, 1));
        // Components for payment options
        JCheckBox loungeAccess = new JCheckBox("Lounge Access");
        JCheckBox cancellationInsurance = new JCheckBox("Cancellation Insurance");
        JCheckBox useCompanionTicket = new JCheckBox("Use Companion Ticket");
        boolean isAirlineMember = mainView.getUserController().getCurrentCustomer().isAirlineMember();
        useCompanionTicket.setEnabled(isAirlineMember);

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

        makePaymentButton = new JButton("Make Payment");
        // Add ActionListener to handle payment logic

        makePaymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean loungeAccessSelected = loungeAccess.isSelected();
                boolean cancellationInsuranceSelected = cancellationInsurance.isSelected();
                boolean useCompanionTicketSelected = mainView.getUserController().isCustomerLoggedIn() && useCompanionTicket.isSelected();
                String creditCardNumber = creditCardField.getText();

                if (creditCardNumber.length() != 16) {
                    JOptionPane.showMessageDialog(mainView, "Credit Card Must be 16 digits in length", "Invalid Card Number", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Integer securityCode = Integer.parseInt(securityCodeField.getText());
                if (securityCode.toString().length() != 3) {
                    JOptionPane.showMessageDialog(mainView, "Security Code Must be 3 digits in length", "Invalid Security Code", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    mainView.getUserController().purchaseForCustomer(loungeAccessSelected, cancellationInsuranceSelected, useCompanionTicketSelected, creditCardNumber, securityCode);
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