package sample;

/**
 * Created by Party on 19.4.2016.
 */
public class tableFlights {

    private String locationFromData,locationToData;
    private double priceData;
    private int passengersData;



    public tableFlights(String locationFromData,String locationToData, double priceData, int passengersData){
        this.locationFromData = locationFromData;
        this.locationToData = locationToData;
        this.priceData = priceData;
        this.passengersData = passengersData;
    }


    public String getLocationFromData() {
        return locationFromData;
    }

    public void setLocationFromData(String locationFromData) {
        this.locationFromData = locationFromData;
    }

    public String getLocationToData() {
        return locationToData;
    }

    public void setLocationToData(String locationToData) {
        this.locationToData = locationToData;
    }

    public double getPriceData() {
        return priceData;
    }

    public void setPriceData(double priceData) {
        this.priceData = priceData;
    }

    public int getPassengersData() {
        return passengersData;
    }

    public void setPassengersData(int passengersData) {
        this.passengersData = passengersData;
    }
}
