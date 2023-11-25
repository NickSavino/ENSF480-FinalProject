package flightapp.gui.panel;

import flightapp.main.MainApplication;

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
    public MainApplication mainApp;
    public LoginPanel(MainApplication mainApp) {
        this.mainApp = mainApp;
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(5, 0, 5, 0); // External padding

        // Create UI components for username and password inputs
        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        // Create a login button and add an action listener to it
        loginButton = new JButton("Login");
        exitButton = new JButton("Exit");
        registerButton = new JButton("Register");
        guestButton = new JButton("Guest");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainApp.onLogin(usernameField.getText(), passwordField.getPassword());
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });

        registerButton.addActionListener(e -> mainApp.onRegister());
        guestButton.addActionListener(e -> mainApp.browseAsGuest());

        // Add components to the login panel
        add(new JLabel("Username:"), gbc);
        add(usernameField, gbc);
        add(new JLabel("Password:"), gbc);
        add(passwordField, gbc);
        add(loginButton, gbc);
        add(registerButton, gbc);
        add(guestButton, gbc);
    }


}
