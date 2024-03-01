package com.learning.payment.acceptor;

import com.learning.payment.Payment;

public interface PaymentAcceptor {
    boolean acceptPayment(Payment payment);
    boolean support(Payment payment);
}
