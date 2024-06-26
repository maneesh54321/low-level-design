package com.ms.show.booking;

import com.ms.cinemahall.CinemaHall;
import com.ms.cinemahall.Screen;
import com.ms.show.ShowSchedule;
import com.ms.show.booking.user.User;

public record Booking(CinemaHall cinemaHall, Screen screen, ShowSchedule showSchedule, User user) {
}
