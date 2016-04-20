package main.flightsearch.controllers;

import com.sun.org.apache.xpath.internal.SourceTree;
import main.flightsearch.models.Flight;

import java.sql.*;

/**
 * Connects to MySQL database and updates relevant info for flights
 */
public class DatabaseUpdater {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost/flight_database?autoReconnect=true&useSSL=false";
    private static final String USER = "root";
    private static final String PASS = "";

    /**
     * Function to update data for specific flight
     * @param flightToBook Flight object with data to be changed
     * @param bookedSeatQty Integer number of seats to be booked in economy class
     * @param bookSagaSeats Integer number of seats to be booked in business class
     */
    public void updateFlight(Flight flightToBook, int bookedSeatQty, boolean bookSagaSeats) {

        String query = "update flight set bookedSeats = bookedSeats + ? WHERE flightNumber = ? AND departureDate = ?";

        if (bookSagaSeats) {
            query = "update flight set bookedSagaSeats = bookedSagaSeats + ? WHERE flightNumber = ? AND departureDate = ?";
        }

        String flightNumber = flightToBook.getFlightNumber();
        String departureDate = flightToBook.getDepartureDate();
        try {
            try {
                Class.forName(JDBC_DRIVER);
            }catch (ClassNotFoundException e){
                System.out.println(e.getMessage());
            }

            System.out.println("Establishing a connection to the DB)");
            Connection conn = DriverManager.getConnection(DATABASE_URL, USER, PASS);
            System.out.println("connection success");
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, bookedSeatQty);
            statement.setString(2, flightNumber);
            statement.setString(3, departureDate);
            statement.executeUpdate();

            System.out.println("Update successful");
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}