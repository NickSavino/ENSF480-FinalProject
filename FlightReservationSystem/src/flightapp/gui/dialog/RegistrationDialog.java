package flightapp.gui.dialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationDialog extends JDialog {
    private JTextField usernameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JButton registerButton;
    private JButton cancelButton;

    public RegistrationDialog(JFrame parent) {
        super(parent, "Registration", true);
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new GridLayout(0, 2));

        usernameField = new JTextField();
        emailField = new JTextField();
        passwordField = new JPasswordField();
        confirmPasswordField = new JPasswordField();

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
        add(new JLabel("Email:"));
        add(emailField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(new JLabel("Confirm Password:"));
        add(confirmPasswordField);
        add(registerButton);
        add(cancelButton);

        pack();
        setLocationRelativeTo(getParent());
    }

    private void onRegister() {
        // TODO: Implement registration logic
        // Validate input, check password match, etc.
    }
}