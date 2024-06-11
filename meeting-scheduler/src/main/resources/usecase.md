## UseCase: Schedule Meeting
### Actor: Organizer, Participant
### Steps:
1. Organizer chooses date, time & duration.
2. Organizer adds participants to meeting.
3. Organizer adds an agenda into the meeting.
4. Organizer creates meeting.
5. System filters all meeting rooms according to number of participants.
6. System checks availability of each meeting room based on any booking in the interval until
it finds one with no booking in the selected interval.
7. System finds an available meeting room and books it for the selected interval.
   1. System doesn't find any available meeting room in the given interval and asks Organizer
    to select a different interval.
8. System sends the meeting invite notification to each participant.

### Nouns
Organizer, Interval, Participants, Meeting, Agenda, Meeting Rooms, Number of participants, availability,
Booking, Meeting Invite Notification.

### Verbs
Chooses Interval, add participants, add an agenda, create meeting, filters all meeting rooms,
check availability, book meeting room, sends meeting invite notification

