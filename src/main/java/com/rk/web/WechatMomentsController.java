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
@RequestMapping("/weChatMoments")
public class WechatMomentsController {
	
//	@ApiOperation(value = "朋友圈点赞", notes = "朋友圈点赞")
//	@RequestMapping(value = "/070001", method = RequestMethod.POST)
//	public ResponseBoxDto getSnsPraise(HttpServletRequest request,HttpSession session) throws IOException {
//		JSONObject json = new JSONObject();
//		Map<Object, Object> map =new HashMap<Object, Object>();
//		map.put("wId",session.getAttribute("wId"));//微信实例ID
//		map.put("id",session.getAttribute("wId"));//朋友圈id
//		JsonConfig jsonConfig = new JsonConfig();
//		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
//		JSONObject resultJson =WKConfig.doPost("/snsPraise",json,request,WKConfig.Authorization);
//		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
//		System.out.println(resultJson1.get("Authorization"));
//		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
//		return result;
//	}
//	
//	@ApiOperation(value = "取消点赞", notes = "取消点赞")
//	@RequestMapping(value = "/070002", method = RequestMethod.POST)
//	public ResponseBoxDto delSnsCancelPraise(HttpServletRequest request,HttpSession session) throws IOException {
//		JSONObject json = new JSONObject();
//		Map<Object, Object> map =new HashMap<Object, Object>();
//		map.put("wId",session.getAttribute("wId"));//微信实例ID
//		map.put("id",session.getAttribute("wId"));//朋友圈id
//		JsonConfig jsonConfig = new JsonConfig();
//		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
//		JSONObject resultJson =WKConfig.doPost("/snsCancelPraise",json,request,WKConfig.Authorization);
//		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
//		System.out.println(resultJson1.get("Authorization"));
//		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
//		return result;
//	}
//    
//	@ApiOperation(value = "朋友圈评论", notes = "朋友圈评论")
//	@RequestMapping(value = "/070003", method = RequestMethod.POST)
//	public ResponseBoxDto addSnsComment(HttpServletRequest request,HttpSession session) throws IOException {
//		JSONObject json = new JSONObject();
//		Map<Object, Object> map =new HashMap<Object, Object>();
//		map.put("wId",session.getAttribute("wId"));//微信实例ID
//		map.put("id",session.getAttribute("wId"));//朋友圈id
//		map.put("replyCommentId",session.getAttribute("wId"));//评论标识
//		map.put("content",session.getAttribute("wId"));//内容
//		JsonConfig jsonConfig = new JsonConfig();
//		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
//		JSONObject resultJson =WKConfig.doPost("/snsComment",json,request,WKConfig.Authorization);
//		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
//		System.out.println(resultJson1.get("Authorization"));
//		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
//		return result;
//	}
//	
//	@ApiOperation(value = "删除朋友圈评论", notes = "删除朋友圈评论")
//	@RequestMapping(value = "/070004", method = RequestMethod.POST)
//	public ResponseBoxDto delSnsCommentDel(HttpServletRequest request,HttpSession session) throws IOException {
//		JSONObject json = new JSONObject();
//		Map<Object, Object> map =new HashMap<Object, Object>();
//		map.put("wId",session.getAttribute("wId"));//微信实例ID
//		map.put("id",session.getAttribute("wId"));//朋友圈id
//		map.put("commentId",session.getAttribute("wId"));//评论标识
//		JsonConfig jsonConfig = new JsonConfig();
//		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
//		JSONObject resultJson =WKConfig.doPost("/snsCommentDel",json,request,WKConfig.Authorization);
//		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
//		System.out.println(resultJson1.get("Authorization"));
//		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
//		return result;
//	}
//	
//	@ApiOperation(value = "获取自己朋友圈", notes = "获取自己朋友圈")
//	@RequestMapping(value = "/070005", method = RequestMethod.POST)
//	public ResponseBoxDto getCircle(HttpServletRequest request,HttpSession session) throws IOException {
//		JSONObject json = new JSONObject();
//		Map<Object, Object> map =new HashMap<Object, Object>();
//		map.put("wId",session.getAttribute("wId"));//微信实例ID
//		map.put("firstPageMd5",session.getAttribute("wId"));//首次传:""，下次传返回的firstPageMd5
//		map.put("maxId",session.getAttribute("wId"));//获取首页的话：0
//		JsonConfig jsonConfig = new JsonConfig();
//		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
//		JSONObject resultJson =WKConfig.doPost("/getCircle",json,request,WKConfig.Authorization);
//		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
//		System.out.println(resultJson1.get("Authorization"));
//		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
//		return result;
//	}
//	
//	@ApiOperation(value = "获取好友朋友圈", notes = "获取好友朋友圈")
//	@RequestMapping(value = "/070006", method = RequestMethod.POST)
//	public ResponseBoxDto getFriendCircle(HttpServletRequest request,HttpSession session) throws IOException {
//		JSONObject json = new JSONObject();
//		Map<Object, Object> map =new HashMap<Object, Object>();
//		map.put("wId",session.getAttribute("wId"));//微信实例ID
//		map.put("wcId",session.getAttribute("wId"));//微信号
//		map.put("firstPageMd5",session.getAttribute("wId"));//首次传:""，下次传返回的firstPageMd5
//		map.put("maxId",session.getAttribute("wId"));//获取首页的话：0，第二次获取传列表中最后一项的id
//		JsonConfig jsonConfig = new JsonConfig();
//		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
//		JSONObject resultJson =WKConfig.doPost("/getFriendCircle",json,request,WKConfig.Authorization);
//		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
//		System.out.println(resultJson1.get("Authorization"));
//		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
//		return result;
//	}
//	
//	@ApiOperation(value = "发送文字朋友圈消息", notes = "发送文字朋友圈消息")
//	@RequestMapping(value = "/070007", method = RequestMethod.POST)
//	public ResponseBoxDto addSnsSend(HttpServletRequest request,HttpSession session) throws IOException {
//		JSONObject json = new JSONObject();
//		Map<Object, Object> map =new HashMap<Object, Object>();
//		map.put("wId",session.getAttribute("wId"));//微信实例ID
//		map.put("content",session.getAttribute("wId"));//文本内容
//		map.put("groupUser",session.getAttribute("wId"));//对谁可见（传微信号,多个用;分隔）
//		map.put("blackList",session.getAttribute("wId"));//对谁不可见（传微信号,多个用;分隔）
//		JsonConfig jsonConfig = new JsonConfig();
//		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
//		JSONObject resultJson =WKConfig.doPost("/snsSend",json,request,WKConfig.Authorization);
//		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
//		System.out.println(resultJson1.get("Authorization"));
//		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
//		return result;
//	}
//	
//	@ApiOperation(value = "发送链接朋友圈消息", notes = "发送链接朋友圈消息")
//	@RequestMapping(value = "/070008", method = RequestMethod.POST)
//	public ResponseBoxDto addSnsSendUrl(HttpServletRequest request,HttpSession session) throws IOException {
//		JSONObject json = new JSONObject();
//		Map<Object, Object> map =new HashMap<Object, Object>();
//		map.put("wId",session.getAttribute("wId"));//微信实例ID
//		map.put("content",session.getAttribute("wId"));//文本内容
//		map.put("title",session.getAttribute("wId"));//标题
//		map.put("description",session.getAttribute("wId"));
//		map.put("url",session.getAttribute("wId"));
//		map.put("thumbUrl",session.getAttribute("wId"));
//		map.put("groupUser",session.getAttribute("wId"));//对谁可见（传微信号,多个用;分隔）
//		map.put("blackList",session.getAttribute("wId"));//对谁不可见（传微信号,多个用;分隔）
//		JsonConfig jsonConfig = new JsonConfig();
//		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
//		JSONObject resultJson =WKConfig.doPost("/snsSendUrl",json,request,WKConfig.Authorization);
//		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
//		System.out.println(resultJson1.get("Authorization"));
//		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
//		return result;
//	}
//	
//	@ApiOperation(value = "发送图片朋友圈消息", notes = "发送图片朋友圈消息")
//	@RequestMapping(value = "/070009", method = RequestMethod.POST)
//	public ResponseBoxDto addSnsSendImage(HttpServletRequest request,HttpSession session) throws IOException {
//		JSONObject json = new JSONObject();
//		Map<Object, Object> map =new HashMap<Object, Object>();
//		map.put("wId",session.getAttribute("wId"));//微信实例ID
//		map.put("content",session.getAttribute("wId"));//文本内容
//		map.put("paths",session.getAttribute("wId"));//图片url(多个用;分隔)
//		map.put("groupUser",session.getAttribute("wId"));//对谁可见（传微信号,多个用;分隔）
//		map.put("blackList",session.getAttribute("wId"));//对谁不可见（传微信号,多个用;分隔）
//		JsonConfig jsonConfig = new JsonConfig();
//		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
//		JSONObject resultJson =WKConfig.doPost("/snsSendImage",json,request,WKConfig.Authorization);
//		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
//		System.out.println(resultJson1.get("Authorization"));
//		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
//		return result;
//	}
//	
//	@ApiOperation(value = "发送视频朋友圈消息", notes = "发送视频朋友圈消息")
//	@RequestMapping(value = "/070010", method = RequestMethod.POST)
//	public ResponseBoxDto addSsnsSendVideo(HttpServletRequest request,HttpSession session) throws IOException {
//		JSONObject json = new JSONObject();
//		Map<Object, Object> map =new HashMap<Object, Object>();
//		map.put("wId",session.getAttribute("wId"));//微信实例ID
//		map.put("content",session.getAttribute("wId"));//文本内容
//		map.put("videoPath",session.getAttribute("wId"));//视频链接URL
//		map.put("thumbPath",session.getAttribute("wId"));//视频封面URL
//		map.put("groupUser",session.getAttribute("wId"));//对谁可见（传微信号,多个用;分隔）
//		map.put("blackList",session.getAttribute("wId"));//对谁不可见（传微信号,多个用;分隔）
//		JsonConfig jsonConfig = new JsonConfig();
//		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
//		JSONObject resultJson =WKConfig.doPost("/snsSendVideo",json,request,WKConfig.Authorization);
//		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
//		System.out.println(resultJson1.get("Authorization"));
//		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
//		return result;
//	}
//	
//	@ApiOperation(value = "获取某条朋友圈消息", notes = "获取某条朋友圈消息")
//	@RequestMapping(value = "/070011", method = RequestMethod.POST)
//	public ResponseBoxDto getSnsObject(HttpServletRequest request,HttpSession session) throws IOException {
//		JSONObject json = new JSONObject();
//		Map<Object, Object> map =new HashMap<Object, Object>();
//		map.put("wId",session.getAttribute("wId"));//微信实例ID
//		map.put("wcId",session.getAttribute("wId"));//好友微信号
//		map.put("id",session.getAttribute("wId"));//朋友圈标识
//		JsonConfig jsonConfig = new JsonConfig();
//		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
//		JSONObject resultJson =WKConfig.doPost("/getSnsObject",json,request,WKConfig.Authorization);
//		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
//		System.out.println(resultJson1.get("Authorization"));
//		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
//		return result;
//	}
//	
//	@ApiOperation(value = "转发朋友圈", notes = "转发朋友圈")
//	@RequestMapping(value = "/070012", method = RequestMethod.POST)
//	public ResponseBoxDto addForwardSns(HttpServletRequest request,HttpSession session) throws IOException {
//		JSONObject json = new JSONObject();
//		Map<Object, Object> map =new HashMap<Object, Object>();
//		map.put("wId",session.getAttribute("wId"));//微信实例ID
//		map.put("content",session.getAttribute("wId"));//收到的xml
//		map.put("blackList",session.getAttribute("wId"));//对谁不可见
//		map.put("withUserList",session.getAttribute("wId"));//对谁可见
//		JsonConfig jsonConfig = new JsonConfig();
//		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
//		JSONObject resultJson =WKConfig.doPost("/forwardSns",json,request,WKConfig.Authorization);
//		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
//		System.out.println(resultJson1.get("Authorization"));
//		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
//		return result;
//	}
//	
//	@ApiOperation(value = "删除朋友圈", notes = "删除朋友圈")
//	@RequestMapping(value = "/070013", method = RequestMethod.POST)
//	public ResponseBoxDto delDeleteSns(HttpServletRequest request,HttpSession session) throws IOException {
//		JSONObject json = new JSONObject();
//		Map<Object, Object> map =new HashMap<Object, Object>();
//		map.put("wId",session.getAttribute("wId"));//微信实例ID
//		map.put("id",session.getAttribute("wId"));//微信号
//		JsonConfig jsonConfig = new JsonConfig();
//		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
//		JSONObject resultJson =WKConfig.doPost("/deleteSns",json,request,WKConfig.Authorization);
//		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
//		System.out.println(resultJson1.get("Authorization"));
//		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
//		return result;
//	}
//	
//	@ApiOperation(value = "设置朋友圈隐藏", notes = "设置朋友圈权限")
//	@RequestMapping(value = "/070014", method = RequestMethod.POST)
//	public ResponseBoxDto setSnsSetAsPrivacy(HttpServletRequest request,HttpSession session) throws IOException {
//		JSONObject json = new JSONObject();
//		Map<Object, Object> map =new HashMap<Object, Object>();
//		map.put("wId",session.getAttribute("wId"));//微信实例ID
//		map.put("id",session.getAttribute("wId"));//微信号
//		JsonConfig jsonConfig = new JsonConfig();
//		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
//		JSONObject resultJson =WKConfig.doPost("/snsSetAsPrivacy",json,request,WKConfig.Authorization);
//		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
//		System.out.println(resultJson1.get("Authorization"));
//		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
//		return result;
//	}
//	
//	@ApiOperation(value = "设置朋友圈公开", notes = "设置朋友圈公开")
//	@RequestMapping(value = "/070015", method = RequestMethod.POST)
//	public ResponseBoxDto setSnsSetPublic(HttpServletRequest request,HttpSession session) throws IOException {
//		JSONObject json = new JSONObject();
//		Map<Object, Object> map =new HashMap<Object, Object>();
//		map.put("wId",session.getAttribute("wId"));//微信实例ID
//		map.put("id",session.getAttribute("wId"));//微信号
//		JsonConfig jsonConfig = new JsonConfig();
//		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
//		JSONObject resultJson =WKConfig.doPost("/snsSetPublic",json,request,WKConfig.Authorization);
//		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
//		System.out.println(resultJson1.get("Authorization"));
//		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
//		return result;
//	}
}
