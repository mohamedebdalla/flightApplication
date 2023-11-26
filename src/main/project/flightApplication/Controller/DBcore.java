package main.project.flightApplication.Controller;
import java.sql.*;

public class DBcore {
    private static DBcore instance;
    private Connection dbConnect;

    private DBcore() {
        createConnection();
    }

    public static DBcore getInstance() {
        if (instance == null) {
            instance = new DBcore();
        }
        return instance;
    }

    public Connection getConnection(){
        return dbConnect;
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
        try{
            String deleteQuery = "DELETE FROM Users WHERE Username = ?";
            try (PreparedStatement preparedStatement = dbConnect.prepareStatement(deleteQuery)) {
                preparedStatement.setString(1, username);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        try{
            String deleteQuery = "DELETE FROM Aircraft WHERE AircraftID = ?";
            try (PreparedStatement preparedStatement = dbConnect.prepareStatement(deleteQuery)) {
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
            try (PreparedStatement preparedStatement = dbConnect.prepareStatement(insertQuery)) {
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
    public void removePassengers(int flightID, String seatNumber){
        try{
            String deleteQuery = "DELETE FROM passengers WHERE FlightID = ? AND SeatNumber = ?";
            try (PreparedStatement preparedStatement = dbConnect.prepareStatement(deleteQuery)) {
                preparedStatement.setInt(1, flightID);
                preparedStatement.setString(2, seatNumber);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //method for adding flights to the database
    public void addFlight(int flightID, String flightNumber, String origin, String 
    destination, String departureTime, String arrivalTime, int aircraftID){
        try{
            String insertQuery = "INSERT INTO Flights (FlightID, FlightNumber, Origin, Destination, DepartureTime, ArrivalTime, AircraftID) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = dbConnect.prepareStatement(insertQuery)) {
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
