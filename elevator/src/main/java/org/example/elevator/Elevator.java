package org.example.elevator;

import org.example.elevator.car.ElevatorCar;
import org.example.elevator.request.Request;
import org.example.elevator.request.RequestType;
import org.example.floor.ElevatorStop;
import org.example.floor.Floor;

import java.util.List;

public record Elevator(List<ElevatorStop> stops, ElevatorCar elevatorCar) {

  public void requestCar(Floor floor, Direction direction) {
    stops.stream()
        .filter(stop -> stop.floor().equals(floor))
        .findFirst()
        .ifPresent(stop -> elevatorCar.addRequest(new Request(floor, RequestType.CALL, direction)));
  }

  private boolean validateFloor(Floor floor) {
    return stops.stream()
        .filter(ElevatorStop::isActive)
        .anyMatch(stop -> stop.floor().equals(floor));
  }

  public boolean requestStop(Floor floor) {
    boolean validStop = validateFloor(floor);
    if (validStop) {
      stops.stream()
          .filter(stop -> stop.floor().equals(floor))
          .findFirst()
          .ifPresent(stop -> elevatorCar.addRequest(Request.createStopRequest(floor)));
    } else {
      System.out.println(floor + " is not active!!!");
    }
    return validStop;
  }
}
