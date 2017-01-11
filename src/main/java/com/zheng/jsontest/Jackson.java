package com.zheng.jsontest;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Jackson {

	@Call
	public void testObjectMapper() {
		String carJson = "{\"brand\":\"jeep\",\"doors\":5}";

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			JsonNode node = objectMapper.readValue(carJson, JsonNode.class);
			Car car = objectMapper.readValue(carJson, Car.class);
			System.out.println(node.get("brand").asText());
			System.out.println(car.getBrand());
			System.out.println(carJson.getBytes("UTF-8")[0]);

			String json = objectMapper.writeValueAsString(car);
			System.out.println(json);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Call
	public void testJSONParser() {
		String carJson = "{\"brand\":\"jeep\",\"doors\":5}";
		JsonFactory jsonFactory = new JsonFactory();
		try {
			JsonParser jsonParser = jsonFactory.createParser(carJson);
			while (!jsonParser.isClosed()) {
				JsonToken jsonToken = jsonParser.nextToken();
				System.out.println("jsonToken = " + jsonToken);
			}

			System.out.println("======================");

			jsonParser = jsonFactory.createParser(carJson);
			while (!jsonParser.isClosed()) {
				JsonToken jsonToken = jsonParser.nextToken();
				if (jsonToken == null) {
					break;
				}
				if (jsonToken.equals(JsonToken.FIELD_NAME)) {
					String fieldName = jsonParser.getCurrentName();
					jsonToken = jsonParser.nextToken();
					if (fieldName.equals("brand")) {
						System.out.println(fieldName + ":" + jsonParser.getValueAsString());
					} else if (fieldName.equals("doors")) {
						System.out.println(fieldName + ":" + jsonParser.getValueAsInt());
					}
				}
			}

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Call
	public void testJsonGenerator() {
		System.out.println("testJsonGenerator");
		JsonFactory jsonFactory = new JsonFactory();
		try {
			JsonGenerator jsonGenerator = jsonFactory.createGenerator(
			        new File("C:\\Users\\zgong\\workspace\\amqp-client\\output.json"), JsonEncoding.UTF8);
			jsonGenerator.writeStartObject();
			jsonGenerator.writeStringField("brand", "Jeep");
			jsonGenerator.writeNumberField("doors", 5);
			jsonGenerator.writeEndObject();
			jsonGenerator.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Call
	public void testAnnotation() {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			PersonDeserialize person = objectMapper
			        .reader(PersonDeserialize.class)
			        .readValue(new File("C:\\Users\\zgong\\workspace\\amqp-client\\person-optimized-boolean.json"));
			System.out.println(person.id +"|"+person.enabled+"|"+ person.name);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Call
	public void testAnnotation2() {
		PersonValue personValue = new PersonValue();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String strValue = objectMapper.writeValueAsString(personValue);
			objectMapper.writeValue(new File("C:\\Users\\zgong\\workspace\\amqp-client\\testAnnotation2.json"), strValue);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private static boolean isCallable(Method method) {
		return method.getDeclaredAnnotation(Call.class) != null;
	}

	public static void main(String[] args) {
		Jackson jackson = new Jackson();
		Method[] methods = jackson.getClass().getMethods();
		for (int i = 0; i < methods.length; i++) {
			if (isCallable(methods[i])) {
				System.out.println("method name:" + methods[i].getName());
				try {
					Object invokeResult = methods[i].invoke(jackson, null);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
