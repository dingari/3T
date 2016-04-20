package main.flightsearch.exceptions;

/**
 * Custom exception for invalid flight time boundaries
 */
public class InvalidTimeException extends Exception {

    public InvalidTimeException(String message){
        super(message);
    }
}