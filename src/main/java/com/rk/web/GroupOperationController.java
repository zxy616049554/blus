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
@RequestMapping("/groupOperation")
public class GroupOperationController {
	
//	@ApiOperation(value = "群聊@", notes = "群聊@")
//	@RequestMapping(value = "/050001", method = RequestMethod.POST)
//	public ResponseBoxDto getsendText(HttpServletRequest request,HttpSession session) throws IOException {
//		JSONObject json = new JSONObject();
//		Map<Object, Object> map =new HashMap<Object, Object>();
//		map.put("wId",session.getAttribute("wId"));//微信实例ID
//		map.put("wcId",WKConfig.httpUrl);//群号
//		map.put("content",WKConfig.httpUrl);//文本内容消息
//		map.put("at",WKConfig.httpUrl);//艾特
//		JsonConfig jsonConfig = new JsonConfig();
//		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
//		JSONObject resultJson =WKConfig.doPost("/sendText",json,request,WKConfig.Authorization);
//		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
//		System.out.println(resultJson1.get("Authorization"));
//		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
//		return result;
//	}
//	
//	@ApiOperation(value = "修改群名", notes = "修改群名")
//	@RequestMapping(value = "/050002", method = RequestMethod.POST)
//	public ResponseBoxDto getmodifyGroupRemark(HttpServletRequest request,HttpSession session) throws IOException {
//		JSONObject json = new JSONObject();
//		Map<Object, Object> map =new HashMap<Object, Object>();
//		map.put("wId",session.getAttribute("wId"));//微信实例ID
//		map.put("chatRoomId",WKConfig.httpUrl);//群号
//		map.put("content",WKConfig.httpUrl);//群名
//		JsonConfig jsonConfig = new JsonConfig();
//		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
//		JSONObject resultJson =WKConfig.doPost("/modifyGroupRemark",json,request,WKConfig.Authorization);
//		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
//		System.out.println(resultJson1.get("Authorization"));
//		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
//		return result;
//	}
//	
//	@ApiOperation(value = "创建微信群", notes = "创建微信群")
//	@RequestMapping(value = "/050003", method = RequestMethod.POST)
//	public ResponseBoxDto createChatroom(HttpServletRequest request,HttpSession session) throws IOException {
//		JSONObject json = new JSONObject();
//		Map<Object, Object> map =new HashMap<Object, Object>();
//		map.put("wId",session.getAttribute("wId"));//微信实例ID
//		map.put("userList",WKConfig.httpUrl);//群成员微信号，多个已 "," 分割，（必须传输2个微信号以上才可创建群聊）
//		map.put("topic",WKConfig.httpUrl);//群名
//		JsonConfig jsonConfig = new JsonConfig();
//		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
//		JSONObject resultJson =WKConfig.doPost("/createChatroom",json,request,WKConfig.Authorization);
//		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
//		System.out.println(resultJson1.get("Authorization"));
//		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
//		return result;
//	}
//	
//	@ApiOperation(value = "退出微信群", notes = "退出微信群")
//	@RequestMapping(value = "/050004", method = RequestMethod.POST)
//	public ResponseBoxDto exitQuitChatRoom(HttpServletRequest request,HttpSession session) throws IOException {
//		JSONObject json = new JSONObject();
//		Map<Object, Object> map =new HashMap<Object, Object>();
//		map.put("wId",session.getAttribute("wId"));//微信实例ID
//		map.put("chatRoomId",WKConfig.httpUrl);//群id
//		JsonConfig jsonConfig = new JsonConfig();
//		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
//		JSONObject resultJson =WKConfig.doPost("/quitChatRoom",json,request,WKConfig.Authorization);
//		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
//		System.out.println(resultJson1.get("Authorization"));
//		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
//		return result;
//	}
//	
//	@ApiOperation(value = "添加群成员", notes = "添加群成员")
//	@RequestMapping(value = "/050005", method = RequestMethod.POST)
//	public ResponseBoxDto addChatRoomMember(HttpServletRequest request,HttpSession session) throws IOException {
//		JSONObject json = new JSONObject();
//		Map<Object, Object> map =new HashMap<Object, Object>();
//		map.put("wId",session.getAttribute("wId"));//微信实例ID
//		map.put("chatRoomId",WKConfig.httpUrl);//群id
//		map.put("userList",WKConfig.httpUrl);//群成员微信号，多个已 "," 分割，（必须传输2个微信号以上才可创建群聊）
//		JsonConfig jsonConfig = new JsonConfig();
//		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
//		JSONObject resultJson =WKConfig.doPost("/addChatRoomMember",json,request,WKConfig.Authorization);
//		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
//		System.out.println(resultJson1.get("Authorization"));
//		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
//		return result;
//	}
//	
//	@ApiOperation(value = "邀请群成员", notes = "邀请群成员")
//	@RequestMapping(value = "/050006", method = RequestMethod.POST)
//	public ResponseBoxDto addinviteChatRoomMember(HttpServletRequest request,HttpSession session) throws IOException {
//		JSONObject json = new JSONObject();
//		Map<Object, Object> map =new HashMap<Object, Object>();
//		map.put("wId",session.getAttribute("wId"));//微信实例ID
//		map.put("chatRoomId",WKConfig.httpUrl);//群id
//		map.put("userList",WKConfig.httpUrl);//群成员微信号，多个已 "," 分割，（必须传输2个微信号以上才可创建群聊）
//		JsonConfig jsonConfig = new JsonConfig();
//		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
//		JSONObject resultJson =WKConfig.doPost("/inviteChatRoomMember",json,request,WKConfig.Authorization);
//		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
//		System.out.println(resultJson1.get("Authorization"));
//		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
//		return result;
//	}
//	
//	@ApiOperation(value = "删除群成员", notes = "删除群成员")
//	@RequestMapping(value = "/050007", method = RequestMethod.POST)
//	public ResponseBoxDto deleteChatRoomMember(HttpServletRequest request,HttpSession session) throws IOException {
//		JSONObject json = new JSONObject();
//		Map<Object, Object> map =new HashMap<Object, Object>();
//		map.put("wId",session.getAttribute("wId"));//微信实例ID
//		map.put("chatRoomId",WKConfig.httpUrl);//群id
//		map.put("userList",WKConfig.httpUrl);//群成员微信号，多个已 "," 分割，（必须传输2个微信号以上才可创建群聊）
//		JsonConfig jsonConfig = new JsonConfig();
//		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
//		JSONObject resultJson =WKConfig.doPost("/deleteChatRoomMember",json,request,WKConfig.Authorization);
//		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
//		System.out.println(resultJson1.get("Authorization"));
//		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
//		return result;
//	}
//	
//	@ApiOperation(value = "设置群公告", notes = "设置群公告")
//	@RequestMapping(value = "/050008", method = RequestMethod.POST)
//	public ResponseBoxDto setChatRoomAnnouncement(HttpServletRequest request,HttpSession session) throws IOException {
//		JSONObject json = new JSONObject();
//		Map<Object, Object> map =new HashMap<Object, Object>();
//		map.put("wId",session.getAttribute("wId"));//微信实例ID
//		map.put("chatRoomId",WKConfig.httpUrl);//群id
//		map.put("content",WKConfig.httpUrl);//内容
//		JsonConfig jsonConfig = new JsonConfig();
//		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
//		JSONObject resultJson =WKConfig.doPost("/setChatRoomAnnouncement",json,request,WKConfig.Authorization);
//		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
//		System.out.println(resultJson1.get("Authorization"));
//		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
//		return result;
//	}
//	
//	@ApiOperation(value = "获取群二维码", notes = "获取群二维码")
//	@RequestMapping(value = "/050009", method = RequestMethod.POST)
//	public ResponseBoxDto getGroupQrCode(HttpServletRequest request,HttpSession session) throws IOException {
//		JSONObject json = new JSONObject();
//		Map<Object, Object> map =new HashMap<Object, Object>();
//		map.put("wId",session.getAttribute("wId"));//微信实例ID
//		map.put("chatRoomId",WKConfig.httpUrl);//群id
//		JsonConfig jsonConfig = new JsonConfig();
//		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
//		JSONObject resultJson =WKConfig.doPost("/getGroupQrCode",json,request,WKConfig.Authorization);
//		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
//		System.out.println(resultJson1.get("Authorization"));
//		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
//		return result;
//	}
//	
//	@ApiOperation(value = "群管理操作（群主）", notes = "群管理操作（群主）")
//	@RequestMapping(value = "/050010", method = RequestMethod.POST)
//	public ResponseBoxDto getoperateChatRoom(HttpServletRequest request,HttpSession session) throws IOException {
//		JSONObject json = new JSONObject();
//		Map<Object, Object> map =new HashMap<Object, Object>();
//		map.put("wId",session.getAttribute("wId"));//微信实例ID
//		map.put("chatRoomId",WKConfig.httpUrl);//群id
//		map.put("wcId",session.getAttribute("wId"));//群成员微信号，多个用 "," 分割
//		map.put("type",WKConfig.httpUrl);//1：添加群管理（可添加多个微信号） 2：删除群管理（可删除多个） 3：转让（只能转让一个微信号）
//		JsonConfig jsonConfig = new JsonConfig();
//		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
//		JSONObject resultJson =WKConfig.doPost("/operateChatRoom",json,request,WKConfig.Authorization);
//		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
//		System.out.println(resultJson1.get("Authorization"));
//		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
//		return result;
//	}
//	
//	@ApiOperation(value = "获取群成员列表", notes = "获取群成员列表")
//	@RequestMapping(value = "/050011", method = RequestMethod.POST)
//	public ResponseBoxDto getChatRoomMember(HttpServletRequest request,HttpSession session) throws IOException {
//		JSONObject json = new JSONObject();
//		Map<Object, Object> map =new HashMap<Object, Object>();
//		map.put("wId",session.getAttribute("wId"));//微信实例ID
//		map.put("chatRoomId",WKConfig.httpUrl);//群id
//		JsonConfig jsonConfig = new JsonConfig();
//		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
//		JSONObject resultJson =WKConfig.doPost("/getChatRoomMember",json,request,WKConfig.Authorization);
//		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
//		System.out.println(resultJson1.get("Authorization"));
//		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
//		return result;
//	}
//	
//	@ApiOperation(value = "获取群成员详情", notes = "获取群成员详情")
//	@RequestMapping(value = "/050012", method = RequestMethod.POST)
//	public ResponseBoxDto getChatRoomMemberInfo(HttpServletRequest request,HttpSession session) throws IOException {
//		JSONObject json = new JSONObject();
//		Map<Object, Object> map =new HashMap<Object, Object>();
//		map.put("wId",session.getAttribute("wId"));//微信实例ID
//		map.put("chatRoomId",WKConfig.httpUrl);//群id
//		map.put("userList", WKConfig.httpUrl);//群成员标识
//		JsonConfig jsonConfig = new JsonConfig();
//		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
//		JSONObject resultJson =WKConfig.doPost("/getChatRoomMemberInfo",json,request,WKConfig.Authorization);
//		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
//		System.out.println(resultJson1.get("Authorization"));
//		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
//		return result;
//	}
//	
//	@ApiOperation(value = "获取群详情信息", notes = "获取群详情信息")
//	@RequestMapping(value = "/050013", method = RequestMethod.POST)
//	public ResponseBoxDto getChatRoomInfo(HttpServletRequest request,HttpSession session) throws IOException {
//		JSONObject json = new JSONObject();
//		Map<Object, Object> map =new HashMap<Object, Object>();
//		map.put("wId",session.getAttribute("wId"));//微信实例ID
//		map.put("chatRoomId",WKConfig.httpUrl);//群id
//		JsonConfig jsonConfig = new JsonConfig();
//		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
//		JSONObject resultJson =WKConfig.doPost("/getChatRoomInfo",json,request,WKConfig.Authorization);
//		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
//		System.out.println(resultJson1.get("Authorization"));
//		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
//		return result;
//	}
//	
//	@ApiOperation(value = "修改我在本群的昵称", notes = "修改我在本群的昵称")
//	@RequestMapping(value = "/050014", method = RequestMethod.POST)
//	public ResponseBoxDto updateIInChatRoomNickName(HttpServletRequest request,HttpSession session) throws IOException {
//		JSONObject json = new JSONObject();
//		Map<Object, Object> map =new HashMap<Object, Object>();
//		map.put("wId",session.getAttribute("wId"));//微信实例ID
//		JsonConfig jsonConfig = new JsonConfig();
//		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
//		JSONObject resultJson =WKConfig.doPost("/updateIInChatRoomNickName",json,request,WKConfig.Authorization);
//		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
//		System.out.println(resultJson1.get("Authorization"));
//		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
//		return result;
//	}
//	
//	@ApiOperation(value = "群保存/取消到通讯录", notes = "群保存/取消到通讯录")
//	@RequestMapping(value = "/050015", method = RequestMethod.POST)
//	public ResponseBoxDto showInAddressBook(HttpServletRequest request,HttpSession session) throws IOException {
//		JSONObject json = new JSONObject();
//		Map<Object, Object> map =new HashMap<Object, Object>();
//		map.put("wId",session.getAttribute("wId"));//微信实例ID
//		map.put("chatRoomId",WKConfig.httpUrl);//群id
//		map.put("flag",WKConfig.httpUrl);//3：保存到群通讯录		2： 从通讯录移除群
//		JsonConfig jsonConfig = new JsonConfig();
//		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
//		JSONObject resultJson =WKConfig.doPost("/showInAddressBook",json,request,WKConfig.Authorization);
//		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
//		System.out.println(resultJson1.get("Authorization"));
//		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
//		return result;
//	}
//	
//	@ApiOperation(value = "自动同意入群邀请", notes = "自动同意入群邀请")
//	@RequestMapping(value = "/050016", method = RequestMethod.POST)
//	public ResponseBoxDto acceptUrl(HttpServletRequest request,HttpSession session) throws IOException {
//		JSONObject json = new JSONObject();
//		Map<Object, Object> map =new HashMap<Object, Object>();
//		map.put("wId",session.getAttribute("wId"));//微信实例ID
//		map.put("url",WKConfig.httpUrl);//原始 url，好友发送的入群邀请卡片信息链接(回调中取)
//		JsonConfig jsonConfig = new JsonConfig();
//		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
//		JSONObject resultJson =WKConfig.doPost("/acceptUrl",json,request,WKConfig.Authorization);
//		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
//		System.out.println(resultJson1.get("Authorization"));
//		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
//		return result;
//	}
	
}
