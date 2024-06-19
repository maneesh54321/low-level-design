package com.ms;

import com.ms.cinemahall.CinemaHallRepository;
import com.ms.cinemahall.Screen;
import com.ms.loader.CinemaHallLoader;
import com.ms.service.CinemaHallManager;
import com.ms.service.ShowScheduler;
import com.ms.show.Interval;
import com.ms.show.Show;
import com.ms.show.ShowType;
import com.ms.show.price.SeatPriceCatalog;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        var cinemaHallRepository = new CinemaHallRepository(new ArrayList<>());
        try (InputStream file = Main.class.getResourceAsStream("/data.txt")) {
            CinemaHallLoader.getInstance(cinemaHallRepository).load(file);
            System.out.println(cinemaHallRepository);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        CinemaHallManager cinemaHallManager = new CinemaHallManager(cinemaHallRepository);

        System.out.println("""
                #####################################
                #####################################
                #####################################""");
        ShowScheduler showScheduler = new ShowScheduler();
        Screen screen = cinemaHallManager.getAllCinemaHalls("Bengaluru").getFirst().screens().getFirst();
        SeatPriceCatalog seatPriceCatalog = new SeatPriceCatalog(screen.seatingLayout());
        int basePrice = 240;
        screen.seatingLayout().seatGroups()
                .forEach(seatGroup ->
                        seatPriceCatalog.setSeatGroupPrice(seatGroup.id(), basePrice + (seatGroup.id() - 1) * basePrice));
        showScheduler.scheduleShow(
                new Show("Mr & Mrs Mahi", "Romance", "Hindi", LocalDate.of(2024, 5, 24), ShowType.MOVIE),
                screen,
                Interval.of(LocalDateTime.of(2024, 6, 21, 10, 0), Duration.ofMinutes(150)),
                seatPriceCatalog
        );
    }
}