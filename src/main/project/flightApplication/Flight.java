package main.project.flightApplication;

import java.util.ArrayList;

public class Flight {
    private ArrayList<CrewMember> crew;
    private ArrayList<Passenger> passengers;
    private Aircraft aircraft;
    private FlightRoute route;
    private String flightID;
    
    public Flight(ArrayList<CrewMember> crew, ArrayList<Passenger> passengers, Aircraft aircraft, String originName, String destinationName, String originCode, String destinationCode, String flightID) {
        this.crew = crew;
        this.passengers = passengers;
        this.aircraft = aircraft;
        this.route = new FlightRoute(originName, destinationName, originCode, destinationCode); //composition
        this.flightID = flightID;
    }

    
}
