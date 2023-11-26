package main.project.flightApplication.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.project.flightApplication.Controller.DBcore;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;

public class RegisterPage extends JFrame{
    private JTextField nameField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField emailField;
    private JTextField addressField;
    DBcore dbcore = DBcore.getInstance();

    public RegisterPage(){
        JLabel title = new JLabel("Register");
        title.setVerticalAlignment(JLabel.CENTER);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //create components 
        JLabel nameLabel = new JLabel("Name");
        JLabel usernameLabel = new JLabel("Username");
        JLabel passwordLabel = new JLabel("Password");
        JLabel emailLabel = new JLabel("Email");
        JLabel adressLabel = new JLabel("Address");

        nameField = new JTextField(20);
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        emailField = new JTextField(60);
        addressField = new JTextField(200);  

        JButton registerButton = new JButton("Create account");
        registerButton.addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e){
                register();
            }
        });

        //create layout panels
        JPanel mainPanel = new JPanel(new BorderLayout());

        //panel for the title 
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Register");
        titlePanel.add(titleLabel);

        //panel for the form
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2));
        
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(adressLabel);
        panel.add(addressField);
        panel.add(new JLabel());

        //add form panel and button to main panel
        mainPanel.add(BorderLayout.NORTH, titlePanel);
        mainPanel.add(BorderLayout.CENTER, panel);
        mainPanel.add(BorderLayout.SOUTH, registerButton);

        //add layout to the frame
        getContentPane().add(mainPanel);

    }

    //function to register user
    private void register(){
        String name = nameField.getText();
        String username = usernameField.getText();
        String password = String.valueOf(passwordField.getPassword());
        String email = emailField.getText();
        String address = addressField.getText();

        //validate the input fields
        if(username.isEmpty() || password.isEmpty() || email.isEmpty() || address.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //insert the user into the database
        try{
            String query = "INSERT INTO users (name, username, password, email, address, userType) VALUES (?, ?, ?, ?, ?, ?)";

            try(PreparedStatement preparedStatement = dbcore.getConnection().prepareStatement(query)){
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, username);
                preparedStatement.setString(3, password);
                preparedStatement.setString(4, email);
                preparedStatement.setString(5, address);
                preparedStatement.setString(6, "registered user");

                int rowsAffected = preparedStatement.executeUpdate();

                if(rowsAffected > 0){
                    JOptionPane.showMessageDialog(this, "User created successfully");
                    return;
                } else{
                    JOptionPane.showMessageDialog(this, "Error creating user", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error creating user", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
