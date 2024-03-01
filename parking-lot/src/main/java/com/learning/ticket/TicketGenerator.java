package com.learning.ticket;

import com.learning.parking.ParkingSpot;
import com.learning.vehicle.Vehicle;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

public class TicketGenerator {

    public Optional<Ticket> generateTicket(Vehicle vehicle, ParkingSpot parkingSpot) {
        return Optional.ofNullable(parkingSpot)
                .map(ps -> new Ticket(
                        LocalDateTime.now(),
                        vehicle.getRegNo(),
                        ps.getParkingFloor().floorNumber(),
                        ps.getId(),
                        ps.getType().name()
                ));
    }
}
