package com.learning.parking;

import com.learning.vehicle.Vehicle;
import com.learning.vehicle.VehicleType;

public class LargeParkingSpot extends ParkingSpot{
    public LargeParkingSpot(int id, boolean occupied, ParkingFloor parkingFloor) {
        super(id, occupied, parkingFloor);
    }

    @Override
    public boolean support(Vehicle vehicle) {
        return vehicle.getType() == VehicleType.VAN
                || vehicle.getType() == VehicleType.TRUCK
                || vehicle.getType() == VehicleType.CAR
                || vehicle.getType() == VehicleType.MOTORCYCLE
                || vehicle.getType() == VehicleType.CYCLE;
    }

    @Override
    public String getType() {
        return "Large";
    }

    @Override
    public String toString() {
        return String.format("LargeParkingSpot{%s}", super.toString());
    }
}
