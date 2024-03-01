package com.learning.parking;

import java.util.List;
import java.util.Optional;

public record ParkingLot(List<ParkingFloor> parkingFloors) {

    public boolean hasParkingSpotAvailable(ParkingSpotType parkingSpotType) {
        return parkingFloors.stream()
                .flatMap(parkingFloor -> parkingFloor.parkingSpots().stream())
                .anyMatch(parkingSpot -> !parkingSpot.isOccupied());
    }

    public Optional<ParkingSpot> occupyAvailableParkingSpot(ParkingSpotType parkingSpotType) {
        return parkingFloors.stream()
                .flatMap(parkingFloor -> parkingFloor.parkingSpots().stream())
                .filter(parkingSpot -> !parkingSpot.isOccupied()).findFirst();
    }

    public void vacateParkingSpot(int floorNo, int id){
        parkingFloors.get(floorNo).parkingSpots().stream()
                .filter(ps -> ps.getId() == id).findFirst().ifPresent(ParkingSpot::vacate);
    }
}
