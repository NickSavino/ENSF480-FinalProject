package flightapp.gui.panel;


import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AttendantPanel extends JPanel {
    private JComboBox<String> flightSelector;
    private JList<String> passengerList;
    private DefaultListModel<String> passengerListModel;

    public AttendantPanel() {
        setLayout(new BorderLayout());


        // Passenger list - to display passengers of the selected flight
        passengerListModel = new DefaultListModel<>();
        passengerList = new JList<>(passengerListModel);
        passengerList.setLayoutOrientation(JList.VERTICAL_WRAP);
        JScrollPane passengerScrollPane = new JScrollPane(passengerList);


        add(createSeatMapPanel(), BorderLayout.NORTH);
        add(passengerScrollPane, BorderLayout.CENTER);
    }

    private JPanel createSeatMapPanel() {
        JPanel seatMapPanel = new JPanel();
        int rows = 5; // Example: 5 rows
        int cols = 4; // Example: 4 seats per row

        seatMapPanel.setLayout(new GridLayout(rows, cols));

        // Example seat statuses (you will fetch these from your data model)
        boolean[][] seatStatuses = new boolean[rows][cols]; // true for occupied, false for free

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                JLabel seatLabel = new JLabel(String.format("%d%c", i + 1, 'A' + j), SwingConstants.CENTER);
                seatLabel.setOpaque(true);
                if (seatStatuses[i][j]) {
                    seatLabel.setBackground(Color.RED); // Occupied seat
                } else {
                    seatLabel.setBackground(Color.GREEN); // Free seat
                }
                seatMapPanel.add(seatLabel);
            }
        }

        return seatMapPanel;
    }

    private void updatePassengerList() {
        // Fetch and display passengers for the selected flight
        String selectedFlight = (String) flightSelector.getSelectedItem();
        List<String> passengers = getPassengersForFlight(selectedFlight);

        passengerListModel.clear();
        for (String passenger : passengers) {
            passengerListModel.addElement(passenger);
        }

        //updateSeatMap("32");

    }

    private void updateSeatMap(String selectedFlight) {
        // TDO: Fetch updated seat statuses
    }


    private List<String> getPassengersForFlight(String flight) {
        // TODO: Implement logic to fetch passengers based on the selected flight
        return List.of(); // Placeholder for fetched passenger data
    }
}