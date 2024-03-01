package com.learning.payment.acceptor;

import com.learning.payment.Payment;
import com.learning.payment.PaymentType;

public class PaymentKiosk implements PaymentAcceptor {
    @Override
    public boolean acceptPayment(Payment payment) {
        return true;
    }

    @Override
    public boolean support(Payment payment) {
        return payment.paymentType() == PaymentType.CREDIT_CARD
                || payment.paymentType() == PaymentType.DEBIT_CARD;
    }
}
