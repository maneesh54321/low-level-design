package com.learning.display;

import com.learning.parking.ParkingLot;
import com.learning.parking.ParkingSpotType;
import java.util.Map;

public class ParkingLotMetricsDisplay implements Display, Observer {

	private final Subject subject;

	private Map<ParkingSpotType, Long> freeParkingSpots;

	public ParkingLotMetricsDisplay(Subject subject) {
		this.subject = subject;
		this.subject.addObserver(this);
		update(subject);
	}

	@Override
	public void display() {
		assert freeParkingSpots != null;
		System.out.println(freeParkingSpots);
	}

	@Override
	public void update(Subject subject) {
		if (subject instanceof ParkingLot parkingLot) {
			freeParkingSpots = parkingLot.getFreeParkingSpots();
			display();
		}
	}
}
