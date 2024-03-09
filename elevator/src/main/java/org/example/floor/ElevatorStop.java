package org.example.floor;

import org.example.elevator.Door;

public class ElevatorStop {
    private final Floor floor;

    private final AssignStatusDisplay assignStatusDisplay;

    public ElevatorStop(Floor floor, AssignStatusDisplay assignStatusDisplay) {
        this.floor = floor;
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
