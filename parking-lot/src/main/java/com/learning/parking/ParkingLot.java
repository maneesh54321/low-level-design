package com.learning.parking;

import com.learning.vehicle.Vehicle;

import java.util.Map;
import java.util.Optional;

public interface ParkingLot {
	boolean hasParkingSpotAvailable(Vehicle vehicle);

	Optional<ParkingSpot> occupyAvailableParkingSpot(Vehicle vehicle);

	void vacateParkingSpot(int floorNo, int id);

	Map<String, Long> getFreeParkingSpots();

	void addParkingFloor(ParkingFloor parkingFloor);
}
