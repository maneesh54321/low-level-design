package com.ms.room;

import com.ms.Interval;

import java.util.ArrayList;
import java.util.List;

public class MeetingRoom {

    private final int floor;
    private final String roomNo;

    private final int maxParticipants;

    private final List<Booking> bookings;

    public MeetingRoom(int floor, String roomNo, int maxParticipants) {
        this.floor = floor;
        this.roomNo = roomNo;
        this.maxParticipants = maxParticipants;
        this.bookings = new ArrayList<>();
    }

    public void book(Interval interval) {

    }

    public boolean isAvailable(Interval interval, int numOfParticipants){
        return false;
    }
}
