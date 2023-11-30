package main.project.flightApplication.GUI;

import main.project.flightApplication.Controller.DBcore;
import main.project.flightApplication.Admin;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginGUI extends JDialog{
    LoginController loginController = new LoginController();
    private JTextField usernameField;
    private JPasswordField passwordField;
    boolean loginValue;
    DBcore dbcore = DBcore.getInstance();
    private Admin admin = new Admin();

    public LoginGUI(Frame parent, int i) {
        super(parent, "Login", true);

        // Create components
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);

        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(e ->{
            this.loginValue = onLogin(i);
            if(loginValue){
                System.out.println("Go to next page");
            }
        });

        // Set layout
        setLayout(new GridLayout(3, 2));

        // Add components to the dialog
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);

        // Set default close operation
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Set dialog size and make it visible
        setSize(300, 150);
        setLocationRelativeTo(parent);
        setResizable(false);
        setVisible(true);
    }

    public boolean onLogin(int i){
        if (i == 0){
            System.out.println("User");
            return loginController.validateUserLogin(usernameField.getText(), passwordField.getText());
        }
        if (i == 1){
            System.out.println("Admin");
            return loginController.validateAdminLogin(usernameField.getText(), passwordField.getText());
        }
        if (i == 1){
            System.out.println("Crew");
            return loginController.validateCrewLogin(usernameField.getText(), passwordField.getText());
        }
        else{
            return false;
        }
    }


}
