package main.project.flightApplication.Boundary.GUI;

import javax.swing.*;
import java.awt.*;

public class MainGUI extends JFrame {
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
        JButton guestButton = new JButton("Continue as a guest");
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");
        JButton adminButton = new JButton("Admin Login");
        JButton cancelButton = new JButton("Cancel flight");

        //creating panels

        header.setPreferredSize(new Dimension(50,60));
        header.setLayout(new BorderLayout());
        header.add(label);


        left.setPreferredSize(new Dimension(50,100));

        right.setPreferredSize(new Dimension(50,100));

        footer.setPreferredSize(new Dimension(50,50));


        main.setPreferredSize(new Dimension(85,100));
        main.setLayout(new GridLayout(4,1,10,10));
        main.add(guestButton);
        main.add(loginButton);
        main.add(registerButton);
        main.add(adminButton);
        main.add(cancelButton);

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

        guestButton.addActionListener(e -> {
            displayPage(null);
        });

        loginButton.addActionListener(e -> {
            new LoginGUI(this, this, "registered user");
        });

        registerButton.addActionListener(e -> {
            if(registerPage == null){
                registerPage = new RegisterPage(this);
            }
            main.setVisible(false);
            registerPage.setVisible(true);
            this.add(registerPage, BorderLayout.CENTER);
            label.setText("Register Page");
        });

        adminButton.addActionListener(e -> {
            new LoginGUI(this, this, "admin");
            if(adminPage == null){
                adminPage = new AdminPage();
            }
            adminPage.setVisible(true);
            this.add(adminPage, BorderLayout.CENTER);
            label.setText("Admin Page");
        });

        cancelButton.addActionListener(e ->{
           CancelFlightGUI cancelFlightGUI = new CancelFlightGUI();
        });
    }

    public void displayPage(String name){
        if (userPage == null) {
            userPage = new UserPage(this);
        }
        main.setVisible(false);
        header.setVisible(false);
        left.setVisible(false);
        right.setVisible(false);
        footer.setVisible(false);
        userPage.setVisible(true);
        if(name !=null){
            userPage.forUsers(name);
        }
        userPage.addBack();
        this.setSize(new Dimension(1000,600));
        this.add(userPage.top,BorderLayout.NORTH);
        this.add(userPage.center,BorderLayout.CENTER);
        this.add(userPage.side, BorderLayout.WEST);
        label.setText("Browse Flight");
        System.out.println("Continue as guest");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainGUI());
        System.out.println("Hello World!");
    }
}
