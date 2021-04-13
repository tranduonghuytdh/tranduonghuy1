package com.jsptintuc.utils;

import java.io.BufferedReader;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpUtil {

	private String value;

	public HttpUtil(String value) {
		this.value = value;
	}
	
	// Làm đơn 1 model
	public <T> T toModel(Class<T> tClass) {
		try {
			// ObjectMapper Chuyển đổi giữa class model json
			return new ObjectMapper().readValue(value, tClass);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	// Chuyển chuỗi json thành chuỗi String(value)
	public static HttpUtil of (BufferedReader reader) {
		StringBuilder sb = new StringBuilder();
		try {
			String line;
			while((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return new HttpUtil(sb.toString());
	}
	
}
