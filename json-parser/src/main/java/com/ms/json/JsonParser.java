package com.ms.json;

import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public class JsonParser {

	private static final Map<Class<?>, Function<Object, Object>> converters;
	private static final Map<Class<?>, Consumern<Object, Object>> setters;

	static {
		converters = new HashMap<>();
		converters.put(int.class, Function.identity());
		converters.put(boolean.class, Function.identity());
		converters.put(long.class, Function.identity());
		converters.put(float.class, Function.identity());
		converters.put(double.class, Function.identity());
		converters.put(Integer.class, Function.identity());
		converters.put(Long.class, Function.identity());
		converters.put(Float.class, Function.identity());
		converters.put(Double.class, Function.identity());
		converters.put(Boolean.class, Function.identity());
		converters.put(String.class, Function.identity());

		setters = new HashMap<>();
	}

	public <T> T parseJson(String json, Class<T> clazz) {
		System.out.println(json);
		JSONObject jsonObject = new JSONObject(json);
		return parseJson(jsonObject, clazz);
	}

	private <T> T parseJson(JSONObject jsonObject, Class<T> clazz) {
		try {
			T obj = clazz.getConstructor().newInstance();
			Field[] fields = clazz.getFields();
			for (Field field: fields) {
				if(isJavaType(field)) {
					Class<?> type = field.getType();
					Object value = converters.get(type).apply(jsonObject.get(field.getName()));
					setters.get(type).set(obj, field, value);
				} else {
					Class<?> type = field.getType();
					JSONObject value = (JSONObject) jsonObject.get(field.getName());
					field.setAccessible(true);
					field.set(field.getName(), parseJson(value, type));
				}
			}
			return obj;
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	private boolean isJavaType(Field field) {
		Class<?> type = field.getType();
		return type == short.class || type == byte.class || type == int.class || type == long.class || type == double.class
				|| type == boolean.class || type == float.class || type == Double.class || type == char.class;
	}
}
