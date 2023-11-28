package main.project.flightApplication.Controller;
import java.sql.*;
import java.util.ArrayList;
import main.project.flightApplication.Flight;


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
            String DB_PASSWORD = "mohamed123";
            dbConnect = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("Connected to database");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    

    public void browseAllFlights(){
        try{
            String selectQuery = "SELECT * FROM Flights";
            try (PreparedStatement preparedStatement = dbConnect.prepareStatement(selectQuery)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    System.out.println(resultSet.getInt("FlightID") + " " + resultSet.getString("FlightNumber") + " " + resultSet.getString("Origin") + " " + resultSet.getString("Destination") + " " + resultSet.getString("DepartureDateTime") + " " + resultSet.getString("ArrivalDateTime") + " " + resultSet.getInt("AircraftID"));
                }
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
