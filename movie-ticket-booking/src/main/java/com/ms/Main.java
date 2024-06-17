package com.ms;

import com.ms.cinemahall.CinemaHallRepository;
import com.ms.loader.CinemaHallLoader;
import com.ms.service.CinemaHallManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        var cinemaHallRepository = new CinemaHallRepository(new ArrayList<>());

		/*CinemaHall cinemaHall = new CinemaHall("S1234",
				"PVR Brookfield",
				new Address("Brookfield mall, 5th cross road", "Brookfield", "watch tower", "560045", "Bengaluru", "Karnataka"),
				List.of(
						new Screen("1", new SeatingConfiguration(List.of(new Seat('A', 1, "X", "normal")))),
						new Screen("2", new SeatingConfiguration(List.of(new Seat('A', 1, "Y", "normal"))))
				)
		);*/
		try(InputStream file = Main.class.getResourceAsStream("/data.txt")){
            CinemaHallLoader.getInstance(cinemaHallRepository).load(file);
            System.out.println(cinemaHallRepository);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        CinemaHallManager cinemaHallManager = new CinemaHallManager(cinemaHallRepository);
    }
}