package main.project.flightApplication.Controller;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import main.project.flightApplication.Entity.Flight;
import main.project.flightApplication.Entity.Passenger;

public class TicketController {
    public void generateTicketFile(Passenger passenger, Flight flight) {
    // Gather ticket information
    String ticketInfo = "Thank you for booking with us! Here's your ticket: \n" +
            "Passenger Name: " + passenger.getPassengerName() + "\n" +
            "Ticket ID: " + passenger.getTicket().getTicketId() + "\n" +
            "Flight ID: " + passenger.getFlightId() + "\n" +
            "Seat Number: " + passenger.getSeatNumber() + "\n" +
            "Flight Number: " + passenger.getFlightNumber() + "\n";

    // Create a file and write the ticket information
    try (PrintWriter writer = new PrintWriter("ticket.txt")) {
        writer.println(ticketInfo);
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
}
}
