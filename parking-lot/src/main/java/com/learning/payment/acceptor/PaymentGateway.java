package com.learning.payment.acceptor;

import com.learning.payment.Payment;

import java.util.List;

public class PaymentGateway implements PaymentAcceptor {

    private final List<PaymentAcceptor> paymentAcceptorList;

    public PaymentGateway(List<PaymentAcceptor> paymentAcceptorList) {
        this.paymentAcceptorList = paymentAcceptorList;
    }

    @Override
    public boolean acceptPayment(Payment payment) {
        return paymentAcceptorList.stream()
                .filter(paymentAcceptor -> paymentAcceptor.support(payment))
                .findFirst().map(paymentAcceptor -> paymentAcceptor.acceptPayment(payment))
                .orElse(false);
    }

    @Override
    public boolean support(Payment payment) {
        return paymentAcceptorList.stream()
                .anyMatch(paymentAcceptor -> paymentAcceptor.support(payment));
    }
}
