package com.learning.payment.acceptor;

import com.learning.payment.Payment;
import com.learning.payment.PaymentType;

public class PaymentKiosk implements PaymentAcceptor {
  @Override
  public boolean acceptPayment(Payment payment) {
    System.out.println("Accepting payment from payment kiosk...");
    System.out.println("Payment Successful!!");
    return true;
  }

  @Override
  public boolean support(Payment payment) {
    return payment.paymentType() == PaymentType.CREDIT_CARD
        || payment.paymentType() == PaymentType.DEBIT_CARD;
  }
}
