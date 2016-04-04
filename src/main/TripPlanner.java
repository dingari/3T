package main;

import main.mock.*;
import main.service.FlightSearchService;
import main.service.HotelFinderService;
import main.service.TourSearchService;

import java.util.*;

public class TripPlanner {

	private String depLocation;
	private String destLocation;
	private Date depTime;
	private Date returnTime;
	private boolean oneWay;
	private int numPeople;
	private int priceLower;
	private int priceHigher;

	private String[] excludedAirlines;
	private String[] excludedHotels;

	private int tourDuration;
	private List<String> tourType;

	private FlightSearchService flightSearch;
	private HotelFinderService hotelSearch;
	private TourSearchService tourSearch;

	public TripPlanner(String depLocation, String destLocation, Date depTime, Date returnTime, int numPeople,
					   int priceLower, int priceHigher, int tourDuration) {

		this.depLocation = depLocation;
		this.destLocation = destLocation;
		this.depTime = depTime;
		this.returnTime = returnTime;
		this.numPeople = numPeople;
		this.priceLower = priceLower;
		this.priceHigher = priceHigher;
		this.tourDuration = tourDuration;

		flightSearch = new FlightSearchMock();
		hotelSearch = new HotelFinderMock();
		tourSearch = new TourSearchMock();
	}

	public Map<String, Collection> search() {
		// TODO: implement this
		return new HashMap<String, Collection>();
	}

	public List<TripCombo> suggestCombos() {
		// TODO: implement this

		return new ArrayList<TripCombo>();
	}

	public TripCombo suggestBestCombo() {
		// TODO: implement this

		return new TripCombo(new FlightMock(), new HotelMock(), new TourMock());
	}



	// Getters and setters


	public String getDepLocation() {
		return depLocation;
	}

	public void setDepLocation(String depLocation) {
		this.depLocation = depLocation;
	}

	public String getDestLocation() {
		return destLocation;
	}

	public void setDestLocation(String destLocation) {
		this.destLocation = destLocation;
	}

	public Date getDepTime() {
		return depTime;
	}

	public void setDepTime(Date depTime) {
		this.depTime = depTime;
	}

	public Date getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}

	public boolean isOneWay() {
		return oneWay;
	}

	public void setOneWay(boolean oneWay) {
		this.oneWay = oneWay;
	}

	public int getNumPeople() {
		return numPeople;
	}

	public void setNumPeople(int numPeople) {
		this.numPeople = numPeople;
	}

	public int getPriceLower() {
		return priceLower;
	}

	public void setPriceLower(int priceLower) {
		this.priceLower = priceLower;
	}

	public int getPriceHigher() {
		return priceHigher;
	}

	public void setPriceHigher(int priceHigher) {
		this.priceHigher = priceHigher;
	}

	public String[] getExcludedAirlines() {
		return excludedAirlines;
	}

	public void setExcludedAirlines(String[] excludedAirlines) {
		this.excludedAirlines = excludedAirlines;
	}

	public String[] getExcludedHotels() {
		return excludedHotels;
	}

	public void setExcludedHotels(String[] excludedHotels) {
		this.excludedHotels = excludedHotels;
	}

	public int getTourDuration() {
		return tourDuration;
	}

	public void setTourDuration(int tourDuration) {
		this.tourDuration = tourDuration;
	}

	public List<String> getTourType() {
		return tourType;
	}

	public void setTourType(List<String> tourType) {
		this.tourType = tourType;
	}

	public FlightSearchService getFlightSearch() {
		return flightSearch;
	}

	public void setFlightSearch(FlightSearchService flightSearch) {
		this.flightSearch = flightSearch;
	}

	public HotelFinderService getHotelSearch() {
		return hotelSearch;
	}

	public void setHotelSearch(HotelFinderService hotelSearch) {
		this.hotelSearch = hotelSearch;
	}

	public TourSearchService getTourSearch() {
		return tourSearch;
	}

	public void setTourSearch(TourSearchService tourSearch) {
		this.tourSearch = tourSearch;
	}
}