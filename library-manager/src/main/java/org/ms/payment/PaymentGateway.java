package org.ms.payment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

public class PaymentGateway {

	private final List<PaymentAcceptor> paymentAcceptors;

	public PaymentGateway() {
		this.paymentAcceptors = List.of(new CashPaymentAcceptor(), new CardPaymentAcceptor());
	}

	public boolean acceptPayment(float amount) {
		System.out.println("How would you like to pay?");
		System.out.println("1. Card      2. Cash");
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			int paymentType = Integer.parseInt(br.readLine());
			Optional<PaymentAcceptor> acceptor = Optional.empty();
			if(paymentType == 1) {
				acceptor = paymentAcceptors.stream()
						.filter(paymentAcceptor -> paymentAcceptor.support(PaymentType.CARD)).findFirst();
			} else if (paymentType == 2) {
				acceptor = paymentAcceptors.stream()
						.filter(paymentAcceptor -> paymentAcceptor.support(PaymentType.CASH)).findFirst();
			}
			return acceptor.map(acc -> acc.acceptPayment(amount)).orElse(false);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
