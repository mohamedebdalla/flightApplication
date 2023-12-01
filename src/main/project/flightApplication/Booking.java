package main.project.flightApplication;

public class Booking {
    private int flightID;
    private String email;
    private int seatNumber;
    private String ticketID;    

    public Booking(int flightID, String email, int seatNumber, String ticketID) {
        this.flightID = flightID;
        this.email = email;
        this.seatNumber = seatNumber;
        this.ticketID = ticketID;
    }

    public int getFlightID() {
        return flightID;
    }

    public String getEmail() {
        return email;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public String getTicketID() {
        return ticketID;
    }
}
