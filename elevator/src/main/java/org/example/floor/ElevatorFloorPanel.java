package org.example.floor;

import org.example.button.Button;

public class ElevatorFloorPanel {

    private final Button upButton;

    private final Button downButton;

    private final ElevatorStation station;

    public ElevatorFloorPanel(ElevatorStation station) {
        this.upButton = new Button();
        this.downButton = new Button();
        this.station = station;
    }

    public void pressUpButton(){
        upButton.press(true);
        System.out.println("Up button pressed at station: " + station);
        this.station.requestUp();
    }

    public void activateUpButton(){
        upButton.activate();
    }

    public void pressDownButton(){
        downButton.press(true);
        this.station.requestDown();
    }

    public void activateDownButton(){
        downButton.activate();
    }
}
