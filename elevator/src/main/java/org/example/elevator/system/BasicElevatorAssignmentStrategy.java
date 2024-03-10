package org.example.elevator.system;

import java.util.List;
import java.util.Optional;
import org.example.elevator.Direction;
import org.example.elevator.Elevator;
import org.example.floor.Floor;

public class BasicElevatorAssignmentStrategy implements ElevatorAssignmentStrategy {

	@Override
	public Optional<Elevator> assignElevator(List<Elevator> elevators, Floor floor,
			Direction direction) {
		return elevators.stream()
				.filter(
						elevator -> elevator.stops().stream().anyMatch(stop -> stop.floor().equals(floor)))
				.findAny();
	}
}
