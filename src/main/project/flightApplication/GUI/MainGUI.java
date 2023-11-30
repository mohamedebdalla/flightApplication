package main.project.flightApplication.GUI;

import javax.swing.*;
import java.awt.*;

public class MainGUI extends JFrame {
    private LoginGUI loginPage;
    private AdminPage adminPage;
    private RegisterPage registerPage;
    private UserPage userPage;
    private JLabel label = new JLabel("Airline System");

    JPanel header = new JPanel();
    JPanel main = new JPanel();
    JPanel left = new JPanel();
    JPanel right = new JPanel();
    JPanel footer = new JPanel();
    public MainGUI(){
        //creating labels

        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);

        //creating buttons
        JButton b1 = new JButton("Continue as a guest");
        JButton b2 = new JButton("Login");
        JButton b3 = new JButton("Register");
        JButton b4 = new JButton("Admin Login");
        JButton b5 = new JButton("Cancel flight");

        //creating panels

        header.setPreferredSize(new Dimension(50,60));
        header.setLayout(new BorderLayout());
        header.add(label);


        left.setPreferredSize(new Dimension(50,100));

        right.setPreferredSize(new Dimension(50,100));

        footer.setPreferredSize(new Dimension(50,50));


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

        //Action Listeners

        b1.addActionListener(e -> {
            if (userPage == null) {
                userPage = new UserPage();
            }
            setInvisible();
            userPage.setVisible(true);
            this.add(userPage.top,BorderLayout.NORTH);
            this.add(userPage.center,BorderLayout.CENTER);
            this.add(userPage.side, BorderLayout.WEST);
            label.setText("User Page");
            System.out.println("Continue as guest");
        });

        b2.addActionListener(e -> {
            new LoginGUI(this);
        });

        b3.addActionListener(e -> {
            if(registerPage == null){
                registerPage = new RegisterPage();
            }
            main.setVisible(false);
            registerPage.setVisible(true);
            this.add(registerPage, BorderLayout.CENTER);
            label.setText("Register Page");
        });

        b4.addActionListener(e -> {
            if(adminPage == null){
                adminPage = new AdminPage();
            }
            adminPage.setVisible(true);
            this.add(adminPage, BorderLayout.CENTER);
            label.setText("Admin Page");
        });
    }

    public void setInvisible(){
        main.setVisible(false);
        header.setVisible(false);
        left.setVisible(false);
        right.setVisible(false);
        footer.setVisible(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainGUI());
        System.out.println("Hello World!");
    }
}
