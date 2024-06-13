package com.ms.room;

import com.ms.Interval;
import com.ms.meeting.Meeting;

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

    public void book(Meeting meeting) {
        bookings.add(new Booking(meeting));
    }

    public boolean isAvailable(Interval interval, int numOfParticipants) {
        if(numOfParticipants > maxParticipants) {
            return false;
        }
        boolean booked = bookings.stream()
                .anyMatch(booking -> booking.meeting().getInterval().overlaps(interval));
        return !booked;
    }

    @Override
    public String toString() {
        return "MeetingRoom{" +
                "floor=" + floor +
                ", roomNo='" + roomNo + '\'' +
                ", maxParticipants=" + maxParticipants +
                '}';
    }
}
