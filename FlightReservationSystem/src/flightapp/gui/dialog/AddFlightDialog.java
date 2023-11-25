package flightapp.gui.dialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddFlightDialog extends JDialog {

    // Components
    private JComboBox<String> planeComboBox;
    private JComboBox<String> originComboBox;
    private JComboBox<String> destinationComboBox;
    private JTextField datePicker; //Need to build a date picking method around this
    private JButton submitButton;
    private JButton cancelButton;

    private DialogCallback callback;

    public AddFlightDialog(JFrame parent, DialogCallback callback) {
        super(parent, "Add a Flight", true);
        this.callback = callback;
        initializeUI();
        this.setSize(400, 300);
        this.setLocationRelativeTo(parent); // Center on parent
    }

    private void initializeUI() {
        this.setLayout(new GridLayout(5, 2, 10, 10));

        String[] planeList = {"747", "Spitfire", "A-10"};
        String[] originList = {"Dallas", "Calgary", "Atlanta"};
        String[] destinationList = {"Dallas", "Calgary", "Atlanta"};
        // Initialize components
        planeComboBox = new JComboBox<>(planeList);
        originComboBox = new JComboBox<>(originList);
        destinationComboBox = new JComboBox<>(destinationList);
        datePicker = new JTextField();
        submitButton = new JButton("Submit");
        cancelButton = new JButton("Cancel");

        // Add components to dialog
        this.add(new JLabel("Plane:"));
        this.add(planeComboBox);
        this.add(new JLabel("Origin:"));
        this.add(originComboBox);
        this.add(new JLabel("Destination:"));
        this.add(destinationComboBox);
        this.add(new JLabel("Date:"));
        this.add(datePicker);
        this.add(submitButton);
        this.add(cancelButton);

        // Event listeners
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onSubmit((String) planeComboBox.getSelectedItem(), (String) originComboBox.getSelectedItem(), (String) destinationComboBox.getSelectedItem());
                dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });
    }

    private void onSubmit(String plane, String origin, String destination) {
        // Handle submission logic
        // Returns submission information and adds to list
        String flightInfo = plane + " - " + origin + " to " + destination;
        if(callback != null) {
            callback.onFlightAdded(flightInfo);
        }
    }

    private void onCancel() {
        // Close the dialog
        this.dispose();
    }
}
