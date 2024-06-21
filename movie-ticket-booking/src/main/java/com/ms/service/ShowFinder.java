package com.ms.service;

import com.ms.cinemahall.CinemaHall;
import com.ms.cinemahall.CinemaHallRepository;
import com.ms.show.Show;
import com.ms.show.ShowSchedule;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class ShowFinder {

	public static final Predicate<String> stringFilterRequired = Predicate.not(
			filter -> filter == null || filter.isBlank() || filter.equalsIgnoreCase("all"));
	public static final Predicate<LocalDate> dateFilterRequired = Predicate.not(Objects::isNull);
	private final CinemaHallRepository cinemaHallRepository;

	public ShowFinder(CinemaHallRepository cinemaHallRepository) {
		this.cinemaHallRepository = cinemaHallRepository;
	}

	public List<Show> searchShow(ShowSearchCriteria searchCriteria, String city) {
		Stream<Show> showScheduleStream = cinemaHallRepository.getCinemaHallsByCity(city).stream()
				.flatMap(cinemaHall -> cinemaHall.screens().stream())
				.flatMap(screen -> screen.showSchedules().stream()).map(ShowSchedule::getShow);

		if (stringFilterRequired.test(searchCriteria.language())) {
			showScheduleStream = showScheduleStream.filter(
					show -> show.language().equalsIgnoreCase(searchCriteria.language()));
		}

		if (stringFilterRequired.test(searchCriteria.genre())) {
			showScheduleStream = showScheduleStream.filter(
					show -> show.genre().equalsIgnoreCase(searchCriteria.genre()));
		}

		if (dateFilterRequired.test(searchCriteria.releaseDate())) {
			showScheduleStream = showScheduleStream.filter(
					show -> show.releaseDate().equals(searchCriteria.releaseDate()));
		}

		return showScheduleStream.filter(show -> show.title().equalsIgnoreCase(searchCriteria.title()))
				.distinct().toList();

	}

	public List<CinemaHall> findCinemaHalls(Show show, String city) {
		return cinemaHallRepository.getCinemaHallsByCity(city).stream()
				.filter(cinemaHall -> cinemaHall.hasShow(show))
				.toList();
	}
}
