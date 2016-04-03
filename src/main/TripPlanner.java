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

}