package main.project.flightApplication.Boundary.GUI;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import java.awt.*;
import javax.swing.*;


import main.project.flightApplication.Controller.StaffController;
import main.project.flightApplication.Entity.Passenger;


public class StaffGUI extends JPanel {
    private MainGUI mainGUI;
    StaffController staffController = new StaffController();

    ArrayList<Passenger> passengers = staffController.getPassengers();

    JPanel panel = new JPanel();


    public StaffGUI(MainGUI frame) {
        this.mainGUI = frame;
        JButton browsePassengers = new JButton("Browse Passengers");
        JButton back = new JButton("Back to Main");

        browsePassengers.addActionListener(e ->{
            displayPassengers();
        });

        back.addActionListener(e ->{
            MainGUI mainGUI = new MainGUI();
        });


        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.add(browsePassengers);
        panel.add(back);

        //displayPassengers();
    }

    public void displayPassengers(){
        JFrame frame = new JFrame();
        frame.setSize(800,400);
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
            frame.add(scrollPane, BorderLayout.CENTER);

            frame.setVisible(true);


    }


}