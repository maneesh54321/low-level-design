package org.example;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.example.elevator.Direction;
import org.example.floor.Floor;
import org.example.loader.ElevatorSystemLoader;

public class Main {

	public static void main(String[] args)
			throws URISyntaxException, IOException, InterruptedException {
		var elevatorSystemLoader = new ElevatorSystemLoader();
		elevatorSystemLoader.load(Files.newBufferedReader(
				Path.of(ClassLoader.getSystemResource("elevator-system.txt").toURI())));
		elevatorSystemLoader.getElevatorSystem().callElevator(new Floor(2), Direction.UP);
		elevatorSystemLoader.getElevatorSystem().callElevator(new Floor(4), Direction.UP);
		elevatorSystemLoader.getElevatorSystem().callElevator(new Floor(8), Direction.DOWN);
		elevatorSystemLoader.getElevatorSystem().callElevator(new Floor(9), Direction.DOWN);
		Thread.sleep(15000);
		elevatorSystemLoader.getElevators().stream().skip(1).findFirst().ifPresent(elevator -> elevator.requestStop(new Floor(8)));
		elevatorSystemLoader.getElevators().stream().skip(1).findFirst().ifPresent(elevator -> elevator.requestStop(new Floor(3)));
		elevatorSystemLoader.getElevators().stream().skip(1).findFirst().ifPresent(elevator -> elevator.requestStop(new Floor(5)));
		elevatorSystemLoader.getElevators().stream().skip(1).findFirst().ifPresent(elevator -> elevator.requestStop(new Floor(1)));
		Thread.sleep(15000);
	}
}