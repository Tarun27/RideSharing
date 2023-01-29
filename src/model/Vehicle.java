package model;

import java.util.List;

public class Vehicle {
	
	public String username;
	
	public Vehicle(String username, String model, String registationNo, int totalSeats) {
		super();
		this.username = username;
		this.model = model;
		this.registationNo = registationNo;
		this.totalSeats=totalSeats;
	}
	
	public String model;
	public String registationNo;
	public int totalSeats;
	public int availableSeats;

}
