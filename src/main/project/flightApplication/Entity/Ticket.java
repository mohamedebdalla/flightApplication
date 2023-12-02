package main.project.flightApplication.Entity;

public class Ticket {
    private String ticketId;
    private String ticketInsurance;
    
    public Ticket(String ticketId, String ticketInsurance) {
        this.ticketId = ticketId;
        this.ticketInsurance = ticketInsurance;
    }

    public String getTicketId() {
        return ticketId;
    }

    public String getTicketInsurance() {
        return ticketInsurance;
    }

    //add passenger
}
