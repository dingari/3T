package main.flightsearch.controllers;

import main.flightsearch.models.Flight;

/**
 * Class that receives bookings for flights from GUI and sends them to database handler for
 * further processing.
 */
public class BookingManager {
    // PRIVATE VARIABLES
    private DatabaseUpdater databaseUpdater = new DatabaseUpdater();

    public void updateFlight(Flight flightToBook, int bookedSeatQty, boolean bookSagaSeats) {
        databaseUpdater.updateFlight(flightToBook, bookedSeatQty, bookSagaSeats);
    }
}