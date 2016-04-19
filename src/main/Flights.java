package main;

/**
 * FLIGHTS
 */
public class Flights {
    private String flightDepartureTime, flightArrivalTime, flightAirportFrom,flightAirportTo;
    private int flightPrice;

    public Flights(String flightDepartureTime, String flightArrivalTime, String flightAirportFrom,String flightAirportTo, int flightPrice){
        this.flightDepartureTime = flightDepartureTime;
        this.flightArrivalTime = flightArrivalTime;
        this.flightAirportFrom = flightAirportFrom;
        this.flightAirportTo = flightAirportTo;
        this.flightPrice = flightPrice;

    }

    public String getFlightDepartureTime() {
        return flightDepartureTime;
    }

    public void setFlightDepartureTime(String flightDepartureTime) {
        this.flightDepartureTime = flightDepartureTime;
    }

    public String getFlightArrivalTime() {
        return flightArrivalTime;
    }

    public void setFlightArrivalTime(String flightArrivalTime) {
        this.flightArrivalTime = flightArrivalTime;
    }

    public String getFlightAirportFrom() {
        return flightAirportFrom;
    }

    public void setFlightAirportFrom(String flightAirportFrom) {
        this.flightAirportFrom = flightAirportFrom;
    }

    public String getFlightAirportTo() {
        return flightAirportTo;
    }

    public void setFlightAirportTo(String flightAirportTo) {
        this.flightAirportTo = flightAirportTo;
    }

    public int getFlightPrice() {
        return flightPrice;
    }

    public void setFlightPrice(int flightPrice) {
        this.flightPrice = flightPrice;
    }
}
