package main.project.flightApplication.Controller;

import main.project.flightApplication.Booking;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;

public class CancelFlightController {
    private Booking booking;
    private DBcore dbcore = DBcore.getInstance();
    // public CancelFlightController(Booking booking){
    //     this.booking = booking;
    // }

    public ArrayList<String> getAllTicketIDs(){
        ArrayList<String> ticketIDs = new ArrayList<>();
        try{
            String selectQuery = "SELECT ticketID FROM Bookings";
            try (PreparedStatement preparedStatement = dbcore.getConnection().prepareStatement(selectQuery)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    ticketIDs.add(resultSet.getString("ticketID"));
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return ticketIDs;
    }

    public void removeBooking(String ticketID){
        try{
            String deleteQuery = "DELETE FROM Bookings WHERE ticketID = ?";
            try (PreparedStatement preparedStatement = dbcore.getConnection().prepareStatement(deleteQuery)) {
                preparedStatement.setString(1, ticketID);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void removePassenger(String ticketID){
        try{
            String deleteQuery = "DELETE FROM Passengers WHERE ticketID = ?";
            try (PreparedStatement preparedStatement = dbcore.getConnection().prepareStatement(deleteQuery)) {
                preparedStatement.setString(1, ticketID);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
