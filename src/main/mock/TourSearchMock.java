package main.mock;

import main.service.TourSearchService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TourSearchMock implements TourSearchService{

    public TourSearchMock() {

    }

    @Override
    public ArrayList<Tour> createList() {
        ArrayList<Tour> tours = new ArrayList<>();
        tours.add(new TourMock());
        tours.add(new TourMock());
        tours.add(new TourMock());

        return tours;
    }

    @Override
    public ArrayList<Tour> createList(Integer priceLower, Integer priceHigher, Integer durationLower,
                                      Integer durationHigher, Date dateLower, Date dateHigher,
                                      Integer minAvailableSeats, String destination, String departure, String type,
                                      Integer rating, boolean hotelPickup, String name) {

        ArrayList<Tour> list = new ArrayList<>();
        list.add(new TourMock());
        list.add(new TourMock());
        list.add(new TourMock());

        return list;
    }

    @Override
    public void bookTourSeats(Tour tour, int bookedSeats) {

    }
}
