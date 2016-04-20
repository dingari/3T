package main.flightsearch.test;

import main.flightsearch.controllers.SearchEngine;
import main.flightsearch.exceptions.InvalidTimeException;
import main.flightsearch.models.Flight;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * JUnit class for testing SearchEngine class and capabilites.
 */
public class TestSearchEngine {
    /**
     * Instance variables
     */
    private SearchEngine searchEngine;
    private ArrayList<Flight> flightListAll;
    private SimpleDateFormat format;
    private SimpleDateFormat timeFormat;


    @Before
    public void setUp() throws Exception {
        searchEngine = new SearchEngine();
        format = new SimpleDateFormat("yyyy-mm-dd");
        timeFormat = new SimpleDateFormat("HH:mm:ss");
        flightListAll = searchEngine.searchFlightByCriteria("2016-04-20", "Reykjavik", "Akureyri", 1);
    }

    @After
    public void tearDown() throws Exception {
        flightListAll.clear();
    }

    @Test
    public void testSetup() throws Exception {
        assertNotNull(flightListAll.get(0));
    }

    @Test
    public void testFilterWifiFlights() throws Exception{
        ArrayList<Flight> filteredWifiList = searchEngine.filterFlightList(flightListAll,"00:01:00","23:59:00",false,true,false,0);

        for(Flight flight : filteredWifiList){
            assertEquals(true, flight.getPassengerLuxuries().isWifiAvailable());
        }
    }

    @Test
    public void testDescendingPriceOrder() throws Exception{
        ArrayList<Flight> filteredDescendingList = searchEngine.filterFlightList(flightListAll,"00:01:00","23:59:00",false, false, true, 0);

        //Some large value here that is guaranteed to be larger than the most expensive flight
        double oldPrice = 1000000000;

        for (Flight flight : filteredDescendingList){
            assertTrue(flight.getPrice() <= oldPrice);
            oldPrice = flight.getPrice();
        }
    }

    @Test
    public void testAscendingPriceOrder() throws Exception{
        ArrayList<Flight> filteredAscendingList = searchEngine.filterFlightList(flightListAll,"00:01:00","23:59:00",false, false, false, 0);

        // Negative value guaranteed to be lower than least expensive flight
        double tmpPrice = 0;

        for(Flight flight : filteredAscendingList){
            assertTrue(flight.getPrice() >= tmpPrice);
            tmpPrice = flight.getPrice();
        }
    }

    @Test
    public void testSagaAvailability() throws Exception{
        ArrayList<Flight>  filteredSagaList = searchEngine.filterFlightList(flightListAll,"00:01:00","23:59:00",true,false,false,0);
        for (Flight flight : filteredSagaList){
            int numAvailableSagaSeats = flight.getNumSagaSeats()-flight.getBookedSagaSeats();
            assertTrue(numAvailableSagaSeats > 0);
        }
    }

    @Test
    public void testWithinTimeFrame() throws Exception{
        String dateFromString = "00:01:00";
        String dateToString = "23:59:00";
        Date dateFrom = timeFormat.parse(dateFromString);
        Date dateTo = timeFormat.parse(dateToString);

        ArrayList<Flight>  filteredDateList = searchEngine.filterFlightList(flightListAll,dateFromString, dateToString,false,false,false,0);
        for (Flight flight : filteredDateList){
            Date flightDate = format.parse(flight.getDepartureDate());
            assertTrue(dateFrom.compareTo(flightDate) <= 0 && dateTo.compareTo(flightDate) >= 0);
        }
    }

    @Test
    public void testDateException(){
        String[] connectArray = {"","","",""};
        boolean[] luxArray = {true,true};
        int[] luxInt = {1,2};
        try {
            Flight flight = new Flight("2016-04-03", 60000, "LFW698", "Munich", "15:30:00", "MUH", "Iceland", "2016-04-02",
                    "14:30:00", "KEF", 50, 0, 15, 0, connectArray, luxArray, luxInt);
        } catch (InvalidTimeException i){
            System.out.println(i.getMessage());
            assertEquals(i.getMessage(),"Error: Arrival date is before departure date.");
        }

    }
}
