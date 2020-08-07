package com.rk.util;

import net.sf.json.JSONObject;

public class JsonUtil {
	public static String getWid(String wId) {
		JSONObject jsonWid = JSONObject.fromObject(wId);  
		JSONObject requestBody = (JSONObject) jsonWid.get("requestBody");
		String requestBodyWid = (String) requestBody.get("wId");
		return requestBodyWid;
	}
		
	public static String getRoomId(String roomId) {
		JSONObject jsonWid = JSONObject.fromObject(roomId);  
		JSONObject requestBody = (JSONObject) jsonWid.get("requestBody");
		String requestBodyRoomid = (String) requestBody.get("roomId");
		return requestBodyRoomid;
	}
	
	
}
