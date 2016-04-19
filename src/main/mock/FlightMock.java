package main.mock;

import java.util.Date;

public class FlightMock implements Flight {

    @Override
    public int getPrice() {
        return (int) (Math.random()*100);
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
