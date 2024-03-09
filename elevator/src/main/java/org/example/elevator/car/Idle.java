package org.example.elevator.car;

import org.example.floor.ElevatorStop;

class Idle implements ElevatorCarState {

    private final ElevatorCar elevatorCar;

    public Idle(ElevatorCar elevatorCar) {
        this.elevatorCar = elevatorCar;
    }

    @Override
    public void driveTo(ElevatorStop elevatorStop) {
        elevatorCar.addUpcomingStop(elevatorStop);
        elevatorCar.move(elevatorCar.getUpcomingStops().getNextStop());
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
    public void move(ElevatorStop elevatorStop) {
        System.out.println("In idle state, starting elevator now, next stop is: " + elevatorStop);
        elevatorCar.setState(new Moving(elevatorCar));
        elevatorCar.launch(elevatorStop);
    }
}
