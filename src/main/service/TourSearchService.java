package main.service;

import main.mock.Review;
import main.mock.Tour;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface TourSearchService {

    public ArrayList<Tour> createList(Integer priceLower, Integer priceHigher, Integer durationLower, Integer durationHigher,
                                      Date dateLower, Date dateHigher, Integer minAvailableSeats, String destination, String departure,
                                      String type, Integer rating, boolean hotelPickup, String name);

    public void bookTourSeats(Tour tour, int bookedSeats);
}
