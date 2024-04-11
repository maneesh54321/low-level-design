package com.ms.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TimingMethodInvocationHandler implements InvocationHandler {

	private final Map<String, Method> map;

	private final Object target;

	public TimingMethodInvocationHandler(Object target){
		map = new HashMap<>();
		this.target = target;
		Method[] methods = target.getClass().getMethods();
		Arrays.stream(methods).forEach(m -> map.put(m.getName(), m));
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("Before method execution");
		int before = LocalDateTime.now().getNano();
		Object result = map.get(method.getName()).invoke(target, args); // Invoke the method on the proxy object
		int after = LocalDateTime.now().getNano();
		System.out.println("After method execution");
		System.out.println("Time taken for method execution (nano sec): " + (after - before));
		return result;
	}
}
