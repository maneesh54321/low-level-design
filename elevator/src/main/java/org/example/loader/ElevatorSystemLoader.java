package org.example.loader;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.example.elevator.Door;
import org.example.elevator.Elevator;
import org.example.elevator.ElevatorSystem;
import org.example.elevator.car.ElevatorCar;
import org.example.floor.ElevatorFloorPanel;
import org.example.floor.ElevatorStation;
import org.example.floor.ElevatorStop;
import org.example.floor.Floor;

public class ElevatorSystemLoader {

	private ElevatorSystem elevatorSystem;

	private List<ElevatorStation> elevatorStations;

	public void load(BufferedReader bufferedReader) throws IOException {
		List<Elevator> elevators = new ArrayList<>();
		elevatorSystem = new ElevatorSystem(elevators);
//		elevatorStations = new ArrayList<>();
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			ArrayList<ElevatorStop> stops = new ArrayList<>();
			var elevator = new Elevator(stops, new ElevatorCar(new Door()));
			elevators.add(elevator);
			Arrays.stream(line.split(" "))
					.map(num -> new Floor(Integer.parseInt(num)))
					.forEach(floor -> {
						var elevatorStop = new ElevatorStop(floor, null);
						stops.add(elevatorStop);

//						List<ElevatorFloorPanel> floorPanels = new ArrayList<>();
//						ElevatorStation elevatorStation = new ElevatorStation(floor, elevatorSystem,
//								floorPanels);
//						floorPanels.add(new ElevatorFloorPanel(elevatorStation));
//						elevatorStations.add(elevatorStation);
					});
		}
	}

	public ElevatorSystem getElevatorSystem() {
		return elevatorSystem;
	}

	public List<ElevatorStation> getElevatorStations() {
		return elevatorStations;
	}
}
