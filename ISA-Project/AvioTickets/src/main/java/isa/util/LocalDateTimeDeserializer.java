package isa.util;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
	  
	@Override
	public LocalDateTime deserialize(JsonParser jp, DeserializationContext ctxt) 
			  throws IOException, JsonProcessingException{
		
		String dateTimeString = jp.getText();
		return LocalDateTime.parse(dateTimeString, DateTimeFormatter.ISO_DATE_TIME);
	}

}
