package main.mock;

public class HotelRoomMock implements HotelRoom {
    @Override
    public int getRate() {
        return ((int) Math.random()*100);
    }

    @Override
    public int getNumPerson() {
        return ((int) Math.random()*100);
    }

    @Override
    public int getId() {
        return ((int) Math.random()*10);
    }
}
