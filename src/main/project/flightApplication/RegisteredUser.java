package main.project.flightApplication;

public class RegisteredUser {
    private String name;
    private String username;
    private String password;
    private int creditCardNo;
    private Address address;
    
    public RegisteredUser(String name, String username, String password, int creditCardNo, Address address) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.creditCardNo = creditCardNo;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public int getCreditCardNo() {
        return creditCardNo;
    }

    public Address getAddress() {
        return address;
    }

    public String getPassword() {
        return password;
    }
}
