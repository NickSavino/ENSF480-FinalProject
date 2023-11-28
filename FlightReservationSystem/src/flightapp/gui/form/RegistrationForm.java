package flightapp.gui.form;

import flightapp.gui.main.MainView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationForm extends JDialog{
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField creditCardNumberField;
    private JTextField creditCardSecurityCodeField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField houseNumberField;
    private JTextField streetField;
    private JTextField cityField;
    private JTextField provinceField;
    private JTextField countryField;
    private JTextField emailField;
    private JButton registerButton;
    private JButton cancelButton;

    private RegistrationCallback callback;

    public RegistrationForm(JFrame parent, RegistrationCallback callback) {
        super(parent, "Registration", true);
        initializeUI();
        this.callback = callback;
    }



    private void initializeUI() {
        setLayout(new GridLayout(0, 2));

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        creditCardNumberField = new JTextField();
        creditCardSecurityCodeField = new JTextField();
        firstNameField = new JTextField();
        lastNameField = new JTextField();
        houseNumberField = new JTextField();
        streetField = new JTextField();
        cityField = new JTextField();
        provinceField = new JTextField();
        countryField = new JTextField();
        emailField = new JTextField();

        registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onRegister();
            }
        });

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose()); // Close dialog

        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(new JLabel("Credit Card Number:"));
        add(creditCardNumberField);
        add(new JLabel("Credit Card Security Code:"));
        add(creditCardSecurityCodeField);
        add(new JLabel("First Name:"));
        add(firstNameField);
        add(new JLabel("Last Name:"));
        add(lastNameField);
        add(new JLabel("House Number:"));
        add(houseNumberField);
        add(new JLabel("Street:"));
        add(streetField);
        add(new JLabel("City:"));
        add(cityField);
        add(new JLabel("Province:"));
        add(provinceField);
        add(new JLabel("Country:"));
        add(countryField);
        add(new JLabel("Email:"));
        add(emailField);
        add(registerButton);
        add(cancelButton);

        pack();
        setLocationRelativeTo(getParent());
    }

    private void onRegister() {
        // Extract data from fields and use callback
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword()); // Convert char[] to String
        String creditCardNumber = creditCardNumberField.getText();
        String creditCardSecurityCode = creditCardSecurityCodeField.getText();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        int houseNumber = Integer.parseInt(houseNumberField.getText());
        String street = streetField.getText();
        String city = cityField.getText();
        String province = provinceField.getText();
        String country = countryField.getText();
        String email = emailField.getText();

        // Now pass this data to the callback
        callback.onRegistrationComplete(username, password, creditCardNumber, creditCardSecurityCode,
                firstName, lastName, houseNumber, street, city,
                province, country, email);

        dispose(); // Close the dialog
    }
}