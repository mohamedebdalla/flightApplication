package main.project.flightApplication.GUI;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import main.project.flightApplication.Flight;
import main.project.flightApplication.Admin;

public class FlightGUI extends JFrame {
    private Admin admin = new Admin();
    private int currentIndex = 0;
    private JPanel mainPanel;
    private JButton prevButton;
    private JButton nextButton;

    public JPanel createFlightDetailsPanel(Flight flight) {
        JPanel detailsPanel = new JPanel(new GridLayout(5, 1));
        detailsPanel.setBorder(BorderFactory.createTitledBorder("Flight Details"));

        JLabel flightNumberLabel = new JLabel("Flight Number: " + flight.getFlightNumber());
        JLabel originLabel = new JLabel("Origin: " + flight.getOrigin());
        JLabel destinationLabel = new JLabel("Destination: " + flight.getDestination());
        JLabel departureLabel = new JLabel("Departure: " + flight.getDepartureDateTime());
        JLabel arrivalLabel = new JLabel("Arrival:      " + flight.getArrivalDateTime());

        detailsPanel.add(flightNumberLabel);
        detailsPanel.add(originLabel);
        detailsPanel.add(destinationLabel);
        detailsPanel.add(departureLabel);
        detailsPanel.add(arrivalLabel);

        return detailsPanel;
    }

    public void displayFlight(Flight flight) {
        if (mainPanel != null) {
            remove(mainPanel);
        }

        mainPanel = new JPanel(); // Initialize mainPanel
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        JPanel imagePanel = new JPanel();
        JLabel imageLabel = createImageLabel("flightPic.jpg");
        imagePanel.add(imageLabel);

        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));

        // Calculate the endIndex based on currentIndex and flights size
        int endIndex = Math.min(currentIndex + 2, admin.getAllFlights().size());

        for (int i = currentIndex; i < endIndex; i++) {
            detailsPanel.add(createFlightDetailsPanel(admin.getAllFlights().get(i)));
        }

        mainPanel.add(imagePanel);
        mainPanel.add(detailsPanel);

        add(mainPanel);
        pack();
        setVisible(true);
        updatePrevButtonVisibility();
        updateNextButtonVisibility();
    }



    public FlightGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        nextButton = new JButton("Next Page");
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentIndex += 2;
                displayFlight(null); // Pass null as argument to trigger redrawing of the panel

            }
        });
        prevButton = new JButton("Previous Page");
        prevButton.setVisible(false); // Initially set it invisible
        prevButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentIndex = Math.max(0, currentIndex - 2); // Ensure currentIndex doesn't go below 0
                displayFlight(null); // Pass null as argument to trigger redrawing of the panel
                updatePrevButtonVisibility(); // Update the visibility of the previous button after moving to the previous page
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);

        add(buttonPanel, BorderLayout.SOUTH);
        displayFlight(null); // Initial display with currentIndex = 0
        updatePrevButtonVisibility(); // Update the visibility initially
    }
    

    private JLabel createImageLabel(String imagePath) {
        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image image = imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(image);
        return new JLabel(scaledIcon);
    }

    private void updatePrevButtonVisibility() {
        if (currentIndex > 0) {
            prevButton.setVisible(true); // Set previous button visible if it's beyond the initial page and not at the end
            System.out.print("prec");
        } else {
            prevButton.setVisible(false); // Hide previous button
        }
    }

    private void updateNextButtonVisibility() {
        int endIndex = Math.min(currentIndex + 2, admin.getAllFlights().size());
        if (endIndex >= admin.getAllFlights().size()) {
            nextButton.setVisible(false); // Hide next button on the last page
        } else {
            nextButton.setVisible(true); // Show next button if there are more pages
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FlightGUI display = new FlightGUI();
            Admin admin = new Admin();
            ArrayList<Flight> flights = admin.getAllFlights();

            if (!flights.isEmpty()) {
                display.displayFlight(flights.get(0));
            }
        });
    }
}