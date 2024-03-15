package org.ms.elevator.core.state;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.ms.elevator.core.Direction;
import org.ms.elevator.core.Elevator;
import org.ms.elevator.core.Request;
import org.ms.elevator.utils.ThreadUtils;

public class MovingDown implements ElevatorState {

	private Elevator elevator;

	public MovingDown(Elevator elevator) {
		this.elevator = elevator;
	}

	@Override
	public Direction getDirection() {
		return Direction.DOWN;
	}

	@Override
	public void move() {
		List<Integer> floors = elevator.getRequests().stream()
				.filter(request -> request.floor() < elevator.getCurrentFloor())
				.min(Comparator.comparingInt(Request::floor)).map(Request::floor).map(
						minFloor -> elevator.getAllowedFloors().stream().dropWhile(floor -> floor < minFloor)
								.takeWhile(floor -> floor < elevator.getCurrentFloor())
								.sorted(Comparator.reverseOrder()).collect(Collectors.toList()))
				.orElse(Collections.emptyList());
		for (Integer floor : floors) {
			System.out.printf("[Elevator - %d] Moving to floor: %s\n", elevator.getId(), floor);
			ThreadUtils.simulateDelay(3000);
			System.out.printf("[Elevator - %d] Reached floor: %s\n", elevator.getId(), floor);
			elevator.setCurrentFloor(floor);
			boolean stopRequestedOnFloor = elevator.getRequests().stream().map(Request::floor)
					.anyMatch(reqFloor -> reqFloor.equals(floor));
			if (stopRequestedOnFloor) {
				elevator.setState(new StoppedDown(elevator, floor));
				break;
			}
		}
	}

	@Override
	public void openDoor() {
		System.out.println("Can't play with door while elevator is moving...");
	}

	@Override
	public void closeDoor() {
		System.out.println("Can't play with door while elevator is moving...");
	}
}
