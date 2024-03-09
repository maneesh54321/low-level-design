package org.example.floor;

import org.example.elevator.Door;

public class ElevatorStop {
    private final Floor floor;

    private final Door door;

    private final AssignStatusDisplay assignStatusDisplay;

    public ElevatorStop(Floor floor, Door door, AssignStatusDisplay assignStatusDisplay) {
        this.floor = floor;
        this.door = door;
        this.assignStatusDisplay = assignStatusDisplay;
    }

    public Floor getFloor() {
        return floor;
    }

    @Override
    public String toString() {
        return "ElevatorStop{" +
                "floor=" + floor +
                '}';
    }
}
