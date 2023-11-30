package main.project.flightApplication.GUI;

import main.project.flightApplication.Controller.DBcore;
import main.project.flightApplication.Admin;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginGUI extends JDialog{
    LoginController loginController;
    private JTextField usernameField;
    private JPasswordField passwordField;
    boolean loginValue;
    DBcore dbcore = DBcore.getInstance();
    private Admin admin = new Admin();

    public LoginGUI(Frame parent) {
        super(parent, "Login", true);

        // Create components
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);

        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(e ->{
            System.out.println("please work");
            //loginController = new LoginController(usernameField.getText(), passwordField.getText());
            if(isValidUser(usernameField.getText(), passwordField.getText())){
                System.out.println("valid");
                JOptionPane.showMessageDialog(null, "Login Successful!");
            }
            else {
                System.out.println("not valid");
                JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
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

    public boolean isValidUser(String username, String password) {
        try{
            String query = "SELECT * FROM users WHERE username = ? AND password = ? AND userType = ?";

            try(PreparedStatement preparedStatement = dbcore.getConnection().prepareStatement(query)){
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                preparedStatement.setString(3, "registered user");

                ResultSet resultSet = preparedStatement.executeQuery();
                return resultSet.next();
            }

        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }



}