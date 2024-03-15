package org.ms.elevator;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import org.ms.elevator.core.Direction;
import org.ms.elevator.core.Elevator;
import org.ms.elevator.core.Request;
import org.ms.elevator.core.RequestType;
import org.ms.elevator.dispatcher.BasicElevatorSelectionStrategy;
import org.ms.elevator.dispatcher.ElevatorDispatcher;
import org.ms.elevator.utils.ThreadUtils;

public class Main {

	public static void main(String[] args)
			throws URISyntaxException, IOException, InterruptedException {
		Elevator elevator1 = new Elevator(1, List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 1);
		Elevator elevator2 = new Elevator(2, List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 1);
		elevator1.start();
		elevator2.start();

		ElevatorDispatcher elevatorDispatcher = new ElevatorDispatcher(List.of(elevator1, elevator2), new BasicElevatorSelectionStrategy());
		elevatorDispatcher.requestElevator(new Request(5, Direction.UP, RequestType.CALL));
		elevatorDispatcher.requestElevator(new Request(3, Direction.UP, RequestType.CALL));
		elevatorDispatcher.requestElevator(new Request(6, Direction.UP, RequestType.CALL));
		ThreadUtils.simulateDelay(10000);
		elevatorDispatcher.requestElevator(new Request(4, Direction.UP, RequestType.CALL));
		elevatorDispatcher.requestElevator(new Request(1, Direction.UP, RequestType.CALL));
//		ThreadUtils.simulateDelay(30000);
//		elevator1.shutdown();
//		elevator2.shutdown();

//		var elevatorSystemLoader = new ElevatorSystemLoader();
//		elevatorSystemLoader.load(Files.newBufferedReader(
//				Path.of(ClassLoader.getSystemResource("elevator-system.txt").toURI())));
//		elevatorSystemLoader.getElevatorSystem().callElevator(new Floor(2), Direction.UP);
//		elevatorSystemLoader.getElevatorSystem().callElevator(new Floor(4), Direction.UP);
//		elevatorSystemLoader.getElevatorSystem().callElevator(new Floor(8), Direction.DOWN);
//		elevatorSystemLoader.getElevatorSystem().callElevator(new Floor(9), Direction.DOWN);
//		Thread.sleep(15000);
//		elevatorSystemLoader.getElevators().stream().skip(1).findFirst().ifPresent(elevator -> elevator.requestStop(new Floor(8)));
//		elevatorSystemLoader.getElevators().stream().skip(1).findFirst().ifPresent(elevator -> elevator.requestStop(new Floor(3)));
//		elevatorSystemLoader.getElevators().stream().skip(1).findFirst().ifPresent(elevator -> elevator.requestStop(new Floor(5)));
//		elevatorSystemLoader.getElevators().stream().skip(1).findFirst().ifPresent(elevator -> elevator.requestStop(new Floor(1)));
//		Thread.sleep(15000);
	}
}