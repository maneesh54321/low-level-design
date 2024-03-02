package com.learning.parking;

import com.learning.vehicle.Vehicle;

public abstract class ParkingSpot {
    private final int id;
    private boolean occupied;
    private final ParkingFloor parkingFloor;

    public ParkingSpot(int id, boolean occupied, ParkingFloor parkingFloor) {
        this.id = id;
        this.occupied = occupied;
        this.parkingFloor = parkingFloor;
    }

    public void occupy(){
        this.occupied = true;
    }

    public int getId() {
        return id;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public ParkingFloor getParkingFloor() {
        return parkingFloor;
    }

    public void vacate() { occupied = false; }

    public abstract boolean support(Vehicle vehicle);

    public abstract String getType();

    @Override
    public String toString() {
        return "ParkingSpot{" +
                "id=" + id +
                ", occupied=" + occupied +
                ", parkingFloor=" + parkingFloor +
                '}';
    }
}
