package org.example.elevator.system;

import org.example.elevator.Direction;
import org.example.elevator.Elevator;
import org.example.floor.ElevatorStop;
import org.example.floor.Floor;

import java.util.List;
import java.util.Optional;

public class BasicElevatorAssignmentStrategy implements ElevatorAssignmentStrategy {

    @Override
    public Optional<Elevator> assignElevator(List<Elevator> elevators, Floor floor,
                                             Direction direction) {
        return elevators.stream()
                .filter(
                        elevator -> elevator.stops().stream()
                                .filter(ElevatorStop::isActive)
                                .anyMatch(stop -> stop.floor().equals(floor)))
                .findAny();
    }
}
