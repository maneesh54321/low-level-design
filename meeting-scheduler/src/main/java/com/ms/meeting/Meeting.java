package com.ms.meeting;

import com.ms.Interval;
import com.ms.room.MeetingRoom;
import com.ms.user.MeetingOrganizer;
import com.ms.user.MeetingParticipant;

import java.util.List;

public class Meeting {

    private final int id;

    private final Interval interval;

    private final String agenda;

    private final MeetingRoom meetingRoom;

    private final List<MeetingParticipant> meetingParticipants;

    private final MeetingOrganizer organizer;

    public Meeting(int id, Interval interval, String agenda, MeetingRoom meetingRoom, List<MeetingParticipant> meetingParticipants, MeetingOrganizer organizer) {
        this.id = id;
        this.interval = interval;
        this.agenda = agenda;
        this.meetingRoom = meetingRoom;
        this.meetingParticipants = meetingParticipants;
        this.organizer = organizer;
    }

    public int getId() {
        return id;
    }

    public Interval getInterval() {
        return interval;
    }

    public List<MeetingParticipant> getMeetingParticipants() {
        return meetingParticipants;
    }

    public void removeParticipant(MeetingParticipant participant) {
        meetingParticipants.removeIf(meetingParticipant -> meetingParticipant.getId().equals(participant.getId()));
    }

    public MeetingOrganizer getOrganizer() {
        return organizer;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "id=" + id +
                ", interval=" + interval +
                ", agenda='" + agenda + '\'' +
                ", meetingRoom=" + meetingRoom +
                ", meetingParticipants=" + meetingParticipants +
                ", organizer=" + organizer +
                '}';
    }
}
