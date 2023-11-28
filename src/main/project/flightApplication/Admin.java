package main.project.flightApplication;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.project.flightApplication.Controller.DBcore;

public class Admin {
    private DBcore dbcore = DBcore.getInstance();

    public Admin() {
        // TODO Auto-generated constructor stub
        
    }
        //method for adding users into the database
    public void addRegisteredUser(String username, String password, String email, String userType) {
        try {
            String insertQuery = "INSERT INTO Users (Username, Password, Email, UserType) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = dbcore.getConnection().prepareStatement(insertQuery)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, userType);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    //method for removing users from the database
    public void removeRegisteredUser(String username){
        try{
            String deleteQuery = "DELETE FROM Users WHERE Username = ?";
            try (PreparedStatement preparedStatement = dbcore.getConnection().prepareStatement(deleteQuery)) {
                preparedStatement.setString(1, username);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //method to get the list of users who registered from the database, returns an arraylist of strings
    //I think we would need to use this to display them on the GUI
    public ArrayList<RegisteredUser> getRegisteredUsers() {
        ArrayList<RegisteredUser> registeredUsers = new ArrayList<>();
        try {
            String selectQuery = "SELECT * FROM Users WHERE UserType = 'registered'";
            try (PreparedStatement preparedStatement = dbcore.getConnection().prepareStatement(selectQuery)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    RegisteredUser regUser = new RegisteredUser( resultSet.getString("Name"),
                                         resultSet.getString("Username"),
                                         resultSet.getString("Password"),
                                         resultSet.getString("Email"),
                                         resultSet.getString("Address"));
                    // Set other attributes similarly
                    registeredUsers.add(regUser);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registeredUsers;
    }
    
   
    //method for adding aircraft to the database
    public void addAircraft(int aircraftID){
        try{
            String insertQuery = "INSERT INTO Aircraft (AircraftID) VALUES (?)";
            try (PreparedStatement preparedStatement = dbcore.getConnection().prepareStatement(insertQuery)) {
                preparedStatement.setInt(1, aircraftID);
                preparedStatement.executeUpdate();
                }
            } catch (SQLException e) {
                //this should open a pop up window saying that the aircraft already exists
                //maybe create an exception class which opens a pop up window depending on the error?
                e.printStackTrace();
            }
    }

        //method for removing aircraft from the database
    public void removeAircraft(int aircraftID){
        try{
            String deleteQuery = "DELETE FROM Aircraft WHERE AircraftID = ?";
            try (PreparedStatement preparedStatement = dbcore.getConnection().prepareStatement(deleteQuery)) {
                preparedStatement.setInt(1, aircraftID);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

            //method for adding passengers to the database
    public void addPassengers(int flightID, String seatNumber, String ticketInsurance, String paymentStatus){
        try{
            String insertQuery = "INSERT INTO passengers (FlightID, SeatNumber, TicketInsurance, PaymentStatus) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = dbcore.getConnection().prepareStatement(insertQuery)) {
                preparedStatement.setInt(1, flightID);
                preparedStatement.setString(2, seatNumber);
                preparedStatement.setString(3, ticketInsurance);
                preparedStatement.setString(4, paymentStatus);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            //this should open a pop up window saying that the aircraft already exists
            //maybe create an exception class which opens a pop up window depending on the error?
            e.printStackTrace();
        }
    }

    
    //method for removing passengers from the database
    //this would be called by cancel flight?
    public void removePassengers(int flightID, String seatNumber){
        try{
            String deleteQuery = "DELETE FROM passengers WHERE FlightID = ? AND SeatNumber = ?";
            try (PreparedStatement preparedStatement = dbcore.getConnection().prepareStatement(deleteQuery)) {
                preparedStatement.setInt(1, flightID);
                preparedStatement.setString(2, seatNumber);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //method for adding flights to the database
    public void addFlight(int flightID, String flightNumber, String origin, String destination, String departureTime, String arrivalTime, int aircraftID){
        try{
            String insertQuery = "INSERT INTO Flights (FlightID, FlightNumber, Origin, Destination, DepartureTime, ArrivalTime, AircraftID) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = dbcore.getConnection().prepareStatement(insertQuery)) {
                preparedStatement.setInt(1, flightID);
                preparedStatement.setString(2, flightNumber);
                preparedStatement.setString(3, origin);
                preparedStatement.setString(4, destination);
                preparedStatement.setString(5, departureTime);
                preparedStatement.setString(6, arrivalTime);
                preparedStatement.setInt(7, aircraftID);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

}
