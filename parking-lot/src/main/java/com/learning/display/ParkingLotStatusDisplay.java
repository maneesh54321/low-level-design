package com.learning.display;

import com.learning.parking.ParkingLot;

public class ParkingLotStatusDisplay implements Display, Observer {

	private boolean parkingSpotsFree;

	private final Subject subject;

	public ParkingLotStatusDisplay(Subject subject) {
		this.subject = subject;
		this.subject.addObserver(this);
		update(subject);
	}

	@Override
	public void display() {
		if(parkingSpotsFree){
			System.out.println("Parking spots are free!!!");
		} else {
			System.out.println("Parking is full!!");
		}
	}

	@Override
	public void update(Subject subject) {
		if(subject instanceof ParkingLot parkingLot){
			parkingSpotsFree = !parkingLot.getFreeParkingSpots().isEmpty();
			display();
		}
	}
}
