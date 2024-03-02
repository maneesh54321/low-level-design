package com.learning.parking;

import com.learning.payment.Payment;
import com.learning.payment.PaymentType;
import com.learning.payment.acceptor.PaymentAcceptor;
import com.learning.payment.calculator.PaymentCalculator;
import com.learning.ticket.Ticket;
import com.learning.ticket.TicketScanner;
import com.learning.ticket.TicketValidator;

import java.io.File;
import java.util.Optional;

public class CheckoutKiosk {
    private final TicketScanner ticketScanner;
    private final TicketValidator ticketValidator;
    private final PaymentCalculator paymentCalculator;
    private final PaymentAcceptor paymentAcceptor;
    private final Gate exitGate;
    private final ConcreteParkingLot parkingLot;

    public CheckoutKiosk(TicketScanner ticketScanner, TicketValidator ticketValidator, PaymentCalculator paymentCalculator,
                         PaymentAcceptor paymentAcceptor, Gate exitGate, ConcreteParkingLot parkingLot) {
        this.ticketScanner = ticketScanner;
        this.ticketValidator = ticketValidator;
        this.paymentCalculator = paymentCalculator;
        this.paymentAcceptor = paymentAcceptor;
        this.exitGate = exitGate;
        this.parkingLot = parkingLot;
    }

    public void checkout(File ticketFile, PaymentType paymentType) {
        Optional<Ticket> ticket = ticketScanner.scanTicket(ticketFile);
        boolean checkoutSuccess = ticket.filter(ticketValidator::isValid)
                .map(tk -> new Payment(paymentCalculator.calculatePayment(tk), paymentType))
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
