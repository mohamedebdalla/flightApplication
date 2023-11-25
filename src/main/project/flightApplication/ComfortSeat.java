package main.project.flightApplication;

public class ComfortSeat implements SeatStrategy{
    @Override
    public double calculatePrice(double basePrice){
        //comfort seats are 40% more expensive than ordinary seats
        return basePrice * 1.4;
    }
}
