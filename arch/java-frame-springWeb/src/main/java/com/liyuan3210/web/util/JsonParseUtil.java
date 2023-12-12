package com.liyuan3210.web.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParseUtil {
	//
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println(jsonToMap(""));
		
	}
	public static String objctToJson(Object obj) {
		ObjectMapper mapper = new ObjectMapper();
		String result="";
		try {
			result = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			result="";
		}
		return result;
	}
	public static Map<String,Object> jsonToMap(Object s){
		Map<String,Object> map = new HashMap<String,Object>();
		ObjectMapper mapper = new ObjectMapper();
		try {
			if(s!=null){
				map = mapper.readValue(s.toString(), Map.class);
			}
		} catch (JsonParseException e) {
//			e.printStackTrace();
		} catch (JsonMappingException e) {
//			e.printStackTrace();
		} catch (IOException e) {
//			e.printStackTrace();
		}
		return map;
	}
}
