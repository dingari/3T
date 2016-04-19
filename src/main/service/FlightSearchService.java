package main.service;

import main.mock.Flight;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface FlightSearchService {

    public ArrayList<Flight> searchFlightByCriteria(String departureDate, String departureLocation, String arrivalLocation,
                                               int passengerQty);

    public ArrayList<Flight> filterFlightList(ArrayList<Flight> flightListToFilter, String timeFrom, String timeTo,
                                         boolean wantSagaSeats, boolean wantWifi, boolean priceDescending, int maxPrice);
}
