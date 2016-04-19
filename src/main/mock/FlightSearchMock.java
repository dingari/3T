package main.mock;

import main.service.FlightSearchService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FlightSearchMock implements FlightSearchService {

    @Override
    public ArrayList<Flight> searchFlightByCriteria(String departureDate, String departureLocation, String arrivalLocation,
                                               int passengerQty) {

        ArrayList<Flight> list = new ArrayList<>();
        list.add(new FlightMock());
        list.add(new FlightMock());
        list.add(new FlightMock());

        return list;
    }

    @Override
    public ArrayList<Flight> filterFlightList(ArrayList<Flight> flightListToFilter, String timeFrom, String timeTo,
                                         boolean wantSagaSeats, boolean wantWifi, boolean priceDescending,
                                         int maxPrice) {

        ArrayList<Flight> list = new ArrayList<>();
        list.add(new FlightMock());
        list.add(new FlightMock());
        list.add(new FlightMock());

        return list;
    }
}
