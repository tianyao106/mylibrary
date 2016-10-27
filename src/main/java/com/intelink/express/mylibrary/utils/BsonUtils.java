package com.intelink.express.mylibrary.utils;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.PascalCaseStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.intelink.express.mylibrary.exception.AppException;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.InflaterInputStream;

import de.undercouch.bson4jackson.BsonFactory;

public class BsonUtils {
	private static final String ENCODE_ERROR_MSG = "编码数据出现错误";
	private static final String DECODE_ERROR_MSG = "解析数据出现错误";
	
	public static byte[] encode(Object object) throws AppException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ObjectMapper mapper = new ObjectMapper(new BsonFactory());
		mapper.setPropertyNamingStrategy(new PascalCaseStrategy());
		//mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		try {
			mapper.writeValue(outputStream, object);
		} catch (JsonGenerationException e) {
			throw new AppException(ENCODE_ERROR_MSG, e);
		} catch (JsonMappingException e) {
			throw new AppException(ENCODE_ERROR_MSG, e);
		} catch (IOException e) {
			throw new AppException(ENCODE_ERROR_MSG, e);
		}
		return outputStream.toByteArray();
	}
	
	public static <T> T decode(byte[] data, Class<T> classs) throws AppException {
		return decode(data, classs, 0);
	}
	
	public static <T> T decode(byte[] data, Class<T> classs, int encryptType) throws AppException {
		ObjectMapper mapper = new ObjectMapper(new BsonFactory());
		mapper.setPropertyNamingStrategy(new PascalCaseStrategy());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        T t = null;
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(data);
			if (encryptType == 1) {
				InflaterInputStream zipInputStream = new InflaterInputStream(bais);
				t = mapper.readValue(zipInputStream, classs);
			} else {
				t = mapper.readValue(bais, classs);
			}			
		} catch (JsonParseException e) {
			throw new AppException(DECODE_ERROR_MSG, e);
		} catch (JsonMappingException e) {
			throw new AppException(DECODE_ERROR_MSG, e);
		} catch (IOException e) {
			throw new AppException(DECODE_ERROR_MSG, e);
		}
		return t;		
	}
	
	public static <T> T decode(byte[] data, TypeReference<T> classs, int encryptType) throws AppException {
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		return decode(bais, classs, encryptType);
	}	
		
	public static <T> T decode(InputStream inputStream, TypeReference<T> classs, int encryptType) throws AppException {
		ObjectMapper mapper = new ObjectMapper(new BsonFactory());
		mapper.setPropertyNamingStrategy(new PascalCaseStrategy());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        T t = null;
		try {
			if (encryptType == 1) {
				InflaterInputStream zipInputStream = new InflaterInputStream(inputStream);
				t = mapper.readValue(zipInputStream, classs);
			} else {
				t = mapper.readValue(inputStream, classs);
			}	
		} catch (JsonParseException e) {
			throw new AppException(DECODE_ERROR_MSG, e);
		} catch (JsonMappingException e) {
			throw new AppException(DECODE_ERROR_MSG, e);
		} catch (IOException e) {
			throw new AppException(DECODE_ERROR_MSG, e);
		}
		return t;		
	}				
	
	public static Map<String, String> decodeAsString(byte[] data) {
		Map<String, String> map = new HashMap<String, String>();
		BsonFactory factory = new BsonFactory();
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		try {
			JsonParser parser = factory.createJsonParser(bais);
			parser.nextToken();
			while (parser.nextToken() != JsonToken.END_OBJECT) {
			  parser.nextToken();
			  if (parser.getText() != null) {
				  map.put(parser.getCurrentName(), parser.getText());
			  }
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return map;
	}
}
