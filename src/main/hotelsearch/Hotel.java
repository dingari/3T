package main.hotelsearch;

import java.util.ArrayList;


public class Hotel {
	private int id;
	private String name;
	private String hotelChain;
	private String city;
	private ArrayList<HotelRoom> hotelRooms;
	private ArrayList<Facility> hotelFacilities;
	private ArrayList<Review> hotelReviews;

	public Hotel(int id, String name, String city, String hotelChain, ArrayList<HotelRoom> hotelRooms, ArrayList<Facility> hotelFacilities, ArrayList<Review> hotelReviews){
		super();
		this.id = id;
		this.name = name;
		this.hotelChain = hotelChain;
		this.city = city;
		this.hotelRooms = hotelRooms;
		this.hotelFacilities = hotelFacilities;
		this.hotelReviews = hotelReviews;
	}
	public ArrayList<HotelRoom> getHotelRooms(){
		return hotelRooms;
	}
	
	public ArrayList<Facility> getFacilities(){
		return hotelFacilities;
	}
	public ArrayList<Review> getReviews(){
		return hotelReviews;
	}
	public int getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}

	public String getCity()
	{
		return city;
	}
	
	public String getChain()
	{
		return hotelChain;
	}

	@Override
	public String toString(){
		return "[ id : " + id + " ]"   + "\n" +  "[ name : " + name + " ]" + "\n" + "[ city : " + city + " ]" + "\n";
	}
}