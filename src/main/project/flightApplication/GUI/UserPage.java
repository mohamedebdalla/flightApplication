package main.project.flightApplication.GUI;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import main.project.flightApplication.Flight;
import main.project.flightApplication.Controller.FlightController;

public class UserPage extends JPanel {
    public JPanel top = new JPanel();
    public JPanel center = new JPanel();
    public JPanel side = new JPanel();
    private JLabel label = new JLabel("User Page");
    private FlightController flightController = new FlightController();
    private JComboBox<String> originField;
    private JComboBox<String> destField;
    private JComboBox<String> dateField;
    private JTextField flightField;
    private ArrayList<String> destination = flightController.getAllDest();
    private ArrayList<String> departureOrigin = flightController.getAllDepartureOrigin();
    private ArrayList<String> dates = flightController.getAllDepartureDates();
    private String[] origin = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5"};
    private String[] desination = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5"};
    

    public UserPage() {
        JButton browse = new JButton("Search Flights");
        JButton cancel = new JButton("Cancel Flight");

        top.setPreferredSize(new Dimension(100, 40));
        top.add(label);
        top.setBackground(Color.BLUE);
        center.setBackground(Color.CYAN);
        side.setPreferredSize(new Dimension(150, 100));
        side.setLayout(new FlowLayout(FlowLayout.LEFT));
        side.add(browse);
        side.add(cancel);

        this.setLayout(new BorderLayout());
        this.add(top, BorderLayout.NORTH);
        this.add(center, BorderLayout.CENTER);
        this.add(side, BorderLayout.WEST);

        browse.addActionListener(e -> {
            label.setText("Book a flight");
            Browse();
        });

        cancel.addActionListener(e -> {
            label.setText("Cancel a flight");
            Cancel();
        });

        registeredOptions();
    }

    public void registeredOptions() {
        JButton apply = new JButton("Get Credit Card");
        side.add(apply);
        apply.addActionListener(e -> {
            System.out.println("Apply for cc");
        });
    }

    public void Browse() {
        JPanel info = new JPanel();
        JPanel display = new JPanel(new GridLayout(0, 1)); // Panel to hold flight details
        JLabel originLabel = new JLabel("From:");
        originField = new JComboBox<>(departureOrigin.toArray(new String[departureOrigin.size()]));
        updateOriginField();
        JLabel destLabel = new JLabel("To:");
        destField = new JComboBox<>(destination.toArray(new String[destination.size()]));
        updateDestinationField();
        JLabel dateLabel = new JLabel("Date:");
        dateField = new JComboBox<>();
        JButton searchButton = new JButton("Search");
        JButton fetchDatesButton = new JButton("Fetch Dates");

        fetchDatesButton.addActionListener(e -> {
            String selectedOrigin = (String) originField.getSelectedItem();
            String selectedDestination = (String) destField.getSelectedItem();
            ArrayList<String> fetchedDates = flightController.getDatesByOriginAndDestination(selectedOrigin, selectedDestination);
            updateDateField(fetchedDates);
        });

        searchButton.addActionListener(e -> {
            String selectedOrigin = (String) originField.getSelectedItem();
            String selectedDestination = (String) destField.getSelectedItem();
            String selectedDate = (String) dateField.getSelectedItem();
            ArrayList<Flight> flights = flightController.getFlightByInput(selectedOrigin, selectedDestination, selectedDate);

            display.removeAll(); // Clear previous flight details

            for (Flight flight : flights) {
                JPanel flightPanel = new JPanel();
                JPanel detailsPanel = new JPanel(new GridLayout(5, 1));
                detailsPanel.setBorder(BorderFactory.createTitledBorder("Flight Details"));

                JLabel flightNumberLabel = new JLabel("Flight Number: " + flight.getFlightNumber());
                JLabel theOriginLabel = new JLabel("Origin: " + flight.getOrigin());
                JLabel destinationLabel = new JLabel("Destination: " + flight.getDestination());
                JLabel departureLabel = new JLabel("Departure: " + flight.getDepartureDate());
                JLabel arrivalLabel = new JLabel("Arrival:      " + flight.getArrivalDateTime());

                detailsPanel.add(flightNumberLabel);
                detailsPanel.add(theOriginLabel);
                detailsPanel.add(destinationLabel);
                detailsPanel.add(departureLabel);
                detailsPanel.add(arrivalLabel);

                display.add(detailsPanel); // Add each flight details panel to the display panel
            }

            // Update the UI after adding new flight details
            center.removeAll();
            center.add(display, BorderLayout.CENTER);
            revalidate();
            repaint();
        });

        info.setBackground(Color.green);
        info.setPreferredSize(new Dimension(500, 40));
        info.add(originLabel);
        info.add(originField);
        info.add(destLabel);
        info.add(destField);
        info.add(fetchDatesButton);
        info.add(dateLabel);
        info.add(dateField);
        info.add(searchButton);

        center.setLayout(new BorderLayout());
        center.add(info, BorderLayout.NORTH);
        revalidate();
        repaint();
    }

    public void Cancel() {
        // Similar structure to the Browse method, but for canceling flights
    }

    public void updateDestinationField() {
        int size = destination.size();
        String[] locationArray = destination.toArray(new String[size]);
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(locationArray);
        destField.setModel(model);
    }

    public void updateOriginField() {
        int size = departureOrigin.size();
        String[] departureOriginArray = departureOrigin.toArray(new String[size]);
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(departureOriginArray);
        originField.setModel(model);
    }

    public void updateDateField(ArrayList<String> dates) {
        String[] datesArray = dates.toArray(new String[0]);
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(datesArray);
        dateField.setModel(model);
    }
}
