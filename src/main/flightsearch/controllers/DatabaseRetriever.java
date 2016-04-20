package main.flightsearch.controllers;


import java.sql.*;
import java.util.ArrayList;

import main.flightsearch.exceptions.InvalidTimeException;
import main.flightsearch.models.Flight;

/**
 * Class for establishing a connection to MySQL database via JDBC to retrieve information regarding
 * flights. Various methods for returning Flight objects.
 */
public class DatabaseRetriever {


    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost/flight_database?autoReconnect=true&useSSL=false";
    private static final String USER = "root";
    private static final String PASS = "";


    public DatabaseRetriever() {
    }

    /**
     * Retrieves flights from MySQL database with given parameters.
     * @param departureDate Date of departure on format "yyyy-MM-dd"
     * @param departureLocation Name of location of departure
     * @param arrivalLocation Name of loacation of arrival
     * @param passengerQty Amount of bookable seats on flight
     * @return
     * @throws SQLException
     */
    public ArrayList<Flight> retrieveFlightsByCriteria(String departureDate, String departureLocation, String arrivalLocation, int passengerQty) throws SQLException {


        ArrayList<Flight> flightList = new ArrayList<>();

        // SQL query formation and sanitization
        String queryString = "SELECT * FROM flight f LEFT JOIN connect_flight c ON c.departureDate = f.departureDate " +
                "AND c.flightNumber = f.flightNumber LEFT JOIN passenger_luxuries p ON p.departureDate = f.departureDate " +
                "AND p.flightNumber = f.flightNumber WHERE f.departureDate = ? " +
                "AND f.departureLoc = ? AND f.arrivalLoc = ? AND ((f.numSeats+f.numSagaSeats)-(f.bookedSeats+f.bookedSagaSeats-?))>0";

        try {
            // Retrieve JDBC driver
            try {
                Class.forName(JDBC_DRIVER);
            }catch (ClassNotFoundException e){
                System.out.println(e.getMessage());
            }

            Connection conn = DriverManager.getConnection(DATABASE_URL, USER, PASS);

            PreparedStatement statement = conn.prepareStatement(queryString);
            statement.setString(1, departureDate);
            statement.setString(2, departureLocation);
            statement.setString(3, arrivalLocation);
            statement.setInt(4, passengerQty);
            ResultSet results = statement.executeQuery();
            ArrayList<Flight> flightResults = new ArrayList<>();

            while (results.next()) {
                /*
                * Extract data from resultSet
                 */
                String flightNumber = results.getString("flightNumber");
                int price = results.getInt("price");


                String depDate = results.getString("departureDate");
                String departureTime = results.getString("departureTime");
                String departureLoc = results.getString("departureLoc");
                String depAirportId = results.getString("depAirportId");

                String arrivalDate = results.getString("arrivalDate");
                String arrivalTime = results.getString("arrivalTime");
                String arrivalLoc = results.getString("arrivalLoc");
                String arrAirportId = results.getString("arrAirportId");

                int numSeats = results.getInt("numSeats");
                int bookedSeats = results.getInt("bookedSeats");
                int numSagaSeats = results.getInt("numSagaSeats");
                int bookedSagaSeats = results.getInt("bookedSagaSeats");

                String connLoc = results.getString("connLoc");
                String connArrivalTime = results.getString("connArrivalTime");
                String connAirportId = results.getString("connAirportId");
                String connDepartTime = results.getString("connDepartTime");

                String[] connectFlight = new String[]{connLoc, connArrivalTime, connAirportId, connDepartTime};


                boolean wifiAvailable = results.getBoolean("wifiAvailable");
                boolean mealsAvailable = results.getBoolean("mealsAvailable");

                boolean[] passengerLuxBool = new boolean[]{wifiAvailable, mealsAvailable};


                int priceInFlightPoints = results.getInt("priceInFlightPoints");
                int flightPointsGained = results.getInt("flightPointsGained");

                int[] passengerLuxInt = new int[]{priceInFlightPoints, flightPointsGained};

                Flight tmpFlight = null;

                try {
                    tmpFlight = new Flight(depDate, price, flightNumber, departureLoc, departureTime,
                            depAirportId, arrivalLoc, arrivalDate, arrivalTime, arrAirportId, numSeats, bookedSeats,
                            numSagaSeats, bookedSagaSeats, connectFlight, passengerLuxBool, passengerLuxInt);
                } catch(InvalidTimeException i){
                    System.out.println(i.getMessage());
                }

                flightResults.add(tmpFlight);
            }

            flightList = flightResults;

        } catch (SQLException e) {
            System.out.println("Error message: " + e.getMessage());
        }

        return flightList;
    }
}
