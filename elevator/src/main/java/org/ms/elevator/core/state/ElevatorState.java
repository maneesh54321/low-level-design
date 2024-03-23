package org.ms.elevator.core.state;

import org.ms.elevator.core.Direction;

public sealed interface ElevatorState permits Idle, MovingDown, MovingUp, StoppedDown, StoppedUp {
	Direction getDirection();

	void move();

	void openDoor();

	void closeDoor();
}
