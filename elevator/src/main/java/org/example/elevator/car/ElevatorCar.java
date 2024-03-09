package org.example.elevator.car;

import org.example.elevator.Door;
import org.example.floor.ElevatorStop;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class ElevatorCar {

    private final int id;

    private final Door door;

    private ElevatorStop lastStop;

    private final UpcomingStops upcomingStops;

    private ElevatorCarState state;

    public ElevatorCar(int id, Door door) {
        this.id = id;
        this.door = door;
        this.upcomingStops = new UpcomingStops();
    }

    public int getId() {
        return id;
    }

    void addUpcomingStop(ElevatorStop elevatorStop) {
        assert elevatorStop != null;
        this.upcomingStops.addStop(elevatorStop);
    }

    UpcomingStops getUpcomingStops() {
        return upcomingStops;
    }

    public ElevatorStop getLastStop() {
        return lastStop;
    }

    void setLastStop(ElevatorStop elevatorStop) {
        assert elevatorStop != null;
        this.lastStop = elevatorStop;
    }

    Door getDoor() {
        return door;
    }

    void setState(ElevatorCarState state) {
        this.state = state;
    }

    void move(ElevatorStop elevatorStop) {
        state.move(elevatorStop);
    }

    void launch(ElevatorStop elevatorStop) {
        Thread thread = new Thread(() -> {
            while (upcomingStops.hasStop()) {
                this.move(upcomingStops.getNextStop());
            }
        });
        thread.setName("Elevator-" + this.id);
        thread.start();
    }

    public void driveTo(ElevatorStop elevatorStop) {
        System.out.println("Stop requested at: " + elevatorStop);
        state.driveTo(elevatorStop);
    }

    public void openDoor() {
        System.out.println("Opening elevator door!!!");
        state.openDoor();
    }

    public void closeDoor() {
        state.closeDoor();
    }

    static class UpcomingStops {
        List<ElevatorStop> stopList;

        private final ReentrantLock lock;

        public UpcomingStops() {
            this.stopList = new ArrayList<>();
            lock = new ReentrantLock();
        }

        public boolean hasStop() {
            boolean hasStop;
            lock.lock();
            hasStop = !stopList.isEmpty();
            lock.unlock();
            return hasStop;
        }

        public void addStop(ElevatorStop elevatorStop) {
            lock.lock();
            stopList.add(elevatorStop);
            lock.unlock();
        }

        public ElevatorStop getNextStop() {
            lock.lock();
            ElevatorStop result = stopList.removeFirst();
            lock.unlock();
            return result;
        }
    }
}
