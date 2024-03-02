package com.learning.display;

import com.learning.parking.ParkingLot;

import java.util.Map;

public class ParkingLotMetricsDisplay implements Display, Observer {

	private final Subject subject;

	private Map<String, Long> freeParkingSpots;

	public ParkingLotMetricsDisplay(Subject subject) {
		this.subject = subject;
		this.subject.addObserver(this);
		update(subject);
	}

	@Override
	public void display() {
		assert freeParkingSpots != null;
		System.out.println("Displaying available parking spots: " + freeParkingSpots);
	}

	@Override
	public void update(Subject subject) {
		if (subject instanceof ParkingLot parkingLot) {
			freeParkingSpots = parkingLot.getFreeParkingSpots();
			display();
		}
	}
}
