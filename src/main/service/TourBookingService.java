package main.service;

import main.mock.Tour;

public interface TourBookingService {

    public void bookTour(Tour tour, int bookedSeats);
}
