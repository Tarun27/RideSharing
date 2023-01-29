package service;

import java.util.ArrayList;
import java.util.List;

import model.Booking;
import model.User;
import model.Vehicle;
import repo.RideShareRepo;

public class RideShareService {

	public boolean userExists(String userName, RideShareRepo repo) {

		if(repo.users.stream().anyMatch(usr->usr.username.equals(userName))) return true;
		
		return false;
	}

	public void createUser(String userName, RideShareRepo repo) {

		User user = new User(userName);
		repo.users.add(user);	
	}

	public boolean validateCities(String[] cities, RideShareRepo repo) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean bookRide(String userName, String[] cities, RideShareRepo repo) {
		// TODO Auto-generated method stub
		return false;
	}

	public void shareRide(String userName, String[] cities, String seats, RideShareRepo repo) {
		int seat = Integer.parseInt(seats);
		Vehicle vehicle= repo.vehicles.stream().filter(v->v.username.equals(userName)).findFirst().get();
		Booking booking = new Booking();
		
		booking.origin=cities[0];
		booking.destination=cities[1];
		booking.driver=userName;
		booking.bookedSeatsNo=0;
		
		int availSeats = vehicle.availableSeats;
		
		if(availSeats > seat) {
			repo.bookings.add(booking);

		}else {
			System.out.println("seats not available");
		}
		
		
	}

	public List<String> checkAvailableRides(String userName, String[] cities, RideShareRepo repo) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addVehicle(String userName, String[] inputs, RideShareRepo repo) {
		// TODO Auto-generated method stub
		
	}
	

}
