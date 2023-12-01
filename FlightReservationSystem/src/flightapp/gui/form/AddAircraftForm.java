package flightapp.gui.form;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AddAircraftForm extends JDialog {

    // Components
    private JTextField aircraftIdField;
    private JTextField aircraftModelField;
    private JTextField comfortSeatsField;
    private JTextField businessSeatsField;
    private JTextField ordinarySeatsField;
    private JButton submitButton;
    private JButton cancelButton;
    private AdminFormCallback callback;
    ArrayList<Integer> aircraftIds;

    public AddAircraftForm(JFrame parent, AdminFormCallback callback, ArrayList<Integer> aircraftIds) {
        super(parent, "Add an Aircraft", true);
        this.callback = callback;
        this.aircraftIds = aircraftIds;
        initializeUI();
        this.setSize(300, 200);
        this.setLocationRelativeTo(parent); // Center on parent
    }

    private void initializeUI() {
        this.setLayout(new GridLayout(3, 2, 10, 10));

        // Initialize components
        aircraftIdField = new JTextField();
        aircraftModelField = new JTextField();
        comfortSeatsField = new JTextField();
        businessSeatsField = new JTextField();
        ordinarySeatsField = new JTextField();
        submitButton = new JButton("Submit");
        cancelButton = new JButton("Cancel");

        // Add components to dialog
        this.add(new JLabel("Aircraft Id:"));
        this.add(aircraftIdField);
        this.add(new JLabel("Model:"));
        this.add(aircraftModelField);
        this.add(new JLabel("Comfort Seats:"));
        this.add(comfortSeatsField);
        this.add(new JLabel("Business Seats:"));
        this.add(businessSeatsField);
        this.add(new JLabel("Ordinary Seats:"));
        this.add(ordinarySeatsField);
        this.add(submitButton);
        this.add(cancelButton);

        // Event listeners
        submitButton.addActionListener(e -> onSubmit());
        cancelButton.addActionListener(e -> onCancel());
    }

    private void onSubmit() {
        // Validate input, etc.
        try
        {
            int id = Integer.parseInt(aircraftIdField.getText());
            String model = aircraftModelField.getText();
            if (model.equals(""))
            {
                JOptionPane.showMessageDialog(null, "Please fill all fields.");
                return;
            }
            if (aircraftIds.contains(id))
            {
                JOptionPane.showMessageDialog(null, "Aircraft ID already exists. Please choose another aircraft ID.");
                return;
            }
            int comfortSeats = Integer.parseInt(comfortSeatsField.getText()); // Add error handling for non-integer input
            int businessSeats = Integer.parseInt(businessSeatsField.getText());
            int ordinarySeats = Integer.parseInt(ordinarySeatsField.getText());
            // Return submission information and potentially add to a list
            if (callback != null) {
                callback.onAircraftAdded(id, model, comfortSeats, businessSeats, ordinarySeats);
            }
            dispose();
        }
        catch (NumberFormatException e)
        {
            JOptionPane.showMessageDialog(null, "Please enter a numerical value for the aircraft ID and the number of seats.");
        }
    }

    private void onCancel() {
        dispose();
    }
}