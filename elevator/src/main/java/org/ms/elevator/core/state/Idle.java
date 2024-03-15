package org.ms.elevator.core.state;

import java.util.Optional;
import org.ms.elevator.core.Direction;
import org.ms.elevator.core.Elevator;
import org.ms.elevator.utils.ThreadUtils;

public class Idle implements ElevatorState {

	private final Elevator elevator;

	public Idle(Elevator elevator) {
		this.elevator = elevator;
	}

	@Override
	public Direction getDirection() {
		return Direction.IDLE;
	}

	@Override
	public void move() {
		Optional.ofNullable(elevator.getRequests().peek()).ifPresent(request -> {
			if (elevator.getCurrentFloor() < request.floor()) {
				elevator.setState(new MovingUp(elevator));
			} else if (elevator.getCurrentFloor() > request.floor()) {
				elevator.setState(new MovingDown(elevator));
			} else {
				openDoor();
				elevator.getRequests().removeIf(req -> req.floor() == elevator.getCurrentFloor());
				ThreadUtils.simulateDelay(3000);
				closeDoor();
			}
		});
	}

	@Override
	public void openDoor() {
		System.out.printf("[Elevator - %d] Opening door @ floor = %d\n", elevator.getId(), elevator.getCurrentFloor());
	}

	@Override
	public void closeDoor() {
		System.out.printf("[Elevator - %d] Closing door @ floor = %d\n", elevator.getId(), elevator.getCurrentFloor());
	}
}
