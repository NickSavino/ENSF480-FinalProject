package flightapp.gui.main;

import flightapp.gui.dialog.RegistrationDialog;
import flightapp.gui.panel.AdminPanel;
import flightapp.gui.panel.AttendantPanel;
import flightapp.gui.panel.CustomerPanel;
import flightapp.gui.panel.LoginPanel;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame{

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
        cardPanel.add(new LoginPanel(this), "Login");
        cardPanel.add(new CustomerPanel(), "Customer");
        cardPanel.add(new AttendantPanel(), "Attendant");
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

    }
    public void onLogin(String username, char[] password) {

        // TODO: Implement login Authentication

        System.out.println("Login attempted with username: " + username  + " and password: " + password);
        cardLayout.show(cardPanel, "Admin");
        logoutButton.setVisible(true);
    }

    private void performLogout() {
        // Logic to handle logout
        cardLayout.show(cardPanel, "Login");
        logoutButton.setVisible(false); // Hide the logout button on logout
    }

    public void onRegister() {
        RegistrationDialog registrationDialog = new RegistrationDialog(this);
        registrationDialog.setVisible(true);
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

        // Start the application on the EDT
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainView();
            }
        });
    }
}