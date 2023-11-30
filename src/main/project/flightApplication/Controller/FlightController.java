package main.project.flightApplication.Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.project.flightApplication.Flight;

public class FlightController {
    private DBcore dbcore = DBcore.getInstance();


        public ArrayList<Flight> getAllFlights(){
        ArrayList<Flight> flights = new ArrayList<>();
        try{
            String selectQuery = "SELECT * FROM Flights";
            try (PreparedStatement preparedStatement = dbcore.getConnection().prepareStatement(selectQuery)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    Flight flight = new Flight(resultSet.getInt("FlightID"),
                            resultSet.getString("FlightNumber"),
                            resultSet.getString("Origin"),
                            resultSet.getString("Destination"),
                            resultSet.getString("DepartureDate"),
                            resultSet.getString("DepartureTime"),
                            resultSet.getString("ArrivalDate"),
                            resultSet.getString("ArrivalTime"),
                            resultSet.getInt("AircraftID"));
                            
                    flights.add(flight);
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return flights;
    }


    public ArrayList <String> getAllDepartureDates(){
        ArrayList<String> dates = new ArrayList<>();
        try{
            String selectQuery = "SELECT DISTINCT DepartureDate FROM Flights";
            try (PreparedStatement preparedStatement = dbcore.getConnection().prepareStatement(selectQuery)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    dates.add(resultSet.getString("DepartureDate"));
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        for(String date: dates){
            System.out.println(date);
        }
        return dates;
    }

    public ArrayList<String> getAllDepartureOrigin(){
        ArrayList <String> location = new ArrayList<>();
        try{
            String selectQuery = "SELECT DISTINCT Origin FROM Flights";
            try (PreparedStatement preparedStatement = dbcore.getConnection().prepareStatement(selectQuery)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    location.add(resultSet.getString("Origin"));
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return location;
    }

    public ArrayList<String> getAllDest(){
        ArrayList <String> location = new ArrayList<>();
        try{
            String selectQuery = "SELECT DISTINCT Destination FROM Flights";
            try (PreparedStatement preparedStatement = dbcore.getConnection().prepareStatement(selectQuery)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    location.add(resultSet.getString("Destination"));
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return location;
    }

    public ArrayList<String> getDatesByOriginAndDestination(String selectedOrigin, String selectedDest){
        ArrayList<String> dates = new ArrayList<>();
        try{
            String selectQuery = "SELECT DISTINCT DepartureDate FROM Flights WHERE Origin = ? AND Destination = ?";
            try (PreparedStatement preparedStatement = dbcore.getConnection().prepareStatement(selectQuery)) {
                preparedStatement.setString(1, selectedOrigin);
                preparedStatement.setString(2, selectedDest);
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    dates.add(resultSet.getString("DepartureDate"));
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return dates;
    }

    public ArrayList<Flight> getFlightByInput(String origin, String dest, String date){
        ArrayList<Flight> flights = new ArrayList<>();
        try{
            String selectQuery = "SELECT * FROM Flights WHERE Origin = ? AND Destination = ? AND DepartureDate = ?";
            try (PreparedStatement preparedStatement = dbcore.getConnection().prepareStatement(selectQuery)) {
                preparedStatement.setString(1, origin);
                preparedStatement.setString(2, dest);
                preparedStatement.setString(3, date);
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    Flight flight = new Flight(resultSet.getInt("FlightID"),
                            resultSet.getString("FlightNumber"),
                            resultSet.getString("Origin"),
                            resultSet.getString("Destination"),
                            resultSet.getString("DepartureDate"),
                            resultSet.getString("DepartureTime"),
                            resultSet.getString("ArrivalDate"),
                            resultSet.getString("ArrivalTime"),
                            resultSet.getInt("AircraftID"));
                    flights.add(flight);
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return flights;
    }

    
}
