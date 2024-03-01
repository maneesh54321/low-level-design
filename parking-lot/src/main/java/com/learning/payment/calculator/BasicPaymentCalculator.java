package com.learning.payment.calculator;

import com.learning.ticket.Ticket;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class BasicPaymentCalculator implements PaymentCalculator {

    private static final Double PER_HOUR_CHARGE = 20.0;

    @Override
    public double calculatePayment(Ticket ticket) {
        LocalDateTime checkoutTime = LocalDateTime.now();
        double hours = ticket.issueTime().until(checkoutTime, ChronoUnit.MINUTES);
        hours = Math.ceil(hours);
        return hours * PER_HOUR_CHARGE;
    }
}
