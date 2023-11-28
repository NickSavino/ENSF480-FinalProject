package flightapp.gui.main;

import flightapp.DatabaseConnection;
import flightapp.controllers.AirlineUserController;
import flightapp.gui.form.FormCallback;
import flightapp.gui.form.RegistrationCallback;
import flightapp.gui.form.RegistrationForm;
import flightapp.gui.panel.*;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame implements RegistrationCallback {

    AirlineUserController userController;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JButton exitButton;
    private JButton logoutButton;
    private JLabel titleLabel;
    public MainView() {
        // Initialize the main frame
        super("Flight Reservation System");
        //Specify on action on close
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Specify Minimum Size
        setMinimumSize(new Dimension(400, 200));

        //Create a title label
        titleLabel = new JLabel("Flight Reservation System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        // Create an exit button
        exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));

        logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> performLogout());
        logoutButton.setVisible(false); // Initially hidden

        // Create a panel to hold the back and exit buttons
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.add(exitButton, BorderLayout.EAST);
        buttonPanel.add(logoutButton, BorderLayout.WEST);

        //Create a card layout for managing different user options
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);


        //Instantiate and add user panels to cardPanel
        cardPanel.add(new GuestPanel(this), "Guest");
        cardPanel.add(new LoginPanel(this), "Login");
        cardPanel.add(new CustomerPanel(), "Customer");
        cardPanel.add(new AttendantPanel(), "Attendant");
        cardPanel.add(new AirlineAgentPanel(), "AirlineAgent");
        cardPanel.add(new AdminPanel(this), "Admin");
        //Set Layout to organize main page elements
        setLayout(new BorderLayout());
        add(titleLabel, BorderLayout.NORTH);
        add(cardPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        // Center the frame on the screen
        setLocationRelativeTo(null);
        // Set the frame to be visible
        setVisible(true);
        cardLayout.show(cardPanel, "Login");

        userController = new AirlineUserController();


    }
    public void onLogin(String username, String password) {
        cardLayout.show(cardPanel, "Attendant");
//        userController = new AirlineUserController();
//
//        int userId;
//        try {
//            userId = Integer.parseInt(username); // Assuming the username is a user ID
//        } catch (NumberFormatException e) {
//            JOptionPane.showMessageDialog(this, "Invalid User ID", "Login Error", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//
//        boolean loginSuccess = userController.customerLogin(userId, password) ||
//                userController.employeeLogin(userId, password);
//
//        if (loginSuccess) {
//            if (userController.isCustomerLoggedIn()) {
//                cardLayout.show(cardPanel, "Customer");
//            } else if (userController.isEmployeeLoggedIn()) {
//                cardLayout.show(cardPanel, "Employee");
//            }
//            logoutButton.setVisible(true);
//        } else {
//            JOptionPane.showMessageDialog(this, "Invalid username or password", "Login Error", JOptionPane.ERROR_MESSAGE);
//        }
    }

    private void performLogout() {
        // Logic to handle logout
        cardLayout.show(cardPanel, "Login");
        logoutButton.setVisible(false); // Hide the logout button on logout
    }

    public void onRegister() {
        RegistrationForm registrationForm = new RegistrationForm(this, this);
        registrationForm.setVisible(true);
    }

    public void onRegistrationComplete(String username, String password, String creditCardNumber,
                                String creditCardSecurityCode, String firstName, String lastName,
                                int houseNumber, String street, String city,
                                String province, String country, String email) {
        System.out.println("Registration Complete");

        userController.customerSignup( username,  password,  creditCardNumber, creditCardSecurityCode, firstName, lastName,
         houseNumber, street, city, province, country, email);
        JOptionPane.showMessageDialog(this, "Registration Successful!");
    }

    public void browseAsGuest() {
        cardLayout.show(cardPanel, "Customer");
    }
    public static void main(String[] args) {
        // Set the look and feel to the system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException |
                 IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        DatabaseConnection.initialize();

        // Start the application on the EDT
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainView();
            }
        });
    }
}