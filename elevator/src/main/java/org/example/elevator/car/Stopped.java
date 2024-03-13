package org.example.elevator.car;

import org.example.elevator.Direction;
import org.example.elevator.request.Request;

class Stopped implements ElevatorCarState {

  private final ElevatorCar elevatorCar;

  private final Direction direction;

  public Stopped(ElevatorCar elevatorCar, Direction direction) {
    this.elevatorCar = elevatorCar;
    this.direction = direction;
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
    System.out.printf("[Elevator-%d] Opening door at stop %s...%n", elevatorCar.getId(), elevatorCar.getLastStop());
    elevatorCar.openDoor();
    if (elevatorCar.getRequestStore().isEmpty()) {
      elevatorCar.setState(new Idle(elevatorCar));
      return;
    }

    ElevatorCarState newState =
        switch (direction) {
          case Direction.UP -> {
            if (elevatorCar.getRequestStore().getAllRequests().stream()
                .noneMatch(
                    request -> request.floor().compareTo(elevatorCar.getLastStop().floor()) > 0)) {
              yield new Moving(elevatorCar, Direction.DOWN);
            } else {
              yield new Moving(elevatorCar, direction);
            }
          }
          case Direction.DOWN -> {
            if (elevatorCar.getRequestStore().getAllRequests().stream()
                .noneMatch(
                    request -> request.floor().compareTo(elevatorCar.getLastStop().floor()) < 0)) {
              yield new Moving(elevatorCar, Direction.UP);
            } else {
              yield new Moving(elevatorCar, direction);
            }
          }
        };

    elevatorCar.setState(newState);
    elevatorCar.move();
  }

  @Override
  public void addRequest(Request request) {
    elevatorCar.getRequestStore().addRequest(request);
  }
}
