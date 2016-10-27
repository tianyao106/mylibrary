package com.intelink.express.mylibrary.api;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.intelink.express.mylibrary.utils.DateUtils;


import java.io.IOException;
import java.util.Date;

public class CustomDateSerializer extends JsonSerializer<Date> {

	@Override
	public void serialize(Date date, JsonGenerator generator, SerializerProvider provider) throws IOException,
			JsonProcessingException {
        generator.writeString(DateUtils.format(date));
	}
}
