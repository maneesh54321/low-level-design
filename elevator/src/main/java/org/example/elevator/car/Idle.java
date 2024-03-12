package org.example.elevator.car;

import org.example.elevator.Direction;
import org.example.elevator.request.Request;

public class Idle implements ElevatorCarState {

    private final ElevatorCar elevatorCar;

    public Idle(ElevatorCar elevatorCar) {
        this.elevatorCar = elevatorCar;
    }

    @Override
    public void openDoor() {
        elevatorCar.getDoor().open();
    }

    @Override
    public void closeDoor() {
        elevatorCar.getDoor().close();
    }

    @Override
    public void move() {
        int compare = elevatorCar.getLastStop().floor().compareTo(elevatorCar.getRequestStore().getFirst().floor());
        if(compare == 0) {
            return;
        }
        if(compare > 0) {
            elevatorCar.setState(new Moving(elevatorCar, Direction.DOWN));
        } else {
            elevatorCar.setState(new Moving(elevatorCar, Direction.UP));
        }
        new Thread(elevatorCar::move).start();
    }

    @Override
    public void addRequest(Request request) {
        elevatorCar.getRequestStore().addRequest(request);
        elevatorCar.move();
    }
}
