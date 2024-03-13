package org.example.elevator.car;

import org.example.elevator.Door;
import org.example.elevator.request.Request;
import org.example.elevator.request.RequestStore;
import org.example.floor.ElevatorStop;
import org.example.floor.Floor;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class ElevatorCar {

    private final int id;

    private final Door door;

    private ElevatorStop lastStop;

    private final RequestStore requestStore;

    private final List<ElevatorStop> allStops;

    private ElevatorCarState state;

    public ElevatorCar(int id, Door door, List<ElevatorStop> allStops, ElevatorStop lastStop) {
        this.id = id;
        this.door = door;
        this.state = new Idle(this);
        this.requestStore = new RequestStore();
        this.allStops = allStops;
        this.lastStop = lastStop;
    }

    public int getId() {
        return id;
    }

    public void addRequest(Request request) {
        assert request != null;
        this.state.addRequest(request);
    }

    RequestStore getRequestStore() {
        return requestStore;
    }

    public ElevatorStop getLastStop() {
        return lastStop;
    }

    void setLastStop(ElevatorStop elevatorStop) {
        assert elevatorStop != null;
        this.lastStop = elevatorStop;
    }

    public List<ElevatorStop> getAllStops() {
        return allStops;
    }

    Door getDoor() {
        return door;
    }

    void setState(ElevatorCarState state) {
        this.state = state;
    }

    public void openDoor() {
        state.openDoor();
    }

    public void closeDoor() {
        state.closeDoor();
    }

    void move() {
        state.move();
    }
}
