package com.ms.cinemahall;

import java.util.List;

public record SeatGroup(int id, String name, List<Seat> seats) {

    public boolean hasSeat(int seatNum) {
        return seats.stream().anyMatch(seat -> seat.seatNum() == seatNum);
    }
}
