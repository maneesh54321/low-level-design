package com.learning.ticket;

import com.learning.parking.ParkingSpot;
import com.learning.vehicle.Vehicle;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class TicketGenerator {

  private final OutputStream outputStream;

  public TicketGenerator(OutputStream outputStream) {
    this.outputStream = outputStream;
  }

  public void generateTicket(Vehicle vehicle, ParkingSpot parkingSpot) {
    Optional.ofNullable(parkingSpot)
        .map(
            ps ->
                new Ticket(
                    LocalDateTime.now(),
                    vehicle.getRegNo(),
                    ps.getParkingFloor().floorNumber(),
                    ps.getId(),
                    ps.getType()))
        .ifPresent(tk -> printTicket(tk, outputStream));
  }

  private void printTicket(Ticket ticket, OutputStream outputStream) {
    System.out.println("Printing ticket...");
    OutputStream outStream = new BufferedOutputStream(outputStream);
    PrintWriter printWriter = new PrintWriter(outStream);
    printWriter.println(("Vehicle registration no::" + ticket.vehicleRegistrationNo()));
    printWriter.println(("Issue time::" + Ticket.ISSUE_TIME_FORMATTER.format(ticket.issueTime())));
    printWriter.println(("Parking spot floor::" + ticket.parkingFloorNo()));
    printWriter.println(("Parking spot type::" + ticket.parkingSpotType()));
    printWriter.println(("Parking spot id::" + ticket.parkingSpotId()));
    printWriter.flush();
  }
}
