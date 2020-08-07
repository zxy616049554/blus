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
@RequestMapping("/accountManagement")
public class AccountManagementController {
	
//	@ApiOperation(value = "批量下线微信号", notes = "批量下线微信号")
//	@RequestMapping(value = "/100001", method = RequestMethod.POST)
//	public ResponseBoxDto delOffline(HttpServletRequest request,HttpSession session) throws IOException {
//		JSONObject json = new JSONObject();
//		Map<Object, Object> map =new HashMap<Object, Object>();
//		map.put("account",session.getAttribute("wId"));//账号
//		map.put("wcIds",session.getAttribute("wId"));//须下线的微信号
//		JsonConfig jsonConfig = new JsonConfig();
//		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
//		JSONObject resultJson =WKConfig.doPost("/member/offline",json,request,WKConfig.Authorization);
//		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
//		System.out.println(resultJson1.get("Authorization"));
//		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
//		return result;
//	}
//	
//	@ApiOperation(value = "微控升级专用接口", notes = "微控升级专用接口")
//	@RequestMapping(value = "/100002", method = RequestMethod.POST)
//	public ResponseBoxDto updateReconnectCallBack(HttpServletRequest request,HttpSession session) throws IOException {
//		JSONObject json = new JSONObject();
//		Map<Object, Object> map =new HashMap<Object, Object>();
//		map.put("wId",session.getAttribute("wId"));//微信实例ID
//		JsonConfig jsonConfig = new JsonConfig();
//		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
//		JSONObject resultJson =WKConfig.doPost("/reconnectCallBack",json,request,WKConfig.Authorization);
//		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
//		System.out.println(resultJson1.get("Authorization"));
//		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
//		return result;
//	}//此接口应用场景是微控服务升级导致用户需要重新登录，使用此接口则避免用户重新扫码，在新地址用此接口哦
//
//	@ApiOperation(value = "查询微信是否在线", notes = "查询微信是否在线")
//	@RequestMapping(value = "/100003", method = RequestMethod.POST)
//	public ResponseBoxDto getIsOnline(HttpServletRequest request,HttpSession session) throws IOException {
//		JSONObject json = new JSONObject();
//		Map<Object, Object> map =new HashMap<Object, Object>();
//		map.put("wId",session.getAttribute("wId"));//微信实例ID
//		JsonConfig jsonConfig = new JsonConfig();
//		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
//		JSONObject resultJson =WKConfig.doPost("/isOnline",json,request,WKConfig.Authorization);
//		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
//		System.out.println(resultJson1.get("Authorization"));
//		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
//		return result;
//	}
//	
//	@ApiOperation(value = "退出微控平台", notes = "退出微控平台")
//	@RequestMapping(value = "/100004", method = RequestMethod.POST)
//	public ResponseBoxDto delLogoutWK(HttpServletRequest request,HttpSession session) throws IOException {
//		JSONObject json = new JSONObject();
//		Map<Object, Object> map =new HashMap<Object, Object>();
//		JsonConfig jsonConfig = new JsonConfig();
//		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
//		JSONObject resultJson =WKConfig.doPost("/member/logout",json,request,WKConfig.Authorization);
//		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
//		System.out.println(resultJson1.get("Authorization"));
//		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
//		return result;
//	}

}
