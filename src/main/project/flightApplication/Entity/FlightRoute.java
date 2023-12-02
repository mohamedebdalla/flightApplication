package main.project.flightApplication.Entity;

public class FlightRoute {
    private String originName;
    private String destinationName;
    // private String originCode;
    // private String destinationCode;

    public FlightRoute(String originName, String destinationName) {
        this.originName = originName;
        this.destinationName = destinationName;
        // this.originCode = originCode;
        // this.destinationCode = destinationCode;
    }

    public String getOriginName(String flightID) {
        return originName;
    }

    public String getDestinationName(String flightID) {
        return destinationName;
    }

    // public String getOriginCode() {
    //     return originCode;
    // }

    // public String getDestinationCode() {
    //     return destinationCode;
    // }
}
