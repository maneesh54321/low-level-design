package com.ms.show.booking.payment.gateway;

import com.ms.show.booking.payment.Payment;
import com.ms.show.booking.payment.PaymentReceipt;

public interface PaymentGateway {
	PaymentReceipt pay(Payment payment);
}
