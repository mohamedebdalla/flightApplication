package main.project.flightApplication.Entity;

public class BusinessClassSeat implements SeatStrategy{
    private double basePrice;

    public BusinessClassSeat(double basePrice){
        this.basePrice = basePrice;
    }

    @Override
    public double calculatePrice(){
        //business class seats are more than double the price of ordinary seats
        return basePrice * 2.5;
    }

}
