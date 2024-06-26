package com.ms.show.booking;

import com.ms.cinemahall.CinemaHall;
import com.ms.cinemahall.Screen;
import com.ms.show.ShowSchedule;

public record Reservation(CinemaHall cinemaHall, Screen screen,
                          ShowSchedule showSchedule, SeatReservation seatReservation) {
}
