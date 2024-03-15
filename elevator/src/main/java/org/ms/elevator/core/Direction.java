package org.ms.elevator.core;

public enum Direction {
	UP, DOWN, IDLE;

	public Direction oppositeDirection() {
		return switch (this) {
			case UP -> DOWN;
			case DOWN -> UP;
			case IDLE -> IDLE;
		};
	}
}
