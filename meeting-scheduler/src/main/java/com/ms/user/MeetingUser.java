package com.ms.user;

import com.ms.Calendar;
import com.ms.Interval;
import com.ms.Main;
import com.ms.meeting.MeetingScheduler;
import com.ms.notification.CancelledMeetingEventNotification;
import com.ms.notification.MeetingEventNotification;
import com.ms.notification.NewMeetingEventNotification;
import com.ms.user.notification.CancelledMeetingEventNotificationHandler;
import com.ms.user.notification.MeetingEventNotificationHandler;
import com.ms.user.notification.MeetingScheduledEventNotificationHandler;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class MeetingUser extends User implements MeetingParticipant, MeetingOrganizer {

    private final Calendar calendar;

    private final MeetingScheduler scheduler;

    private static final Map<Class<? extends MeetingEventNotification>, MeetingEventNotificationHandler> handlers = Map.of(
            NewMeetingEventNotification.class, new MeetingScheduledEventNotificationHandler(),
            CancelledMeetingEventNotification.class, new CancelledMeetingEventNotificationHandler()
    );

    public MeetingUser(String id, String name, Calendar calendar, MeetingScheduler scheduler) {
        super(id, name);
        this.calendar = calendar;
        this.scheduler = scheduler;
    }

    @Override
    public void createMeeting() {
        System.out.println("Your calendar: \n" + calendar);
        var sc = Main.SC;
        Interval interval = chooseInterval(sc);
        List<MeetingParticipant> participants = chooseParticipants(sc);
        System.out.println("Enter agenda of the meeting.");
        var agenda = sc.nextLine();
        if (!participants.isEmpty()) {
            boolean meetingScheduled;
            do {
                meetingScheduled = scheduler.scheduleMeeting(this, agenda, interval, participants);
                if (!meetingScheduled) {
                    System.out.println("Meeting room is unavailable, Enter 1 to continue, 0 to exit");
                    if (sc.nextLine().equals("0")) break;
                    interval = chooseInterval(sc);
                }
            } while (!meetingScheduled);
        }
    }

    private List<MeetingParticipant> chooseParticipants(Scanner sc) {
        List<MeetingParticipant> participants = new ArrayList<>();
        while (true) {
            System.out.println("Enter participant id to add participant or enter 0 to stop.");
            var participantId = sc.nextLine();
            if (participantId.isBlank() || participantId.equals("0")) break;
            var participant = scheduler.getUserRepository().getUserById(participantId);
            participant.ifPresent(participants::add);
        }
        return participants;
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
        var sc = Main.SC;
        System.out.println("Enter meeting id of meeting to cancel or 0 to exit:");
        String input = sc.nextLine();
        if(!input.equals("0")){
            var id = Integer.valueOf(input);
            var meeting = calendar.getMeetingById(id);
            meeting.ifPresent(scheduler::cancelMeeting);
        }
    }

    @Override
    public void receive(MeetingEventNotification meetingEventNotification) {
        System.out.println("Received notification: " + meetingEventNotification);
        calendar.addPendingMeeting(meetingEventNotification);
        respond();
    }

    @Override
    public void respond() {
        List<MeetingEventNotification> pendingInvites = calendar.getPendingInvites();
        System.out.println("Pending invites:\n" + pendingInvites);
        var sc = Main.SC;
        System.out.println("Enter 1 to respond to meeting invite.");
        if (sc.nextLine().trim().equals("1")) {
            System.out.println("Choose invite to respond(Enter id):");
            String inviteId = sc.nextLine();
            Optional<MeetingEventNotification> pendingNotification = calendar.getPendingInvites().stream()
                    .filter(invite -> invite.getId().equals(inviteId))
                    .findFirst();
            if(pendingNotification.isPresent()) {
                handlers.get(pendingNotification.get().getClass()).handleNotification(pendingNotification.get(), this);
                pendingInvites.removeIf(invite -> invite.getId().equals(pendingNotification.get().getId()));
            } else {
                System.out.println("No notification found with given id.");
            }
        } else {
            System.out.println("Will respond later!!");
        }
    }

    public Calendar getCalendar() {
        return calendar;
    }

    @Override
    public String toString() {
        return String.format("MeetingUser{%s}", super.toString());
    }
}
