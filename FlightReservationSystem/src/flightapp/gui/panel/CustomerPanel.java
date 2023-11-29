package flightapp.gui.panel;

import com.sun.tools.javac.Main;

import flightapp.domain.entity.*;
import flightapp.gui.main.MainView;
import flightapp.gui.navigation.NavigationController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    private DefaultListModel<String> flightsModel;

    public CustomerPanel(MainView mainView) {
        this.mainView = mainView;
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Initialize and add sub-panels
        cardPanel.add(createMainMenuPanel(), "MainMenu");
        cardPanel.add(createFlightSelectionPanel(), "FlightSelection");
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

    // private JPanel createSeatSelectionPanel() {
    //     JPanel seatSelectionPanel = new JPanel();
    //     // Define the number of rows and columns for seats



    //     int rows = 5; // For example, 5 rows
    //     int cols = 4; // For example, 4 seats per row

    //     seatSelectionPanel.setLayout(new GridLayout(rows, cols));

    //     ButtonGroup seatGroup = new ButtonGroup(); // To allow only one seat to be selected at a time

    //     for (int i = 0; i < rows * cols; i++) {
    //         JToggleButton seatButton = new JToggleButton("" + (i + 1));
    //         seatButton.addActionListener(e -> {
    //             // Handle seat selection
    //             // use seatButton.getText() to get the seat number
    //         });

    //         seatGroup.add(seatButton);
    //         seatSelectionPanel.add(seatButton);
    //     }

    //     selectSeatButton = new JButton("Confirm Seat");
    //     selectSeatButton.addActionListener(e -> {
    //         // Confirm the selected seat and move to the next step
    //         navigationController.navigateTo("Payment");
    //     });

    //     JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    //     bottomPanel.add(selectSeatButton);

    //     JPanel mainPanel = new JPanel(new BorderLayout());
    //     mainPanel.add(seatSelectionPanel, BorderLayout.CENTER);
    //     mainPanel.add(bottomPanel, BorderLayout.SOUTH);

    //     return mainPanel;
    // }

    private JPanel createSeatSelectionPanel() {
        JPanel seatSelectionPanel = new JPanel();

        Flight flight = mainView.getUserController().getSelectedFlight();
        System.out.println("Selected Flight: " + flight);
        if (flight == null) {
            
        }
        ArrayList<Seat> seatList = null;
        seatList = mainView.getUserController().getSelectedFlight().getSeatList();

        int rows = 0; // Amoount of rows on the aircraft
        int cols = 6; // Amount of seats per row
        
        for (int i = 0; i < seatList.size(); i++) {
            if (i % cols == 0) {
                rows++;
            }
        }
        // Define the number of rows and columns for seats
        
    
        seatSelectionPanel.setLayout(new GridLayout(rows, cols));
    
        // Use a list to store selected seats
        ArrayList<JToggleButton> selectedSeats = new ArrayList<>();
        for (int i = 0; i < rows * cols; i++) {
            JToggleButton seatButton = new JToggleButton("" + (i + 1));
            seatButton.setFocusPainted(false);
            seatButton.setBorderPainted(false);
            System.out.println("Seat: " + seatList.get(i).getSeatId());
            System.out.println("Seat Type: " + seatList.get(i).getSeatType());
            if (seatList.get(i).isBooked()) {
                seatButton.setEnabled(false);
                seatButton.setBackground(new Color(100, 100, 100));
                seatButton.repaint();
            }
            
            

            // Customize the color of the buttons based on another condition
            else if (seatList.get(i).getSeatType().strip().equals("Comfort")) 
            {
                seatButton.setBackground(new Color(255, 140, 0));
                seatButton.setForeground(Color.CYAN);
                System.out.println("is comfort");
                seatButton.repaint();
            }
            else if (seatList.get(i).getSeatType().equals("Business")) 
            {
                seatButton.setForeground(Color.YELLOW); 
                System.out.println("is business");
                seatButton.repaint();
            }
            else if (seatList.get(i).getSeatType().equals("Ordinary")) 
            {
                seatButton.setForeground(Color.orange); 
                System.out.println("ordinery aru");
                seatButton.repaint();
            }
            else 
            {
                seatButton.setForeground(Color.RED); 
                System.out.println("raui0nsan");
            }
            
            seatSelectionPanel.add(seatButton);
        }
    
        selectSeatButton = new JButton("Confirm Seats");
        selectSeatButton.addActionListener(e -> {
            // Confirm the selected seats and move to the next step
            // You can access the selected seats using the 'selectedSeats' list
            for (JToggleButton seat : selectedSeats) {
                System.out.println("Selected Seat: " + seat.getText());
            }
    
            // Move to the next step (e.g., Payment)
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