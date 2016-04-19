package main.mock;

import main.service.HotelFinderService;

import java.util.ArrayList;

public class HotelFinderMock implements HotelFinderService {

    public HotelFinderMock(String newCheckInDate, String newCheckoutDate) {

    }

    @Override
    public ArrayList<Hotel> getFreeRoomsFromHotel(String hotelName) {
        return null;
    }

    @Override
    public ArrayList<Hotel> getFreeRoomsFromHotelLocation(String hotelLocation) {
        ArrayList<Hotel> list = new ArrayList<>();
        list.add(new HotelMock());
        list.add(new HotelMock());
        list.add(new HotelMock());

        return list;
    }

    @Override
    public ArrayList<Hotel> getFreeRoomsFromHotelChain(String hotelChain) {
        return null;
    }

    @Override
    public ArrayList<Hotel> getFreeRoomsFromAnyHotel() {
        return null;
    }

    @Override
    public ArrayList<Hotel> getFreeHotelsFromAnyHotelSubString(String substring) {
        return null;
    }

    @Override
    public void book(Hotel hotel, int roomId) {

    }

    @Override
    public void unbook(Hotel hotel, int BookID) {

    }

    @Override
    public ArrayList<Hotel> getHotelWithFacilities(int[] facilityID) {
        return null;
    }

    @Override
    public ArrayList<Hotel> filterHotelWithFacilities(ArrayList<Hotel> hotels, int[] facilityId) {
        return null;
    }
}
