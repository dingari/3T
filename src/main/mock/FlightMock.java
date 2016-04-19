package main.mock;

import java.util.Date;

public class FlightMock implements Flight {


    @Override
    public int getPrice() {
        return 0;
    }

    @Override
    public String getFlightNumber() {
        return "flightnumber";
    }

    @Override
    public String getDepartureLoc() {
        return "departure location";
    }

    @Override
    public String getDepartureDate() {
        return "departure date";
    }

    @Override
    public String getReturnDate() {
        return "return date";
    }

    @Override
    public String getDepartureTime() {
        return "departure time";
    }

    @Override
    public String getDepAirportId() {
        return "departure airport id";
    }

    @Override
    public String getArrivalLoc() {
        return "arrival location";
    }

    @Override
    public String getArrivalDate() {
        return "arrival date";
    }

    @Override
    public String getArrivalTime() {
        return "arrival time";
    }

    @Override
    public String getArrAirportId() {
        return "arriival airport id";
    }
}
