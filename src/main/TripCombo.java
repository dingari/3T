package main;

import main.mock.Flight;
import main.mock.Hotel;
import main.mock.Tour;

public class TripCombo {

    private Flight flight;
    private Hotel hotel;
    private Tour tour;

    public TripCombo(Flight flight, Hotel hotel, Tour tour) {
        this.flight = flight;
        this.hotel = hotel;
        this.tour = tour;
    }

    public int getPrice() {
        int flightPrice = (flight == null) ? 0 : flight.getPrice();
        int hotelPrice = (hotel == null) ? 0 : hotel.getPrice();
        int tourPrice = (tour == null) ? 0 : tour.getPrice();

        return flightPrice + hotelPrice + tourPrice;
    }
}
