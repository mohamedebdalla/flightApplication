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
