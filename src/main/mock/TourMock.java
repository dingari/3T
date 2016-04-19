package main.mock;

import java.util.Date;

public class TourMock implements Tour {

    public TourMock() {

    }


    @Override
    public String getName() {
        return (Math.random() < 0.5) ? "foo" : "bar";
    }

    @Override
    public String getType() {
        return (Math.random() < 0.5) ? "adventure" : "historic";
    }

    @Override
    public String getDepartureLocation() {
        return (Math.random() < 0.5) ? "Reykjavik" : "Geysir";
    }

    @Override
    public int getDuration() {
        return (int) (Math.random()*15);
    }

    @Override
    public int getPrice() {
        return (int) (Math.random()*1000);
    }
}
