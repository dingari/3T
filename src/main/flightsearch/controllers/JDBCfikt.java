
package main.flightsearch.controllers;


import main.flightsearch.models.Flight;

import java.util.ArrayList;

/**
 * Created by stefa on 4/11/2016.
 */
public class JDBCfikt {

    public static void main(String[] args) {
        ArrayList<Flight> flights = new ArrayList<>();

        flights = queryFlight();
        updateFlightTest(flights);
        flights = queryFlight();
    }

    public static ArrayList<Flight> queryFlight(){
        SearchEngine searchEngine = new SearchEngine();
        ArrayList<Flight> flightList = new ArrayList<>();
        try {
            System.out.println("Retrieving results");
            flightList = searchEngine.searchFlightByCriteria("2016-04-20", "Iceland", "Casablanca", 1);
        } catch (Exception e)
        {
            System.err.println("Shiiii "+ e.getMessage());
        }

        System.out.println("Displaying results:");

        for(Flight currentFlight : flightList) {
            //System.out.println("Flight number: " + currentFlight.getFlightNumber() + ", Price: " + currentFlight.getPrice() + ", Wifi Availablle? " + currentFlight.getPassengerLuxuries().isWifiAvailable());
            System.out.println("Flight number:" + currentFlight.getFlightNumber() + " Booked Seats: "+currentFlight.getBookedSeats() + " Booked saga seats: "+currentFlight.getBookedSagaSeats() );
        }

        return flightList;
    }

    public static void updateFlightTest(ArrayList<Flight> flightList){

        if(flightList == null){
            System.out.println("Listinn er tómur");
        }
        else {
            Flight flightToBook = flightList.get(0);

            BookingManager bookFlight = new BookingManager();
            bookFlight.updateFlight(flightToBook, 2, true);
            bookFlight.updateFlight(flightToBook, 5, false);

        }


    }



}
