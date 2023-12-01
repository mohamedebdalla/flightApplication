package main.project.flightApplication;

public class Passenger {
    private String passengerName;
    //private int passengerId;
    private Ticket ticket;
    private int flightId;
    private int seatNumber;
    private String flightNumber;

    public Passenger(String passName, int flightId, int seatNumber, String ticketId, String ticketInsurance, String flightNumber){
        this.passengerName = passName;
        //this.passengerId = passId;
        this.flightId = flightId;
        this.seatNumber = seatNumber;
        this.ticket = new Ticket(ticketId, ticketInsurance);
        this.flightNumber = flightNumber;
    }

    public String getPassengerName() {
        return passengerName;
    }

    // public int getPassengerId() {
    //     return passengerId;
    // }

    public Ticket getTicket() {
        return ticket;
    }

    public int getFlightId() {
        return flightId;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public String getFlightNumber() {
        return flightNumber;
    }
}
