package main.mock;

import main.service.TourSearchService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TourSearchMock implements TourSearchService{

    public TourSearchMock() {

    }


    @Override
    public List<Tour> getTourList(int priceLow, int priceHigh, int duration, Date dateLower, Date dateUpper, List<String> type, int availSeats, String tourName) {
        return null;
    }

    @Override
    public List<Review> getTourReviews(String tourName) {
        ArrayList<Review> reviews = new ArrayList<Review>();
        reviews.add(new ReviewMock());
        return reviews;
    }

    @Override
    public List<Review> getGuideReview(String guideName) {
        ArrayList<Review> reviews = new ArrayList<Review>();
        reviews.add(new ReviewMock());
        return reviews;
    }

    @Override
    public void updateSeats(String tourName, int decrease) {

    }

    @Override
    public void updateRating(String tourName, float rated) {

    }
}
