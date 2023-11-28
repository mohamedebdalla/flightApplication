package main.project.flightApplication;

import java.util.ArrayList;
import main.project.flightApplication.Controller.DBcore;

public class Flight {
    private ArrayList<CrewMember> crew;
    private ArrayList<Passenger> passengers;
    private Aircraft aircraft;
    private FlightRoute route;
    private int flightID;
    private String flightNumber;
    private String departureDateTime;
    private String arrivalDateTime;
    private String origin;
    private String destination;
    private int aircraftID;

    private DBcore db = DBcore.getInstance();
    
    public Flight(int flightID, String flightNumber, String Origin, String Destination, String DepartureDateTime, String ArrivalDateTime, int AircraftID){
        this.flightID = flightID;
        this.flightNumber = flightNumber;
        this.origin = Origin;
        this.destination = Destination;
        this.departureDateTime = DepartureDateTime;
        this.arrivalDateTime = ArrivalDateTime;
        this.aircraftID = AircraftID;
    }
    public Flight(ArrayList<CrewMember> crew, ArrayList<Passenger> passengers, Aircraft aircraft, String originName, String destinationName, int flightID) {
        this.crew = crew;
        this.passengers = passengers;
        this.aircraft = aircraft;
        this.route = new FlightRoute(originName, destinationName); //composition
        this.flightID = flightID;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getDepartureDateTime() {
        return departureDateTime;
    }

    public String getArrivalDateTime() {
        return arrivalDateTime;
    }

    public String getOrigin() {
        return origin;
    }

    public int getFlightID() {
        return flightID;
    }

    public String getDestination() {
        return destination;
    }   
}
