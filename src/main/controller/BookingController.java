package main.controller;

import main.model.ComboBooking;
import main.model.TripCombo;
import main.flightsearch.controllers.BookingManager;
import main.hotelsearch.Booker;

import java.sql.Date;

public class BookingController {

    BookingManager flightBookingService;
    Booker hotelBookingService;
//    TourBookingService tourBookingService;

    public BookingController() {
        this.flightBookingService = new BookingManager();
        this.hotelBookingService = new Booker();
//        this.tourBookingService = new TourBookingMock();
    }

    public ComboBooking bookCombo(TripCombo combo) {
        int numPeople = combo.getNumPeople();

        // Book flight
        flightBookingService.updateFlight(combo.getInboundFlight(), numPeople, false);
        flightBookingService.updateFlight(combo.getOutboundFlight(), numPeople, false);

        // Book hotel room
        hotelBookingService.book(
                combo.getHotel().getHotel(),
                combo.getHotel().getHotelRoom().getId(),
                Date.valueOf(combo.getDepartureTime()),
                Date.valueOf(combo.getReturnTime())
        );

        // Book tour
//        tourBookingService.bookTour(combo.getTour(), numPeople);

        // Just return an empty object for now, maybe unneccessary
        return new ComboBooking();
    }
}
