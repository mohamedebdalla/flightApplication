package main.project.flightApplication.Entity;

public class OrdinarySeat implements SeatStrategy{
    private double basePrice;

    public OrdinarySeat(double basePrice){
        this.basePrice = basePrice;
    }

    @Override
    public double calculatePrice(){
        //ordinary seats have the base price
        return basePrice;
    }
    
}
