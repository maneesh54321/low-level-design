package org.ms.elevator.core;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.ms.elevator.core.state.ElevatorState;
import org.ms.elevator.core.state.Idle;

public class Elevator {

	private final int id;

	private final Set<Integer> allowedFloors;

	private int currentFloor;

	private ElevatorState state;

	private final BlockingQueue<Request> requests;

	private final ScheduledExecutorService executorService;

	public Elevator(int id, List<Integer> allowedFloors, int currentFloor) {
		this.id = id;
		this.allowedFloors = new LinkedHashSet<>(allowedFloors);
		this.currentFloor = currentFloor;
		this.requests = new LinkedBlockingDeque<>();
		this.state = new Idle(this);
		executorService = Executors.newScheduledThreadPool(1);
	}

	public int getId() {
		return id;
	}

	public int getCurrentFloor() {
		return currentFloor;
	}

	public void setCurrentFloor(int currentFloor) {
		this.currentFloor = currentFloor;
	}

	public Direction getDirection() {
		return state.getDirection();
	}

	public Set<Integer> getAllowedFloors() {
		return allowedFloors;
	}

	public boolean goesToFloor(int floor) {
		return allowedFloors.contains(floor);
	}

	public void setState(ElevatorState state) {
		this.state = state;
	}

	public BlockingQueue<Request> getRequests() {
		return requests;
	}

	public void start(){
		executorService.scheduleAtFixedRate(() -> {
			if(!requests.isEmpty()){
				move();
			}
		}, 50, 50, TimeUnit.MILLISECONDS);
	}

	public void shutdown(){
		executorService.shutdown();
	}

	public void move(){
		state.move();
	}

	public void addStopRequest(Request request){
		requests.add(request);
	}

	@Override
	public String toString() {
		return "Elevator{" +
				"id=" + id +
				'}';
	}
}
