package com.learning.parking;

import java.util.List;

public record ParkingFloor(int floorNumber, List<ParkingSpot> parkingSpots) {
}
