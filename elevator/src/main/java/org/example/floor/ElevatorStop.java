package org.example.floor;

public record ElevatorStop(Floor floor, AssignStatusDisplay assignStatusDisplay) {
	@Override
	public String toString() {
		return "ElevatorStop{" +
				"floor=" + floor +
				'}';
	}
}
