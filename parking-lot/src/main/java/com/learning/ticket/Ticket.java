package com.learning.ticket;

import java.time.LocalDateTime;

public record Ticket(
        LocalDateTime issueTime,
        String vehicleRegistrationNo,
        int parkingFloorNo,
        int parkingSpotId,
        String parkingSpotType
) {
}
