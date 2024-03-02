package com.learning.parking;

import com.learning.payment.Payment;
import com.learning.payment.PaymentType;
import com.learning.payment.acceptor.PaymentAcceptor;
import com.learning.payment.calculator.PaymentCalculator;
import com.learning.ticket.Ticket;
import com.learning.ticket.TicketScanner;
import com.learning.ticket.TicketValidator;

import java.io.File;
import java.io.InputStream;
import java.util.Optional;

public class CheckoutKiosk {
    private final TicketScanner ticketScanner;
    private final TicketValidator ticketValidator;
    private final PaymentCalculator paymentCalculator;
    private final PaymentAcceptor paymentAcceptor;
    private final Gate exitGate;
    private final ParkingLot parkingLot;

    public CheckoutKiosk(TicketScanner ticketScanner, TicketValidator ticketValidator, PaymentCalculator paymentCalculator,
                         PaymentAcceptor paymentAcceptor, Gate exitGate, ParkingLot parkingLot) {
        this.ticketScanner = ticketScanner;
        this.ticketValidator = ticketValidator;
        this.paymentCalculator = paymentCalculator;
        this.paymentAcceptor = paymentAcceptor;
        this.exitGate = exitGate;
        this.parkingLot = parkingLot;
    }

    public void checkout(InputStream inputStream, PaymentType paymentType) {
    System.out.println("Starting checkout...");
        Optional<Ticket> ticket = ticketScanner.scanTicket(inputStream);
    boolean checkoutSuccess =
        ticket
            .filter(ticketValidator::isValid)
            .map(
                tk -> {
                  Payment paymentRequired =
                      new Payment(paymentCalculator.calculatePayment(tk), paymentType);
                  System.out.println("Payment required: " + paymentRequired);
                  return paymentRequired;
                })
            .filter(paymentAcceptor::support)
            .map(paymentAcceptor::acceptPayment)
            .orElse(false);
        if (checkoutSuccess) {
            parkingLot.vacateParkingSpot(ticket.get().parkingFloorNo(), ticket.get().parkingSpotId());
            exitGate.open();
            System.out.println("Checkout successful!!");
        } else {
            System.out.println("Checkout failed!!");
        }
    }
}
