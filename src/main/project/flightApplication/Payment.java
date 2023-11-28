package main.project.flightApplication;

public class Payment {
    private CreditCard creditCard;
    private double amount;
    private Ticket ticket; 
    private String date;

    public Payment(CreditCard creditCard, double amount, Ticket ticket, String date) {
        this.creditCard = creditCard;
        this.amount = amount;
        this.ticket = ticket;
        this.date = date;
    }

    //should call an addpassengers 
}


