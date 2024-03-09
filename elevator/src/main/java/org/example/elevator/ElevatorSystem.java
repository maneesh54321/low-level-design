package org.example.elevator;

import java.util.List;
import org.example.floor.Floor;

public class ElevatorSystem {

	private final List<Elevator> elevators;

	public ElevatorSystem(List<Elevator> elevators) {
		this.elevators = elevators;
	}

	public void assignElevator(Floor floor, Direction direction) {
		System.out.println("Calling an elevator!!!");
		elevators.stream()
				.filter(
						elevator -> elevator.stops().stream().anyMatch(stop -> stop.getFloor().equals(floor)))
        .findAny().ifPresent(elevator -> elevator.requestCar(floor));
	}
}
