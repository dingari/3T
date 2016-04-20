package main.flightsearch.controllers;
/*
import java.sql.ResultSet;
import java.sql.SQLException;
*/

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import jdk.jfr.events.ExceptionThrownEvent;
import main.flightsearch.controllers.DatabaseRetriever;
import main.flightsearch.models.Flight;


/**
 * Class built for retrieval of inital ArrayList of Flight
 * objects and subsequent filtering of said list. Searching
 * generates a new list while filtering narrows the list down.
 */
public class SearchEngine {

    private DatabaseRetriever DBConnection;
    private ArrayList<Flight> flightList;
    private ArrayList<Flight> filteredFlightList;
    private int passengerQty;
    private SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");

    public SearchEngine(){
        DBConnection = new DatabaseRetriever();
    }

    /**
     *  Search for flights given some criterias
     * @param departureDate String containing date of departure on format "yyyy-MM-dd"
     * @param departureLocation String containing name of departure location
     * @param arrivalLocation String containing name of arrival location
     * @param passengerQty Integer representing amount of minimum amount of free seats
     * @return Returns ArrayList of type <Flight>
     */
    public ArrayList<Flight> searchFlightByCriteria(String departureDate, String departureLocation, String arrivalLocation, int passengerQty){
        // Connect to MySQL database and retrieve relevant data
        try{
            flightList = DBConnection.retrieveFlightsByCriteria(departureDate, departureLocation, arrivalLocation, passengerQty);
        } catch (Exception e){
            System.err.println("Message: "+e.getMessage());
        }
        this.passengerQty = passengerQty;
        return flightList;
    }

    /**
     * Method for further narrowing of already created list of Flight objects. Allows further
     * specifications to be made on what flights are included.
     * @param flightListToFilter Array list of type Flight containing flights to be narrowed down
     *                           with set parameters
     * @param timeFrom String containing earliest time possible for flight on format "hh:mm:ss"
     * @param timeTo String containing latest time possible for flight on format "hh:mm:ss"
     * @param wantSagaSeats Boolean determining whether only business class flights are shown
     * @param wantWifi Boolean determining whether only flights with WiFi capabilities are shown
     * @param priceDescending Boolean determining whether flights are shown in descending order with
     *                        regard to price
     * @param maxPrice Integer determining maximum price of flights to be shown
     * @return Returns ArrayList of type <Flight> with regard to above stated parameters
     */

    public ArrayList<Flight> filterFlightList(ArrayList<Flight> flightListToFilter, String timeFrom, String timeTo, boolean wantSagaSeats, boolean wantWifi, boolean priceDescending, int maxPrice){

        filteredFlightList = new ArrayList<Flight>();
        for(Flight flight : flightListToFilter){
            // Determines if the flight is within specified parameters
            boolean flightEligible = true;
            // Desired date restricitions
            Date timeFromWanted = new Date();
            Date timeToWanted = new Date();
            Date flightDepTime = new Date();
            try {
                timeFromWanted = timeFormat.parse(timeFrom);
                timeToWanted = timeFormat.parse(timeTo);
                flightDepTime = timeFormat.parse(flight.getDepartureTime());
            } catch(ParseException e) {
                System.err.println("String to Date parsing error:" + e.getMessage());
            }

            // Dates of flight available
            if(timeFromWanted.compareTo(flightDepTime) >= 0 && timeToWanted.compareTo(flightDepTime) <= 0){
                flightEligible = false;
            }

            // Reject flight if client wants Saga class seats but none are available
            int availableSagaSeats = flight.getNumSagaSeats() - flight.getBookedSagaSeats();
            if(wantSagaSeats && availableSagaSeats < passengerQty ){
                flightEligible = false;
            }

            // Reject flight if client wants wifi but none is available
            if(wantWifi && flight.getPassengerLuxuries().isWifiAvailable()){
                flightEligible = false;
            }

            // Reject flight if price is too high for client or and client does care about price (price != 0)
            if(flight.getPrice() > maxPrice && flight.getPrice() != 0){
                flightEligible = false;
            }

            // Add flight to list if it passes all conditions
            if(flightEligible){
                filteredFlightList.add(flight);
            }

        }

        // Determine how to sort list of flights
        Collections.sort(filteredFlightList);
        if(!priceDescending){
            ArrayList<Flight> tmpList = new ArrayList<Flight>();
            while(!filteredFlightList.isEmpty()){
                Flight tmpFlight = filteredFlightList.remove(0);
                tmpList.add(0,tmpFlight);
            }
            filteredFlightList = tmpList;
        }

        return filteredFlightList;
    }


}