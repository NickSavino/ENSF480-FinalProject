package flightapp.gui.panel;

import javax.swing.*;
import java.awt.*;

public class AirlineAgentPanel extends JPanel {

    private JComboBox<String> flightSelector;
    private JPanel seatMapPanel;

    private int rows = 5;
    private int cols = 4;

    public AirlineAgentPanel() {
        setLayout(new BorderLayout());

        flightSelector = new JComboBox<>();
        // Populate flightSelector with all available flight IDs or names...

        flightSelector.addActionListener(e -> updateSeatMap());

        add(flightSelector, BorderLayout.NORTH);

        // Initialize the seat map panel (empty at first)
        seatMapPanel = new JPanel();
        add(seatMapPanel, BorderLayout.CENTER);
    }

    private void createSeatMap(String selectedFlight) {
        seatMapPanel.removeAll();
        seatMapPanel.setLayout(new GridLayout(rows, cols)); // Set rows and cols based on selected flight

        // Example data - replace with real data
        boolean[][] seatStatuses = fetchSeatStatusesForFlight(selectedFlight);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                JLabel seatLabel = new JLabel(String.format("%d%c", i + 1, 'A' + j), SwingConstants.CENTER);
                seatLabel.setOpaque(true);
                seatLabel.setBackground(seatStatuses[i][j] ? Color.RED : Color.GREEN);
                seatMapPanel.add(seatLabel);
            }
        }

        seatMapPanel.revalidate();
        seatMapPanel.repaint();
    }

    private void updateSeatMap() {
        String selectedFlight = (String) flightSelector.getSelectedItem();
        createSeatMap(selectedFlight);
    }

    private boolean[][] fetchSeatStatusesForFlight(String flight) {
        // TODO: Fetch the seat occupancy data for the selected flight
        // Example return for a placeholder
        return new boolean[rows][cols]; // Replace with real data
    }
}
