package flightapp.gui.form;

import javax.swing.*;
import java.awt.*;

public class AddCrewForm extends JDialog {

    // Components
    private JTextField crewNameField;
    private JTextField crewPositionField;
    private JButton submitButton;
    private JButton cancelButton;

    private FormCallback callback;

    public AddCrewForm(JFrame parent, FormCallback callback) {
        super(parent, "Add a Crew Member", true);
        this.callback = callback;
        initializeUI();
        this.setSize(300, 200);
        this.setLocationRelativeTo(parent); // Center on parent
    }

    private void initializeUI() {
        this.setLayout(new GridLayout(3, 2, 10, 10));

        // Initialize components
        crewNameField = new JTextField();
        crewPositionField = new JTextField();
        submitButton = new JButton("Submit");
        cancelButton = new JButton("Cancel");

        // Add components to dialog
        this.add(new JLabel("Name:"));
        this.add(crewNameField);
        this.add(new JLabel("Position:"));
        this.add(crewPositionField);
        this.add(submitButton);
        this.add(cancelButton);

        // Event listeners
        submitButton.addActionListener(e -> onSubmit());
        cancelButton.addActionListener(e -> onCancel());
    }

    private void onSubmit() {
        // Validate input, etc.
        String crewName = crewNameField.getText();
        String crewPosition = crewPositionField.getText();
        // Return submission information and potentially add to a list
        if (callback != null) {
            callback.onCrewAdded(crewName, crewPosition);
        }
        dispose();
    }

    private void onCancel() {
        dispose();
    }
}