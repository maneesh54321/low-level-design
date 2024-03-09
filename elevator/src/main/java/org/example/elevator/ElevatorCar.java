package org.example.elevator;

import java.util.ArrayList;
import java.util.List;
import org.example.floor.ElevatorStop;

public class ElevatorCar {

    private final Door door;

    private ElevatorStop lastStop;

    private final List<ElevatorStop> upcomingStops;

    public ElevatorCar(Door door) {
        this.door = door;
        this.upcomingStops = new ArrayList<>();
    }

    private void upcomingStop(ElevatorStop elevatorStop){
        assert elevatorStop != null;
        this.upcomingStops.add(elevatorStop);
    }

    public ElevatorStop getLastStop() {
        return lastStop;
    }

    private void setLastStop(ElevatorStop elevatorStop) {
        assert elevatorStop != null;
        this.lastStop = elevatorStop;
    }

    public void driveTo(ElevatorStop elevatorStop) {

    }
}
