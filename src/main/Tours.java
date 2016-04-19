package sample;

/**
 *  TOURS
 */
public class Tours {
    private String TourName, TourType, TourDeparture;
    private int TourDuration, TourPrice;

    public Tours(String TourName, String TourType, String TourDeparture, int TourDuration, int TourPrice){
        this.TourName = TourName;
        this.TourType = TourType;
        this.TourDeparture = TourDeparture;
        this.TourDuration = TourDuration;
        this.TourPrice = TourPrice;
    }

    public String getTourName() {
        return TourName;
    }

    public void setTourName(String tourName) {
        TourName = tourName;
    }

    public String getTourType() {
        return TourType;
    }

    public void setTourType(String tourType) {
        TourType = tourType;
    }

    public String getTourDeparture() {
        return TourDeparture;
    }

    public void setTourDeparture(String tourDeparture) {
        TourDeparture = tourDeparture;
    }

    public int getTourDuration() {
        return TourDuration;
    }

    public void setTourDuration(int tourDuration) {
        TourDuration = tourDuration;
    }

    public int getTourPrice() {
        return TourPrice;
    }

    public void setTourPrice(int tourPrice) {
        TourPrice = tourPrice;
    }
}
