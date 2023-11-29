package flightapp.gui.panel;

import flightapp.gui.main.MainView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton exitButton;
    private JButton registerButton;
    private JButton guestButton;
    public MainView mainApp;

    public LoginPanel(MainView mainApp) {
        this.mainApp = mainApp;
        setLayout(new BorderLayout(10, 10)); // Set main layout with horizontal and vertical gaps

        // Panel for form
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(5, 5, 5, 5); // External padding

        // UI components for username and password inputs
        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);

        // Login button and its action listener
        loginButton = new JButton("Login");
        this.mainApp.styleButton(loginButton);
        loginButton.setPreferredSize(new Dimension(150, 40));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int employeeId = Integer.parseInt(usernameField.getText());
                    mainApp.onLogin(employeeId, new String(passwordField.getPassword()));
                } catch (NumberFormatException ex) {
                    mainApp.onLogin(usernameField.getText(), new String(passwordField.getPassword()));
                }
            }
        });

        // Register and guest buttons with action listeners
        registerButton = new JButton("Register");
        this.mainApp.styleButton(registerButton);
        registerButton.setPreferredSize(new Dimension(150, 40));
        registerButton.addActionListener(e -> mainApp.onRegister());
        guestButton = new JButton("Guest");
        this.mainApp.styleButton(guestButton);
        guestButton.setPreferredSize(new Dimension(150, 40));
        guestButton.addActionListener(e -> mainApp.browseAsGuest());



        // Adding components to the formPanel
        formPanel.add(new JLabel("Username:"), gbc);
        formPanel.add(usernameField, gbc);
        formPanel.add(new JLabel("Password:"), gbc);
        formPanel.add(passwordField, gbc);
        formPanel.add(loginButton, gbc);


        // Panel for bottom buttons
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(registerButton, gbc);
        bottomPanel.add(guestButton, gbc);
        // Add formPanel and bottomPanel to LoginPanel
        add(formPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}
