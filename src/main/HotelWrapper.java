package main;


import main.hotelsearch.Hotel;
import main.hotelsearch.HotelRoom;

import java.util.ArrayList;

public class HotelWrapper {

    Hotel hotel;
    HotelRoom hotelRoom;

    public HotelWrapper(Hotel hotel, HotelRoom room) {
        this.hotel = hotel;
        this.hotelRoom = room;
    }

    public static ArrayList<HotelWrapper> wrapList(ArrayList<Hotel> hotels) {
        ArrayList<HotelWrapper> wrappedList = new ArrayList<>();

        for(Hotel hotel: hotels) {
            ArrayList<HotelRoom> rooms = hotel.getHotelRooms();
            for(HotelRoom room: rooms) {
                wrappedList.add(new HotelWrapper(hotel, room));
            }
        }

        return wrappedList;
    }

    public String getName() {
        return hotel.getName();
    }

    public String getChain() {
        return hotel.getChain();
    }

    public String getCity() {
        return hotel.getCity();
    }

    public int getRate() {
        return hotelRoom.getRate();
    }

    public Hotel getHotel() {
        return hotel;
    }

    public HotelRoom getHotelRoom() {
        return hotelRoom;
    }

    public int getCapacity() {
        return hotelRoom.getNumPerson();
    }
}
