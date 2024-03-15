package org.ms.elevator.dispatcher;

import java.util.List;
import java.util.Optional;
import org.ms.elevator.core.Direction;
import org.ms.elevator.core.Elevator;
import org.ms.elevator.core.Request;

public class BasicElevatorSelectionStrategy implements ElevatorSelectionStrategy {

	@Override
	public Elevator selectElevator(Request request, List<Elevator> elevators) {
		// 1. All elevator which can go to requested floor
		final List<Elevator> elevatorList = elevators.stream()
				.filter(elevator -> elevator.goesToFloor(request.floor())).toList();

		Optional<Elevator> result = switch (request.direction()) {
			case UP -> {
				// elevators on same floor going in same direction
				yield elevatorList.stream()
						.filter(elevator -> elevator.getCurrentFloor() == request.floor())
						.filter(elevator -> elevator.getDirection() == request.direction()).findFirst().or(
								// 2. All idle elevators on same floor
								() -> elevatorList.stream()
										.filter(elevator -> elevator.getDirection() == Direction.IDLE
												&& elevator.getCurrentFloor() == request.floor()).findFirst()).or(
								// 3.1 In case UP is requested
								// 3.1.1 Below and going in same direction
								() -> elevatorList.stream()
										.filter(elevator -> elevator.getCurrentFloor() <= request.floor())
										.filter(elevator -> elevator.getDirection() == request.direction()).findFirst())
						.or(
								// 3.1.2 Below and Idle
								() -> elevatorList.stream()
										.filter(elevator -> elevator.getCurrentFloor() < request.floor())
										.filter(elevator -> elevator.getDirection() == Direction.IDLE).findFirst()).or(
								// Below and in Opposite Direction
								() -> elevatorList.stream()
										.filter(elevator -> elevator.getCurrentFloor() <= request.floor())
										.filter(
												elevator -> elevator.getDirection() == request.direction()
														.oppositeDirection())
										.findFirst()).or(
								// 3.1.3 Above & Idle
								() -> elevatorList.stream()
										.filter(elevator -> elevator.getCurrentFloor() > request.floor())
										.filter(elevator -> elevator.getDirection() == Direction.IDLE).findFirst()).or(
								// 3.1.4 Above & going in opposite direction
								() -> elevatorList.stream()
										.filter(elevator -> elevator.getCurrentFloor() >= request.floor())
										.filter(
												elevator -> elevator.getDirection() == request.direction()
														.oppositeDirection())
										.findFirst()).or(
								// 3.1.5 Above & going in same direction
								() -> elevatorList.stream()
										.filter(elevator -> elevator.getCurrentFloor() > request.floor())
										.filter(elevator -> elevator.getDirection() == request.direction())
										.findFirst());
			}
			// 3.2 In case DOWN is requested
			case DOWN -> {
				yield elevatorList.stream()
						.filter(elevator -> elevator.getCurrentFloor() == request.floor())
						.filter(elevator -> elevator.getDirection() == request.direction()).findFirst().or(
								// Above and going in same direction
								() -> elevatorList.stream()
										.filter(elevator -> elevator.getCurrentFloor() >= request.floor())
										.filter(elevator -> elevator.getDirection() == request.direction()).findFirst()
						).or(
								// 3.2.1 Above and Idle
								() -> elevatorList.stream()
										.filter(elevator -> elevator.getCurrentFloor() > request.floor())
										.filter(elevator -> elevator.getDirection() == Direction.IDLE).findFirst()
						).or(
								// Above and going in opposite direction
								() -> elevatorList.stream()
										.filter(elevator -> elevator.getCurrentFloor() >= request.floor())
										.filter(elevator -> elevator.getDirection() == request.direction()
												.oppositeDirection()).findFirst()
						).or(
								// 3.2.2 Below and Idle
								() -> elevatorList.stream()
										.filter(elevator -> elevator.getCurrentFloor() < request.floor())
										.filter(elevator -> elevator.getDirection() == Direction.IDLE).findFirst()
						).or(
								// 3.2.3 Below & going in opposite direction
								() -> elevatorList.stream()
										.filter(elevator -> elevator.getCurrentFloor() <= request.floor())
										.filter(elevator -> elevator.getDirection() == request.direction()
												.oppositeDirection()).findFirst()
						).or(
								// 3.2.5 Below & going in same direction
								() -> elevatorList.stream()
										.filter(elevator -> elevator.getCurrentFloor() < request.floor())
										.filter(elevator -> elevator.getDirection() == request.direction()).findFirst()
						);
			}
			case IDLE -> Optional.empty();
		};

		return result.orElseThrow();
	}
}
