package main.flightsearch.models;

public class PassengerLuxuries {
    // PRIVATE VARIABLES
    private boolean wifiAvailable;
    private boolean mealsAvailable;
    private int priceInFlightPoints;
    private int flightPointsGained;

    // CONSTRUCTOR
    public PassengerLuxuries(boolean wifiAvailable, boolean mealsAvailable, int priceInFlightPoints,
                             int flightPointsGained) {

        this.wifiAvailable = wifiAvailable;
        this.mealsAvailable = mealsAvailable;
        this.priceInFlightPoints = priceInFlightPoints;
        this.flightPointsGained = flightPointsGained;

    }

    // GET/SET METHODS
    public boolean isWifiAvailable() {
        return wifiAvailable;
    }
    public void setWifiAvailable(boolean wifiAvailable) {
        this.wifiAvailable = wifiAvailable;
    }
    public boolean isMealsAvailable() {
        return mealsAvailable;
    }
    public void setMealsAvailable(boolean mealsAvailable) {
        this.mealsAvailable = mealsAvailable;
    }
    public int getPriceInFlightPoints() {
        return priceInFlightPoints;
    }
    public void setPriceInFlightPoints(int priceInFlightPoints) {
        this.priceInFlightPoints = priceInFlightPoints;
    }
    public int getFlightPointsGained() {
        return flightPointsGained;
    }
    public void setFlightPointsGained(int flightPointsGained) {
        this.flightPointsGained = flightPointsGained;
    }

}
