package main.mock;

import java.util.ArrayList;
import java.util.Date;

public interface Hotel {

    public String getName();

    public String getCity();

    public String getChain();

    public ArrayList<HotelRoom> getHotelRooms();

    public ArrayList<Facility> getFacilities();

    public ArrayList<Review> getReviews();
}
