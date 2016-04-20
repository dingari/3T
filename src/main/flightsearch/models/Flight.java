package main.flightsearch.models;

import main.flightsearch.exceptions.InvalidTimeException;
import main.flightsearch.models.ConnectFlight;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by - Flight Group - HBV401G
 * Spring 2016
 *
 *
 * Flight class, where each instance of this class
 * represents one flight, it implements Comparable
 * so it is possible to sort flights depending on
 * price criteria.
 */
public class Flight implements Comparable<Flight>{

    /**
     * Instance variables
     */
    private double price;
    private String flightNumber;
    private String departureLoc;
    private String departureDate;
    private String departureTime;
    private String depAirportId;
    private String arrivalLoc;
    private String arrivalDate;
    private String arrivalTime;
    private String arrAirportId;
    private int numSeats;
    private int bookedSeats;
    private int numSagaSeats;
    private int bookedSagaSeats;
    private ConnectFlight connectFlight;
    private PassengerLuxuries passengerLuxuries;

    /**
     * The flight constructor with all of it's properties
     * @param departureDate String of format "yyyy-mm-dd"
     * @param price Double containing price of flight in unspecified currency
     * @param flightNumber String with number of flight, often of format [A-Z]\{2}[0-9]\{3}
     * @param departureLoc String containing name of departure location
     * @param departureTime String containing local departure time in format "hh:mm:ss"
     * @param depAirportId String containing 3 letter international identification tag for departure airport
     * @param arrivalLoc String containing name of arrival location
     * @param arrivalDate String of format "yyyy-mm-dd"
     * @param arrivalTime String containing local arrival time in format "hh:mm:ss"
     * @param arrAirportId String containing 3 letter international identification tag for departure airport
     * @param numSeats Int variable representing total amount of economy class seats on flight
     * @param bookedSeats Int variable representing total amount of economy class seats already booked
     * @param numSagaSeats Int variable representing total amount of business class seats on flight
     * @param bookedSagaSeats Int variable representing total amount of business class seats already booked
     * @param connectFlight Custom Object containing data on layovers for connected flights.
     * @param passengerLuxBool Boolean array determining whether wifi is available and whether meals are
     *                         available in flight. See PassengerLuxuries.java for further information.
     * @param passengerLuxInt Integer array for determining how much a flight ticket costs in flight points
     *                        and how many flight points are gained by flying. See
     *                        PassengerLuxuries.java for further information.
     */
    public Flight(String departureDate, double price, String flightNumber, String departureLoc, String departureTime,
                  String depAirportId, String arrivalLoc, String arrivalDate, String arrivalTime, String arrAirportId,
                  int numSeats, int bookedSeats, int numSagaSeats, int bookedSagaSeats,
                  String[] connectFlight, boolean[] passengerLuxBool, int[] passengerLuxInt) throws InvalidTimeException {

        this.setFlightNumber(flightNumber);
        this.setPrice(price);
        this.setDepartureDate(departureDate);
        this.setDepartureTime(departureTime);
        this.setDepartureLoc(departureLoc);
        this.setDepAirportId(depAirportId);
        this.setArrivalDate(arrivalDate);
        this.setArrivalTime(arrivalTime);
        this.setArrivalLoc(arrivalLoc);
        this.setArrAirportId(arrAirportId);

        this.setNumSeats(numSeats);
        this.setBookedSeats(bookedSeats);
        this.setNumSagaSeats(numSagaSeats);
        this.setBookedSagaSeats(bookedSagaSeats);




        // Create an instance of PassengerLuxuries and ConnectFlight objects
        if(connectFlight == null){
            this.setConnectFlight(null);
        }
        else{
            this.setConnectFlight(new ConnectFlight(connectFlight[0], connectFlight[1],
                    connectFlight[2], connectFlight[3]));
        }

        this.setPassengerLuxuries(new PassengerLuxuries(passengerLuxBool[0], passengerLuxBool[1],
                passengerLuxInt[0], passengerLuxInt[1]));

        // Test to see if time and date of departure and arrival are valid
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-ddHH:mm:ss");
        Date dateStart = new Date();
        Date dateEnd = new Date();

        try {
            dateStart = dateFormat.parse(departureDate + departureTime);
            dateEnd = dateFormat.parse(arrivalDate + arrivalTime);
        } catch (ParseException parseException){
            System.out.println(parseException.getMessage());
        }
        if(dateStart.after(dateEnd)){
            throw new InvalidTimeException("Error: Arrival date is before departure date.");
        }


    }

    /**
     * This function compares prices between two different flight objects
     * @param flight1 Flight with price to be compared to
     * @return returns negative if flight1 is cheaper, 0 if price
     * is the same and positive number if it is more expensive
     */
    @Override
    public int compareTo(Flight flight1){
        int comparePrice = (int)flight1.getPrice();

        return comparePrice - (int) this.getPrice();
    }

    /*
        GET/SET  METHODS
     */

    private boolean isConnectedFlight() {
        return getConnectFlight() == null;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDepartureLoc() {
        return departureLoc;
    }

    public void setDepartureLoc(String departureLoc) {
        this.departureLoc = departureLoc;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getDepAirportId() {
        return depAirportId;
    }

    public void setDepAirportId(String depAirportId) {
        this.depAirportId = depAirportId;
    }

    public String getArrivalLoc() {
        return arrivalLoc;
    }

    public void setArrivalLoc(String arrivalLoc) {
        this.arrivalLoc = arrivalLoc;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getArrAirportId() {
        return arrAirportId;
    }

    public void setArrAirportId(String arrAirportId) {
        this.arrAirportId = arrAirportId;
    }

    public int getNumSeats() {
        return numSeats;
    }

    public void setNumSeats(int numSeats) {
        this.numSeats = numSeats;
    }

    public int getBookedSeats() {
        return bookedSeats;
    }

    public void setBookedSeats(int bookedSeats) {
        this.bookedSeats = bookedSeats;
    }

    public int getNumSagaSeats() {
        return numSagaSeats;
    }

    public void setNumSagaSeats(int numSagaSeats) {
        this.numSagaSeats = numSagaSeats;
    }

    public int getBookedSagaSeats() {
        return bookedSagaSeats;
    }

    public void setBookedSagaSeats(int bookedSagaSeats) {
        this.bookedSagaSeats = bookedSagaSeats;
    }

    public ConnectFlight getConnectFlight() {
        return connectFlight;
    }

    public void setConnectFlight(ConnectFlight connectFlight) {
        this.connectFlight = connectFlight;
    }

    public PassengerLuxuries getPassengerLuxuries() {
        return passengerLuxuries;
    }

    public void setPassengerLuxuries(PassengerLuxuries passengerLuxuries) {
        this.passengerLuxuries = passengerLuxuries;
    }
}
