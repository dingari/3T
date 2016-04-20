package main.flightsearch.models;

/**
 * Custom Object containing information regarding layover flights
 */
public class ConnectFlight {

    // PRIVATE VARIABLES
    private String connLoc; // String containing name of location of connecting flights
    private String connArrivalTime; // String with arrival at connective location on format "hh:mm:ss"
    private String connAirportId; // String containing 3 character international identification tag of airport
    private String connDepartTime; // String with departure time at connective location on format "hh:mm:ss"

    // CONSTRUCTOR
    public ConnectFlight(String connLoc, String connArrivalTime, String connAirportId,
                         String connDepartTime) {

        this.connLoc = connLoc;
        this.connArrivalTime = connArrivalTime;
        this.connAirportId = connAirportId;
        this.connDepartTime = connDepartTime;

    }

    // GET/SET METHODS
    public String getConnLoc() {
        return connLoc;
    }
    public void setConnLoc(String connLoc) {
        this.connLoc = connLoc;
    }
    public String getConnArrivalTime() {
        return connArrivalTime;
    }
    public void setConnArrivalTime(String connArrivalTime) {
        this.connArrivalTime = connArrivalTime;
    }
    public String getConnAirportId() {
        return connAirportId;
    }
    public void setConnAirportId(String connAirportId) {
        this.connAirportId = connAirportId;
    }
    public String getConnDepartTime() {
        return connDepartTime;
    }
    public void setConnDepartTime(String connDepartTime) {
        this.connDepartTime = connDepartTime;
    }
}
