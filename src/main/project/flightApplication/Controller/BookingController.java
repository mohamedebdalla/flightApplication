package main.project.flightApplication.Controller;

import main.project.flightApplication.Booking;

import java.sql.PreparedStatement;
import java.sql.SQLException;


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
}
