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
//		var elevatorStations = elevatorSystemLoader.getElevatorStations();
		elevatorSystemLoader.getElevatorSystem().callElevator(new Floor(2), Direction.UP);
		elevatorSystemLoader.getElevatorSystem().callElevator(new Floor(4), Direction.UP);
		elevatorSystemLoader.getElevatorSystem().callElevator(new Floor(8), Direction.DOWN);
		Thread.sleep(15000);
	}
}