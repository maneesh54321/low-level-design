package com.ms.user.notification;

import com.ms.Main;
import com.ms.notification.MeetingEventNotification;
import com.ms.user.MeetingUser;

public class MeetingScheduledEventNotificationHandler implements MeetingEventNotificationHandler {
    @Override
    public void handleNotification(MeetingEventNotification eventNotification, MeetingUser meetingUser) {
        System.out.println("User: " + meetingUser);
        System.out.println("Received notification: " + eventNotification);

        var sc = Main.SC;
        System.out.println("""
                How do you want to respond?
                1.Accept
                2.Cancel""");
        String response = sc.nextLine();
        switch (response) {
            case "1":
                meetingUser.getCalendar().addMeeting(eventNotification.getMeeting());
                meetingUser.getCalendar().getPendingInvites().removeIf(invite -> invite.getId().equals(eventNotification.getId()));
                System.out.println("Accepted");
                break;
            case "2":
                meetingUser.getCalendar().getPendingInvites().removeIf(invite -> invite.getId().equals(eventNotification.getId()));
                eventNotification.getMeeting().removeParticipant(meetingUser);
                System.out.println("Rejected");
                System.out.println("Updated meeting: \n" + eventNotification.getMeeting());
                break;
            default:
                System.out.println("Invalid input!!!");
        }

    }
}
