package com.learning.ticket;

import com.learning.parking.ParkingSpotType;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Optional;

public class TicketScanner {

    public Optional<Ticket> scanTicket(InputStream inputStream){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            var line = bufferedReader.readLine().split("::");
            var vehicleRegNo = line[1].trim();
            line = bufferedReader.readLine().split("::");
            var issueTime = Ticket.ISSUE_TIME_FORMATTER.parse(line[1]);
            line = bufferedReader.readLine().split("::");
            var parkingSpotFloor = Integer.parseInt(line[1]);
            line = bufferedReader.readLine().split("::");
            var parkingSpotType = line[1];
            line = bufferedReader.readLine().split("::");
            var parkingSpotId = Integer.parseInt(line[1]);
            return Optional.of(new Ticket(LocalDateTime.from(issueTime), vehicleRegNo, parkingSpotFloor, parkingSpotId, parkingSpotType));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
