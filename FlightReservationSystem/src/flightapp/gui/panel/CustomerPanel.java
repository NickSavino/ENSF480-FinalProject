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

public class CustomerPanel extends JPanel {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JButton selectFlightButton;
    private JButton selectSeatButton;
    private JButton makePaymentButton;

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
    
        selectSeatButton = new JButton("Confirm Seats");
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

        makePaymentButton = new JButton("Make Payment");
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

    private boolean userIsRegistered() {
        // Implement logic to determine if the current user is a registered customer
        return false; // Placeholder
    }

    public void addtoFlightList(String flightInfo) {
        flightsModel.addElement(flightInfo);
    }
}