package org.ms.payment;

public interface PaymentAcceptor {
	boolean acceptPayment(float amount);
	boolean support(PaymentType paymentType);
}
