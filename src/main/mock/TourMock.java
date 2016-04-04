package main.mock;

import java.util.Date;

public class TourMock implements Tour {

    public TourMock() {

    }

    @Override
    public int getPrice() {
        return 0;
    }

    @Override
    public String getLocation() {
        return "";
    }

    @Override
    public Date getStartDate() {
        return new Date();
    }

    @Override
    public Date getEndDate() {
        return new Date();
    }
}
