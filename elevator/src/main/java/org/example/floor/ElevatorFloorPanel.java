package org.example.floor;

import org.example.button.Button;

public class ElevatorFloorPanel {

    private final Button upButton;

    private final Button downButton;

    private final ElevatorStation station;

    public ElevatorFloorPanel(Button upButton, Button downButton, ElevatorStation station) {
        this.upButton = upButton;
        this.downButton = downButton;
        this.station = station;
    }

    public void pressUpButton(){
        upButton.press();
        this.station.requestUp();
    }

    public void pressDownButton(){
        downButton.press();
        this.station.requestDown();
    }
}
