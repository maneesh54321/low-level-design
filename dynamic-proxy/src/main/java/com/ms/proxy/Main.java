package com.ms.proxy;

public class Main {
	public static void main(String[] args) {
		CustomerService custService = new CustomerServiceImpl();
		CustomerService customerService = new Proxy(custService);

		customerService.getDocName(124);
		customerService.serve("124");
	}
}