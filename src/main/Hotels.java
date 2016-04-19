package main;

/**
 *  HOTELS
 */
public class Hotels {

    private String HotelName, HotelChain, HotelLocation;
    private int HotelPrice;

    public Hotels(String HotelName, String HotelChain, String HotelLocation, int HotelPrice){
        this.HotelName = HotelName;
        this.HotelChain = HotelChain;
        this.HotelLocation = HotelLocation;
        this.HotelPrice = HotelPrice;
    }

    public String getHotelName() {
        return HotelName;
    }

    public void setHotelName(String hotelName) {
        HotelName = hotelName;
    }

    public String getHotelChain() {
        return HotelChain;
    }

    public void setHotelChain(String hotelChain) {
        HotelChain = hotelChain;
    }

    public String getHotelLocation() {
        return HotelLocation;
    }

    public void setHotelLocation(String hotelLocation) {
        HotelLocation = hotelLocation;
    }

    public int getHotelPrice() {
        return HotelPrice;
    }

    public void setHotelPrice(int hotelPrice) {
        HotelPrice = hotelPrice;
    }
}
