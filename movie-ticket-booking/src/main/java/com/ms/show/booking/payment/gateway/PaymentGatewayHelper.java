package com.ms.show.booking.payment.gateway;

import com.ms.show.booking.payment.Payment;
import com.ms.show.booking.payment.PaymentReceipt;
import java.util.List;

public class PaymentGatewayHelper implements PaymentGateway {

	private final List<PaymentGateway> paymentGateways;

	public PaymentGatewayHelper(List<PaymentGateway> paymentGateways) {
		this.paymentGateways = paymentGateways;
	}

	@Override
	public PaymentReceipt pay(Payment payment) {
		return null;
	}
}
