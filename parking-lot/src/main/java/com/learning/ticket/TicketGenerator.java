package com.learning.ticket;

import com.learning.parking.EntranceGate;
import com.learning.parking.ParkingLot;
import com.learning.parking.ParkingSpot;
import com.learning.parking.ParkingSpotType;
import com.learning.vehicle.Vehicle;

import java.util.Date;
import java.util.Optional;

public class TicketGenerator {

    private final EntranceGate entranceGate;

    private final ParkingLot parkingLot;

    public TicketGenerator(EntranceGate entranceGate, ParkingLot parkingLot) {
        this.entranceGate = entranceGate;
        this.parkingLot = parkingLot;
    }

    public Ticket generateTicket(Vehicle vehicle, boolean disabledPerson) {
        ParkingSpotType parkingSpotType = determineRequiredParkingSpot(vehicle, disabledPerson);
        if (parkingLot.hasParkingSpotAvailable(parkingSpotType)) {
            Optional<ParkingSpot> parkingSpot = parkingLot.occupyAvailableParkingSpot(parkingSpotType);
            return parkingSpot.map(ps -> {
                entranceGate.open();
                return new Ticket(
                        new Date(),
                        vehicle.getRegNo(),
                        ps.getParkingFloor().floorNumber(),
                        ps.getId(),
                        ps.getType().name()
                );
            }).orElse(null);
        } else {
            System.out.println("Parking spot not available!!");
        }
        return null;
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
