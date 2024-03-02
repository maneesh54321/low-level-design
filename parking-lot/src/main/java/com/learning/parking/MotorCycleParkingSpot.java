package com.learning.parking;

import com.learning.vehicle.Vehicle;
import com.learning.vehicle.VehicleType;

public class MotorCycleParkingSpot extends ParkingSpot {
    public MotorCycleParkingSpot(int id, boolean occupied, ParkingFloor parkingFloor) {
        super(id, occupied, parkingFloor);
    }

    @Override
    public boolean support(Vehicle vehicle) {
        return vehicle.getType() == VehicleType.MOTORCYCLE
                || vehicle.getType() == VehicleType.CYCLE;
    }

    @Override
    public String getType() {
        return "MotorCycle";
    }

    @Override
    public String toString() {
        return String.format("MotorCycleParkingSpot{%s}", super.toString());
    }
}
