package main.project.flightApplication.Entity;

public class Address {
    private String streetNo;
    private String streetName;
    private String city;
    private String province;
    private String postalCode;

    public Address(String streetNo, String streetName, String city, String province, String postalCode) {
        this.streetNo = streetNo;
        this.streetName = streetName;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
    }

    
}
