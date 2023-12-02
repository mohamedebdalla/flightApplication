package main.project.flightApplication;

public class Payment {
    private String creditCardNumber;
    private String cardholderName;
    private String expiryDate;
    private String cvv;
    private double amountPaid;

    public Payment(String creditCardNumber, String cardholderName, String expiryDate, String cvv, double amountPaid) {
        this.creditCardNumber = creditCardNumber;
        this.cardholderName = cardholderName;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
        this.amountPaid = amountPaid;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public String getCardholderName() {
        return cardholderName;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public String getCvv() {
        return cvv;
    }

    public double getAmountPaid() {
        return amountPaid;
    }
}


