package org.example.elevator.car;

import org.example.floor.ElevatorStop;

class Stopped implements ElevatorCarState {

    private final ElevatorCar elevatorCar;

    public Stopped(ElevatorCar elevatorCar) {
        this.elevatorCar = elevatorCar;
    }

    @Override
    public void driveTo(ElevatorStop elevatorStop) {
        elevatorCar.addUpcomingStop(elevatorStop);
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
        System.out.println("Next stop is: " + elevatorStop);
        elevatorCar.setState(new Moving(elevatorCar));
    }
}
