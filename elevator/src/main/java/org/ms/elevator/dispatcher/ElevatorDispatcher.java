package org.ms.elevator.dispatcher;

import java.util.List;
import org.ms.elevator.core.Elevator;
import org.ms.elevator.core.Request;

public class ElevatorDispatcher {

	private final List<Elevator> elevators;

	private final ElevatorSelectionStrategy elevatorSelectionStrategy;

	public ElevatorDispatcher(List<Elevator> elevators,
			ElevatorSelectionStrategy elevatorSelectionStrategy) {
		this.elevators = elevators;
		this.elevatorSelectionStrategy = elevatorSelectionStrategy;
	}

	public void requestElevator(Request request) {
		Elevator selectedElevator = elevatorSelectionStrategy.selectElevator(request, elevators);
		System.out.printf("Elevator %s selected for Request: %s \n", selectedElevator, request);
		selectedElevator.addStopRequest(request);
	}
}
