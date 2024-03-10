package org.example.elevator.system;

import java.util.List;
import java.util.Optional;
import org.example.elevator.Direction;
import org.example.elevator.Elevator;
import org.example.floor.Floor;

public class ElevatorSystem {

	private int totalFloors;

	private final List<Elevator> elevators;

	private ElevatorAssignmentStrategy elevatorAssignmentStrategy;

	public ElevatorSystem(int totalFloors, List<Elevator> elevators,
                          ElevatorAssignmentStrategy elevatorAssignmentStrategy) {
        this.totalFloors = totalFloors;
        this.elevators = elevators;
		this.elevatorAssignmentStrategy = elevatorAssignmentStrategy;
	}

	public void setElevatorAssignmentStrategy(
			ElevatorAssignmentStrategy elevatorAssignmentStrategy) {
		this.elevatorAssignmentStrategy = elevatorAssignmentStrategy;
	}

	private Optional<Elevator> assignElevator(Floor floor, Direction direction) {
		return elevatorAssignmentStrategy.assignElevator(elevators, floor, direction);
	}

	public void callElevator(Floor floor, Direction direction) {
		System.out.println("Calling an elevator!!!");
		assignElevator(floor, direction).ifPresentOrElse(elevator -> {
			elevator.requestCar(floor);
			elevator.stops().stream()
					.filter(stop -> stop.floor().equals(floor))
					.findFirst()
					.ifPresent(stop -> stop.assignStatusDisplay().indicateAssigned(direction));
		}, () -> System.out.println("No elevator can go to floor: " + floor));
	}
}
