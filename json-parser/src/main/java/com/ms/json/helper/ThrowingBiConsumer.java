package com.ms.json.helper;

@FunctionalInterface
public interface ThrowingBiConsumer<T, V> {
	void accept(T t, V v) throws Exception;
}
