package org.example.floor;

import org.example.elevator.Direction;

public class AssignStatusDisplay {

    public void indicateAssigned(Direction direction){
        System.out.printf("Elevator will stop here for going %s!!%n", direction);
    }
}
