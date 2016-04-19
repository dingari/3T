package main;

import main.mock.Hotel;
import main.mock.HotelRoom;

public class HotelWrapper {

    private Hotel hotel;
    private HotelRoom hotelRoom;

    public HotelWrapper(Hotel hotel, HotelRoom room) {
        this.hotel = hotel;
        this.hotelRoom = hotelRoom;
    }


}
