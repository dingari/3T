package main;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

	public TripPlanner(String depLocation, String destLocation, Date depTime, Date returnTime, int numPeople,
					   int priceLower, int priceHigher, String[] excludedAirlines, String[] excludedHotels, boolean oneWay) {

		this.depLocation = depLocation;
		this.destLocation = destLocation;
		this.depTime = depTime;
		this.returnTime = returnTime;
		this.numPeople = numPeople;
		this.priceLower = priceLower;
		this.priceHigher = priceHigher;
		this.excludedAirlines = excludedAirlines;
		this.excludedHotels = excludedHotels;
		this.oneWay = oneWay;
	}

	public List<TripCombo> suggestCombos() {
		// TODO: implement this
		return new ArrayList<TripCombo>();
	}

	public TripCombo suggestBestCombo() {
		// TODO: implement this

		return new TripCombo();
	}

}