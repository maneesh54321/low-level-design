package org.example.elevator.car;

import org.example.elevator.request.Request;

interface ElevatorCarState {

    void openDoor();

    void closeDoor();

    void move();

    void addRequest(Request request);
}
