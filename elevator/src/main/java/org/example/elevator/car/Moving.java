package org.example.elevator.car;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.example.elevator.Direction;
import org.example.elevator.request.Request;
import org.example.elevator.request.RequestType;
import org.example.floor.ElevatorStop;
import org.example.utils.ThreadUtils;

class Moving implements ElevatorCarState {

  private final ElevatorCar elevatorCar;

  private final Direction direction;

  public Moving(ElevatorCar elevatorCar, Direction direction) {
    this.elevatorCar = elevatorCar;
    this.direction = direction;
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
  public void move() {
    List<ElevatorStop> stopList = getIntermediateStops();
    for (ElevatorStop nextStop : stopList) {
      System.out.printf("[Elevator-%d] Moving to %s%n", elevatorCar.getId(), nextStop);
      ThreadUtils.simulateTimeGap(2000);
      System.out.printf("[Elevator-%d] Reached %s%n", elevatorCar.getId(), nextStop);
      elevatorCar.setLastStop(nextStop);

      Predicate<Request> shouldStopAtStop =
          request ->
              (request.type() == RequestType.STOP && request.floor().equals(nextStop.floor()))
                  || (request.type() == RequestType.CALL
                      && request.floor().equals(nextStop.floor())
                      && request.direction() == direction);

      if (elevatorCar.getRequestStore().removeRequest(shouldStopAtStop)) {
        elevatorCar.setState(new Stopped(elevatorCar, direction));
        elevatorCar.move();
        break;
      }
    }
  }

  @Override
  public void addRequest(Request request) {
    elevatorCar.getRequestStore().addRequest(request);
  }

  private List<ElevatorStop> getIntermediateStops() {
    return switch (direction) {
      case Direction.UP -> {
        Optional<Request> topRequest =
            elevatorCar.getRequestStore().getAllRequests().stream()
                .filter(request -> request.floor().compareTo(elevatorCar.getLastStop().floor()) > 0)
                .max(Comparator.comparing(Request::floor));
        yield topRequest
            .map(
                request ->
                    elevatorCar.getAllStops().stream()
                        .dropWhile(stop -> stop.compareTo(elevatorCar.getLastStop()) <= 0)
                        .takeWhile(stop -> stop.floor().compareTo(request.floor()) <= 0)
                        .collect(Collectors.toList()))
            .orElse(Collections.emptyList());
      }
      case Direction.DOWN -> {
        Optional<Request> bottomRequest =
            elevatorCar.getRequestStore().getAllRequests().stream()
                .filter(
                    request -> request.floor().compareTo(elevatorCar.getLastStop().floor()) <= 0)
                .max(Comparator.comparing(Request::floor));
        yield bottomRequest
            .map(
                request ->
                    elevatorCar.getAllStops().stream()
                        .dropWhile(stop -> !stop.floor().equals(request.floor()))
                        .takeWhile(stop -> !stop.floor().equals(elevatorCar.getLastStop().floor()))
                        .sorted(Comparator.reverseOrder())
                        .collect(Collectors.toList()))
            .orElse(Collections.emptyList());
      }
    };
  }
}
