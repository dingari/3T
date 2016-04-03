package main.service;

import main.mock.Review;
import main.mock.Tour;

import java.util.Date;
import java.util.List;

public interface TourSearchService {

    public List<Tour> getTourList(int priceLow, int priceHigh, int duration, Date dateLower, Date dateUpper, List<String> type, int availSeats, String tourName);

    public List<Review> getTourReviews(String tourName);

    public List<Review> getGuideReview(String guideName);

    public void updateSeats(String tourName, int decrease);

    public void updateRating(String tourName, float rated);
}
