package org.example.elevator;

import org.example.floor.ElevatorStop;
import org.example.floor.Floor;

import java.util.List;

public class Elevator {

    private final List<ElevatorStop> stops;

    private final ElevatorCar elevatorCar;

    public Elevator(List<ElevatorStop> stops, ElevatorCar elevatorCar) {
        this.stops = stops;
        this.elevatorCar = elevatorCar;
    }

    public List<ElevatorStop> getStops(){
        return this.stops;
    }

    public void requestCar(Floor floor, Direction direction) {

    }
}
