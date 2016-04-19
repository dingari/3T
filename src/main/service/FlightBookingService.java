package main.service;

import main.mock.Flight;

public interface FlightBookingService {

    public void updateFlight(Flight flightToBook, int bookedSeatQty, boolean bookSagaSeats);
}
