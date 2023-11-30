package main.project.flightApplication.GUI;

import main.project.flightApplication.Controller.DBcore;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    DBcore dbcore;
    String username;
    String password;
    public LoginController(String username, String password){
        this.username = username;
        this.password = password;
        if(isValidUser(this.username, this.password)){
            System.out.println("valid");
            JOptionPane.showMessageDialog(null, "Login Successful!");
        }
        else {
            System.out.println("not valid");
            JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
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
