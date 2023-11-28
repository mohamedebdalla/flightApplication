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
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        JPanel imagePanel = new JPanel();
        JLabel imageLabel = createImageLabel("flightPic.jpg");
        imagePanel.add(imageLabel);

        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));

        JPanel firstFlightPanel = new JPanel();
        firstFlightPanel.add(createFlightDetailsPanel(flight));

        detailsPanel.add(firstFlightPanel);

        // Retrieve the second flight from the list (assuming there are at least two flights)
        ArrayList<Flight> flights = admin.getAllFlights();
        if (flights.size() > 1) {
            JPanel secondFlightPanel = new JPanel();
            secondFlightPanel.add(createFlightDetailsPanel(flights.get(1))); // Displaying the second flight
            detailsPanel.add(secondFlightPanel);
        }

        mainPanel.add(imagePanel);
        mainPanel.add(detailsPanel);

        add(mainPanel);
        pack();
        setVisible(true);
    }

 
    public FlightGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentIndex += 2;
                
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(nextButton);

        add(buttonPanel, BorderLayout.SOUTH);

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
}
}
