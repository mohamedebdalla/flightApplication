package main.project.flightApplication.Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.project.flightApplication.Passenger;

public class StaffController {
    DBcore db = DBcore.getInstance();

    public ArrayList<Passenger> getPassengers() {
        ArrayList<Passenger> passengers = new ArrayList<>();
        try {
            String selectQuery = "SELECT * FROM passengers";
            try (PreparedStatement preparedStatement = db.getConnection().prepareStatement(selectQuery)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Passenger passenger = new Passenger( resultSet.getString("PassengerName"),
                                         resultSet.getInt("FlightID"),
                                         resultSet.getInt("SeatNumber"),
                                         resultSet.getString("TicketID"),
                                         resultSet.getString("TicketInsurance"),
                                         resultSet.getString("FlightNumber"));
                    passengers.add(passenger);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passengers;
    }
}
