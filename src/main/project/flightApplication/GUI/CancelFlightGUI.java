package main.project.flightApplication.GUI;

import javax.swing.*;

import main.project.flightApplication.Controller.BookingController;
import main.project.flightApplication.Controller.CancelFlightController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class CancelFlightGUI extends JFrame {
    private JTextField ticketIdField;
    private CancelFlightController cancelFlightController = new CancelFlightController();
    private ArrayList<String> ticketIDs = cancelFlightController.getAllTicketIDs();
    

    public CancelFlightGUI() {
        setTitle("Cancel Flight");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(300,200);
        this.setVisible(true);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel ticketIdLabel = new JLabel("Input Ticket ID:");
        ticketIdField = new JTextField();

        JButton cancelButton = new JButton("Cancel Flight");

        mainPanel.add(ticketIdLabel);
        mainPanel.add(ticketIdField);
        mainPanel.add(cancelButton);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ticketId = ticketIdField.getText();

                if (ticketIDs.contains(ticketId)) {
                   cancelFlightController.removeBooking(ticketId);

                } else {
                    JOptionPane.showMessageDialog(null, "Ticket ID is invalid.");
                }

                // Perform cancellation logic here

                JOptionPane.showMessageDialog(null, "Flight has been cancelled.");
            }
        });

        add(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CancelFlightGUI());
    }
}
