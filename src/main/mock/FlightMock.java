package main.mock;

import java.util.Date;

public class FlightMock implements Flight {
    @Override
    public int getPrice() {
        return 0;
    }

    @Override
    public Date getDepartureDate() {
        return new Date();
    }

    @Override
    public Date getReturnDate() {
        return new Date();
    }
}
