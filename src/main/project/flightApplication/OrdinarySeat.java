package main.project.flightApplication;

public class OrdinarySeat implements SeatStrategy{
    @Override
    public double calculatePrice(double basePrice){
        //ordinary seats have the base price
        return basePrice;
    }
    
}
