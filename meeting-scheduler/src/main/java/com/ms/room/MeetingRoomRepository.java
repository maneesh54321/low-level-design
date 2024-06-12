package com.ms.room;

import com.ms.Interval;

import java.util.List;
import java.util.Optional;

public class MeetingRoomRepository {
    private final List<MeetingRoom> meetingRooms;

    public MeetingRoomRepository(List<MeetingRoom> meetingRooms) {
        this.meetingRooms = meetingRooms;
    }

    public Optional<MeetingRoom> getAvailableMeetingRoom(Interval interval, int numOfParticipants) {
        return meetingRooms.stream().filter(meetingRoom -> meetingRoom.isAvailable(interval, numOfParticipants)).findFirst();
    }
}
