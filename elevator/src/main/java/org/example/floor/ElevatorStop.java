package org.example.floor;

import java.util.Objects;

public final class ElevatorStop {
    private final Floor floor;
    private final AssignStatusDisplay assignStatusDisplay;
    private boolean isActive;

    public ElevatorStop(Floor floor, AssignStatusDisplay assignStatusDisplay, boolean isActive) {
        this.floor = floor;
        this.assignStatusDisplay = assignStatusDisplay;
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "ElevatorStop{" +
                "floor=" + floor +
                '}';
    }

    public Floor floor() {
        return floor;
    }

    public AssignStatusDisplay assignStatusDisplay() {
        return assignStatusDisplay;
    }

    public boolean isActive() {
        return isActive;
    }

	public void activate(){
		isActive = true;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ElevatorStop that = (ElevatorStop) o;
        return Objects.equals(floor, that.floor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(floor);
    }
}
