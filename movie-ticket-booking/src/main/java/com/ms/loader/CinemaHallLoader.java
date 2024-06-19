package com.ms.loader;

import com.ms.cinemahall.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class CinemaHallLoader {

	private final CinemaHallRepository cinemaHallRepository;

	private final AtomicInteger cinemaIdSequence = new AtomicInteger(0);

	public static final String TOKEN_SEPARATOR = "</N>";

	private CinemaHallLoader(CinemaHallRepository cinemaHallRepository) {
		this.cinemaHallRepository = cinemaHallRepository;
	}

	private static CinemaHallLoader cinemaHallLoader;

	public static CinemaHallLoader getInstance(CinemaHallRepository cinemaHallRepository) {
		if(cinemaHallLoader == null) {
			cinemaHallLoader = new CinemaHallLoader(cinemaHallRepository);
		}
		return cinemaHallLoader;
	}

	public void load(InputStream inputStream) {
		Scanner sc = new Scanner(inputStream);
		List<CinemaHall> cinemaHalls = new ArrayList<>();
		Optional<CinemaHall> cinemaHall;
		do {
			cinemaHall = readCinemaHall(sc);
            cinemaHall.ifPresent(cinemaHalls::add);
		} while (cinemaHall.isPresent());
		cinemaHallRepository.loadCinemaHalls(cinemaHalls);
	}

	private Optional<CinemaHall> readCinemaHall(Scanner sc) {
		if(!sc.hasNext()) {
			return Optional.empty();
		}
		var line = sc.nextLine();
		if(line == null) return Optional.empty();
		var cinemaName = line;
		line = sc.nextLine();
		var addressTokens = line.split(TOKEN_SEPARATOR);
		var addressLine1 = addressTokens[0];
		var addressLine2 = addressTokens[1];
		var landmark = addressTokens[2];
		var pinCode = addressTokens[3];
		var city = addressTokens[4];
		var state = addressTokens[5];
		var address = new Address(addressLine1, addressLine2, landmark, pinCode, city, state);

		var screens = new ArrayList<Screen>();

		line = sc.nextLine();
		while (line != null && !line.isBlank()){
			var screenTokens = line.split(TOKEN_SEPARATOR);
			var screenNo = screenTokens[1];
			line = sc.nextLine();
			var seatGroups = new ArrayList<SeatGroup>();
			int seatGroupId = 1;
			while (line != null && !line.isBlank() && !line.split(TOKEN_SEPARATOR)[0].equalsIgnoreCase("screen")){
				var seatTokens = line.split(TOKEN_SEPARATOR);
				var groupName = seatTokens[0];
				var seats = new ArrayList<Seat>();
				for (int i = 1; i < seatTokens.length; i++) {
					var noOfSeatsInRow = Integer.parseInt(seatTokens[i].split(" ")[1]);
					var rowChar = seatTokens[i].charAt(0);
					IntStream.range(1, noOfSeatsInRow + 1).forEach(j -> seats.add(new Seat(rowChar, j, "Normal")));
				}
				var seatGroup = new SeatGroup(seatGroupId++, groupName, seats);
				seatGroups.add(seatGroup);
				if(sc.hasNext()) {
					line = sc.nextLine();
				} else {
					line = null;
				}
			}
			screens.add(new Screen(screenNo, new SeatingLayout(seatGroups)));
		}


		return Optional.of(new CinemaHall(String.valueOf(cinemaIdSequence.incrementAndGet()), cinemaName, address, screens));
	}
}
