package main;

import main.flightsearch.models.Flight;
import main.mock.Hotel;
import main.mock.HotelRoom;
import main.mock.Tour;

public class TripCombo {

    private Flight outboundFlight;
    private Flight inboundFlight;
    private HotelWrapper hotel;
    private Tour tour;

    private int numPeople;

    public TripCombo(Flight oubboundFlight, Flight inboundFlight, HotelWrapper hotel, Tour tour, int numPeople) {
        this.outboundFlight = oubboundFlight;
        this.inboundFlight = inboundFlight;
        this.hotel = hotel;
        this.tour = tour;
        this.numPeople = numPeople;
    }

    public int getPrice() {
        int outboundFlightPrice = (int) ((outboundFlight == null) ? 0 : outboundFlight.getPrice());
        int inboundFlightPrice = (int) ((inboundFlight == null) ? 0 : inboundFlight.getPrice());
        int hotelPrice = (hotel == null) ? 0 : hotel.getRate();
        int tourPrice = (tour == null) ? 0 : tour.getPrice();

        return outboundFlightPrice + inboundFlightPrice + hotelPrice + tourPrice;
    }

    public int getNumPeople() {
        return numPeople;
    }

    public void setNumPeople(int numPeople) {
        this.numPeople = numPeople;
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

    public HotelWrapper getHotel() {
        return hotel;
    }

    public void setHotel(HotelWrapper hotel) {
        this.hotel = hotel;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public String getDepartureTime() {
        return outboundFlight.getDepartureTime();
    }

    public String getReturnTime() {
        return inboundFlight.getArrivalTime();
    }

    public String getHotelName() {
        return hotel.getName();
    }

    public String getTourName() {
        return tour.getName();
    }

    public String getTourType() {
        return tour.getType();
    }
}
