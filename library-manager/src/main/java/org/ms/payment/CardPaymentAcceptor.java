package org.ms.payment;

public class CardPaymentAcceptor implements PaymentAcceptor {

	@Override
	public boolean acceptPayment(float amount) {
		System.out.println("Accepting payment through Card...");
		return true;
	}

	@Override
	public boolean support(PaymentType paymentType) {
		return paymentType == PaymentType.CARD;
	}
}
