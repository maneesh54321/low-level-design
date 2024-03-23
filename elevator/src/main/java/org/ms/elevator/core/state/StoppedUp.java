package org.ms.elevator.core.state;

import org.ms.elevator.core.Direction;
import org.ms.elevator.core.Elevator;
import org.ms.elevator.core.RequestType;
import org.ms.elevator.utils.ThreadUtils;

public final class StoppedUp implements ElevatorState {

	private final Elevator elevator;

	private final Integer floor;

	public StoppedUp(Elevator elevator, Integer floor) {
		this.elevator = elevator;
		this.floor = floor;
	}

	@Override
	public Direction getDirection() {
		return Direction.UP;
	}

	@Override
	public void move() {
		boolean removed = elevator.getRequests().removeIf(
				request -> request.floor() == floor && (request.type() == RequestType.STOP || (
						request.type() == RequestType.CALL && request.direction() == getDirection())));
		if (removed) {
			openDoor();
			ThreadUtils.simulateDelay(5000);
			closeDoor();
			if (elevator.getRequests().isEmpty()) {
				System.out.println("No more requests left, going to idle state...");
				elevator.setState(new Idle(elevator));
			} else {
				boolean noFloorsLeftFurther = elevator.getRequests().stream()
						.noneMatch(request -> request.floor() > elevator.getCurrentFloor());
				if (noFloorsLeftFurther) {
					elevator.setState(new MovingDown(elevator));
				} else {
					elevator.setState(new MovingUp(elevator));
				}
			}
		}
	}

	@Override
	public void openDoor() {
		System.out.printf("[StoppedUp] Opening door @ floor = %d...\n", this.floor);
	}

	@Override
	public void closeDoor() {
		System.out.printf("[StoppedUp] Closing door @ floor = %d...\n", this.floor);
	}
}
