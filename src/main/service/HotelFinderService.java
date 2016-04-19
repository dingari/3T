package main.service;

import main.mock.Facility;
import main.mock.Hotel;
import main.mock.Location;

import java.util.ArrayList;

public interface HotelFinderService {

    public ArrayList<Hotel> getFreeRoomsFromHotel(String hotelName);

    public ArrayList<Hotel> getFreeRoomsFromHotelLocation(String hotelLocation);

    public ArrayList<Hotel> getFreeRoomsFromHotelChain(String hotelChain);

    public ArrayList<Hotel> getFreeRoomsFromAnyHotel();

    public ArrayList<Hotel> getFreeHotelsFromAnyHotelSubString(String substring);

    public void book(Hotel hotel, int roomId);

    public void unbook(Hotel hotel, int BookID);

    public ArrayList<Hotel> getHotelWithFacilities(int[] facilityID);

    public ArrayList<Hotel> filterHotelWithFacilities(ArrayList<Hotel> hotels, int[] facilityId);
}
