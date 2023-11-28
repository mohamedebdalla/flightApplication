package main.project.flightApplication;

public class RegisteredUser implements User {
    private String name;
    private String username;
    private String password;
    private String email;
    // private int creditCardNo; -> commenting out for now, not sure if this needs to be stored in the database
    private String address; //changed this to a type String because can't use Address class in resultset to get from the database
    
    public RegisteredUser(String name, String username, String password, String email, String address) {
    
        this.name = name;
        this.username = username;
        this.password = password;
        this.address = address;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail(){
        return email;
    }
    // public int getCreditCardNo() {
    //     return creditCardNo;
    // }

    public String getAddress() {
        return address;
    }


}
