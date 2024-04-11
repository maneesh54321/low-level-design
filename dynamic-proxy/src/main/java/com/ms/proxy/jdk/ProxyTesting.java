package com.ms.proxy.jdk;

import com.ms.proxy.CustomerService;
import com.ms.proxy.factory.CustomerServiceFactory;

public class ProxyTesting {
	public static void main(String[] args) {
		CustomerService customerService = CustomerServiceFactory.createCustomerService();

		customerService.serve("Maneesh");
	}
}
