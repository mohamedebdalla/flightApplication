package main.project.flightApplication.Boundary.GUI;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import java.awt.*;
import javax.swing.*;


import main.project.flightApplication.Controller.StaffController;
import main.project.flightApplication.Entity.Passenger;


public class StaffGUI extends JFrame {

    public StaffGUI() {
        displayPassengers();
    }
    StaffController staffController = new StaffController();

    ArrayList<Passenger> passengers = staffController.getPassengers();
    public void displayPassengers(){
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Name");
            model.addColumn("FlightID");
            model.addColumn("Seat Number");
            model.addColumn("TicketID");
            model.addColumn("Ticket Insurance");
            model.addColumn("Flight Number");
    

     for (Passenger passenger : passengers) {
                model.addRow(new Object[]{
                        passenger.getPassengerName(),
                        passenger.getFlightId(),
                        passenger.getSeatNumber(),
                        passenger.getTicket().getTicketId(),
                        passenger.getTicket().getTicketInsurance(),
                        passenger.getFlightNumber(),
                        // Add other attributes as needed
                }); 
            }

            JTable table = new JTable(model);
            table.setPreferredScrollableViewportSize(new Dimension(500, 500));
            table.setFillsViewportHeight(true);

            // Add the table to a scroll pane
            JScrollPane scrollPane = new JScrollPane(table);

            // Add scroll pane to the frame
            this.add(scrollPane, BorderLayout.CENTER);

            this.setTitle("Passenger List");
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.pack();
            this.setVisible(true);

    }

    public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> new StaffGUI());
        }
}