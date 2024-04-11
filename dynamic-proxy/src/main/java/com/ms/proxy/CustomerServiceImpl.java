package com.ms.proxy;

import com.ms.proxy.annotation.TrackMethods;

@TrackMethods
public class CustomerServiceImpl implements CustomerService {
	@Override
	public void serve(String name) {
		System.out.println("Serving " + name);
	}

	@Override
	public String getDocName(int key) {
		return "Value";
	}
}
