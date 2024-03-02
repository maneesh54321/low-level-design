package com.learning.parking;

public class ParkingSpot {
    private final int id;
    private boolean occupied;
    private final ParkingSpotType type;
    private final ParkingFloor parkingFloor;

    public ParkingSpot(int id, boolean occupied, ParkingSpotType type, ParkingFloor parkingFloor) {
        this.id = id;
        this.occupied = occupied;
        this.type = type;
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

    public ParkingSpotType getType() {
        return type;
    }

    public ParkingFloor getParkingFloor() {
        return parkingFloor;
    }

    public void vacate() { occupied = false; }

    @Override
    public String toString() {
        return "ParkingSpot{" +
                "id=" + id +
                ", occupied=" + occupied +
                ", type=" + type +
                ", parkingFloor=" + parkingFloor +
                '}';
    }
}
