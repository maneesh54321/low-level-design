package org.ms.elevator.core.state;

import org.ms.elevator.core.Direction;

public interface ElevatorState {
	Direction getDirection();

	void move();

	void openDoor();

	void closeDoor();
}
