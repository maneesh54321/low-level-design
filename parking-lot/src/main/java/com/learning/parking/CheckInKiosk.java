package com.learning.parking;

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
    if (parkingLot.hasParkingSpotAvailable(vehicle)) {
      System.out.println("Parking spot is available for the vehicle, booking it!!");
      Optional<ParkingSpot> parkingSpot = parkingLot.occupyAvailableParkingSpot(vehicle);
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
}
