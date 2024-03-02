package com.learning.parking;

import com.learning.ticket.Ticket;
import com.learning.ticket.TicketGenerator;
import com.learning.vehicle.Vehicle;

import java.util.Optional;

public class CheckInKiosk {
    private final Gate entranceGate;
    private final ConcreteParkingLot parkingLot;
    private final TicketGenerator ticketGenerator;

    public CheckInKiosk(Gate entranceGate, ConcreteParkingLot parkingLot, TicketGenerator ticketGenerator) {
        this.entranceGate = entranceGate;
        this.parkingLot = parkingLot;
        this.ticketGenerator = ticketGenerator;
    }

    public Optional<Ticket> checkIn(Vehicle vehicle, boolean disabilityCardAvailable) {
        ParkingSpotType parkingSpotType = determineRequiredParkingSpot(vehicle, disabilityCardAvailable);
        if (parkingLot.hasParkingSpotAvailable(parkingSpotType)) {
            Optional<ParkingSpot> parkingSpot = parkingLot.occupyAvailableParkingSpot(parkingSpotType);
            return parkingSpot.flatMap(ps -> {
                entranceGate.open();
                return ticketGenerator.generateTicket(vehicle, ps);
            });
        } else {
            System.out.println("Parking spot not available!!");
        }
        return Optional.empty();
    }

    private static ParkingSpotType determineRequiredParkingSpot(Vehicle vehicle, boolean disabledPerson) {
        return switch (vehicle.getType()) {
            case CAR -> {
                if (disabledPerson)
                    yield ParkingSpotType.HANDICAP;
                yield ParkingSpotType.COMPACT;
            }
            case TRUCK, VAN -> ParkingSpotType.LARGE;
            case MOTORCYCLE -> ParkingSpotType.MOTORCYCLE;
        };
    }

}
