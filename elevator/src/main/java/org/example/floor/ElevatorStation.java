package org.example.floor;

import org.example.elevator.Direction;
import org.example.elevator.ElevatorSystem;

import java.util.List;

public class ElevatorStation {

    private final Floor floor;

    private final ElevatorSystem elevatorSystem;

    private final List<ElevatorFloorPanel> floorPanels;

    public ElevatorStation(Floor floor, ElevatorSystem elevatorSystem, List<ElevatorFloorPanel> floorPanels) {
        this.floor = floor;
        this.elevatorSystem = elevatorSystem;
        this.floorPanels = floorPanels;
    }

    public void requestUp(){
        // Activate all up buttons
        floorPanels.forEach(ElevatorFloorPanel::pressUpButton);

        this.elevatorSystem.assignElevator(floor, Direction.UP);
    }

    public void requestDown(){
        // Activate all down buttons
        floorPanels.forEach(ElevatorFloorPanel::pressDownButton);

        this.elevatorSystem.assignElevator(floor, Direction.DOWN);
    }
}
