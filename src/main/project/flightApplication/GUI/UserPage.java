package main.project.flightApplication.GUI;

import javax.swing.*;
import java.awt.*;

public class UserPage extends JFrame {
    // constructor has everything that a normal user can do
    JPanel top = new JPanel(); //will delete once it changes to a panel
    JPanel center = new JPanel();
    JPanel side = new JPanel();
    JButton browse = new JButton("Browse Flights");
    JButton cancel = new JButton("Cancel Flight");

    public UserPage(){

        top.setPreferredSize(new Dimension(100,40));
        top.add(new JLabel("User Page"));

        side.setBackground(Color.black);
        side.setPreferredSize(new Dimension(150,100));
        side.setLayout(new FlowLayout(FlowLayout.LEFT));
        side.add(browse);
        side.add(cancel);

        this.setTitle("user page");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        this.setVisible(true);
        this.add(top, BorderLayout.NORTH);
        this.add(center, BorderLayout.CENTER);
        this.add(side, BorderLayout.WEST);

        browse.addActionListener(e ->{
            System.out.println("Browse flight");
        });

        cancel.addActionListener(e ->{
            System.out.println("Cancel flight");
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

    // add a function that is invoked when it's a registered user

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UserPage());
        System.out.println("Hello World!");
    }
}
