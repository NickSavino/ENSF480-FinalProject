package flightapp.gui.panel;

import flightapp.domain.entity.Flight;
import flightapp.domain.entity.Seat;
import flightapp.gui.main.MainView;
import flightapp.gui.navigation.NavigationController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AirlineAgentPanel extends JPanel {
    Map<JButton, Color> buttonColorMap = new HashMap<>();

    private JComboBox<String> flightSelector;
    private JPanel seatMapPanel;
    private NavigationController navigationController;


    private int rows = 5;
    private int cols = 4;
    private MainView mainView;

    CardLayout cardLayout;

    JPanel cardPanel;

    public AirlineAgentPanel(MainView mainView) {
        this.mainView = mainView;
        setLayout(new BorderLayout());


        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        JButton emptyButton = new JButton();
        navigationController = new NavigationController(cardPanel, cardLayout, emptyButton);

        flightSelector = new JComboBox<>();

        for (String flight : mainView.getUserController().getFlightsString()) {
            flightSelector.addItem(flight);
        }

        flightSelector.addActionListener(e -> updateSeatMap());
        updateSeatMap();

        add(flightSelector, BorderLayout.NORTH);

        add(cardPanel, BorderLayout.CENTER);
    }

    private JPanel createSeatMapPanel() {
        JPanel seatSelectionPanel = new JPanel();

        Flight flight = mainView.getUserController().getSelectedFlight();
        if (flight == null) {
            seatSelectionPanel.add(new JLabel("Flight not selected"));
            return seatSelectionPanel;
        }
        ArrayList<Seat> seatList = null;
        String selectedFlight = (String) flightSelector.getSelectedItem();
        mainView.getUserController().setSelectedFlightFromString(selectedFlight);
        seatList = mainView.getUserController().getSelectedFlight().getSeatList();


        // Define the number of rows and columns for seats
        int rows = 0; // Amoount of rows on the aircraft
        int cols = 6; // Amount of seats per row

        for (int i = 0; i < seatList.size(); i++) {
            if (i % cols == 0) {
                rows++;
            }
        }
        seatSelectionPanel.setLayout(new GridLayout(rows, cols));


        for (int i = 0; i < rows * cols; i++) {
            JButton seatButton = new JButton("" + (i + 1));
            seatButton.setEnabled(false);
            seatButton.setOpaque(true);
            seatButton.setBorderPainted(false);
            Color seatColor = new Color(0, 0, 0);
            // Customize the color of the buttons based on another condition
            switch (seatList.get(i).getSeatType()) {
                case "Comfort" :
                    seatColor = (Color.CYAN);
                    break;
                case "Business" :
                    seatColor = (Color.YELLOW);
                    break;
                case "Ordinary" :
                    seatColor = (Color.ORANGE);
                    break;
                default:
                    seatColor = Color.RED;
            }
            seatButton.setBackground(seatColor);
            buttonColorMap.put(seatButton, seatColor);
            if (seatList.get(i).isBooked()) {
                JButton disabledButton = new JButton("" + (i + 1));
                disabledButton.setEnabled(false);
                disabledButton.setBackground(Color.RED);
                seatSelectionPanel.add(disabledButton);
            } else {
                seatSelectionPanel.add(seatButton);
            }
        }

        //Create Scroll pane to store seat grid
        JScrollPane scrollPane = new JScrollPane(seatSelectionPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


        JPanel legendPanel = new JPanel();
        legendPanel.add(createLegendLabel("Comfort", Color.CYAN));
        legendPanel.add(createLegendLabel("Business", Color.YELLOW));
        legendPanel.add(createLegendLabel("Ordinary", Color.ORANGE));
        legendPanel.add(createLegendLabel("Selected", Color.GREEN));
        legendPanel.add(createLegendLabel("Unavailable", Color.RED));

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(legendPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        return mainPanel;
    }

    private void updateSeatMap() {
        String selectedFlight = (String) flightSelector.getSelectedItem();
        mainView.getUserController().setSelectedFlightFromString(selectedFlight);
        cardPanel.add(createSeatMapPanel(), "Seat Map");
        navigationController.navigateTo("Seat Map");

    }

    private boolean[][] fetchSeatStatusesForFlight(String flight) {
        // TODO: Fetch the seat occupancy data for the selected flight
        // Example return for a placeholder
        return new boolean[rows][cols]; // Replace with real data
    }

    private JLabel createLegendLabel(String text, Color color) {
        JLabel label = new JLabel(text);
        label.setOpaque(true);
        label.setBackground(color);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        label.setPreferredSize(new Dimension(100, 20)); // Set preferred size for uniformity
        return label;
    }

    private int extractIdFromSelectedItem(String selectedItem) {
        // Assuming the format is "Name - ID"
        String[] parts = selectedItem.split(" - ");
        return Integer.parseInt(parts[1]);
    }
}
