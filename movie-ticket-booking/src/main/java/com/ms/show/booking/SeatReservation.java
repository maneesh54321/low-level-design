package com.ms.show.booking;

import com.ms.cinemahall.Seat;
import java.util.List;

public record SeatReservation(String id, List<Seat> seats) {
}
