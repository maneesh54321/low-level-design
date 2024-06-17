package com.ms.service;

import com.ms.cinemahall.CinemaHall;
import com.ms.cinemahall.CinemaHallRepository;

public class CinemaHallManager {

	private final CinemaHallRepository cinemaHallRepository;

	public CinemaHallManager(CinemaHallRepository cinemaHallRepository) {
		this.cinemaHallRepository = cinemaHallRepository;
	}

	public void addCinemaHall(CinemaHall cinemaHall){
		this.cinemaHallRepository.addCinemaHall(cinemaHall);
	}

	public void removeCinemaHall(CinemaHall cinemaHall){
		this.cinemaHallRepository.removeCinemaHall(cinemaHall);
	}
}
