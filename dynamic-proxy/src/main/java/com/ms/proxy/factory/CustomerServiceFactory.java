package com.ms.proxy.factory;

import com.ms.proxy.CustomerService;
import com.ms.proxy.CustomerServiceImpl;
import com.ms.proxy.annotation.TrackMethods;
import com.ms.proxy.jdk.TimingMethodInvocationHandler;

import java.lang.reflect.Proxy;

public class CustomerServiceFactory {

	public static CustomerService createCustomerService(){
		Class<? extends CustomerServiceImpl> clazz = CustomerServiceImpl.class;
		CustomerServiceImpl customerService = new CustomerServiceImpl();
		TrackMethods trackMethodsAnn = clazz.getAnnotation(TrackMethods.class);
		if(trackMethodsAnn != null) {
			return (CustomerService) Proxy.newProxyInstance(
					CustomerServiceFactory.class.getClassLoader(),
					new Class<?>[]{CustomerService.class},
					new TimingMethodInvocationHandler(customerService)
			);
		}
		return customerService;
	}
}
