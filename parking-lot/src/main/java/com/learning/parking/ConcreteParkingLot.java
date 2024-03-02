package com.learning.parking;

import com.learning.vehicle.Vehicle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public record ConcreteParkingLot(List<ParkingFloor> parkingFloors) implements ParkingLot {

  public boolean hasParkingSpotAvailable(Vehicle vehicle) {
    return parkingFloors.stream()
        .flatMap(parkingFloor -> parkingFloor.parkingSpots().stream())
        .anyMatch(parkingSpot -> !parkingSpot.isOccupied());
  }

  public Optional<ParkingSpot> occupyAvailableParkingSpot(Vehicle vehicle) {
    return parkingFloors.stream()
        .flatMap(parkingFloor -> parkingFloor.parkingSpots().stream())
        .filter(parkingSpot -> !parkingSpot.isOccupied())
        .findFirst()
        .map(
            ps -> {
              ps.occupy();
              return ps;
            });
  }

  public void vacateParkingSpot(int floorNo, int id) {
    parkingFloors.get(floorNo).parkingSpots().stream()
        .filter(ps -> ps.getId() == id)
        .findFirst()
        .ifPresent(ParkingSpot::vacate);
  }

  @Override
  public Map<String, Long> getFreeParkingSpots() {
    var result = new HashMap<String, Long>();
    parkingFloors.forEach(
        parkingFloor -> {
          var freeParkingSpots = parkingFloor.getFreeParkingSpots();
          freeParkingSpots.forEach(
              (key, value) -> {
                result.computeIfPresent(key, (k, v) -> v + value);
                result.putIfAbsent(key, value);
              });
        });
    return result;
  }

  @Override
  public void addParkingFloor(ParkingFloor parkingFloor) {
    assert parkingFloor != null;
    parkingFloors.add(parkingFloor);
  }
}
