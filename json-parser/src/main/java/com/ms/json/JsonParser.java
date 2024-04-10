package com.ms.json;

import com.ms.json.helper.ThrowingBiConsumer;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class JsonParser {

	private static final Map<Class<?>, Function<Object, Object>> converters;
	private static final Map<Class<?>, Function<Object, ThrowingBiConsumer<Field, Object>>> setters;

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
		converters.put(Map.class, obj -> {
			JSONObject jsonObject = (JSONObject) obj;
//			Map<>
//			jsonObject.keys().forEachRemaining(key -> );
			return null;
		});

		setters = new HashMap<>();
		setters.put(int.class, classObj -> (field, value) -> {
			field.setAccessible(true);
			field.setInt(classObj, (Integer) value);
		});

		setters.put(boolean.class, classObj -> (field, value) -> {
			field.setAccessible(true);
			field.setBoolean(classObj, (Boolean) value);
		});

		setters.put(long.class, classObj -> (field, value) -> {
			field.setAccessible(true);
			field.setLong(classObj, (Long) value);
		});

		setters.put(float.class, classObj -> (field, value) -> {
			field.setAccessible(true);
			field.setFloat(classObj, (Float) value);
		});

		setters.put(double.class, classObj -> (field, value) -> {
			field.setAccessible(true);
			field.setDouble(classObj, (Double) value);
		});

		setters.put(Integer.class, classObj -> (field, value) -> {
			field.setAccessible(true);
			field.setInt(classObj, (Integer) value);
		});

		setters.put(Long.class, classObj -> (field, value) -> {
			field.setAccessible(true);
			field.setLong(classObj, (Long) value);
		});

		setters.put(Float.class, classObj -> (field, value) -> {
			field.setAccessible(true);
			field.setFloat(classObj, (Float) value);
		});

		setters.put(Double.class, classObj -> (field, value) -> {
			field.setAccessible(true);
			field.setDouble(classObj, (Double) value);
		});

		setters.put(Boolean.class, classObj -> (field, value) -> {
			field.setAccessible(true);
			field.setBoolean(classObj, (Boolean) value);
		});

		setters.put(String.class, classObj -> (field, value) -> {
			field.setAccessible(true);
			field.set(classObj, value);
		});
	}

	public <T> T parseJson(String json, Class<T> clazz) {
		System.out.println(json);
		JSONObject jsonObject = new JSONObject(json);
		return parseJson(jsonObject, clazz);
	}

	private <T> T parseJson(JSONObject jsonObject, Class<T> clazz) {
		try {
			T obj = clazz.getConstructor().newInstance();
			Field[] fields = clazz.getDeclaredFields();
			for (Field field: fields) {
				Class<?> type = field.getType();
				if(isJavaType(field)) {
					Object value = converters.get(type).apply(jsonObject.get(field.getName()));
					setters.get(type).apply(obj).accept(field, value);
				} else {
					JSONObject value = jsonObject.getJSONObject(field.getName());
					field.setAccessible(true);
					field.set(obj, parseJson(value, type));
				}
			}
			return obj;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private boolean isJavaType(Field field) {
		Class<?> type = field.getType();
		return type == short.class || type == byte.class || type == int.class || type == long.class
				|| type == double.class	|| type == boolean.class || type == float.class || type == Double.class
				|| type == char.class || type == String.class || type == Map.class;
	}
}
