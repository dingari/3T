package main.service;

import main.mock.Facility;
import main.mock.Hotel;
import main.mock.Location;

public interface HotelFinderService {

    public Hotel[] getAllHotels();

    public Hotel[] limitToChain(Hotel[] hotels, String chain);

    public Hotel[] limitToFacility(Hotel[] hotels, Facility facility);

    public Hotel[] limitToLocation(Hotel[] hotels, Location location);

    public Hotel[] limitToNoStars(Hotel[] hotels, int stars);
}
