package main.mock;

import main.service.FlightSearchService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FlightSearchMock implements FlightSearchService {

    public FlightSearchMock() {

    }

    @Override
    public List<String> getFlightByCriteria(Date timeFrom, Date timeTo, String fromLocation, String toLocation, int travelerQty) {
        ArrayList<String> flights = new ArrayList<String>();

        flights.add("WW555");
        flights.add("IS252");

        return flights;
    }
}
