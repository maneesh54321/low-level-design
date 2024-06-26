package com.ms.show.booking.printer;

import com.ms.cinemahall.CinemaHall;
import com.ms.cinemahall.Screen;
import com.ms.show.ShowSchedule;
import com.ms.show.booking.SeatBooking;
import com.ms.show.booking.user.User;

public record Ticket(ShowSchedule showSchedule, CinemaHall cinemaHall, Screen screen,
                     SeatBooking seatBooking, User user) {

}
