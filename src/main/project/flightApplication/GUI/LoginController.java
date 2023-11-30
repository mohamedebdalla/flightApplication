package main.project.flightApplication.GUI;

import javax.swing.*;

public class LoginController {
    String username;
    String password;
    public LoginController(){

    }

    public boolean validateUserLogin(String username, String password){
        if (username.equals("user") && password.equals("password")) {
            JOptionPane.showMessageDialog(null, "Login Successful!");
            return true;
            // You can add code here to open a new window or perform further actions after successful login
        } else {
            JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    public boolean validateAdminLogin(String username, String password){
        if (username.equals("user") && password.equals("password")) {
            JOptionPane.showMessageDialog(null, "Login Successful!");
            return true;
            // You can add code here to open a new window or perform further actions after successful login
        } else {
            JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    public boolean validateCrewLogin(String username, String password){
        if (username.equals("user") && password.equals("password")) {
            JOptionPane.showMessageDialog(null, "Login Successful!");
            return true;
            // You can add code here to open a new window or perform further actions after successful login
        } else {
            JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
