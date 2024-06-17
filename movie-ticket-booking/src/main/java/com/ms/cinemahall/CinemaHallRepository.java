package com.ms.cinemahall;

import java.util.List;

public class CinemaHallRepository {

	private final List<CinemaHall> cinemaHalls;

	public CinemaHallRepository(List<CinemaHall> cinemaHalls) {
		this.cinemaHalls = cinemaHalls;
	}

	public void loadCinemaHalls(List<CinemaHall> cinemaHalls) {
		this.cinemaHalls.addAll(cinemaHalls);
	}

	public void addCinemaHall(CinemaHall cinemaHall){
		this.cinemaHalls.add(cinemaHall);
	}

	public void removeCinemaHall(CinemaHall cinemaHall){
		this.cinemaHalls.remove(cinemaHall);
	}

	@Override
	public String toString() {
		return "CinemaHallRepository{" +
				"cinemaHalls=" + cinemaHalls +
				'}';
	}
}
