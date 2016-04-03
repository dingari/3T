package main.mock;

import main.service.HotelFinderService;

public class HotelFinderMock implements HotelFinderService {

    public HotelFinderMock() {

    }


    @Override
    public Hotel[] getAllHotels() {
        Hotel[] hotels = new Hotel[1];
        hotels[1] = new HotelMock();
        return hotels;
    }

    @Override
    public Hotel[] limitToChain(Hotel[] hotels, String chain) {
        return hotels;
    }

    @Override
    public Hotel[] limitToFacility(Hotel[] hotels, Facility facility) {
        return hotels;
    }

    @Override
    public Hotel[] limitToLocation(Hotel[] hotels, Location location) {
        return hotels;
    }

    @Override
    public Hotel[] limitToNoStars(Hotel[] hotels, int stars) {
        return hotels;
    }
}
