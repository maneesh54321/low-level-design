package com.learning.parking;

import com.learning.vehicle.Vehicle;
import com.learning.vehicle.VehicleType;

public class HandicapParkingSpot extends ParkingSpot {
    public HandicapParkingSpot(int id, boolean occupied, ParkingFloor parkingFloor) {
        super(id, occupied, parkingFloor);
    }

    @Override
    public boolean support(Vehicle vehicle) {
        return vehicle.getType() == VehicleType.CAR;
    }

    @Override
    public String getType() {
        return "Handicap";
    }

    @Override
    public String toString() {
        return String.format("HandicapParkingSpot{%s}", super.toString());
    }
}
