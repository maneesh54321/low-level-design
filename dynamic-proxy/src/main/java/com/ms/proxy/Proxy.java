package com.ms.proxy;

public class Proxy implements CustomerService {

	private final CustomerService customerService;

	public Proxy(CustomerService customerService) {
		this.customerService = customerService;
	}

	@Override
	public void serve(String name) {
		System.out.println("Starting method serve(name) execution...");
		customerService.serve(name);
		System.out.println("Finished method serve(name) execution...");
	}

	@Override
	public String getDocName(int key) {
		System.out.println("Starting method getDocName(key) execution...");
		String returnObj = customerService.getDocName(key);
		System.out.println("Finished method getDocName(key) execution...");
		return returnObj;
	}
}
