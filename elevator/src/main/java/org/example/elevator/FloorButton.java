package org.example.elevator;

import org.example.button.Button;
import org.example.floor.Floor;

import java.util.stream.Stream;

public class FloorButton {
    private final Button button;
    private final Elevator elevator;
    private final Floor floor;

    public FloorButton(Button button, Elevator elevator, Floor floor) {
        this.button = button;
        this.elevator = elevator;
        this.floor = floor;
    }

    public void pressButton(){
        button.press(elevator.requestStop(floor));
    }
}
