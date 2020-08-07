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
@RequestMapping("/bookMark")
public class BookMarkController {
//	
//	@ApiOperation(value = "获取收藏夹列表", notes = "获取收藏夹列表")
//	@RequestMapping(value = "/090001", method = RequestMethod.POST)
//	public ResponseBoxDto getweChatFavorites(HttpServletRequest request,HttpSession session) throws IOException {
//		JSONObject json = new JSONObject();
//		Map<Object, Object> map =new HashMap<Object, Object>();
//		map.put("wId",session.getAttribute("wId"));//微信实例ID
//		map.put("keyBuf",session.getAttribute("wId"));//第一次传null,如果接口返回keyBuf第二次传keyBuf byte类型存储
//		JsonConfig jsonConfig = new JsonConfig();
//		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
//		JSONObject resultJson =WKConfig.doPost("/weChatFavorites/favSync",json,request,WKConfig.Authorization);
//		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
//		System.out.println(resultJson1.get("Authorization"));
//		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
//		return result;
//	}
//	
//	@ApiOperation(value = "获取收藏夹列表", notes = "获取收藏夹列表")
//	@RequestMapping(value = "/090002", method = RequestMethod.POST)
//	public ResponseBoxDto getFavItem(HttpServletRequest request,HttpSession session) throws IOException {
//		JSONObject json = new JSONObject();
//		Map<Object, Object> map =new HashMap<Object, Object>();
//		map.put("wId",session.getAttribute("wId"));//微信实例ID
//		map.put("favId",session.getAttribute("wId"));//收藏标识
//		JsonConfig jsonConfig = new JsonConfig();
//		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
//		JSONObject resultJson =WKConfig.doPost("/weChatFavorites/getFavItem",json,request,WKConfig.Authorization);
//		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
//		System.out.println(resultJson1.get("Authorization"));
//		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
//		return result;
//	}
//
//	@ApiOperation(value = "删除收藏夹内容", notes = "删除收藏夹内容")
//	@RequestMapping(value = "/090003", method = RequestMethod.POST)
//	public ResponseBoxDto delFavItem(HttpServletRequest request,HttpSession session) throws IOException {
//		JSONObject json = new JSONObject();
//		Map<Object, Object> map =new HashMap<Object, Object>();
//		map.put("wId",session.getAttribute("wId"));//微信实例ID
//		map.put("favId",session.getAttribute("wId"));//收藏标识
//		JsonConfig jsonConfig = new JsonConfig();
//		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
//		JSONObject resultJson =WKConfig.doPost("/weChatFavorites/delFavItem",json,request,WKConfig.Authorization);
//		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
//		System.out.println(resultJson1.get("Authorization"));
//		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
//		return result;
//	}

}
