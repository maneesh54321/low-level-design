package com.learning.parking;

import com.learning.vehicle.Vehicle;
import com.learning.vehicle.VehicleType;

public class CompactParkingSpot extends ParkingSpot {
    public CompactParkingSpot(int id, boolean occupied, ParkingFloor parkingFloor) {
        super(id, occupied, parkingFloor);
    }

    @Override
    public boolean support(Vehicle vehicle) {
        return vehicle.getType() == VehicleType.CAR
                || vehicle.getType() == VehicleType.MOTORCYCLE
                || vehicle.getType() == VehicleType.CYCLE;
    }

    @Override
    public String getType() {
        return "Compact";
    }

    @Override
    public String toString() {
        return String.format("CompactParkingSpot{%s}", super.toString()) ;
    }
}
