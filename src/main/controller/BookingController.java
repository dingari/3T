package main.controller;

import main.ComboBooking;
import main.HotelBooking;
import main.TourBooking;
import main.TripCombo;
import main.flightsearch.controllers.BookingManager;
import main.mock.HotelBookingMock;
import main.mock.TourBookingMock;
import main.service.HotelBookingService;
import main.service.TourBookingService;

public class BookingController {

    BookingManager flightBookingService;
    HotelBookingService hotelBookingService;
    TourBookingService tourBookingService;

    public BookingController() {
        this.flightBookingService = new BookingManager();
        this.hotelBookingService = new HotelBookingMock();
        this.tourBookingService = new TourBookingMock();
    }

    public ComboBooking bookCombo(TripCombo combo) {
        int numPeople = combo.getNumPeople();

        // Book flight
        flightBookingService.updateFlight(combo.getInboundFlight(), numPeople, false);
        flightBookingService.updateFlight(combo.getOutboundFlight(), numPeople, false);

        // Book hotel room
        int roomID = 0; // TODO: Get a room ID somehow
        hotelBookingService.bookHotelRoom(combo.getHotel().getHotel(), roomID);

        // Book tour
        tourBookingService.bookTour(combo.getTour(), numPeople);

        // Just return an empty object for now, maybe unneccessary
        return new ComboBooking();
    }
}
