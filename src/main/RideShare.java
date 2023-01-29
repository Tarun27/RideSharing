package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import model.Vehicle;
import repo.RideShareRepo;
import service.RideShareService;

public class RideShare {
	
	/**
	 * Design a ride-sharing application.
    The application allows users to share rides on a route.

    Users can either offer a shared ride (Driver) or consume a shared ride (Passenger).
    Users can search and select one from multiple available rides on a route with the same source and destination.

     Usecases:

    Application should allow to register users
        create_user(name:"John",gender:M,age:26)
    Users should be able to add vehicle (a user can have multiple vehicles)
        create_vehicle(name:"John", vehicle:"swift", regNo:"KA-09-32321")
    Users should be able offer rides
        offer_ride(name:"John",vehicle:"swift",seats_available:3,origin:bangalore,
         destination:mysore,start:Jan 25 2021 8AM, duration:3h)
    Users should be able to select rides based on origin,destination and selection preference.
        user can have preference like earliest ending ride or lowes duration ride
        select_ride(name:"smith",origin:bangalore,destination:mysore,seats_requierd:2,earliest_ending_ride)
    Users should be able to get all the rides offered and taken by the user.

Bonus: should be able fetch rides if direct rides are not available, but possible through multiple transit rides, should get multiple rides.
	 * 
	 */
	
	/*
	 * features implemented
	 * 1. register user
	 * 2. add vehicle
	 * 3. 
	 */
	

	public static void main() {
		
		RideShareService rideShareService = new RideShareService();
		RideShareRepo repo = new RideShareRepo();
		repo.users = new ArrayList();
		repo.vehicles = new ArrayList<Vehicle>();
		repo.bookings = new ArrayList();


		
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			
			System.out.println("\n---- Ride sharing Application ----\n");
			
			System.out.println("Write the option no: 1. register 2. existing user");
			
			String input = scan.nextLine();
			
			System.out.println("Enter username of the user separated by spaces");
			
			String userName = scan.nextLine();
			
			if(input =="1") {
							
				if(rideShareService.userExists(userName, repo)) {
					int attempts = 3;
					System.out.println("user with the user name + " + userName + "  already exists, please enter different username to register,"
							+ "you have "+ attempts + "  left");
					while(attempts>0) {
						attempts--;
						userName = scan.nextLine();
						if(rideShareService.userExists(userName, repo)) {
							System.out.println("user with the user name + " + userName + "  already exists, please enter different username to register,"
									+ "you have "+ attempts + "  left");
						}else {
							rideShareService.createUser(userName,repo);
							System.out.println("Successfully created user with username  "+ userName );
						}
					}
					
				}else {
					rideShareService.createUser(userName,repo);
					System.out.println("Successfully created user with username  "+ userName );
					System.out.println("would like to add a vehicle for sharing, write yes/no");
					String add = scan.nextLine();
	
					if(add!="yes" && add!="no") {
						System.out.println("Invalid input");
					}else{
						System.out.println("enter vehcile model, registration no, no of available seats separated by spaces");
						input = scan.nextLine();
						String inputs[] = input.split(" ");
						
						if(inputs.length!=3) {
							System.out.println("invalid input");
						}else {
							
							
							int seats =  Integer.parseInt(inputs[3]);
							if(StringUtils.isNumeric(input) && seats < 8) {
								rideShareService.addVehicle(userName,inputs,repo);
								System.out.println("successfully added vehicle");
							}else {
								System.out.println("invalid input for seats");
							}
							
						}
						
						
					}
					
				}
				
			}else if(input =="2"){
				
				if(!rideShareService.userExists(userName, repo)) {
					System.out.println("user with username "+ userName +"does not exist");
				}else {
					
					System.out.println("Write option no: 1. Add vehicle 2. ");
					
					System.out.println("Enter start city and end city separated by spaces");
					
					input = scan.nextLine();
					String[] cities= input.split(" ");
					
					if(cities.length!=2 || rideShareService.validateCities(cities,repo)) {
						System.out.println("invalid cities, please enter valid cities");
					}else {
						System.out.println("Write the option no: 1. book ride 2. share ride 3. available rides");

						input = scan.nextLine();
						
						switch(input) {
						
						case "1":
							if(rideShareService.bookRide(userName,cities,repo)) {
								System.out.println("successfully booked ride, waiting time is :" + (int)Math.random()*60 + "  mintues");
							}else {
								System.out.println("No ride is currently available between the cities");
							}
							break;
						case "2":
							System.out.println("write available no of seats");
							input = scan.nextLine();
							rideShareService.shareRide(userName,cities, input, repo);
							System.out.println("succesfully marked your vehicle for ride sharing between the cities");

							break;
						case "3":
							List<String> availablilty = rideShareService.checkAvailableRides(userName,cities,repo);
							break;
						
						}
						
						
					}
					
				}
				
				
			} else {
				System.out.println("Invalid Input, please write either 1 or 2");
			}
			
		}
		
	}
	
}
