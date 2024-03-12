package org.example.loader;

import org.example.elevator.Door;
import org.example.elevator.Elevator;
import org.example.elevator.car.ElevatorCar;
import org.example.elevator.system.BasicElevatorAssignmentStrategy;
import org.example.elevator.system.ElevatorSystem;
import org.example.floor.AssignStatusDisplay;
import org.example.floor.ElevatorStation;
import org.example.floor.ElevatorStop;
import org.example.floor.Floor;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class ElevatorSystemLoader {

  private ElevatorSystem elevatorSystem;

  private List<ElevatorStation> elevatorStations;

  private List<Elevator> elevators;

  public void load(BufferedReader bufferedReader) throws IOException {
    elevators = new ArrayList<>();
    int totalFloors = Integer.parseInt(bufferedReader.readLine());
    elevatorSystem = new ElevatorSystem(10, elevators, new BasicElevatorAssignmentStrategy());
    String line;
    int elevatorNum = 1;
    while ((line = bufferedReader.readLine()) != null) {
      var stops = new ArrayList<ElevatorStop>();
      IntStream.range(0, totalFloors + 1)
          .forEach(
              floorNumber ->
                  stops.add(
                      new ElevatorStop(new Floor(floorNumber), new AssignStatusDisplay(), false)));
      Arrays.stream(line.split(" "))
          .map(num -> new Floor(Integer.parseInt(num)))
          .forEach(
              floor -> {
                stops.stream()
                    .filter(stop -> stop.floor().equals(floor))
                    .findFirst()
                    .ifPresent(ElevatorStop::activate);
              });
      var elevator =
          new Elevator(stops, new ElevatorCar(elevatorNum, new Door(), stops, stops.getFirst()));

      elevators.add(elevator);
      elevatorNum++;
    }
  }

  public ElevatorSystem getElevatorSystem() {
    return elevatorSystem;
  }

  public List<Elevator> getElevators() {
    return this.elevators;
  }

  public List<ElevatorStation> getElevatorStations() {
    return elevatorStations;
  }
}
