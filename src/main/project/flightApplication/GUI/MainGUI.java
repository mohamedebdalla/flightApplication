package main.project.flightApplication.GUI;

import javax.swing.*;
import java.awt.*;

public class MainGUI extends JFrame {
    MainGUI(){
        //creating labels
        JLabel label = new JLabel("Airline System");
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);

        //creating buttons
        JButton b1 = new JButton("Continue as a guest");
        JButton b2 = new JButton("Login");
        JButton b3 = new JButton("Register");
        JButton b4 = new JButton("Admin Login");
        JButton b5 = new JButton("Cancel flight");

        //creating panels
        JPanel header = new JPanel();
        header.setPreferredSize(new Dimension(50,60));
        header.setLayout(new BorderLayout());
        header.add(label);

        JPanel left = new JPanel();
        left.setPreferredSize(new Dimension(50,100));
        JPanel right = new JPanel();
        right.setPreferredSize(new Dimension(50,100));
        JPanel footer = new JPanel();
        footer.setPreferredSize(new Dimension(50,50));

        JPanel main = new JPanel();
        main.setPreferredSize(new Dimension(85,100));
        main.setLayout(new GridLayout(4,1,10,10));
        main.add(b1);
        main.add(b2);
        main.add(b3);
        main.add(b4);
        main.add(b5);

        this.setTitle("Airline System");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        this.setVisible(true);
        this.setLayout(new BorderLayout(30,0));
        this.add(header, BorderLayout.NORTH);
        this.add(left, BorderLayout.WEST);
        this.add(right, BorderLayout.EAST);
        this.add(footer, BorderLayout.SOUTH);
        this.add(main, BorderLayout.CENTER);
    }
}
