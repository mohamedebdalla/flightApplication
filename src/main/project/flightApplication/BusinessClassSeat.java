package main.project.flightApplication;

public class BusinessClassSeat implements SeatStrategy{
    @Override
    public double calculatePrice(double basePrice){
        //business class seats are more than double the price of ordinary seats
        return basePrice * 2.5;
    }

}
