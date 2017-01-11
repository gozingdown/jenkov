package com.zheng.jsontest;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class OptimizedBooleanDeserializer extends JsonDeserializer<Boolean> {

	@Override
	public Boolean deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
	        throws IOException, JsonProcessingException {

		String text = jsonParser.getText();
		System.out.println("text: "+text);
		if ("0".equals(text))
			return false;
		return true;
	}
}
