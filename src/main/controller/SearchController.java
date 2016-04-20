package main.controller;

import main.HotelWrapper;
import main.TripCombo;
import main.TripPlanner;
import main.flightsearch.controllers.SearchEngine;
import main.flightsearch.models.Flight;
import main.hotelsearch.Hotel;
import main.hotelsearch.HotelFinder;
import main.toursearch.controller.SearchManager;
import main.toursearch.model.Tour;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SearchController {

	SearchEngine flightSearch;
    HotelFinder hotelSearch;
    TripPlanner tripPlanner;

    ArrayList<Flight> flightResults;
    ArrayList<HotelWrapper> hotelResults;
    ArrayList<Tour> tourResults;
	List<TripCombo> combos;

	// General search parameters
	Date departureDate;
	Date returnDate;
	String departLocation;
	String destLocation;
	int numPeople;
	int minPrice;
	int maxPrice;

	// Flight specific parameters
    boolean flightWifi;
	String flightTimeFrom;
	String flightTimeTo;
	boolean flightSagaSeats;

    // Hotel specific parameters
    boolean[] selectedHotelFacilities;
    int selectedHotelSearchMethod;
    String hotelSearchString;

    // Tour specific parameters
    int tourPriceLower;
    int tourPriceHigher;
    int tourDurationLower;
    int tourDurationHigher;
    Date tourDateLower;
    Date tourDateHigher;
	List<String> tourTypes;
	String tourType = "";
	String tourDestination = "";
	int tourRating;
	boolean tourHotelPickup;
	String tourName = "";
	
	public SearchController(Date departDate, Date returnDate) {
		this.departureDate = departDate;
		this.returnDate = returnDate;

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		flightSearch = new SearchEngine();
		hotelSearch = new HotelFinder(dateToString(departDate), dateToString(returnDate));
		tripPlanner = new TripPlanner(departDate, returnDate);

		selectedHotelFacilities = new boolean[14];
		for(int i=1; i<selectedHotelFacilities.length; i++) {
			selectedHotelFacilities[i] = false;
		}

		this.tourRating = 0;
	}

	public ArrayList<Flight> searchFlights() {
		System.out.println("Searching flights " + dateToString(departureDate) + ", " + departLocation + ", " + destLocation + ", " + numPeople);
        flightResults = flightSearch.searchFlightByCriteria(dateToString(departureDate), departLocation, destLocation, numPeople);

		return flightResults;
	}

	public ArrayList<HotelWrapper> searchHotels() {
		hotelSearch = new HotelFinder(dateToString(departureDate), dateToString(returnDate));
		ArrayList<Hotel> hotels = new ArrayList<>();

		switch (selectedHotelSearchMethod) {
			case 0:
				hotels = hotelSearch.getFreeRoomsFromAnyHotel();
				break;
			case 1:
				hotels = hotelSearch.getFreeRoomsFromHotel(hotelSearchString);
				break;
			case 2:
				hotels = hotelSearch.getFreeRoomsFromHotelLocation(hotelSearchString);
				break;
			case 3:
				hotels = hotelSearch.getFreeRoomsFromHotelChain(hotelSearchString);
				break;
			case 4:
				hotels = hotelSearch.getFreeRoomsFromAnyHotelSubString(hotelSearchString);
				break;
		}

		ArrayList<Integer> selectedList = new ArrayList<>();
		for(int i=0; i< selectedHotelFacilities.length; i++) {
			if(selectedHotelFacilities[i]) {
				selectedList.add(i);
			}
		}

		int[] selectedArray = new int[selectedList.size()];
		for(int i=0; i<selectedList.size(); i++) {
			selectedArray[i] = selectedList.get(i);
		}

		hotels = hotelSearch.filterHotelWithFacilities(hotels, selectedArray);

		hotelResults = HotelWrapper.wrapList(hotels);

		return hotelResults;
	}

	public ArrayList<Tour> searchTours() {
		try {
			tourResults = SearchManager.createList(tourPriceLower, tourPriceHigher, tourDurationLower, tourDurationHigher,
					tourDateLower, tourDateHigher, numPeople, tourDestination, departLocation, tourType, tourRating,
					tourHotelPickup, tourName);
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			tourResults = new ArrayList<>();
		}

		return tourResults;
	}

	public List<TripCombo> searchCombos() {
		combos = tripPlanner.suggestCombos(10);

		return combos;
	}

	private String dateToString(Date date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		return df.format(date);
	}




	/*


			Getters and setters


	 */

	public ArrayList<Flight> getFlightResults() {
		return flightResults;
	}

	public ArrayList<HotelWrapper> getHotelResults() {
		return hotelResults;
	}

	public ArrayList<Tour> getTourResults() {
		return tourResults;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
		tripPlanner.setDepTime(departureDate);
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
		tripPlanner.setReturnTime(returnDate);
	}

	public String getDepartLocation() {
		return departLocation;
	}

	public void setDepartLocation(String departLocation) {
		this.departLocation = departLocation;
		tripPlanner.setDepLocation(departLocation);
	}

	public String getDestLocation() {
		return destLocation;
	}

	public void setDestLocation(String destLocation) {
		this.destLocation = destLocation;
		tripPlanner.setDestLocation(destLocation);
	}

	public int getNumPeople() {
		return numPeople;
	}

	public void setNumPeople(int numPeople) {
		this.numPeople = numPeople;
		tripPlanner.setNumPeople(numPeople);
	}

	public int getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
		tripPlanner.setPriceHigher(maxPrice);
	}

	public boolean isFlightWifi() {
		return flightWifi;
	}

	public void setFlightWifi(boolean flightWifi) {
		this.flightWifi = flightWifi;
	}

	public String getFlightTimeFrom() {
		return flightTimeFrom;
	}

	public void setFlightTimeFrom(String flightTimeFrom) {
		this.flightTimeFrom = flightTimeFrom;
	}

	public String getFlightTimeTo() {
		return flightTimeTo;
	}

	public void setFlightTimeTo(String flightTimeTo) {
		this.flightTimeTo = flightTimeTo;
	}

	public boolean getFlightSagaSeats() {
		return flightSagaSeats;
	}

	public void setFlightSagaSeats(boolean flightSagaSeats) {
		this.flightSagaSeats = flightSagaSeats;
	}

	public boolean[] getSelectedHotelFacilities() {
		return selectedHotelFacilities;
	}

	public void setSelectedHotelFacilities(boolean[] selectedHotelFacilities) {
		this.selectedHotelFacilities = selectedHotelFacilities;
	}

	public void chooseFacility(int i) {
		selectedHotelFacilities[i] = !selectedHotelFacilities[i];
		System.out.println("Chose facility " + i);
	}

	public int getSelectedHotelSearchMethod() {
		return selectedHotelSearchMethod;
	}

	public void setSelectedHotelSearchMethod(int selectedHotelSearchMethod) {
		this.selectedHotelSearchMethod = selectedHotelSearchMethod;
	}

	public String getHotelSearchString() {
		return hotelSearchString;
	}

	public void setHotelSearchString(String hotelSearchString) {
		this.hotelSearchString = hotelSearchString;
	}

	public int getTourPriceLower() {
		return tourPriceLower;
	}

	public void setTourPriceLower(int tourPriceLower) {
		this.tourPriceLower = tourPriceLower;
	}

	public int getTourPriceHigher() {
		return tourPriceHigher;
	}

	public void setTourPriceHigher(int tourPriceHigher) {
		this.tourPriceHigher = tourPriceHigher;
	}

	public int getTourDurationLower() {
		return tourDurationLower;
	}

	public void setTourDurationLower(int tourDurationLower) {
		this.tourDurationLower = tourDurationLower;
	}

	public int getTourDurationHigher() {
		return tourDurationHigher;
	}

	public void setTourDurationHigher(int tourDurationHigher) {
		this.tourDurationHigher = tourDurationHigher;
	}

	public Date getTourDateLower() {
		return tourDateLower;
	}

	public void setTourDateLower(Date tourDateLower) {
		this.tourDateLower = tourDateLower;
	}

	public Date getTourDateHigher() {
		return tourDateHigher;
	}

	public void setTourDateHigher(Date tourDateHigher) {
		this.tourDateHigher = tourDateHigher;
	}

	public List<String> getTourTypes() {
		return tourTypes;
	}

	public void setTourTypes(List<String> tourTypes) {
		this.tourTypes = tourTypes;
		tripPlanner.setTourType(tourTypes);
	}

	public String getTourType() {
		return tourType;
	}

	public void setTourType(String tourType) {
		this.tourType = tourType;
	}

	public String getTourDestination() {
		return tourDestination;
	}

	public void setTourDestination(String tourDestination) {
		this.tourDestination = tourDestination;
	}

	public int getTourRating() {
		return tourRating;
	}

	public void setTourRating(int tourRating) {
		System.out.println(tourRating);
		this.tourRating = tourRating;
	}

	public boolean isTourHotelPickup() {
		return tourHotelPickup;
	}

	public void setTourHotelPickup(boolean tourHotelPickup) {
		this.tourHotelPickup = tourHotelPickup;
	}

	public String getTourName() {
		return tourName;
	}

	public void setTourName(String tourName) {
		this.tourName = tourName;
	}

	public int getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
		tripPlanner.setPriceLower(minPrice);
	}
}