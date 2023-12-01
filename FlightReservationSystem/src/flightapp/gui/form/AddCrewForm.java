package flightapp.gui.form;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AddCrewForm extends JDialog {

    // Components
    private JTextField crewIdField;
    private JTextField crewNameField;
    private JTextField assignedFlightIdField;
    private JButton submitButton;
    private JButton cancelButton;
    private ArrayList<Integer> crewIds;
    private AdminFormCallback callback;

    public AddCrewForm(JFrame parent, AdminFormCallback callback, ArrayList<Integer> crewIds) {
        super(parent, "Add a Crew Member", true);
        this.crewIds = crewIds;
        this.callback = callback;
        initializeUI();
        this.setSize(300, 250);
        this.setLocationRelativeTo(parent); // Center on parent
    }

    private void initializeUI() {
        this.setLayout(new GridLayout(4, 2, 10, 10));

        // Initialize components
        crewIdField = new JTextField();
        crewNameField = new JTextField();
        assignedFlightIdField = new JTextField();
        submitButton = new JButton("Submit");
        cancelButton = new JButton("Cancel");

        // Add components to dialog
        this.add(new JLabel("Crew ID:"));
        this.add(crewIdField);
        this.add(new JLabel("Name:"));
        this.add(crewNameField);
        this.add(new JLabel("Assigned Flight ID:"));
        this.add(assignedFlightIdField);
        this.add(submitButton);
        this.add(cancelButton);

        // Event listeners
        submitButton.addActionListener(e -> onSubmit());
        cancelButton.addActionListener(e -> onCancel());
    }

    private void onSubmit() {
        // Validate input
        try {
            int crewId = Integer.parseInt(crewIdField.getText());
            String crewName = crewNameField.getText();
            if (crewName.isEmpty()) 
            {
                JOptionPane.showMessageDialog(this, "Please fill all input fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (crewIds.contains(crewId)) 
            {
                JOptionPane.showMessageDialog(this, "Crew ID already exists. Please choose another Crew ID.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int assignedFlightId = Integer.parseInt(assignedFlightIdField.getText());

            if (callback != null) {
                callback.onCrewAdded(crewId, crewName, assignedFlightId);
            }
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values for Crew ID and Assigned Flight ID", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onCancel() {
        dispose();
    }
}
