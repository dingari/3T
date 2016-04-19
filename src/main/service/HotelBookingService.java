package main.service;

import main.mock.Hotel;

public interface HotelBookingService {

    public void bookHotelRoom(Hotel hotel, int roomID);

    public void unbookHotelRoom(Hotel hotel, int roomID);
}
