package main.project.flightApplication;

public class CreditCard {
    private String cardNumber;
    private int cvv;
    private String expiryDate;

    public CreditCard(String cardNumber, int cvv, String expiryDate) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public int getCvv() {
        return cvv;
    }

    public String getExpiryDate() {
        return expiryDate;
    }
}
