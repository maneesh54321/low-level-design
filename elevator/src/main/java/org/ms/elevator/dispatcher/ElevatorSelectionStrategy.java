package org.ms.elevator.dispatcher;

import java.util.List;
import org.ms.elevator.core.Elevator;
import org.ms.elevator.core.Request;

public interface ElevatorSelectionStrategy {

	Elevator selectElevator(Request request, List<Elevator> elevators);
}
