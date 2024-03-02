package com.learning;

import com.learning.display.ParkingLotMetricsDisplay;
import com.learning.display.ParkingLotStatusDisplay;
import com.learning.display.Subject;
import com.learning.parking.*;
import com.learning.payment.PaymentType;
import com.learning.payment.acceptor.ParkingAgentScreen;
import com.learning.payment.acceptor.PaymentAcceptor;
import com.learning.payment.acceptor.PaymentGateway;
import com.learning.payment.acceptor.PaymentKiosk;
import com.learning.payment.calculator.BasicPaymentCalculator;
import com.learning.payment.calculator.PaymentCalculator;
import com.learning.ticket.TicketGenerator;
import com.learning.ticket.TicketScanner;
import com.learning.ticket.TicketValidator;
import com.learning.vehicle.Vehicle;
import com.learning.vehicle.VehicleType;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class Main {
  public static void main(String[] args) {

    try {
      Path path =
          Path.of(Objects.requireNonNull(Main.class.getResource("/parking-lot.txt")).toURI());
      ParkingLot parkingLot = new ConcreteParkingLot(new ArrayList<>());
      try (BufferedReader reader = Files.newBufferedReader(path)) {
        String line1, line2;
        while ((line1 = reader.readLine()) != null && (line2 = reader.readLine()) != null) {
          var floorNo = Integer.parseInt(line1);
          var parkingFloor = new ParkingFloor(floorNo, new ArrayList<>());
          String[] spots = line2.split(" ");
          IntStream.range(0, Integer.parseInt(spots[0]))
              .forEachOrdered(
                  idx ->
                      parkingFloor.addParkingSpot(
                          new CompactParkingSpot(idx, false, parkingFloor)));
          IntStream.range(0, Integer.parseInt(spots[1]))
              .forEachOrdered(
                  idx ->
                      parkingFloor.addParkingSpot(new LargeParkingSpot(idx, false, parkingFloor)));
          IntStream.range(0, Integer.parseInt(spots[2]))
              .forEachOrdered(
                  idx ->
                      parkingFloor.addParkingSpot(
                          new MotorCycleParkingSpot(idx, false, parkingFloor)));
          IntStream.range(0, Integer.parseInt(spots[3]))
              .forEachOrdered(
                  idx ->
                      parkingFloor.addParkingSpot(
                          new HandicapParkingSpot(idx, false, parkingFloor)));
          parkingLot.addParkingFloor(parkingFloor);
        }
      }
      parkingLot = new ParkingLotSubject(parkingLot);
      var parkingDisplay = new ParkingLotMetricsDisplay((Subject) parkingLot);
      var parkingFullDisplay = new ParkingLotStatusDisplay((Subject) parkingLot);
      Gate entranceGate = new Gate(1);
      String ticketFileLocation = "D:\\ticket.txt";
      try (FileOutputStream ticketOutStream = new FileOutputStream(ticketFileLocation)) {
        TicketGenerator ticketGenerator = new TicketGenerator(ticketOutStream);
        CheckInKiosk checkInKiosk = new CheckInKiosk(entranceGate, parkingLot, ticketGenerator);
        System.out.println();
        checkInKiosk.checkIn(new Vehicle("KA04HG3670", VehicleType.CAR), true);
      }
      System.out.println();
      System.out.println("Watching movie...");
      Thread.sleep(5000);
      System.out.println("Done watching movie...");
      System.out.println("Going to parking...");
      System.out.println("Taking my vehicle to exit gate...");
      System.out.println();
      // checkout begins
      CheckoutKiosk checkoutKiosk = createCheckoutKiosk(parkingLot);
      try (var ticketInputStream = new FileInputStream(ticketFileLocation)) {
        //        checkoutKiosk.checkout(ticketInputStream, PaymentType.CREDIT_CARD);
        checkoutKiosk.checkout(ticketInputStream, PaymentType.CASH);
      }
    } catch (IOException | URISyntaxException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  private static CheckoutKiosk createCheckoutKiosk(ParkingLot parkingLot) {
    Gate exitGate = new Gate(2);
    TicketScanner ticketScanner = new TicketScanner();
    TicketValidator ticketValidator = new TicketValidator();
    PaymentCalculator paymentCalculator = new BasicPaymentCalculator();
    PaymentAcceptor paymentAcceptor =
        new PaymentGateway(List.of(new ParkingAgentScreen(), new PaymentKiosk()));
    return new CheckoutKiosk(
        ticketScanner, ticketValidator, paymentCalculator, paymentAcceptor, exitGate, parkingLot);
  }
}
