package flightapp.gui.main;

import flightapp.DatabaseConnection;
import flightapp.controllers.AirlineUserController;
import flightapp.gui.form.RegistrationCallback;
import flightapp.gui.form.RegistrationForm;
import flightapp.gui.panel.*;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame implements RegistrationCallback {

    private AirlineUserController userController;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JButton exitButton;
    private JButton logoutButton;
    private JLabel titleLabel;

    private CustomerPanel customerPanel;
    private GuestPanel guestPanel;
    private JLabel loggedInLabel;
    public MainView() {
        // Initialize the main frame
        super("Flight Reservation System");
        userController = new AirlineUserController();

        //Wait for initialization to finish
        while (!userController.getInitalizationStatus()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        UIManager.put("ToggleButton.select", Color.GREEN);
        //Specify on action on close
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Specify Minimum Size
        setMinimumSize(new Dimension(600, 400));
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(50, 50, 50));

        //Create a title label
        titleLabel = new JLabel("Flight Reservation System", SwingConstants.CENTER);
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEtchedBorder(Color.black, Color.white));



        // Create an exit button
        exitButton = new JButton("Exit");
        styleButton(exitButton);
        exitButton.addActionListener(e -> System.exit(0));
        logoutButton = new JButton("Logout");
        styleButton(logoutButton);

        logoutButton.addActionListener(e -> performLogout());
        logoutButton.setVisible(false); // Initially hidden

        // Create a panel to hold the back and exit buttons
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setOpaque(false);
        buttonPanel.add(exitButton, BorderLayout.EAST);
        buttonPanel.add(logoutButton, BorderLayout.WEST);

        //Create a card layout for managing different user options
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setBorder(BorderFactory.createBevelBorder(1, Color.BLACK, Color.WHITE));
        cardPanel.setOpaque(false);

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());
        titlePanel.add(titleLabel, BorderLayout.NORTH);

        loggedInLabel = new JLabel("\t\t Not Logged in.");
        titlePanel.add(loggedInLabel, BorderLayout.SOUTH);

        //Instantiate and add user panels to cardPanel
        cardPanel.add(new LoginPanel(this), "Login");
        cardPanel.add(new AttendantPanel(), "Attendant");
        cardPanel.add(new AirlineAgentPanel(this), "AirlineAgent");
        cardPanel.add(new AdminPanel(this), "Admin");
        this.customerPanel = new CustomerPanel(this);
        cardPanel.add(customerPanel, "Customer");
        this.guestPanel = new GuestPanel(this);
        cardPanel.add(guestPanel, "Guest");


        //Set Layout to organize main page elements
        setLayout(new BorderLayout());
        add(titlePanel, BorderLayout.NORTH);
        add(cardPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        // Center the frame on the screen
        setLocationRelativeTo(null);
        // Set the frame to be visible
        setVisible(true);
        cardLayout.show(cardPanel, "Login");

    }
    public void onLogin(String username, String password) {
        boolean loginSuccess = userController.customerLogin(username, password);

        if (loginSuccess) {
            if (userController.isCustomerLoggedIn()) {
                customerPanel = new CustomerPanel(this);
                cardPanel.add(customerPanel);
                cardLayout.show(cardPanel, "Customer");
                loggedInLabel.setText("Logged in as: " + username);
                JOptionPane.showMessageDialog(this, userController.getPromotionalNews(), "Promotional News", JOptionPane.OK_OPTION);
            }
            logoutButton.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password", "Login Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void onLogin(int employeeId, String password) {
        boolean loginSuccess = userController.employeeLogin(employeeId, password);

        if (loginSuccess) {
            if (userController.getEmployeeType().equals("Admin")) {
                AdminPanel adminPanel = new AdminPanel(this);
                cardPanel.add(adminPanel, "Admin");
                cardLayout.show(cardPanel, "Admin");
                loggedInLabel.setText("\t\tLogged in as: Admin - " + employeeId);

            } else if (userController.getEmployeeType().equals("Flight Attendant")) {
                AirlineAgentPanel agentPanel = new AirlineAgentPanel(this);
                cardPanel.add(agentPanel, "AirlineAgent");
                cardLayout.show(cardPanel, "AirlineAgent");
                loggedInLabel.setText("\t\t Logged in as: Flight Attendant - " + employeeId);

            } else if (userController.getEmployeeType().equals("Airline Agent")) {
                AirlineAgentPanel agentPanel = new AirlineAgentPanel(this);
                cardPanel.add(agentPanel, "AirlineAgent");
                cardLayout.show(cardPanel, "AirlineAgent");
                loggedInLabel.setText("\t\t Logged in as: Airline Agent - " + employeeId);

            }
            logoutButton.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password", "Login Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void performLogout() {
        // Logic to handle logout
        cardLayout.show(cardPanel, "Login");
        loggedInLabel.setText("\t\t Not Logged in.");
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
        loggedInLabel.setText("\t\tLogged in as: " + username + " - " + firstName + " " + lastName);
        cardLayout.show(cardPanel, "Customer");
    }

    public void browseAsGuest() {
        loggedInLabel.setText("\t\t Browsing as Guest.");
        cardLayout.show(cardPanel, "Guest");
        userController.browseAsGuest();
    }
    public static void main(String[] args) {
        // Set the look and feel to the system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
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

    public AirlineUserController getUserController() {
        return userController;
    }

    public void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(100, 100, 100));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.repaint();
    }

    public CustomerPanel getCustomerPanel() {
        return this.customerPanel;
    }

    public GuestPanel getGuestPanel() { return this.guestPanel; }

}