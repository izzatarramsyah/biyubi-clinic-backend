package com.clinic.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Util {
	
	public static Map<String, Object> setMapResponse(String message, boolean status){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", message);
		map.put("status", status);
		return map;
	}
	
	public static Map<String, Object> setMapResponse(String message, boolean status, Object obj){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", message);
		map.put("status", status);
		map.put("object", obj);
		return map;
	}
	
	public static String formatDateWithTime(Date date){
		SimpleDateFormat formatDate = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		return formatDate.format(date);
	}
	
	public static String formatDate(Date date){
		SimpleDateFormat formatDate = new SimpleDateFormat("dd-MMM-yyyy");
		return formatDate.format(date);
	}
	
	public static Date formatDate(String str) throws Exception{
		Date dateStr = new SimpleDateFormat("dd-MM-yyyy").parse(str); 
		return dateStr;
	}

}
