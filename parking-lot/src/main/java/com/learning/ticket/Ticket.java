package com.learning.ticket;

import java.util.Date;

public record Ticket(
        Date issueTime,
        String vehicleRegistrationNo,
        int parkingFloorNo,
        int parkingSpotId,
        String parkingSpotType
) {
}
