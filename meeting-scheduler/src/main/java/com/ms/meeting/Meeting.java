package com.ms.meeting;

import com.ms.Interval;
import com.ms.room.MeetingRoom;
import com.ms.user.MeetingOrganizer;
import com.ms.user.MeetingParticipant;

import java.util.List;

public class Meeting {

    private final Interval interval;

    private final String agenda;

    private final MeetingRoom meetingRoom;

    private final List<MeetingParticipant> meetingParticipants;

    private final MeetingOrganizer organizer;

    public Meeting(Interval interval, String agenda, MeetingRoom meetingRoom, List<MeetingParticipant> meetingParticipants, MeetingOrganizer organizer) {
        this.interval = interval;
        this.agenda = agenda;
        this.meetingRoom = meetingRoom;
        this.meetingParticipants = meetingParticipants;
        this.organizer = organizer;
    }
}
