package model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Booking {

	public String  origin;
	public String destination;
	public int  bookedSeatsNo;
	public List<User> passengers;
	public String driver;
	public LocalDateTime rideStart;
	public LocalDateTime rideEnd;
	
}
