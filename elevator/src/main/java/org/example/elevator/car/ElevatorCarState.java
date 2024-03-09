package org.example.elevator.car;

import org.example.floor.ElevatorStop;

interface ElevatorCarState {

    void driveTo(ElevatorStop elevatorStop);

    void openDoor();

    void closeDoor();

    void move(ElevatorStop elevatorStop);
}
