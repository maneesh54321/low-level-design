package com.ms.show.booking;

import com.ms.cinemahall.Seat;
import java.util.List;

public record SeatBooking(String id, List<Seat> seats) {
}
