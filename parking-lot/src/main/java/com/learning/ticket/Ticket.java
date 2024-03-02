package com.learning.ticket;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record Ticket(
        LocalDateTime issueTime,
        String vehicleRegistrationNo,
        int parkingFloorNo,
        int parkingSpotId,
        String parkingSpotType
) {

    public static final DateTimeFormatter ISSUE_TIME_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;
}
