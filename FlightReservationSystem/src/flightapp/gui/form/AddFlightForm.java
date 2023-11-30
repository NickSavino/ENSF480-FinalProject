package flightapp.gui.form;

import flightapp.gui.main.MainView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddFlightForm extends JDialog {

    // Components
    private JComboBox<Object> planeComboBox;
    private JComboBox<Object> originComboBox;
    private JComboBox<Object> destinationComboBox;

    private JComboBox<Integer> durationComboxBox;
    private JComboBox<Object> flightCrewComboBox;
    private JTextField flightCostField;
    private JComboBox<Integer> dayBox;
    private JComboBox<Integer> monthBox;
    private JComboBox<Integer> yearBox;
    private JComboBox<Integer> hourBox;
    private JComboBox<Integer> minuteBox;

    private JPanel datePicker;
    private JButton submitButton;
    private JButton cancelButton;
    private AdminFormCallback callback;

    private MainView mainView;

    public AddFlightForm(JFrame parent, AdminFormCallback callback, MainView mainView) {
        super(parent, "Add a Flight", true);
        this.callback = callback;
        this.mainView = mainView;
        initializeUI();
        this.setLocationRelativeTo(parent); // Center on parent
    }

    // Helper method to add components to the panel
    void addComponent(Component component, int gridx, int gridy, int gridwidth, int fill, GridBagConstraints gbc) {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = gridwidth;
        gbc.fill = fill;
        this.add(component, gbc);
    }

    private void addComponentToDatePicker(JLabel label, JComboBox comboBox, JPanel panel, GridBagConstraints gbc, int gridx) {
        gbc.gridx = gridx * 2; // Label position
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        panel.add(label, gbc);

        gbc.gridx = gridx * 2 + 1; // Box position
        gbc.gridwidth = 1;
        panel.add(comboBox, gbc);
    }

    private void initializeUI() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.anchor = GridBagConstraints.WEST;

        Object[] planeList = mainView.getUserController().getAircraftsString().toArray();
        Object[] originList = mainView.getUserController().getLocationsString().toArray();
        Object[] destinationList = mainView.getUserController().getLocationsString().toArray();
        Object[] flightCrewList = mainView.getUserController().getFlightCrewsString().toArray();
        // Initialize components
        planeComboBox = new JComboBox<>(planeList);
        originComboBox = new JComboBox<>(originList);
        destinationComboBox = new JComboBox<>(destinationList);
        flightCrewComboBox = new JComboBox<>(flightCrewList);
        flightCostField = new JTextField();
        durationComboxBox = new JComboBox<Integer>();
        dayBox = new JComboBox<Integer>();
        monthBox = new JComboBox<Integer>();
        yearBox = new JComboBox<Integer>();
        hourBox = new JComboBox<Integer>();
        minuteBox = new JComboBox<Integer>();


        datePicker = new JPanel();

        for (int i = 1; i < 31; i++) {
            dayBox.addItem(i);
        }

        for (int i = 1; i < 12; i++) {
            monthBox.addItem(i);
        }

        for (int i = 2023; i < 2028; i++) {
            yearBox.addItem(i);
        }

        for (int i = 0; i < 24; i++) {
            durationComboxBox.addItem(i);
            hourBox.addItem(i);
        }

        for (int i = 0; i < 60; i++) {
            minuteBox.addItem(i);
        }

        submitButton = new JButton("Submit");
        cancelButton = new JButton("Cancel");

        // Add labels and components to the form
        addComponent(new JLabel("Plane:"), 0, 0, 1, GridBagConstraints.NONE, gbc);
        addComponent(planeComboBox, 1, 0, 2, GridBagConstraints.HORIZONTAL, gbc);
        addComponent(new JLabel("Origin:"), 0, 1, 1, GridBagConstraints.NONE, gbc);
        addComponent(originComboBox, 1, 1, 2, GridBagConstraints.HORIZONTAL, gbc);
        addComponent(new JLabel("Destination:"), 0, 2, 1, GridBagConstraints.NONE, gbc);
        addComponent(destinationComboBox, 1, 2, 2, GridBagConstraints.HORIZONTAL, gbc);
        addComponent(new JLabel("Assigned Crew:"), 0, 3, 1, GridBagConstraints.NONE, gbc);
        addComponent(flightCrewComboBox, 1, 3, 2, GridBagConstraints.HORIZONTAL, gbc);
        addComponent(new JLabel("Flight Cost:"), 0, 4, 1, GridBagConstraints.NONE, gbc);
        addComponent(flightCostField, 1, 4, 2, GridBagConstraints.HORIZONTAL, gbc);

        datePicker.setLayout(new GridBagLayout());
        GridBagConstraints dpGbc = new GridBagConstraints();
        dpGbc.insets = new Insets(5, 5, 5, 5);
        dpGbc.anchor = GridBagConstraints.WEST;

        addComponentToDatePicker(new JLabel("Day:"), dayBox, datePicker, dpGbc, 0);
        addComponentToDatePicker(new JLabel("Month:"), monthBox, datePicker, dpGbc, 1);
        addComponentToDatePicker(new JLabel("Year:"), yearBox, datePicker, dpGbc, 2);
        addComponentToDatePicker(new JLabel("Hour:"), hourBox, datePicker, dpGbc, 3);
        addComponentToDatePicker(new JLabel("Minute:"), minuteBox, datePicker, dpGbc, 4);

        addComponent(datePicker, 0, 5, 2, GridBagConstraints.NONE, gbc);
        addComponent(submitButton, 0, 6, 2, GridBagConstraints.NONE, gbc);
        addComponent(cancelButton, 1, 6, 2, GridBagConstraints.NONE, gbc);

        // Event listeners
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int planeId = extractIdFromSelectedItem((String) planeComboBox.getSelectedItem());
                String origin = (String) originComboBox.getSelectedItem();
                String destination = (String) destinationComboBox.getSelectedItem();

                //Check that origin and destination are different
                if (origin.equals(destination)) {
                    JOptionPane.showMessageDialog(mainView, "Origin and Destination cannot be the same.", "Format Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                Integer flightDuration = (Integer) durationComboxBox.getSelectedItem();
                int crewId = extractIdFromSelectedItem((String) flightCrewComboBox.getSelectedItem());
                System.out.println("Flight Crew ID: " + crewId);

                Integer flightCost;
                try {
                    flightCost = Integer.parseInt(flightCostField.getText());
                } catch (Exception Ex) {
                    JOptionPane.showMessageDialog(mainView, "Please enter a number for Flight Cost.", "Format Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Integer day = (Integer) dayBox.getSelectedItem();
                Integer month = (Integer) monthBox.getSelectedItem();
                Integer year = (Integer) yearBox.getSelectedItem();
                Integer hour = (Integer) hourBox.getSelectedItem();
                Integer minute = (Integer) minuteBox.getSelectedItem();

                onSubmit(planeId, origin, destination, flightDuration, crewId, flightCost, day, month, year, hour, minute);
                dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        pack();
    }

    private void onSubmit(int planeID, String origin, String destination, int flightDuration, int flightCrewID, int baseFlightCost, int day, int month, int year, int hour, int minute) {
        // Handle submission logic
        // Returns submission information and adds to list
        System.out.println("Flight Crew ID in un submit: " + flightCrewID);
        if(callback != null) {
            callback.onFlightAdded(planeID, origin, destination, flightDuration, flightCrewID, baseFlightCost, day, month, year, hour, minute);
        }
    }

    private void onCancel() {
        // Close the dialog
        this.dispose();
    }

    private int extractIdFromSelectedItem(String selectedItem) {
        // Assuming the format is "Name - ID"
        String[] parts = selectedItem.split(" - ");
        for (String part : parts) System.out.println(part);
        return Integer.parseInt(parts[1]);
    }
}
