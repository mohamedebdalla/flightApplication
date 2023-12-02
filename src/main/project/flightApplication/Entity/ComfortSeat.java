package main.project.flightApplication.Entity;

public class ComfortSeat implements SeatStrategy{
    private double basePrice;

    public ComfortSeat(double basePrice){
        this.basePrice = basePrice;
    }

    @Override
    public double calculatePrice(){
        //comfort seats are 40% more expensive than ordinary seats
        return basePrice * 1.4;
    }
}
