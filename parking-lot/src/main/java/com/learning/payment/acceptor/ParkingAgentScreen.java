package com.learning.payment.acceptor;

import com.learning.payment.Payment;
import com.learning.payment.PaymentType;

public class ParkingAgentScreen implements PaymentAcceptor {
  @Override
  public boolean acceptPayment(Payment payment) {
    System.out.println("Accepting payment with cash...");
    System.out.println("Payment successful!!");
    return true;
  }

  @Override
  public boolean support(Payment payment) {
    return payment.paymentType() == PaymentType.CASH;
  }
}
