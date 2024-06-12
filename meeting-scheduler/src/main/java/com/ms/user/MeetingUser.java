package com.ms.user;

import com.ms.Calendar;
import com.ms.Interval;
import com.ms.meeting.MeetingScheduler;
import com.ms.notification.MeetingEventNotification;
import com.ms.user.notification.MeetingEventNotificationHandler;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MeetingUser extends User implements MeetingParticipant, MeetingOrganizer {

    private final Calendar calendar;

    private final MeetingScheduler scheduler;

    private MeetingEventNotificationHandler meetingEventNotificationHandler;

    public MeetingUser(String id, String name, Calendar calendar, MeetingScheduler scheduler) {
        super(id, name);
        this.calendar = calendar;
        this.scheduler = scheduler;
    }

    @Override
    public void createMeeting() {
        System.out.println("Your calendar: \n" + calendar);
        try(var sc = new Scanner(System.in)){
            Interval interval = chooseInterval(sc);
            List<MeetingParticipant> participants = new ArrayList<>();
            while (true) {
                System.out.println("Enter participant id to add participant or enter 0 to stop.");
                var participantId = sc.nextLine();
                if (participantId.equals("0")) break;
                var participant = scheduler.getUserRepository().getUserById(participantId);
                participant.ifPresent(participants::add);
            }
            boolean meetingScheduled;
            do {
                meetingScheduled = scheduler.scheduleMeeting(this, interval, participants);
                if (!meetingScheduled) {
                    System.out.println("Meeting room is unavailable, please chose another interval");
                    interval = chooseInterval(sc);
                }
            } while (!meetingScheduled);
        }
    }

    private Interval chooseInterval(Scanner sc) {
        System.out.println("Choose date and time:");
        System.out.println("Enter date in format dd/mm/yyy");
        var date = sc.nextLine();
        System.out.println("Enter time in format HH:mm");
        var time = sc.nextLine();
        LocalDateTime dateTime = LocalDateTime.parse(date + " " + time, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        System.out.println("Enter duration in minutes:");
        var duration = Integer.parseInt(sc.nextLine());
        return new Interval(dateTime, Duration.of(duration, ChronoUnit.MINUTES));
    }

    @Override
    public void cancelMeeting() {
        System.out.println("Your calendar: \n" + calendar);
        try(var sc = new Scanner(System.in)){
            System.out.println("Enter meeting id of meeting to cancel:");
            var id = Integer.valueOf(sc.nextLine());
            var meeting = calendar.getMeetingById(id);
            meeting.ifPresent(scheduler::cancelMeeting);
        }
    }

    @Override
    public void receive(MeetingEventNotification meetingEventNotification) {
        calendar.addPendingMeeting(meetingEventNotification);
    }

    @Override
    public void respond(MeetingEventNotification meetingEventNotification) {
        meetingEventNotificationHandler.handleNotification(meetingEventNotification, this);
    }

    @Override
    public String toString() {
        return "MeetingUser{} " + super.toString();
    }
}
