package main.project.flightApplication.GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import main.project.flightApplication.Flight;
import main.project.flightApplication.Admin;

public class FlightGUI extends JFrame {

    private Admin admin = new Admin();

    public void displayFlight(Flight flight) {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);

        // Panel for image
        JPanel imagePanel = new JPanel();
        JLabel imageLabel = createImageLabel("flightPic.jpg"); // Change this path to your image
        imagePanel.add(imageLabel);

        // Panel for flight details (right side)
        JPanel detailsPanel = new JPanel(new GridLayout(5, 1));
        detailsPanel.setBorder(BorderFactory.createTitledBorder("Flight Details"));

        JLabel flightNumberLabel = new JLabel("Flight Number: " + flight.getFlightNumber());
        JLabel originLabel = new JLabel("Origin: " + flight.getOrigin());
        JLabel destinationLabel = new JLabel("Destination: " + flight.getDestination());
        JLabel departureLabel = new JLabel("Departure: " + flight.getDepartureDateTime());
        JLabel arrivalLabel = new JLabel("Arrival: " + flight.getArrivalDateTime());

        detailsPanel.add(flightNumberLabel);
        detailsPanel.add(originLabel);
        detailsPanel.add(destinationLabel);
        detailsPanel.add(departureLabel);
        detailsPanel.add(arrivalLabel);

        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(imagePanel, gbc);

        gbc.gridx = 1;
        mainPanel.add(detailsPanel, gbc);

        add(mainPanel);
        pack();
        setVisible(true);
    }

    private JLabel createImageLabel(String imagePath) {
        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image image = imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(image);
        return new JLabel(scaledIcon);
    }

    public static void main(String[] args) {
        FlightGUI display = new FlightGUI();
        Admin admin = new Admin();
        ArrayList<Flight> flights = admin.getAllFlights();

        if (!flights.isEmpty()) {
            display.displayFlight(flights.get(0));
        }
        //...
    }
}