package flightapp.gui.form;

import javax.swing.*;
import java.awt.*;

public class AddAircraftForm extends JDialog {

    // Components
    private JTextField aircraftModelField;
    private JTextField aircraftSeatsField;
    private JButton submitButton;
    private JButton cancelButton;
    private FormCallback callback;

    public AddAircraftForm(JFrame parent, FormCallback callback) {
        super(parent, "Add an Aircraft", true);
        this.callback = callback;
        initializeUI();
        this.setSize(300, 200);
        this.setLocationRelativeTo(parent); // Center on parent
    }

    private void initializeUI() {
        this.setLayout(new GridLayout(3, 2, 10, 10));

        // Initialize components
        aircraftModelField = new JTextField();
        aircraftSeatsField = new JTextField();
        submitButton = new JButton("Submit");
        cancelButton = new JButton("Cancel");

        // Add components to dialog
        this.add(new JLabel("Model:"));
        this.add(aircraftModelField);
        this.add(new JLabel("Seats:"));
        this.add(aircraftSeatsField);
        this.add(submitButton);
        this.add(cancelButton);

        // Event listeners
        submitButton.addActionListener(e -> onSubmit());
        cancelButton.addActionListener(e -> onCancel());
    }

    private void onSubmit() {
        // Validate input, etc.
        String model = aircraftModelField.getText();
        int seats = Integer.parseInt(aircraftSeatsField.getText()); // Add error handling for non-integer input
        // Return submission information and potentially add to a list
        if (callback != null) {
            callback.onAircraftAdded(model, seats);
        }
        dispose();
    }

    private void onCancel() {
        dispose();
    }
}