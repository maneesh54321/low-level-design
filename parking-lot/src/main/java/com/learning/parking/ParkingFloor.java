package com.learning.parking;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public record ParkingFloor(int floorNumber, List<ParkingSpot> parkingSpots) {

  public void addParkingSpot(ParkingSpot parkingSpot) {
    if (parkingSpot != null) parkingSpots.add(parkingSpot);
  }

  public Map<ParkingSpotType, Long> getFreeParkingSpots() {
    Predicate<ParkingSpot> isOccupied = ParkingSpot::isOccupied;
    return parkingSpots.stream()
        .filter(isOccupied.negate())
        .collect(Collectors.groupingBy(ParkingSpot::getType, Collectors.counting()));
  }

  @Override
  public String toString() {
    return "ParkingFloor{" +
            "floorNumber=" + floorNumber +
            '}';
  }
}
