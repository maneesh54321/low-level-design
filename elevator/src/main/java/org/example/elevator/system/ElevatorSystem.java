package org.example.elevator.system;

import java.util.List;
import java.util.Optional;
import org.example.elevator.Direction;
import org.example.elevator.Elevator;
import org.example.floor.Floor;

public class ElevatorSystem {

	private final List<Elevator> elevators;

	private ElevatorAssignmentStrategy elevatorAssignmentStrategy;

	public ElevatorSystem(List<Elevator> elevators,
			ElevatorAssignmentStrategy elevatorAssignmentStrategy) {
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
		assignElevator(floor, direction).ifPresent(elevator -> {
			elevator.requestCar(floor);
			elevator.stops().stream()
					.filter(stop -> stop.floor().equals(floor))
					.findFirst()
					.ifPresent(stop -> stop.assignStatusDisplay().indicateAssigned(direction));
		});
	}
}
