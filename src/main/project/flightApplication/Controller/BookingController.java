package main.project.flightApplication.Controller;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import main.project.flightApplication.Entity.Booking;

import java.sql.ResultSet;

public class BookingController {

    private DBcore dbcore = DBcore.getInstance();
    private Booking booking;
    public void addBooking(int flightID, String email, int seatNumber, String ticketID){
        try{
            String insertQuery = "INSERT INTO Bookings (flightID, email, seatOption, ticketID) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = dbcore.getConnection().prepareStatement(insertQuery)) {
                preparedStatement.setInt(1, flightID);
                preparedStatement.setString(2, email);
                preparedStatement.setInt(3, seatNumber);
                preparedStatement.setString(4, ticketID);
                preparedStatement.executeUpdate();
                booking = new Booking(flightID, email, seatNumber, ticketID);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public ArrayList<Integer> getAllSeatsBooked(int flightID){
        ArrayList<Integer> seatsBooked = new ArrayList<>();
        try{
            String selectQuery = "SELECT seatOption FROM Bookings WHERE flightID = ?";
            try (PreparedStatement preparedStatement = dbcore.getConnection().prepareStatement(selectQuery)) {
                preparedStatement.setInt(1, flightID);
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    seatsBooked.add(resultSet.getInt("seatOption"));
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return seatsBooked;
    }
}
