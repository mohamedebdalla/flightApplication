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
    private String departureDate;
    private String departureTime;
    private String arrivalDate;
    private String arrivalTime;
    private String origin;
    private String destination;
    private int aircraftID;

    private DBcore db = DBcore.getInstance();
    
    public Flight(int flightID, String flightNumber, String Origin, String Destination, String DepartureDate, String DepartureTime, String ArrivalDate, String ArrivalTime, int AircraftID){
        this.flightID = flightID;
        this.flightNumber = flightNumber;
        this.origin = Origin;
        this.destination = Destination;
        this.departureDate = DepartureDate;
        this.departureTime = DepartureTime;
        this.arrivalDate = ArrivalDate;
        this.arrivalTime = ArrivalTime;
        this.aircraftID = AircraftID;
    }
    // public Flight(ArrayList<CrewMember> crew, ArrayList<Passenger> passengers, Aircraft aircraft, String originName, String destinationName, int flightID) {
    //     this.crew = crew;
    //     this.passengers = passengers;
    //     this.aircraft = aircraft;
    //     this.route = new FlightRoute(originName, destinationName); //composition
    //     this.flightID = flightID;
    // }
    
    // Getters
    public ArrayList<CrewMember> getCrew() {
        return crew;
    }

    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public FlightRoute getRoute() {
        return route;
    }

    public int getFlightID() {
        return flightID;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public int getAircraftID() {
        return aircraftID;
    }


    
}

