package org.example.elevator.system;

import java.util.List;
import java.util.Optional;
import org.example.elevator.Direction;
import org.example.elevator.Elevator;
import org.example.floor.Floor;

public interface ElevatorAssignmentStrategy {
	Optional<Elevator> assignElevator(List<Elevator> elevators, Floor floor, Direction direction);
}
