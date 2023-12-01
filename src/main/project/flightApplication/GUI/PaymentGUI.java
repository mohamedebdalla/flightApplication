package main.project.flightApplication.GUI;

import main.project.flightApplication.Controller.DBcore;
import main.project.flightApplication.Payment;
import main.project.flightApplication.Flight;
import main.project.flightApplication.Passenger;
import main.project.flightApplication.Controller.TicketController;
import main.project.flightApplication.Controller.EmailController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PaymentGUI extends JFrame{
    private JTextField passNameField;
    private JTextField cardholderField;
    private JTextField emailField;
    private JTextField cardNumberField;
    private JTextField expiryDateField;
    private JTextField cvvField;
    private DBcore dbcore = DBcore.getInstance();
    private String ticketInsurance;
    private TicketController ticketController;
    private EmailController emailController = new EmailController();

    public PaymentGUI(Flight flight, double price, int seatNumber){
        //set up main frame 
        setTitle("Payment Page");
        setSize(500, 500);

        JLabel flightDetailsLabel = new JLabel("Price: $" + price);
        flightDetailsLabel.setFont(new Font("Arial", Font.BOLD, 16));

        //create components
        JLabel passNameLabel = new JLabel("Passenger Name");
        passNameField = new JTextField(20);
        JLabel cardholderLabel = new JLabel("Cardholder Name");
        cardholderField = new JTextField(20);
        JLabel emailLabel = new JLabel("Email");
        emailField = new JTextField(20);
        JLabel cardNumberLabel = new JLabel("Card Number");
        cardNumberField = new JTextField(20);
        JLabel expiryDateLabel = new JLabel("Expiry Date");
        expiryDateField = new JTextField(10);
        JLabel cvvLabel = new JLabel("CVV");
        cvvField = new JTextField(5);

        JButton payButton = new JButton("Confirm and Pay");
        JButton insuranceButton = new JButton("Add Ticket Insurance");

        //create layout panels 
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel flightPanel = new JPanel(new GridLayout(2, 1));
        JPanel paymentPanel = new JPanel(new GridLayout(6, 2));
        JPanel buttonPanel = new JPanel(new FlowLayout());

        //add components to panels 
        flightPanel.add(new JLabel("Flight Details:"));
        flightPanel.add(flightDetailsLabel);
        flightPanel.add(passNameLabel);
        flightPanel.add(passNameField);

        paymentPanel.add(cardholderLabel);
        paymentPanel.add(cardholderField);
        paymentPanel.add(emailLabel);
        paymentPanel.add(emailField);
        paymentPanel.add(cardNumberLabel);
        paymentPanel.add(cardNumberField);
        paymentPanel.add(expiryDateLabel);
        paymentPanel.add(expiryDateField);
        paymentPanel.add(cvvLabel);
        paymentPanel.add(cvvField);
        
        buttonPanel.add(payButton);
        buttonPanel.add(insuranceButton);

        //add panels to main panel
        mainPanel.add(flightPanel, BorderLayout.NORTH);
        mainPanel.add(paymentPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        //add action listeners to buttons
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Insert data into the database
                insertPaymentData(flight, seatNumber, price);

                //JOptionPane.showMessageDialog(PaymentGUI.this, "Payment Successful!");
            }
        });

        insuranceButton.addActionListener(e -> {
            ticketInsurance = "yes";
            double insuranceAmount = 50.00;

            flightDetailsLabel.setText("Price: $" + (price + insuranceAmount));
            JOptionPane.showMessageDialog(PaymentGUI.this, "Insurance Added!");
            
        });

        //set the main panel as the content pane
        setContentPane(mainPanel);

        setVisible(true);
    }

    private void insertPaymentData(Flight flight, int seatNumber, double price) {
        //get the data from the input fields
        String passName = passNameField.getText();
        String cardholderName = cardholderField.getText();
        String email = emailField.getText();
        String cardNumber = cardNumberField.getText();
        String expiryDate = expiryDateField.getText();
        String cvv = cvvField.getText();

        //validate the input fields
        if (cardholderName.isEmpty() || email.isEmpty() || cardNumber.isEmpty() || expiryDate.isEmpty() || cvv.isEmpty()) {
            JOptionPane.showMessageDialog(PaymentGUI.this, "Please fill in all the fields.");
            return;
        }

        try {
            String query = "INSERT INTO payment (CardholderName, Email, CardNumber, ExpiryDate, CVV, TicketPrice) " +
                           "VALUES (?, ?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = dbcore.getConnection().prepareStatement(query)) {
                preparedStatement.setString(1, cardholderName);
                preparedStatement.setString(2, email);
                preparedStatement.setString(3, cardNumber);
                preparedStatement.setString(4, expiryDate);
                preparedStatement.setString(5, cvv);
                preparedStatement.setDouble(6, price);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Payment data inserted into the database successfully.");
                    Payment newPayment = new Payment(cardNumber, cardholderName, expiryDate, cvv, price);
                    //call function to generate ticket id
                    String ticketId = generateTicketNumber();
                    //create a passenger and add it to the database
                    Passenger newPassenger = new Passenger(passName, flight.getFlightID(), seatNumber, ticketId, ticketInsurance, flight.getFlightNumber());
                    //call function to insert passenger info into the database passenger table 
                    insertPassengerData(newPassenger);
                    JOptionPane.showMessageDialog(PaymentGUI.this, "Payment Successful! Generating Ticket...");
                    displayTicketInfo(newPassenger, flight);
                } else {
                    System.out.println("Error inserting payment data into the database.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error inserting payment data into the database.");
        }
    }

    private String generateTicketNumber() {
        // Use current timestamp to create a unique ticket number
        long timestamp = System.currentTimeMillis();
    
        // Combine a prefix with the timestamp
        String ticketNumber = "TICKET" + timestamp;
    
        return ticketNumber;
    }

    private void displayTicketInfo(Passenger passenger, Flight flight) {
        //create a new frame to display ticket info
        ticketController = new TicketController();
        JFrame ticketFrame = new JFrame("Ticket Information");
        ticketFrame.setSize(500, 500);

        //create components
        JLabel ticketIdLabel = new JLabel("Ticket ID: " + passenger.getTicket().getTicketId());
        JLabel flightNumberLabel = new JLabel("Flight ID: " + passenger.getFlightId());
        JLabel seatNumberLabel = new JLabel("Seat Number: " + passenger.getSeatNumber());
        JLabel ticketInsuranceLabel = new JLabel("Ticket Insurance: " + passenger.getTicket().getTicketInsurance());
        JLabel flightDateLabel = new JLabel("Flight Date: " + flight.getDepartureDate());
        JLabel flightOriginLabel = new JLabel("Flight Origin: " + flight.getOrigin());
        JLabel flightDestinationLabel = new JLabel("Flight Destination: " + flight.getDestination());

        //create layout panels
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel ticketPanel = new JPanel(new GridLayout(4, 2));
        JPanel flightPanel = new JPanel(new GridLayout(5, 2));
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton emailTicketButton = new JButton("Email Ticket");

        //add components to panels
        ticketPanel.add(ticketIdLabel);
        ticketPanel.add(flightNumberLabel);
        ticketPanel.add(seatNumberLabel);
        ticketPanel.add(ticketInsuranceLabel);
        flightPanel.add(flightDateLabel);
        flightPanel.add(flightOriginLabel);
        flightPanel.add(flightDestinationLabel);

        buttonPanel.add(new JButton("Print Ticket"));
        buttonPanel.add(emailTicketButton);

        emailTicketButton.addActionListener(e -> {
            ticketController.generateTicketFile(passenger, flight);
            emailController.configureEmailProperties(emailField.getText());
            JOptionPane.showMessageDialog(PaymentGUI.this, "Ticket Emailed!");
        });

        //add panels to main panel
        mainPanel.add(ticketPanel, BorderLayout.NORTH);
        mainPanel.add(flightPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        //set the main panel as the content pane
        ticketFrame.setContentPane(mainPanel);

        ticketFrame.setVisible(true);
    }

    private void insertPassengerData(Passenger passenger) {
        try {
            String query = "INSERT INTO passengers (PassengerName, FlightID, SeatNumber, TicketID, TicketInsurance) " +
                    "VALUES (?, ?, ?, ?, ?)";
    
            try (PreparedStatement preparedStatement = dbcore.getConnection().prepareStatement(query)) {
                preparedStatement.setString(1, passenger.getPassengerName());
                preparedStatement.setInt(2, passenger.getFlightId());
                preparedStatement.setInt(3, passenger.getSeatNumber());
                preparedStatement.setString(4, passenger.getTicket().getTicketId());
                preparedStatement.setString(5, passenger.getTicket().getTicketInsurance());
    
                int rowsAffected = preparedStatement.executeUpdate();
    
                if (rowsAffected > 0) {
                    System.out.println("Passenger data inserted into the database successfully.");
                    return;
                } else {
                    System.out.println("Error inserting passenger data into the database.");
                    return;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error inserting passenger data into the database.");
            return;
        }
    }
    
    
}
