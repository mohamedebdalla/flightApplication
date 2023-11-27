package main.project.flightApplication.GUI;

import javax.swing.*;
import java.awt.*;

public class UserPage extends JPanel {
    // constructor has everything that a normal user can do
    JPanel top = new JPanel(); //will delete once it changes to a panel
    JPanel center = new JPanel();
    JPanel side = new JPanel();
    JLabel label = new JLabel("User Page");

    private JComboBox<String> originField;
    private JComboBox<String> destField;
    private JTextField dateField;
    private JTextField flightField;
    private String[] origin = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5"};
    private String[] desination = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5"};


    public UserPage(){
        JButton browse = new JButton("Search Flights");
        JButton cancel = new JButton("Cancel Flight");
        top.setPreferredSize(new Dimension(100,40));
        top.add(label);
        top.setBackground(Color.BLUE);
        center.setBackground(Color.CYAN);
        side.setPreferredSize(new Dimension(150,100));
        side.setLayout(new FlowLayout(FlowLayout.LEFT));
        side.add(browse);
        side.add(cancel);

        this.add(top, BorderLayout.NORTH);
        this.add(center, BorderLayout.CENTER);
        this.add(side, BorderLayout.WEST);

        browse.addActionListener(e ->{
            System.out.println("Search flight");
            label.setText("Book a flight");
            Browse();

        });

        cancel.addActionListener(e ->{
            System.out.println("Cancel flight");
            label.setText("Cancel a flight");
            Cancel();
        });

        registeredOptions();
    }

    public void registeredOptions(){
        JButton apply = new JButton("Get Credit Card");
        side.add(apply);
        apply.addActionListener(e ->{
            System.out.println("Apply for cc");
        });
    }

    public void Browse(){
        JPanel info = new JPanel();
        JPanel display = new JPanel();
        JLabel originLabel = new JLabel("From:");
        originField = new JComboBox<>(origin);
        JLabel destLabel = new JLabel("To:");
        destField = new JComboBox<>(desination);
        JLabel dateLabel = new JLabel("Date:");
        dateField = new JTextField("insert date");

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e ->{
            System.out.println("search");
        });

        info.setBackground(Color.green);
        info.setPreferredSize(new Dimension(500,40));
        info.add(originLabel);
        info.add(originField);
        info.add(destLabel);
        info.add(destField);
        info.add(dateLabel);
        info.add(dateField);
        info.add(searchButton);

        center.setLayout(new BorderLayout());
        center.add(info, BorderLayout.NORTH);
        center.add(display, BorderLayout.CENTER);
    }

    public void Cancel(){
        JPanel info = new JPanel();
        JPanel display = new JPanel();
        JLabel cancelLabel = new JLabel("Enter Flight ID:");
        flightField = new JTextField("Enter flight to cancel");

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e ->{
            System.out.println("cancel");
            int result = JOptionPane.showConfirmDialog(
                    null,
                    "Do you want to cancel your flight?",
                    "Cancel Confirmation",
                    JOptionPane.YES_NO_OPTION);

            // Check the user's choice
            if (result == JOptionPane.YES_OPTION) {
                // User clicked "Yes," delete flight

                System.out.println("Action confirmed and performed.");
            } else {
                // User clicked "No," don't delete flight
                System.out.println("Action canceled.");
            }
        });

        info.setBackground(Color.green);
        info.setPreferredSize(new Dimension(500,40));
        info.add(cancelLabel);
        info.add(flightField);
        info.add(cancelButton);

        center.setLayout(new BorderLayout());
        center.add(info, BorderLayout.NORTH);
        center.add(display, BorderLayout.CENTER);
    }

}
