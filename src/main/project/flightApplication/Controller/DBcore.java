package main.project.flightApplication.Controller;
import java.sql.*;



public class DBcore {
    private Connection dbConnect;

    public DBcore() {
        createConnection();
    }

    private void createConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String DB_URL = "jdbc:mysql://localhost:3306/flightdb";
            String DB_USERNAME = "root";
            String DB_PASSWORD = "password";
            dbConnect = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("Connected to database");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    
    //method for adding users into the database
    public void addRegisteredUser(String username, String password, String email, String userType) {
        try {
            String insertQuery = "INSERT INTO Users (Username, Password, Email, UserType) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = dbConnect.prepareStatement(insertQuery)) {
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

    }

    //method for adding aircraft to the database
    public void addAircraft(int aircraftID){
        try{
            String insertQuery = "INSERT INTO Aircraft (AircraftID) VALUES (?)";
            try (PreparedStatement preparedStatement = dbConnect.prepareStatement(insertQuery)) {
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

    }

    //method for adding passengers to the database
    public void addPassengers(){

    }
    

    public void closeConnection() {
        try {
            if (dbConnect != null && !dbConnect.isClosed()) {
                dbConnect.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
