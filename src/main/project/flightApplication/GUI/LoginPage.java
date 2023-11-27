package main.project.flightApplication.GUI;

import main.project.flightApplication.Controller.DBcore;
import main.project.flightApplication.Admin;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPage extends JPanel implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;
    DBcore dbcore = DBcore.getInstance();
    private Admin admin = new Admin();

    public LoginPage() {
        this.setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(this);

        this.add(usernameLabel);
        this.add(usernameField);
        this.add(passwordLabel);
        this.add(passwordField);
        this.add(new JLabel()); // Placeholder
        this.add(loginButton);

        //add(panel);
        setVisible(false);
    }

    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = String.valueOf(passwordField.getPassword());
        
        admin.addRegisteredUser(username, password, "email", "userType");

        // For simplicity, let's just check if the username is "user" and password is "password"
        if (username.equals("user") && password.equals("password")) {
            JOptionPane.showMessageDialog(this, "Login Successful!");
            // You can add code here to open a new window or perform further actions after successful login
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


}
