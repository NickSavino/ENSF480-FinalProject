package flightapp.gui.form;

import flightapp.gui.main.MainView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class AddFlightForm extends JDialog {

    // Components
    private JComboBox<Object> planeComboBox;
    private JComboBox<Object> originComboBox;
    private JComboBox<Object> destinationComboBox;
    private JTextField datePicker; //Need to build a date picking method around this
    private JButton submitButton;
    private JButton cancelButton;
    private FormCallback callback;

    private MainView mainView;

    public AddFlightForm(JFrame parent, FormCallback callback, MainView mainView) {
        super(parent, "Add a Flight", true);
        this.callback = callback;
        this.mainView = mainView;
        initializeUI();
        this.setSize(400, 300);
        this.setLocationRelativeTo(parent); // Center on parent
    }

    private void initializeUI() {
        this.setLayout(new GridLayout(5, 2, 10, 10));

        Object[] planeList = mainView.getUserController().getAircraftsString().toArray();
        Object[] originList = mainView.getUserController().getLocationsString().toArray();
        Object[] destinationList = mainView.getUserController().getLocationsString().toArray();
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
