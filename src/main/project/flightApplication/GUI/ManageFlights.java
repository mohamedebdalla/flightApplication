package main.project.flightApplication.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import main.project.flightApplication.Controller.FlightController;
import main.project.flightApplication.Admin;
import java.util.Collections;

public class ManageFlights extends JFrame {

    private JPanel mainPanel;
    private JButton addFlightButton;
    private JButton removeFlightButton;
    private JTextField flightNumberField;
    private JTextField originField;
    private JTextField destinationField;
    private JTextField departureDateField;
    private JTextField departureTimeField;
    private JTextField arrivalDateField;
    private JTextField arrivalTimeField;
    private JComboBox<String> aircraftIdDropdown;
    private Admin admin = new Admin();
    private FlightController flightController = new FlightController(); 
    private ArrayList<Integer> flightIDs = flightController.getAllFlightIDs();
    private JButton confirmButton;

    public ManageFlights() {
        setTitle("Flight Management");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(400, 400);

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(10, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        addFlightButton = new JButton("Add Flight");
        removeFlightButton = new JButton("Remove Flight");

        addFlightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
                createAddFlightComponents();
            }
        });

        removeFlightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
                createRemoveFlightComponents();
            }
        });

        mainPanel.add(addFlightButton);
        mainPanel.add(removeFlightButton);

        add(mainPanel);
        setVisible(true);
    }

    private void createAddFlightComponents() {
        JLabel fightIDLabel = new JLabel("Flight ID:");
        JTextField flightIDField = new JTextField();

        JLabel flightNumberLabel = new JLabel("Flight Number:");
        flightNumberField = new JTextField();

        JLabel originLabel = new JLabel("Origin:");
        originField = new JTextField();

        JLabel destinationLabel = new JLabel("Destination:");
        destinationField = new JTextField();

        JLabel departureDateLabel = new JLabel("Departure Date:");
        departureDateField = new JTextField();

        JLabel departureTimeLabel = new JLabel("Departure Time:");
        departureTimeField = new JTextField();

        JLabel arrivalDateLabel = new JLabel("Arrival Date:");
        arrivalDateField = new JTextField();

        JLabel arrivalTimeLabel = new JLabel("Arrival Time:");
        arrivalTimeField = new JTextField();

        JLabel aircraftIdLabel = new JLabel("Aircraft ID:");
        Integer[] aircraftIds = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        JComboBox<Integer> aircraftIdDropdown = new JComboBox<>(aircraftIds);   

        confirmButton = new JButton("Confirm");

        mainPanel.add(fightIDLabel);
        mainPanel.add(flightIDField);

        mainPanel.add(flightNumberLabel);
        mainPanel.add(flightNumberField);

        mainPanel.add(originLabel);
        mainPanel.add(originField);

        mainPanel.add(destinationLabel);
        mainPanel.add(destinationField);

        mainPanel.add(departureDateLabel);
        mainPanel.add(departureDateField);

        mainPanel.add(departureTimeLabel);
        mainPanel.add(departureTimeField);

        mainPanel.add(arrivalDateLabel);
        mainPanel.add(arrivalDateField);

        mainPanel.add(arrivalTimeLabel);
        mainPanel.add(arrivalTimeField);

        mainPanel.add(aircraftIdLabel);
        mainPanel.add(aircraftIdDropdown);

        mainPanel.add(confirmButton);
        validate();
        repaint();

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int flightID = Integer.parseInt(flightIDField.getText());
                String flightNumber = flightNumberField.getText();
                String origin = originField.getText();
                String destination = destinationField.getText();
                String departureDate = departureDateField.getText();
                String departureTime = departureTimeField.getText();
                String arrivalDate = arrivalDateField.getText();
                String arrivalTime = arrivalTimeField.getText();
                int aircraftId = (int)aircraftIdDropdown.getSelectedItem();

                if (flightNumber.isEmpty() || origin.isEmpty() || destination.isEmpty() || departureDate.isEmpty() || departureTime.isEmpty() || arrivalDate.isEmpty() || arrivalTime.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields.");
                    return;
                }

                if (flightIDs.contains(flightID)) {
                    JOptionPane.showMessageDialog(null, "Flight " + flightNumber + " already exists.");
                    return;
                }

                flightController.addFlight(flightID,flightNumber, origin, destination, departureDate, departureTime, arrivalDate, arrivalTime, aircraftId);
                JOptionPane.showMessageDialog(null, "Flight " + flightNumber + " has been added.");
            }
        });
    }

    private void createRemoveFlightComponents() {
        JLabel removeFlightLabel = new JLabel("Select Flight Number to Remove:");
        Collections.sort(flightIDs);
        JComboBox<Integer> flightNumberDropdown = new JComboBox<>(flightIDs.toArray(new Integer[flightIDs.size()]));
        
        confirmButton = new JButton("Confirm");
        
        confirmButton.addActionListener(e -> {
            int flightNumber = (int) flightNumberDropdown.getSelectedItem();
    
            int choice = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to remove Flight " + flightNumber + "?",
                    "Confirm Removal",
                    JOptionPane.YES_NO_OPTION);
    
            if (choice == JOptionPane.YES_OPTION) {
                flightController.removeAssociatedCrews(flightNumber);
                flightController.removeFlight(flightNumber);
                JOptionPane.showMessageDialog(null, "Flight " + flightNumber + " has been removed.");
            }
        });
    
        mainPanel.add(removeFlightLabel);
        mainPanel.add(flightNumberDropdown);
        mainPanel.add(confirmButton);
        validate();
        repaint();
    }
    

    private void clearFields() {
        mainPanel.removeAll();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ManageFlights());
    }
}
