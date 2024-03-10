package org.example.elevator.car;

import org.example.floor.ElevatorStop;

class Moving implements ElevatorCarState {

    private final ElevatorCar elevatorCar;

    public Moving(ElevatorCar elevatorCar) {
        this.elevatorCar = elevatorCar;
    }

    @Override
    public void driveTo(ElevatorStop elevatorStop) {
        elevatorCar.addUpcomingStop(elevatorStop);
    }

    @Override
    public void openDoor() {
        System.out.println("Can't play with door while lift is moving!!!");
    }

    @Override
    public void closeDoor() {
        System.out.println("Can't play with door while lift is moving!!!");
    }

    @Override
    public void move(ElevatorStop elevatorStop) {
        try {
            System.out.printf("[ElevatorCar - %d]Moving to elevator stop: %s%n", elevatorCar.getId(), elevatorStop);
            Thread.sleep(3000);
            if(elevatorCar.getUpcomingStops().hasStop()){
                elevatorCar.setState(new Stopped(elevatorCar));
            } else {
                elevatorCar.setState(new Idle(elevatorCar));
            }
            System.out.printf("[ElevatorCar - %d]Reached at elevator stop: %s%n", elevatorCar.getId(), elevatorStop);
            elevatorCar.setLastStop(elevatorStop);

            // opens the door and block until it closes automatically.
            elevatorCar.openDoor();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
