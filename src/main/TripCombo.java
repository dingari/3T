package main;

import main.mock.Flight;
import main.mock.Hotel;
import main.mock.Tour;

public class TripCombo {

    private Flight outboundFlight;
    private Flight inboundFlight;
    private Hotel hotel;
    private Tour tour;

    public TripCombo(Flight oubboundFlight, Flight inboundFlight, Hotel hotel, Tour tour) {
        this.outboundFlight = oubboundFlight;
        this.inboundFlight = inboundFlight;
        this.hotel = hotel;
        this.tour = tour;
    }

    public int getPrice() {
        int outboundFlightPrice = (outboundFlight == null) ? 0 : outboundFlight.getPrice();
        int inboundFlightPrice = (inboundFlight == null) ? 0 : inboundFlight.getPrice();
        int hotelPrice = (hotel == null) ? 0 : hotel.getPrice();
        int tourPrice = (tour == null) ? 0 : tour.getPrice();

        return outboundFlightPrice + inboundFlightPrice + hotelPrice + tourPrice;
    }

    public Flight getOutboundFlight() {
        return outboundFlight;
    }

    public void setOutboundFlight(Flight outboundFlight) {
        this.outboundFlight = outboundFlight;
    }

    public Flight getInboundFlight() {
        return inboundFlight;
    }

    public void setInboundFlight(Flight inboundFlight) {
        this.inboundFlight = inboundFlight;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }
}
