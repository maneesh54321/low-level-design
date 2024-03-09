package org.example.elevator;

import org.example.elevator.car.ElevatorCar;
import org.example.floor.ElevatorStop;
import org.example.floor.Floor;

import java.util.List;

public record Elevator(List<ElevatorStop> stops, ElevatorCar elevatorCar) {

    public void requestCar(Floor floor, Direction direction) {

    }
}
