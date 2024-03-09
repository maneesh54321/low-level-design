package org.example;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.example.loader.ElevatorSystemLoader;

public class Main {

	public static void main(String[] args)
			throws URISyntaxException, IOException, InterruptedException {
		var elevatorSystemLoader = new ElevatorSystemLoader();
		elevatorSystemLoader.load(Files.newBufferedReader(
				Path.of(ClassLoader.getSystemResource("elevator-system.txt").toURI())));
		var elevatorStations = elevatorSystemLoader.getElevatorStations();
		elevatorStations.get(4).getFloorPanels().getFirst().pressUpButton();
		Thread.sleep(6000);
	}
}