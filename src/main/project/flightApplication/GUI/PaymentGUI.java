package main.project.flightApplication.GUI;

import main.project.flightApplication.Controller.DBcore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PaymentGUI extends JFrame{
    private JTextField cardholderField;
    private JTextField emailField;
    private JTextField cardNumberField;
    private JTextField expiryDateField;
    private JTextField cvvField;
    private DBcore dbcore = DBcore.getInstance();

    public PaymentGUI(double price){
        //set up main frame 
        setTitle("Payment Page");
        setSize(500, 500);

        JLabel flightDetailsLabel = new JLabel("Price: $" + price);
        flightDetailsLabel.setFont(new Font("Arial", Font.BOLD, 16));

        //create components
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
                insertPaymentData();

                //JOptionPane.showMessageDialog(PaymentGUI.this, "Payment Successful!");
            }
        });

        insuranceButton.addActionListener(e -> {
            
            double insuranceAmount = 50.00;

            flightDetailsLabel.setText("Price: $" + (price + insuranceAmount));
            JOptionPane.showMessageDialog(PaymentGUI.this, "Insurance Added!");
            
        });

        //set the main panel as the content pane
        setContentPane(mainPanel);

        setVisible(true);
    }

    private void insertPaymentData() {
        String cardholderName = cardholderField.getText();
        String email = emailField.getText();
        String cardNumber = cardNumberField.getText();
        String expiryDate = expiryDateField.getText();
        String cvv = cvvField.getText();

        try {
            String query = "INSERT INTO payment (CardholderName, Email, CardNumber, ExpiryDate, CVV) " +
                           "VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = dbcore.getConnection().prepareStatement(query)) {
                preparedStatement.setString(1, cardholderName);
                preparedStatement.setString(2, email);
                preparedStatement.setString(3, cardNumber);
                preparedStatement.setString(4, expiryDate);
                preparedStatement.setString(5, cvv);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Payment data inserted into the database successfully.");
                    JOptionPane.showMessageDialog(PaymentGUI.this, "Payment Successful!");
                } else {
                    System.out.println("Error inserting payment data into the database.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error inserting payment data into the database.");
        }
    }

    public static void main(String[] args) {
        // Example usage
        double price = 100.00;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PaymentGUI(price);
            }
        });
    }
    
}
