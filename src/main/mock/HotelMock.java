package main.mock;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public class HotelMock implements Hotel {

    public HotelMock() {

    }

    @Override
    public String getName() {
        return "hotel foo";
    }

    @Override
    public String getCity() {
        return "reykjavik";
    }

    @Override
    public String getChain() {
        return "radison";
    }

    @Override
    public ArrayList<HotelRoom> getHotelRooms() {
        ArrayList<HotelRoom> list = new ArrayList<HotelRoom>();
        list.add(new HotelRoomMock());
        list.add(new HotelRoomMock());
        list.add(new HotelRoomMock());

        return list;
    }

    @Override
    public ArrayList<Facility> getFacilities() {
        return null;
    }

    @Override
    public ArrayList<Review> getReviews() {
        return null;
    }
}
