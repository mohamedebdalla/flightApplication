package main.project.flightApplication;

public class FlightRoute {
    private String originName;
    private String destinationName;
    private String originCode;
    private String destinationCode;

    public FlightRoute(String originName, String destinationName, String originCode, String destinationCode) {
        this.originName = originName;
        this.destinationName = destinationName;
        this.originCode = originCode;
        this.destinationCode = destinationCode;
    }

    public String getOriginName() {
        return originName;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public String getOriginCode() {
        return originCode;
    }

    public String getDestinationCode() {
        return destinationCode;
    }
}
