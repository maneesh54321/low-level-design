package com.ms.service;

import com.ms.cinemahall.CinemaHallRepository;
import com.ms.show.ShowSchedule;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class ShowFinder {

    private CinemaHallRepository cinemaHallRepository;

    Predicate<String> stringFilterRequired = Predicate.not(filter -> filter == null || filter.isBlank() || filter.equalsIgnoreCase("all"));
    Predicate<LocalDate> dateFilterRequired = Predicate.not(Objects::isNull);

    public List<ShowSchedule> searchShow(String title, String language, String genre, LocalDate releaseDate, String city) {
        Stream<ShowSchedule> showScheduleStream = cinemaHallRepository.getCinemaHallsByCity(city).stream()
                .flatMap(cinemaHall -> cinemaHall.screens().stream())
                .flatMap(screen -> screen.showSchedules().stream());

        if(stringFilterRequired.test(language)) {
            showScheduleStream = showScheduleStream.filter(showSchedule -> showSchedule.getShow().language().equalsIgnoreCase(language));
        }

        if(stringFilterRequired.test(genre)) {
            showScheduleStream = showScheduleStream.filter(showSchedule -> showSchedule.getShow().genre().equalsIgnoreCase(genre));
        }

        if(dateFilterRequired.test(releaseDate)) {
            showScheduleStream = showScheduleStream.filter(showSchedule -> showSchedule.getShow().releaseDate().equals(releaseDate));
        }

        return showScheduleStream.filter(showSchedule -> showSchedule.getShow().title().equalsIgnoreCase(title)).toList();

    }
}
