package org.ms.payment;

public class CashPaymentAcceptor implements PaymentAcceptor {

	@Override
	public boolean acceptPayment(float amount) {
		System.out.println("Accepting payment with cash!!");
		return true;
	}

	@Override
	public boolean support(PaymentType paymentType) {
		return paymentType == PaymentType.CASH;
	}
}
