package com.learning.parking;

import java.util.Map;
import java.util.Optional;

public interface ParkingLot {
	boolean hasParkingSpotAvailable(ParkingSpotType parkingSpotType);

	Optional<ParkingSpot> occupyAvailableParkingSpot(ParkingSpotType parkingSpotType);

	void vacateParkingSpot(int floorNo, int id);

	Map<ParkingSpotType, Long> getFreeParkingSpots();

	void addParkingFloor(ParkingFloor parkingFloor);
}
