package main.service;

import java.util.Date;
import java.util.List;

public interface FlightSearchService {

    public List<String> getFlightByCriteria(Date timeFrom, Date timeTo, String fromLocation, String toLocation, int travelerQty);
}
