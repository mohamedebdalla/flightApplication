package main.project.flightApplication.Boundary.GUI;

import javax.swing.*;
import java.awt.*;


public class PromotionManager extends JFrame {

    public PromotionManager(){

        JButton monthlyNewsletterButton = new JButton("Sign up for Monthly Newsletter");
        JButton freeCompanionTicketButton = new JButton("Check for Free Companion Ticket");
        JButton loungeDiscountButton = new JButton("Get Lounge Discount Promo Code");
        JButton closeButton = new JButton("Close Window");

        monthlyNewsletterButton.addActionListener(e -> showMonthlyNewsletter());

        freeCompanionTicketButton.addActionListener(e -> checkFreeCompanionTicket());

        loungeDiscountButton.addActionListener(e -> showLoungeDiscountPromoCode());
        closeButton.addActionListener(e-> this.dispose());

        this.setTitle("Promotion Page");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(500,500);
        this.setVisible(true);
        this.setLayout(new GridLayout(4,1));
        this.add(monthlyNewsletterButton);
        this.add(freeCompanionTicketButton);
        this.add(loungeDiscountButton);
        this.add(closeButton);

    }

    private void showMonthlyNewsletter() {
        // Implement logic to display the monthly newsletter
        JOptionPane.showMessageDialog(this, "You signed up for monthly newsletter");
    }

    private void checkFreeCompanionTicket() {
        // Implement logic to check if the user has a free companion ticket
        JOptionPane.showMessageDialog(this, "Checking for Free Companion Ticket");
    }

    private void showLoungeDiscountPromoCode() {
        // Implement logic to generate and display the lounge discount promo code
        JOptionPane.showMessageDialog(this, "Your Lounge Discount Promo Code is: XYZ123");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PromotionManager());
        System.out.println("Hello World!");
    }


}
