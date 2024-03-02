package com.learning.parking;

import com.learning.display.Observer;
import com.learning.display.Subject;
import com.learning.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ParkingLotSubject implements ParkingLot, Subject {

	private final ParkingLot parkingLot;

	private final List<Observer> observers;

	public ParkingLotSubject(ParkingLot parkingLot) {
		this.parkingLot = parkingLot;
		this.observers = new ArrayList<>();
	}

	@Override
	public void addObserver(Observer observer) {
		assert observer != null;
		observers.add(observer);
	}

	@Override
	public void deleteObserver(Observer observer) {
		assert observer != null;
		observers.remove(observer);
	}

	@Override
	public void notifyObservers() {
		observers.forEach(observer -> observer.update(this));
	}

	@Override
	public boolean hasParkingSpotAvailable(Vehicle vehicle) {
		return parkingLot.hasParkingSpotAvailable(vehicle);
	}

	@Override
	public Optional<ParkingSpot> occupyAvailableParkingSpot(Vehicle vehicle) {
		Optional<ParkingSpot> parkingSpot = parkingLot.occupyAvailableParkingSpot(vehicle);
		parkingSpot.ifPresent(ps -> notifyObservers());
		return parkingSpot;
	}

	@Override
	public void vacateParkingSpot(int floorNo, int id) {
		parkingLot.vacateParkingSpot(floorNo, id);
		notifyObservers();
	}

	@Override
	public Map<String, Long> getFreeParkingSpots() {
		return parkingLot.getFreeParkingSpots();
	}

	@Override
	public void addParkingFloor(ParkingFloor parkingFloor) {
		this.parkingLot.addParkingFloor(parkingFloor);
	}
}
