package com.learning.parking;

import com.learning.ticket.Ticket;
import com.learning.ticket.TicketGenerator;
import com.learning.vehicle.Vehicle;
import java.util.Optional;

public class CheckInKiosk {
  private final Gate entranceGate;
  private final ParkingLot parkingLot;
  private final TicketGenerator ticketGenerator;

  public CheckInKiosk(Gate entranceGate, ParkingLot parkingLot, TicketGenerator ticketGenerator) {
    this.entranceGate = entranceGate;
    this.parkingLot = parkingLot;
    this.ticketGenerator = ticketGenerator;
  }

  public void checkIn(Vehicle vehicle, boolean disabilityCardAvailable) {
    System.out.println("Starting checking in...");
    ParkingSpotType parkingSpotType =
        determineRequiredParkingSpot(vehicle, disabilityCardAvailable);
    if (parkingLot.hasParkingSpotAvailable(parkingSpotType)) {
      System.out.println("Parking spot is available for the vehicle, booking it!!");
      Optional<ParkingSpot> parkingSpot = parkingLot.occupyAvailableParkingSpot(parkingSpotType);
      parkingSpot
          .ifPresent(
              ps -> {
                System.out.println("Booked " + ps);
                entranceGate.open();
                ticketGenerator.generateTicket(vehicle, ps);
              });
    } else {
      System.out.println("Parking spot not available!!");
    }
  }

  private static ParkingSpotType determineRequiredParkingSpot(
      Vehicle vehicle, boolean disabledPerson) {
    return switch (vehicle.getType()) {
      case CAR -> {
        if (disabledPerson) yield ParkingSpotType.HANDICAP;
        yield ParkingSpotType.COMPACT;
      }
      case TRUCK, VAN -> ParkingSpotType.LARGE;
      case MOTORCYCLE -> ParkingSpotType.MOTORCYCLE;
    };
  }
}
