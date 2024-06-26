package com.ms.show.booking.payment.gateway;

import com.ms.show.booking.payment.Payment;
import com.ms.show.booking.payment.PaymentReceipt;

public class CashPaymentGateway implements PaymentGateway {

	@Override
	public PaymentReceipt pay(Payment payment) {
		return null;
	}
}
