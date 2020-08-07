package com.rk.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rk.enums.ENUM_EXCEPTION_TYPE;
import com.rk.report.ResponseBoxDto;
import com.rk.util.WKConfig;

import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@RestController
@RequestMapping("/label")
public class LabelController {
	
//	@ApiOperation(value = "添加标签", notes = "添加标签")
//	@RequestMapping(value = "/080001", method = RequestMethod.POST)
//	public ResponseBoxDto addContactLabel(HttpServletRequest request,HttpSession session) throws IOException {
//		JSONObject json = new JSONObject();
//		Map<Object, Object> map =new HashMap<Object, Object>();
//		map.put("wId",session.getAttribute("wId"));//微信实例ID
//		map.put("labelName",session.getAttribute("wId"));//标签名称
//		JsonConfig jsonConfig = new JsonConfig();
//		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
//		JSONObject resultJson =WKConfig.doPost("/addContactLabel",json,request,WKConfig.Authorization);
//		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
//		System.out.println(resultJson1.get("Authorization"));
//		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
//		return result;
//	}
//	
//	@ApiOperation(value = "修改标签", notes = "修改标签")
//	@RequestMapping(value = "/080002", method = RequestMethod.POST)
//	public ResponseBoxDto updateModifyContactLabel(HttpServletRequest request,HttpSession session) throws IOException {
//		JSONObject json = new JSONObject();
//		Map<Object, Object> map =new HashMap<Object, Object>();
//		map.put("wId",session.getAttribute("wId"));//微信实例ID
//		map.put("wcId",session.getAttribute("wId"));//好友微信号
//		map.put("labelIdList",session.getAttribute("wId"));//标签标识，多个标签已 "，" 号分割
//		JsonConfig jsonConfig = new JsonConfig();
//		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
//		JSONObject resultJson =WKConfig.doPost("/modifyContactLabel",json,request,WKConfig.Authorization);
//		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
//		System.out.println(resultJson1.get("Authorization"));
//		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
//		return result;
//	}
//
//	@ApiOperation(value = "删除标签", notes = "删除标签")
//	@RequestMapping(value = "/080003", method = RequestMethod.POST)
//	public ResponseBoxDto delContactLabel(HttpServletRequest request,HttpSession session) throws IOException {
//		JSONObject json = new JSONObject();
//		Map<Object, Object> map =new HashMap<Object, Object>();
//		map.put("wId",session.getAttribute("wId"));//微信实例ID
//		map.put("labelIdList",session.getAttribute("wId"));//标签标识，多个标签已 "，" 号分割
//		JsonConfig jsonConfig = new JsonConfig();
//		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
//		JSONObject resultJson =WKConfig.doPost("/delContactLabel",json,request,WKConfig.Authorization);
//		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
//		System.out.println(resultJson1.get("Authorization"));
//		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
//		return result;
//	}
//	
//	@ApiOperation(value = "获取标签列表", notes = "获取标签列表")
//	@RequestMapping(value = "/080004", method = RequestMethod.POST)
//	public ResponseBoxDto getContactLabelList(HttpServletRequest request,HttpSession session) throws IOException {
//		JSONObject json = new JSONObject();
//		Map<Object, Object> map =new HashMap<Object, Object>();
//		map.put("wId",session.getAttribute("wId"));//微信实例ID
//		JsonConfig jsonConfig = new JsonConfig();
//		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
//		JSONObject resultJson =WKConfig.doPost("/getContactLabelList",json,request,WKConfig.Authorization);
//		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
//		System.out.println(resultJson1.get("Authorization"));
//		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
//		return result;
//	}
//	
}
