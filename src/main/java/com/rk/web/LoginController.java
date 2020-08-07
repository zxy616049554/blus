package com.rk.web;

import static org.hamcrest.CoreMatchers.containsString;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.json.XML;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;
import com.baidu.aip.util.Util;
import com.rk.dao.DjplayDao;
import com.rk.dto.ChatRoomMembersDto;
import com.rk.dto.FriendsDto;
import com.rk.dto.GroupDto;
import com.rk.dto.SendMessDto;
import com.rk.entity.ChatRoommembers;
import com.rk.entity.Constellationplay;
import com.rk.entity.Djplay;
import com.rk.entity.DownloadMess;
import com.rk.entity.MessReceiving;
import com.rk.entity.MessSend;
import com.rk.entity.ReplyMessage;
import com.rk.entity.Sum;
import com.rk.entity.WxFriends;
import com.rk.entity.WxGroup;
import com.rk.entity.WxPlatform;
import com.rk.entity.WxUser;
import com.rk.enums.ENUM_EXCEPTION_TYPE;
import com.rk.report.ResponseBoxDto;
import com.rk.service.ChatRoommembersService;
import com.rk.service.ConstellationplayService;
import com.rk.service.DjplayService;
import com.rk.service.DownloadMessService;
import com.rk.service.MessReceivingService;
import com.rk.service.MessSendService;
import com.rk.service.ReplyMessageService;
import com.rk.service.SumService;
import com.rk.service.WxFriendsService;
import com.rk.service.WxGroupService;
import com.rk.service.WxPlatformService;
import com.rk.service.WxUserService;
import com.rk.util.BaseUtil;
import com.rk.util.DateUtil;
import com.rk.util.JsonUtil;
import com.rk.util.MusicUtil;
import com.rk.util.ObjectUtil;
import com.rk.util.StringUtil;
import com.rk.util.WKConfig;
import com.rk.util.WeatherUtil;

import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.xml.XMLSerializer;

@RestController
@RequestMapping("/login")
public class LoginController {	
	static final Logger log = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private WxUserService wxUserService;
	@Autowired
	private WxGroupService wxGroupService;
	@Autowired(required = true)
	private WxFriendsService wxFriendsService;	
	@Autowired(required = true)
	private WxPlatformService wxPlatFormService;
	@Autowired
	private ChatRoommembersService chatRoommembersService;
	@Autowired
	private MessReceivingService messReceivingService;
	@Autowired
	private ReplyMessageService replyMessageService;
	@Autowired
	private MessSendService messSendService;
	@Autowired
	private DownloadMessService downloadMessService;
	@Autowired
	private SumService sumService;
	@Autowired
	private DjplayService djService;
	@Autowired
	private ConstellationplayService conService;
	WxUser wxUser=null;
	
	public String account;
	public String password;
	public int type=2;
	public String Authorization;
	public String httpUrl;
	private Logger logger = LoggerFactory.getLogger(LoginController.class);
	public List<WxPlatform> getPlatformk(){
	  List<WxPlatform> wxPlatform = wxPlatFormService.findAll();
	  account=wxPlatform.get(0).getPlatformAccount();
	  password=wxPlatform.get(0).getPlatformPassword();
	  Authorization=wxPlatform.get(0).getPlatformKey();
	  httpUrl = wxPlatform.get(0).getPlatformUrl();
	  return wxPlatform;
	}	
	
	
	@ApiOperation(value = "调用登录", notes = "调用登录")
	@RequestMapping(value = "/010001", method = RequestMethod.POST)
	public ResponseBoxDto getLogin(HttpServletRequest request) throws IOException {
		JSONObject json = new JSONObject();
		Map<Object, Object> map =new HashMap<Object, Object>();		
		map.put("account",account); // 开发者账号
		map.put("password",password); // 开发者密码
		JsonConfig jsonConfig = new JsonConfig();
		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
		JSONObject resultJson =WKConfig.doPost("member/login",json,request,null);
		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
		if(resultJson1.isEmpty()) {
			ResponseBoxDto result = ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.FAILURE);
			return result;
		}
		System.out.println(resultJson1.get("Authorization"));
		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
		return result;
	}
	
	@ApiOperation(value = "获取微信二维码", notes = "获取微信二维码")
	@RequestMapping(value = "/010002", method = RequestMethod.POST)
	public ResponseBoxDto getwxQrCode(HttpServletRequest request,HttpSession session) {
		JSONObject json = new JSONObject();
		getPlatformk();
		System.out.println(account);
		Map<Object, Object> map =new HashMap<Object, Object>();
		map.put("wcId",""); // 登录的微信id 第一次扫码不需要传，历史扫码必须要有
		map.put("type",type);//登录方式  默认传2
		JsonConfig jsonConfig = new JsonConfig();
		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
		JSONObject resultJson =WKConfig.doPost("/iPadLogin",json,request,Authorization);
		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
		if(ObjectUtil.isNullOrEmpty(resultJson1)) {
			ResponseBoxDto result = ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.FAILURE);
			return result;
		}
		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
		return result;
	}
	
	
	
	@ApiOperation(value = "执行微信登录", notes = "执行微信登录")
	@RequestMapping(value = "/010003", method = RequestMethod.POST)
	public ResponseBoxDto getwxLogin(HttpServletRequest request,HttpSession session,@RequestBody String wId) {
		getPlatformk();
		ResponseBoxDto result = null;
		try {		
			JSONObject json = new JSONObject();
			String requestBodyWid = JsonUtil.getWid(wId);
			Map<Object, Object> map =new HashMap<Object, Object>();
			map.put("wId",requestBodyWid);//微信实例ID
			JsonConfig jsonConfig = new JsonConfig();
			json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
			JSONObject resultJson =WKConfig.doPost("/getIPadLoginInfo",json,request,Authorization);
			JSONObject resultJson1 = (JSONObject) resultJson.get("data");
			if(resultJson1.isEmpty()) {
				result = ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.FAILURE);
				return result;
			}
			wxUser=wxUserService.getMessByWcId(String.valueOf(resultJson1.get("wcId")));
			if(!ObjectUtil.isNullOrEmpty(wxUser)) {// 修改
				wxUser.setUserwid(requestBodyWid);
				wxUser.setUserwcId(String.valueOf(resultJson1.get("wcId")));
				wxUserService.update(wxUser);	
			}else{//添加
				WxUser wx= new WxUser();
				wx.setUserwcId(String.valueOf(resultJson1.get("wcId")));
				wx.setUserwid(requestBodyWid);
				wx.setUserAccount(String.valueOf(resultJson1.get("wAccount")));
				wx.setUserCountry(String.valueOf(resultJson1.get("country")));
				wx.setUserCity(String.valueOf(resultJson1.get("city")));
				wx.setUserSignature(String.valueOf(resultJson1.get("signature")));
				wx.setUserNickName(String.valueOf(resultJson1.get("nickName")));
				String sex=String.valueOf(resultJson1.get("sex"));
				wx.setUserSex(Integer.parseInt(sex));
				wx.setUserHeadUrl(String.valueOf(resultJson1.get("headUrl")));
				wx.setSmallHeadImgUrl(String.valueOf(resultJson1.get("smallHeadImgUrl")));
				String status=String.valueOf(resultJson1.get("status"));
				wx.setStatus(Integer.parseInt(status));
				wxUserService.save(wx);
			}
			if(ObjectUtil.isNullOrEmpty(resultJson1)) {
				ResponseBoxDto re = ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.USER_LOGIN_TIMEOUT);
				return re;
			}else {
				result = ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
				return result;
			}
		} catch (Exception e) {
			ResponseBoxDto re=ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.USER_LOGIN_TIMEOUT);
			return re;
		}
	}
	
	@ApiOperation(value = "初始化好友列表", notes = "初始化好友列表")
	@RequestMapping(value = "/010004", method = RequestMethod.POST)
	public ResponseBoxDto getInitializeFriends(HttpServletRequest request,HttpSession session,@RequestBody String wId) {
		getPlatformk();
		JSONObject json = new JSONObject();
		String requestBodyWid = JsonUtil.getWid(wId);		
		Map<Object, Object> map =new HashMap<Object, Object>();
		map.put("wId",requestBodyWid);//微信实例ID
		JsonConfig jsonConfig = new JsonConfig();
		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
		JSONObject resultJson =WKConfig.doPost("/initializeFriends",json,request,Authorization);
		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
		return result;
	}
	
	@ApiOperation(value = "获取好友列表", notes = "获取好友列表")
	@RequestMapping(value = "/010005", method = RequestMethod.POST)
	public ResponseBoxDto getFriends(HttpServletRequest request,HttpSession session,@RequestBody String wId) {
		getPlatformk();
		JSONObject json = new JSONObject();
		String requestBodyWid = JsonUtil.getWid(wId);
		Map<Object, Object> map =new HashMap<Object, Object>();
		map.put("wId",requestBodyWid);//微信实例ID
		JsonConfig jsonConfig = new JsonConfig();
		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
		JSONObject resultJson =WKConfig.doPost("/getFriends",json,request,Authorization);
		List<String> friendsWxid = (List<String>) resultJson.get("data");//取出好友微信ID
		WxUser wxUser = wxUserService.getUserMessByWId(requestBodyWid);// 根据wid查询出wcid
		for (int i = 0; i < friendsWxid.size(); i++) {
			map.put("wcId",friendsWxid.get(i));//微信实例ID
			json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
			JSONObject resultJsonFriends =WKConfig.doPost("/getContact",json,request,Authorization);
			String friends =resultJsonFriends.get("data").toString();
			com.alibaba.fastjson.JSONArray arrayFriends =JSON.parseArray(friends);  
			for (int j = 0; j < arrayFriends.size(); j++) {
				System.out.println(arrayFriends.getJSONObject(j).get("nickName"));
				String wcid=String.valueOf(arrayFriends.getJSONObject(j).get("userName"));
				WxFriends wxFriends = wxFriendsService.getMessBywcId(wcid);
				if(ObjectUtil.isNullOrEmpty(wxFriends)) {
					WxFriends wx = new WxFriends();				
					wx.setUserwcId(wxUser.getUserwcId());
					wx.setFriendwcId(String.valueOf(arrayFriends.getJSONObject(j).get("userName")));
					String nickName=BaseUtil.encryptBASE64(String.valueOf(arrayFriends.getJSONObject(j).get("nickName")));
					wx.setFriendNickName(nickName);
					String remark=BaseUtil.encryptBASE64(String.valueOf(arrayFriends.getJSONObject(j).get("remark")));
		    		wx.setFriendRemark(remark);
		    		String signature=BaseUtil.encryptBASE64(String.valueOf(arrayFriends.getJSONObject(j).get("signature")));
		    		wx.setFriendSignature(signature);
		    		String sex=String.valueOf(arrayFriends.getJSONObject(j).get("sex"));
		    		wx.setFriendSex(Integer.parseInt(sex));
		    		wx.setAliasName(String.valueOf(arrayFriends.getJSONObject(j).get("aliasName")));
		    		wx.setCountry(String.valueOf(arrayFriends.getJSONObject(j).get("country")));
		    		wx.setBigHead(String.valueOf(arrayFriends.getJSONObject(j).get("bigHead")));
		    		wx.setSmallHead(String.valueOf(arrayFriends.getJSONObject(j).get("smallHead")));
		    		wx.setLabelList(String.valueOf(arrayFriends.getJSONObject(j).get("labelList")));
		    		wx.setV1(String.valueOf(arrayFriends.getJSONObject(j).get("v1")));
		    		wxFriendsService.save(wx);
				}else {
					break;
				}
			}
		} 
		List<WxFriends> friendsList=wxFriendsService.getFriendsMessByWcIds(wxUser.getUserwcId());
		for(int i = 0; i < friendsList.size(); i++) {
			friendsList.get(i).setFriendNickName(BaseUtil.decryptBASE64(friendsList.get(i).getFriendNickName()));
			friendsList.get(i).setFriendNickName(BaseUtil.decryptBASE64(friendsList.get(i).getFriendNickName()));
			friendsList.get(i).setFriendSignature(BaseUtil.decryptBASE64(friendsList.get(i).getFriendSignature()));
		}
		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(friendsList, ENUM_EXCEPTION_TYPE.SUCCESS);
		return result;
	}
	
	@ApiOperation(value = "初始化群列表", notes = "初始化群列表")
	@RequestMapping(value = "/010006", method = RequestMethod.POST)
	public ResponseBoxDto getInitChatRoom(HttpServletRequest request,HttpSession session,@RequestBody String wId) {
		getPlatformk();
		JSONObject json = new JSONObject();
		String requestBodyWid = JsonUtil.getWid(wId);
		Map<Object, Object> map =new HashMap<Object, Object>();
		map.put("wId",requestBodyWid);//微信实例ID
		JsonConfig jsonConfig = new JsonConfig();
		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
		JSONObject resultJson =WKConfig.doPost("/initChatRoom",json,request,Authorization);
		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
		return result;
	}
	
	@ApiOperation(value = "获取群列表", notes = "获取群列表")
	@RequestMapping(value = "/010007", method = RequestMethod.POST)
	public ResponseBoxDto getChatRoom(HttpServletRequest request,HttpSession session,@RequestBody String wId) {
		getPlatformk();
		JSONObject json = new JSONObject();
		String requestBodyWid = JsonUtil.getWid(wId);
		Map<Object, Object> map =new HashMap<Object, Object>();
		map.put("wId",requestBodyWid);//微信实例ID
		JsonConfig jsonConfig = new JsonConfig();
		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
		JSONObject resultJson =WKConfig.doPost("/getChatRooms",json,request,Authorization);
		List<String> groupsWxid = (List<String>) resultJson.get("data");//取出好友微信ID		
		WxUser wxUser = wxUserService.getUserMessByWId(requestBodyWid);// 根据wid查询出wcid
		for (int i = 0; i < groupsWxid.size(); i++) {
			map.put("chatRoomId",groupsWxid.get(i));//微信实例ID
			json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
			JSONObject resultJsonGroups =WKConfig.doPost("/getChatRoomInfo",json,request,Authorization);
			String group =resultJsonGroups.get("data").toString();
			com.alibaba.fastjson.JSONArray arrayGroup =JSON.parseArray(group);   
			for (int j = 0; j < arrayGroup.size(); j++) {
				String chatRoomId=String.valueOf(arrayGroup.getJSONObject(j).get("chatRoomId"));
				WxGroup wxGroups =  wxGroupService.getMessByRoomId(chatRoomId);
				if(ObjectUtil.isNullOrEmpty(wxGroups)) {
					WxGroup wxGroup= new WxGroup();			
					wxGroup.setUserwcId(wxUser.getUserwcId());
					wxGroup.setUsergroupId(String.valueOf(arrayGroup.getJSONObject(j).get("chatRoomId")));
					String nickName=BaseUtil.encryptBASE64(String.valueOf(arrayGroup.getJSONObject(j).get("nickName")));
					wxGroup.setGroupNickName(nickName);
					wxGroup.setChatRoomOwner(String.valueOf(arrayGroup.getJSONObject(j).get("chatRoomOwner")));
					wxGroup.setBigHeadImgUrl(String.valueOf(arrayGroup.getJSONObject(j).get("bigHeadImgUrl")));
					wxGroup.setSmallHeadImgUrl(String.valueOf(arrayGroup.getJSONObject(j).get("smallHeadImgUrl")));
					wxGroup.setV1(String.valueOf(arrayGroup.getJSONObject(j).get("v1")));
					wxGroup.setMemberCount(String.valueOf(arrayGroup.getJSONObject(j).get("memberCount")));
					wxGroupService.save(wxGroup);
				}else {
					break;
				}
			}
		}
		List<WxGroup> wxGroupList=wxGroupService.getGroupMessByWcId(wxUser.getUserwcId());
		for(int i = 0; i < wxGroupList.size(); i++) {
			wxGroupList.get(i).setGroupNickName(BaseUtil.decryptBASE64(wxGroupList.get(i).getGroupNickName()));
		}
		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(wxGroupList, ENUM_EXCEPTION_TYPE.SUCCESS);
		return result;
	}
	
	@ApiOperation(value = "二次登录", notes = "二次登录")
	@RequestMapping(value = "/010008", method = RequestMethod.POST)
	public ResponseBoxDto getsecondLogin(HttpServletRequest request,HttpSession session,@RequestBody String wId) {
		JSONObject json = new JSONObject();
		getPlatformk();
		String requestBodyWid = JsonUtil.getWid(wId);
		Map<Object, Object> map =new HashMap<Object, Object>();
		WxUser wxUser = wxUserService.getUserMessByWId(requestBodyWid);// 根据wid查询出wcid
		String wcId = wxUser.getUserwcId();// 获取出wid
		map.put("wcId",wcId);//微信号
		map.put("type",type);
		JsonConfig jsonConfig = new JsonConfig();
		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
		JSONObject resultJson =WKConfig.doPost("/secondLogin",json,request,Authorization);
		String secondLogin = resultJson.get("data").toString();
		JSONObject jsonTest = JSONObject.fromObject(secondLogin); 
		System.out.println(jsonTest.get("wId"));
		wxUser=wxUserService.getMessByWcId(wcId);
		if(!ObjectUtil.isNullOrEmpty(wxUser)) {// 修改
			wxUser.setUserwid(String.valueOf(jsonTest.get("wId")));
			wxUser.setUserwcId(wcId);
			wxUserService.update(wxUser);	
		}
		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(jsonTest, ENUM_EXCEPTION_TYPE.SUCCESS);
		return result;
	}
	
	@ApiOperation(value = "退出微信", notes = "退出微信")
	@RequestMapping(value = "/010009", method = RequestMethod.POST)
	public ResponseBoxDto getlogout(HttpServletRequest request,HttpSession session,@RequestBody String wId) {
		JSONObject json = new JSONObject();
		getPlatformk();
		String requestBodyWid = JsonUtil.getWid(wId);
		Map<Object, Object> map =new HashMap<Object, Object>();
		map.put("wId",requestBodyWid);//微信实例ID
		JsonConfig jsonConfig = new JsonConfig();
		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
		JSONObject resultJson =WKConfig.doPost("/logout",json,request,Authorization);
		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
		if(resultJson1.isEmpty()) {
			ResponseBoxDto result = ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.FAILURE);
			return result;
		}
		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
		return result;
	}
	

	
	@ApiOperation(value = "获取群成员列表", notes = "获取群成员列表")
	@RequestMapping(value = "/010010", method = RequestMethod.POST)
	public ResponseBoxDto getChatRoomMenbers(HttpServletRequest request,HttpSession session,@RequestBody ChatRoomMembersDto dto) {
		JSONObject json = new JSONObject();
		getPlatformk();
		//String requestBodyWid = JsonUtil.getWid(dto.getwId());
		//String requestBodyRoomid = JsonUtil.getRoomId(dto.getRoomId());
		Map<Object, Object> map =new HashMap<Object, Object>();
		map.put("wId",dto.getwId());//微信实例ID
		map.put("chatRoomId",dto.getRoomId());//群id
		JsonConfig jsonConfig = new JsonConfig();
		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
		JSONObject resultJson =WKConfig.doPost("/getChatRoomMember",json,request,Authorization);
		String chatRoomMembers = resultJson.get("data").toString();
		com.alibaba.fastjson.JSONArray arrayChatRoomMembers =JSON.parseArray(chatRoomMembers);
		for (int k = 0; k < arrayChatRoomMembers.size(); k++) {
			String userName=String.valueOf(arrayChatRoomMembers.getJSONObject(k).get("userName"));
			ChatRoommembers wxRoommembers=  chatRoommembersService.getMessChatRoomsByUserId(userName);
			if(ObjectUtil.isNullOrEmpty(wxRoommembers)) {
				ChatRoommembers chat=new ChatRoommembers();
				chat.setChatRoomId(dto.getRoomId());
				chat.setChatUserName(String.valueOf(arrayChatRoomMembers.getJSONObject(k).get("userName")));
				chat.setChatNickName(String.valueOf(arrayChatRoomMembers.getJSONObject(k).get("nickName")));
				chat.setDisplayName(String.valueOf(arrayChatRoomMembers.getJSONObject(k).get("displayName")));
				chat.setInviterUserName(String.valueOf(arrayChatRoomMembers.getJSONObject(k).get("inviterUserName")));	
				chat.setBigHeadImgUrl(String.valueOf(arrayChatRoomMembers.getJSONObject(k).get("bigHeadImgUrl")));
				chat.setSmallHeadImgUrl(String.valueOf(arrayChatRoomMembers.getJSONObject(k).get("smallHeadImgUrl")));	
				chatRoommembersService.save(chat);
			}
		}
		List<ChatRoommembers> chatRoomMembersList = chatRoommembersService.getMessChatRooms(dto.getRoomId());
		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(chatRoomMembersList, ENUM_EXCEPTION_TYPE.SUCCESS);
		return result;
	}
	
	@ApiOperation(value = "获取反馈消息", notes = "获取反馈消息")
	@RequestMapping(value = "/getMessage", method = RequestMethod.POST)
	public ResponseBoxDto getMessage(@RequestBody JSONObject json,HttpServletRequest request,HttpServletResponse response) throws Throwable {
		logger.debug("接收到数据"+json);
		getPlatformk();
		String wcId=json.get("wcId").toString();
		String account=json.get("account").toString();
		int messageType=Integer.parseInt(json.get("messageType").toString());
		JSONObject resultJson1 = (JSONObject) json.get("data");
		MessReceiving messReceiving=new MessReceiving();
		messReceiving.setMessWcId(wcId);
		messReceiving.setAccount(account);
		messReceiving.setMessageType(messageType);
		messReceiving.setDataFromUser(String.valueOf(resultJson1.get("fromUser")));
		messReceiving.setDataFromGroup(String.valueOf(resultJson1.get("fromGroup")));
		messReceiving.setDataToUser(String.valueOf(resultJson1.get("toUser")));
		messReceiving.setDataMsgId(String.valueOf(resultJson1.get("msgId")));
		messReceiving.setDataNewMsgId(String.valueOf(resultJson1.get("newMsgId")));
		messReceiving.setStandby1(String.valueOf(resultJson1.get("atuserlist")));
		String joinUserName = String.valueOf(resultJson1.get("joinUserName"));
		logger.debug("好友wxid"+joinUserName);
		joinUserName = joinUserName.replace("\u0000", "");
		joinUserName=joinUserName.replace("\u0012", "");
		joinUserName=joinUserName.replace("\u0019", "");
		joinUserName=joinUserName.replace("\u0013", "");
		joinUserName=joinUserName.replace("\n", "");
		System.out.println(joinUserName);
		Object type=resultJson1.get("msgType");
		if(!ObjectUtil.isNullOrEmpty(type)) {
			messReceiving.setDataMsgType((int)type);
		}
		messReceiving.setDataTimestamp(DateUtil.timeStamp2Date(String.valueOf(resultJson1.get("timestamp")), null));
		String content=String.valueOf(resultJson1.get("content"));
		messReceiving.setDataContent(content);
		String selfs=String.valueOf(resultJson1.get("self"));
		int self;
		if(selfs=="true") {
			self=1;
		}else{
			self=0;
		}
		messReceiving.setDataSelf(self);
		messReceivingService.save(messReceiving);
		System.out.println(resultJson1);
		if(messageType==5) {//如果消息类型为私聊文本消息
			List<ReplyMessage> lReplyMessages=replyMessageService.getMessByReceiveMess(content);
			ReplyMessage replyMessages=replyMessageService.getMessByReceive(content);
			if(!ObjectUtil.isNullOrEmpty(replyMessages)&&content.equals(replyMessages.getReceiveMess())) {
				Map<Object, Object> map =new HashMap<Object, Object>();
				map.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
				map.put("wcId",String.valueOf(resultJson1.get("fromUser")));//微信号/群号
				map.put("content",replyMessages.getReplyMess());//文本内容消息
				WKConfig.sendText(map,Authorization);
				MessSend messSend=new MessSend();
				messSend.setWId(String.valueOf(resultJson1.get("wId")));
				messSend.setWcId(String.valueOf(resultJson1.get("fromUser")));
				messSend.setContent(replyMessages.getReplyMess());
				messSend.setMessSendType(1);// 好友发的消息
				messSendService.save(messSend);
				ResponseBoxDto result = ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
				return result;
			}
			for (int i = 0; i < lReplyMessages.size(); i++) {
				if(!content.equals(lReplyMessages.get(i).getReceiveMess()))  {
					String messageSend = "";
					for (int j = 0; j < lReplyMessages.size(); j++) {
						messageSend = messageSend+lReplyMessages.get(j).getReceiveMess()+"\t";
					}
					Map<Object, Object> map =new HashMap<Object, Object>();
					map.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
					map.put("wcId",String.valueOf(resultJson1.get("fromUser")));//微信号/群号
					map.put("content","你需要哪个： "+messageSend);//文本内容消息
					WKConfig.sendText(map,Authorization);
					MessSend messSend=new MessSend();
					messSend.setWId(String.valueOf(resultJson1.get("wId")));
					messSend.setWcId(String.valueOf(resultJson1.get("fromUser")));
					messSend.setContent(lReplyMessages.get(i).getReplyMess());
					messSend.setMessSendType(1);// 好友发的消息
					messSendService.save(messSend);
					ResponseBoxDto result = ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
					return result;
				}
			}
			if(content.equals("天气")) {
				String cont="请发送天气XX或者XX天气。比如: [天气北京] 或者 [北京天气]";
				Map<Object, Object> map =new HashMap<Object, Object>();
				map.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
				map.put("wcId",String.valueOf(resultJson1.get("fromUser")));//微信号/群号
				map.put("content",cont);//文本内容消息
				WKConfig.sendText(map,Authorization);
				MessSend messSend=new MessSend();
				messSend.setWId(String.valueOf(resultJson1.get("wId")));
				messSend.setWcId(String.valueOf(resultJson1.get("fromUser")));
				messSend.setContent(cont);
				messSend.setMessSendType(1);// 好友发的消息
				messSendService.save(messSend);
				ResponseBoxDto result = ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
				return result;
			}else {
				if(content.substring(0, 2).equals("天气")) {
					String wea=content.substring(2, 4);
					List<String> listString=WeatherUtil.read1(wea);
					String replaceAll = listString.toString().replace("[", "").replace("]","");
					String weaReplaceAll=replaceAll.replace(",", "");
					Map<Object, Object> map =new HashMap<Object, Object>();
					map.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
					map.put("wcId",String.valueOf(resultJson1.get("fromUser")));//微信号/群号
					map.put("content",wea+"未来七天天气："+"\n\t"+weaReplaceAll);//文本内容消息
					WKConfig.sendText(map,Authorization);
					MessSend messSend=new MessSend();
					messSend.setWId(String.valueOf(resultJson1.get("wId")));
					messSend.setWcId(String.valueOf(resultJson1.get("fromUser")));
					messSend.setContent(listString.toString());
					messSend.setMessSendType(1);// 好友发的消息
					messSendService.save(messSend);
					ResponseBoxDto result = ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
					return result;
				}
				if(content.substring(2, 4).equals("天气")) {
					String wea=content.substring(0, 2);
					List<String> listString=WeatherUtil.read1(wea);
					String replaceAll = listString.toString().replace("[", "").replace("]","");	
					String weaReplaceAll=replaceAll.replace(",", "").trim();
					Map<Object, Object> map =new HashMap<Object, Object>();
					map.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
					map.put("wcId",String.valueOf(resultJson1.get("fromUser")));//微信号/群号
					map.put("content",wea+"未来七天天气："+"\n\t"+weaReplaceAll);//文本内容消息
					WKConfig.sendText(map,Authorization);
					MessSend messSend=new MessSend();
					messSend.setWId(String.valueOf(resultJson1.get("wId")));
					messSend.setWcId(String.valueOf(resultJson1.get("fromUser")));
					messSend.setContent(listString.toString());
					messSend.setMessSendType(1);// 好友发的消息
					messSendService.save(messSend);
					ResponseBoxDto result = ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
					return result;
				}
			}
			if(content.equals("斗图")) {
				String imgurl="http://121.196.190.126/shareVideo/admin-manage/images/2.gif";
				Map<Object, Object> map =new HashMap<Object, Object>();
				map.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
				map.put("wcId",String.valueOf(resultJson1.get("fromUser")));//微信号/群号
				map.put("content",imgurl);//文本内容消息
				WKConfig.sendImage(map,Authorization);
				MessSend messSend=new MessSend();
				messSend.setWId(String.valueOf(resultJson1.get("wId")));
				messSend.setWcId(String.valueOf(resultJson1.get("fromUser")));
				messSend.setContent(imgurl);
				messSend.setMessSendType(1);// 好友发的消息
				messSendService.save(messSend);
				ResponseBoxDto result = ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
				return result;
			}
			if(content.equals("点歌")) {
				String cont="发送点歌+歌名可点歌";
				Map<Object, Object> map =new HashMap<Object, Object>();
				map.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
				map.put("wcId",String.valueOf(resultJson1.get("fromUser")));//微信号/群号
				map.put("content",cont);//文本内容消息
				WKConfig.sendText(map,Authorization);
				MessSend messSend=new MessSend();
				messSend.setWId(String.valueOf(resultJson1.get("wId")));
				messSend.setWcId(String.valueOf(resultJson1.get("fromUser")));
				messSend.setContent(cont);
				messSend.setMessSendType(1);// 好友发的消息
				messSendService.save(messSend);
				ResponseBoxDto result = ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
				return result;
			}else {
				String musicName=content.substring(2);
				Map<Object, Object> map =new HashMap<Object, Object>();
				map.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
				map.put("wcId",String.valueOf(resultJson1.get("fromUser")));//微信号/群号
				map.put("title",musicName);//文本内容消息
				map.put("url","https://y.music.163.com/m/song?id="+getSeachMusic(null,musicName));//文本内容消息
				map.put("description","");//文本内容消息
				map.put("thumbUrl","http://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg");//文本内容消息
				WKConfig.sendUrl(map,Authorization);
				MessSend messSend=new MessSend();
				messSend.setWId(String.valueOf(resultJson1.get("wId")));
				messSend.setWcId(String.valueOf(resultJson1.get("fromUser")));
				messSend.setContent(musicName);
				messSend.setMessSendType(1);// 好友发的消息
				messSendService.save(messSend);
				ResponseBoxDto result = ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
				return result;
			}

		}
		if(messageType==9) {//如果消息类型为群聊文本消息
			List<ReplyMessage> lReplyMessages=replyMessageService.getMessByReceiveMess(content);
			ReplyMessage replyMessages=replyMessageService.getMessByReceive(content);
			if(!ObjectUtil.isNullOrEmpty(replyMessages)&& content.equals(replyMessages.getReceiveMess())) {
				Map<Object, Object> map =new HashMap<Object, Object>();
				map.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
				map.put("wcId",String.valueOf(resultJson1.get("fromGroup")));//微信号/群号
				map.put("content",replyMessages.getReplyMess());//文本内容消息
				WKConfig.sendText(map,Authorization);
				MessSend messSend=new MessSend();
				messSend.setWId(String.valueOf(resultJson1.get("wId")));
				messSend.setWcId(String.valueOf(resultJson1.get("fromGroup")));
				messSend.setContent(replyMessages.getReplyMess());
				messSend.setMessSendType(2);// 群发的消息
				messSendService.save(messSend);
				ResponseBoxDto result = ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
				return result;
			}
			for (int i = 0; i < lReplyMessages.size(); i++) {
				if(!content.equals(lReplyMessages.get(i).getReceiveMess()))  {
					String messageSend = "";
					for (int j = 0; j < lReplyMessages.size(); j++) {
						messageSend = messageSend+lReplyMessages.get(j).getReceiveMess()+"\t";
					}
					Map<Object, Object> map =new HashMap<Object, Object>();
					map.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
					map.put("wcId",String.valueOf(resultJson1.get("fromGroup")));//微信号/群号
					map.put("content","你需要哪个："+messageSend);//文本内容消息
					WKConfig.sendText(map,Authorization);
					MessSend messSend=new MessSend();
					messSend.setWId(String.valueOf(resultJson1.get("wId")));
					messSend.setWcId(String.valueOf(resultJson1.get("fromGroup")));
					messSend.setContent(lReplyMessages.get(i).getReplyMess());
					messSend.setMessSendType(2);// 群发的消息	
					messSendService.save(messSend);
					ResponseBoxDto result = ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
					return result;
				}
			}
			
			String atlist=String.valueOf(resultJson1.get("atlist"));
			String replaceAllUser=null;
			if(!atlist.equals("null")&&!StringUtil.isNullOrEmpty(atlist)) {//如果atlist不为空
				String atlistReplace = atlist.replaceAll("\\\"","");
				replaceAllUser = atlistReplace.replace("[", "").replace("]","");
			}else {
				String atuserList =String.valueOf(resultJson1.get("msgSource"));
				String atUser="<msgsource>\r\n" + 
						"<silence>1</silence>\r\n" + 
						"<membercount>11</membercount>\r\n" + 
						"</msgsource>";
				String atus="<msgsource>\r\n" + 
						"	<alnode>\r\n" + 
						"		<cf>1</cf>\r\n" + 
						"	</alnode>\r\n" + 
						"	<silence>1</silence>\r\n" + 
						"	<membercount>10</membercount>\r\n" + 
						"</msgsource>";
				int atuserListLength=atuserList.length();
				int atUserLength=atUser.length();
				int atusLength=atus.length();
				if(atuserListLength!=atusLength&&atuserListLength!=atUserLength&&!atuserList.equals("null")&&!StringUtil.isNullOrEmpty(atuserList)) {
					org.jsoup.nodes.Document doc = Jsoup.parse(atuserList.toString());//补全html
				    replaceAllUser = doc.select("atuserlist").first().text();
				}
			}	
			String user=String.valueOf(resultJson1.get("toUser"));
			String receMessString = null;
			if(replaceAllUser!=null) {
				ChatRoommembers chatRoom=  chatRoommembersService.getMessChatRoomsByUserId(replaceAllUser);
				String chatdisplayName=chatRoom.getDisplayName();
				String chatName = chatRoom.getChatNickName();
				if(!StringUtil.isNullOrEmpty(chatdisplayName)) {
					receMessString=content.substring(chatdisplayName.length()+2);
				}else {
					receMessString=content.substring(chatName.length()+2);
				}
			}
			if(content.substring(0,1).equals("@")&&replaceAllUser.equals(user)&&receMessString.equals("斗图")) {
				Random random = new Random();
				int num = random.nextInt(4);
				switch (num) {
				case 0:
					String imgurl="http://emoji.qpic.cn/wx_emoji/BicLgEUEEZL8AnSeOg2b0QcS3PADzcsmHoecGtTSicG0cTicU6oicfk8aj3sNTro5Gvt/";
					Map<Object, Object> map =new HashMap<Object, Object>();
					map.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
					map.put("wcId",String.valueOf(resultJson1.get("fromGroup")));//微信号/群号
					map.put("imageMd5","7393046cbb2f15c75c53954ef0cf0c2f");//文本内容消息
					map.put("imgSize","27541");//文本内容消息
					WKConfig.sendEmoji(map,Authorization);
					MessSend messSend=new MessSend();
					messSend.setWId(String.valueOf(resultJson1.get("wId")));
					messSend.setWcId(String.valueOf(resultJson1.get("fromGroup")));
					messSend.setContent(imgurl);
					messSend.setMessSendType(1);// 好友发的消息
					messSendService.save(messSend);
					return ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
				case 1:
					String imgurl1="http://emoji.qpic.cn/wx_emoji/KyDAZdltJyO47c4oUlnpPNUg50AwNc9r11x5HeuYdwxETiaEODASN1Z6FRac3NApy/";
					Map<Object, Object> map1 =new HashMap<Object, Object>();
					map1.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
					map1.put("wcId",String.valueOf(resultJson1.get("fromGroup")));//微信号/群号
					map1.put("imageMd5","3ad8555e89c45402a3ea32ce246f57ad");//文本内容消息
					map1.put("imgSize","2157");//文本内容消息
					WKConfig.sendEmoji(map1,Authorization);
					MessSend messSend1=new MessSend();
					messSend1.setWId(String.valueOf(resultJson1.get("wId")));
					messSend1.setWcId(String.valueOf(resultJson1.get("fromGroup")));
					messSend1.setContent(imgurl1);
					messSend1.setMessSendType(1);// 好友发的消息
					messSendService.save(messSend1);
					return ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
				case 2:
					String imgurl2="http://emoji.qpic.cn/wx_emoji/hw9EHP48GVsiakBtOtF1p8iczqvnAQ9usj67aRd0Ef7wWJoov54K1mHwk2Xciad4Upib/";
					Map<Object, Object> map2 =new HashMap<Object, Object>();
					map2.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
					map2.put("wcId",String.valueOf(resultJson1.get("fromGroup")));//微信号/群号
					map2.put("imageMd5","30874de068418b6b6c8c87f85bb99259");//文本内容消息
					map2.put("imgSize","622185");//文本内容消息
					WKConfig.sendEmoji(map2,Authorization);
					MessSend messSend2=new MessSend();
					messSend2.setWId(String.valueOf(resultJson1.get("wId")));
					messSend2.setWcId(String.valueOf(resultJson1.get("fromGroup")));
					messSend2.setContent(imgurl2);
					messSend2.setMessSendType(1);// 好友发的消息
					messSendService.save(messSend2);
					return ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
				case 3:
					String imgurl3="http://emoji.qpic.cn/wx_emoji/ic6R2U29TC2YQf14PKkiayYQ8OrbiciaX472O31zPrFY1ZM8r2vFYfLs37OnRLNLXIlU/";
					Map<Object, Object> map3 =new HashMap<Object, Object>();
					map3.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
					map3.put("wcId",String.valueOf(resultJson1.get("fromGroup")));//微信号/群号
					map3.put("imageMd5","7e9a22c62a44a257e13dd7b373d59fcc");//文本内容消息
					map3.put("imgSize","391110");//文本内容消息
					WKConfig.sendEmoji(map3,Authorization);
					MessSend messSend3=new MessSend();
					messSend3.setWId(String.valueOf(resultJson1.get("wId")));
					messSend3.setWcId(String.valueOf(resultJson1.get("fromGroup")));
					messSend3.setContent(imgurl3);
					messSend3.setMessSendType(1);// 好友发的消息
					messSendService.save(messSend3);
					return ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
				}
			}
			if(content.equals("点歌")||content.equals("听歌")) {//点歌
				String cont="发送点歌+歌名 或者听歌+歌名 可点歌";
				Map<Object, Object> map =new HashMap<Object, Object>();
				map.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
				map.put("wcId",String.valueOf(resultJson1.get("fromGroup")));//微信号/群号
				map.put("content",cont);//文本内容消息
				WKConfig.sendText(map,Authorization);
				MessSend messSend=new MessSend();
				messSend.setWId(String.valueOf(resultJson1.get("wId")));
				messSend.setWcId(String.valueOf(resultJson1.get("fromGroup")));
				messSend.setContent(cont);
				messSend.setMessSendType(2);// 好友发的消息
				messSendService.save(messSend);
				ResponseBoxDto result = ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
				return result;
			}else{
				if(content.substring(0,2).equals("点歌")||content.substring(0,2).equals("听歌")) {
					JSONObject jsonObject = new JSONObject();
					String musicName=content.substring(2);
					Map<Object, Object> map =new HashMap<Object, Object>();
					map.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
					map.put("wcId",String.valueOf(resultJson1.get("fromGroup")));//微信号/群号
					map.put("title",musicName);//文本内容消息
					map.put("url","https://y.music.163.com/m/song?id="+getSeachMusic(null,musicName));//文本内容消息
					map.put("description","");//文本内容消息
					map.put("thumbUrl","http://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg");//文本内容消息
					JsonConfig jsonConfig = new JsonConfig();
					jsonObject = JSONObject.fromObject(map, jsonConfig);// stringBuffer
					WKConfig.doPost("/sendUrl",jsonObject,request,Authorization);
					MessSend messSend=new MessSend();
					messSend.setWId(String.valueOf(resultJson1.get("wId")));
					messSend.setWcId(String.valueOf(resultJson1.get("fromGroup")));
					messSend.setContent(musicName);
					messSend.setMessSendType(2);// 好友发的消息
					messSendService.save(messSend);
					ResponseBoxDto result = ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
					return result;
				}
			}
			if(content.substring(0,1).equals("@")&&replaceAllUser.equals(user)&& receMessString.equals("星座")) {//语音加图
				Map<Object, Object> map =new HashMap<Object, Object>();
				map.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
				map.put("wcId",String.valueOf(resultJson1.get("fromGroup")));//微信号/群号
				map.put("content","请发送星座，例如金牛、白羊...");//文本内容消息
				WKConfig.sendText(map, Authorization);
				return ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
			}
			if(content.substring(0,1).equals("@")&&replaceAllUser.equals(user)) {//语音加图
				Constellationplay constellationplay = new Constellationplay();
				constellationplay.setChatRoomId(String.valueOf(resultJson1.get("fromGroup")));
				constellationplay.setChatUserWx(String.valueOf(resultJson1.get("fromUser")));
				constellationplay.setModifyDate(DateUtil.getCurrentStr());
				Constellationplay constellationplay2 = conService.getConsetUserRoomCount(constellationplay);
				if(constellationplay2!=null) {//如果存在
					if(constellationplay2.getCount()>=3) {//判断是当天并且次数超过三次
						return  ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.USER_DJ_OUTPLAY);
					}else {
						constellationplay2.setCount(constellationplay2.getCount()+1);
						conService.update(constellationplay2);
					}
				}else {//如果不存在
					constellationplay.setCount(1);
					conService.save(constellationplay);
				}
				if(receMessString.equals("白羊")||receMessString.equals("白羊座")) {
					JSONObject resultJson= WKConfig.doGet1("白羊", null);
					net.sf.json.JSONArray jsonArray = resultJson.getJSONArray("newslist");
					JSONObject  jsonObject  =  (JSONObject) jsonArray.get(0);
					String hMessage = jsonObject.get("title")+"\n"+jsonObject.get("grade")+"\n"+jsonObject.get("content");
					Map<Object, Object> map =new HashMap<Object, Object>();
					map.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
					map.put("wcId",String.valueOf(resultJson1.get("fromGroup")));//微信号/群号
					map.put("content",hMessage);//文本内容消息
					WKConfig.sendText(map, Authorization);
					return ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
				}else if(receMessString.equals("金牛座")||receMessString.equals("金牛")) {
					JSONObject resultJson= WKConfig.doGet1("金牛", null);
					net.sf.json.JSONArray jsonArray = resultJson.getJSONArray("newslist");
					JSONObject  jsonObject  =  (JSONObject) jsonArray.get(0);
					String hMessage = jsonObject.get("title")+"\n"+jsonObject.get("grade")+"\n"+jsonObject.get("content");
					Map<Object, Object> map =new HashMap<Object, Object>();
					map.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
					map.put("wcId",String.valueOf(resultJson1.get("fromGroup")));//微信号/群号
					map.put("content",hMessage);//文本内容消息
					WKConfig.sendText(map, Authorization);
					return ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
				}else if(receMessString.equals("双子座")||receMessString.equals("双子")) {
					JSONObject resultJson= WKConfig.doGet1("双子", null);
					net.sf.json.JSONArray jsonArray = resultJson.getJSONArray("newslist");
					JSONObject  jsonObject  =  (JSONObject) jsonArray.get(0);
					String hMessage = jsonObject.get("title")+"\n"+jsonObject.get("grade")+"\n"+jsonObject.get("content");
					Map<Object, Object> map =new HashMap<Object, Object>();
					map.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
					map.put("wcId",String.valueOf(resultJson1.get("fromGroup")));//微信号/群号
					map.put("content",hMessage);//文本内容消息
					WKConfig.sendText(map, Authorization);
					return ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
				}else if(receMessString.equals("巨蟹")||receMessString.equals("巨蟹座")) {
					JSONObject resultJson= WKConfig.doGet1("巨蟹", null);
					net.sf.json.JSONArray jsonArray = resultJson.getJSONArray("newslist");
					JSONObject  jsonObject  =  (JSONObject) jsonArray.get(0);
					String hMessage = jsonObject.get("title")+"\n"+jsonObject.get("grade")+"\n"+jsonObject.get("content");
					Map<Object, Object> map =new HashMap<Object, Object>();
					map.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
					map.put("wcId",String.valueOf(resultJson1.get("fromGroup")));//微信号/群号
					map.put("content",hMessage);//文本内容消息
					WKConfig.sendText(map, Authorization);
					return ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
				}else if(receMessString.equals("狮子座")||receMessString.equals("狮子")) {
					JSONObject resultJson= WKConfig.doGet1("狮子", null);
					net.sf.json.JSONArray jsonArray = resultJson.getJSONArray("newslist");
					JSONObject  jsonObject  =  (JSONObject) jsonArray.get(0);
					String hMessage = jsonObject.get("title")+"\n"+jsonObject.get("grade")+"\n"+jsonObject.get("content");
					Map<Object, Object> map =new HashMap<Object, Object>();
					map.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
					map.put("wcId",String.valueOf(resultJson1.get("fromGroup")));//微信号/群号
					map.put("content",hMessage);//文本内容消息
					WKConfig.sendText(map, Authorization);
					return ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
				}else if(receMessString.equals("处女")||receMessString.equals("处女座")) {
					JSONObject resultJson= WKConfig.doGet1("处女", null);
					net.sf.json.JSONArray jsonArray = resultJson.getJSONArray("newslist");
					JSONObject  jsonObject  =  (JSONObject) jsonArray.get(0);
					String hMessage = jsonObject.get("title")+"\n"+jsonObject.get("grade")+"\n"+jsonObject.get("content");
					Map<Object, Object> map =new HashMap<Object, Object>();
					map.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
					map.put("wcId",String.valueOf(resultJson1.get("fromGroup")));//微信号/群号
					map.put("content",hMessage);//文本内容消息
					WKConfig.sendText(map, Authorization);
					return ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
				}else if(receMessString.equals("天秤座")||receMessString.equals("天秤")) {
					JSONObject resultJson= WKConfig.doGet1("天秤", null);
					net.sf.json.JSONArray jsonArray = resultJson.getJSONArray("newslist");
					JSONObject  jsonObject  =  (JSONObject) jsonArray.get(0);
					String hMessage = jsonObject.get("title")+"\n"+jsonObject.get("grade")+"\n"+jsonObject.get("content");
					Map<Object, Object> map =new HashMap<Object, Object>();
					map.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
					map.put("wcId",String.valueOf(resultJson1.get("fromGroup")));//微信号/群号
					map.put("content",hMessage);//文本内容消息
					WKConfig.sendText(map, Authorization);
					return ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
				}else if(receMessString.equals("天蝎")||receMessString.equals("天蝎座")) {
					JSONObject resultJson= WKConfig.doGet1("天蝎", null);
					net.sf.json.JSONArray jsonArray = resultJson.getJSONArray("newslist");
					JSONObject  jsonObject  =  (JSONObject) jsonArray.get(0);
					String hMessage = jsonObject.get("title")+"\n"+jsonObject.get("grade")+"\n"+jsonObject.get("content");
					Map<Object, Object> map =new HashMap<Object, Object>();
					map.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
					map.put("wcId",String.valueOf(resultJson1.get("fromGroup")));//微信号/群号
					map.put("content",hMessage);//文本内容消息
					WKConfig.sendText(map, Authorization);
					return ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
				}else if(receMessString.equals("射手座")||receMessString.equals("射手")) {
					JSONObject resultJson= WKConfig.doGet1("射手", null);
					net.sf.json.JSONArray jsonArray = resultJson.getJSONArray("newslist");
					JSONObject  jsonObject  =  (JSONObject) jsonArray.get(0);
					String hMessage = jsonObject.get("title")+"\n"+jsonObject.get("grade")+"\n"+jsonObject.get("content");
					Map<Object, Object> map =new HashMap<Object, Object>();
					map.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
					map.put("wcId",String.valueOf(resultJson1.get("fromGroup")));//微信号/群号
					map.put("content",hMessage);//文本内容消息
					WKConfig.sendText(map, Authorization);
					return ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
				}else if(receMessString.equals("摩羯座")||receMessString.equals("摩羯")) {
					JSONObject resultJson= WKConfig.doGet1("摩羯", null);
					net.sf.json.JSONArray jsonArray = resultJson.getJSONArray("newslist");
					JSONObject  jsonObject  =  (JSONObject) jsonArray.get(0);
					String hMessage = jsonObject.get("title")+"\n"+jsonObject.get("grade")+"\n"+jsonObject.get("content");
					Map<Object, Object> map =new HashMap<Object, Object>();
					map.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
					map.put("wcId",String.valueOf(resultJson1.get("fromGroup")));//微信号/群号
					map.put("content",hMessage);//文本内容消息
					WKConfig.sendText(map, Authorization);
					return ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
				}else if(receMessString.equals("水瓶座")||receMessString.equals("水瓶")) {
					JSONObject resultJson= WKConfig.doGet1("水瓶", null);
					net.sf.json.JSONArray jsonArray = resultJson.getJSONArray("newslist");
					JSONObject  jsonObject  =  (JSONObject) jsonArray.get(0);
					String hMessage = jsonObject.get("title")+"\n"+jsonObject.get("grade")+"\n"+jsonObject.get("content");
					Map<Object, Object> map =new HashMap<Object, Object>();
					map.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
					map.put("wcId",String.valueOf(resultJson1.get("fromGroup")));//微信号/群号
					map.put("content",hMessage);//文本内容消息
					WKConfig.sendText(map, Authorization);
					return ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
				}else if(receMessString.equals("双鱼座")||receMessString.equals("双鱼")) {
					JSONObject resultJson= WKConfig.doGet1("双鱼", null);
					net.sf.json.JSONArray jsonArray = resultJson.getJSONArray("newslist");
					JSONObject  jsonObject  =  (JSONObject) jsonArray.get(0);
					String hMessage = jsonObject.get("title")+"\n"+jsonObject.get("grade")+"\n"+jsonObject.get("content");
					Map<Object, Object> map =new HashMap<Object, Object>();
					map.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
					map.put("wcId",String.valueOf(resultJson1.get("fromGroup")));//微信号/群号
					map.put("content",hMessage);//文本内容消息
					WKConfig.sendText(map, Authorization);
					return ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
				}
				
			}
			String djString = receMessString.toLowerCase();
			if(content.substring(0,1).equals("@")&&replaceAllUser.equals(user)&& djString.equals("dj")) {//语音加图
				Djplay djplay = new Djplay();
				djplay.setChatRoomId(String.valueOf(resultJson1.get("fromGroup")));
				djplay.setChatUserWx(String.valueOf(resultJson1.get("fromUser")));
				djplay.setModifyDate(DateUtil.getCurrentStr());
				Djplay djplay2 = djService.getUserRoomCount(djplay);
				if(djplay2!=null) {//如果存在
					if(djplay2.getCount()>=3) {//判断是当天并且次数超过三次
						return  ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.USER_DJ_OUTPLAY);
					}else {
						djplay2.setCount(djplay2.getCount()+1);
						djService.update(djplay2);
					}
				}else {//如果不存在
					djplay.setCount(1);
					djService.save(djplay);
				}
				Random random = new Random();
				int num = random.nextInt(6);
				switch (num) {
				case 0:
					Map<Object, Object> map =new HashMap<Object, Object>();
					map.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
					map.put("wcId",String.valueOf(resultJson1.get("fromGroup")));//微信号/群号
					map.put("content","https://weikong-1301028514.cos.ap-shanghai.myqcloud.com//msgVoice/db713bb7-fbbd-4ea1-bd68-8eb0951036f5-1596523412158-100000.silk");//文本内容消息
					map.put("length",1000);//文本内容消息
					WKConfig.sendVoice(map,Authorization);
					
					Map<Object, Object> map1 =new HashMap<Object, Object>();
					map1.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
					map1.put("wcId",String.valueOf(resultJson1.get("fromGroup")));//微信号/群号
					map1.put("imageMd5","97b0ff66a1acaeb46ee8505ed51a9f3e");//文本内容消息
					map1.put("imgSize","912579");//文本内容消息
					WKConfig.sendEmoji(map1,Authorization);
					return  ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
				case 1:
					Map<Object, Object> map2 =new HashMap<Object, Object>();
					map2.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
					map2.put("wcId",String.valueOf(resultJson1.get("fromGroup")));//微信号/群号
					map2.put("content","https://weikong-1301028514.cos.ap-shanghai.myqcloud.com//msgVoice/bcb65f39-5265-4813-be22-a53681f2f910-1596523608839-200000.silk");//文本内容消息
					map2.put("length",1000);//文本内容消息
					WKConfig.sendVoice(map2,Authorization);
					
					Map<Object, Object> map3 =new HashMap<Object, Object>();
					map3.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
					map3.put("wcId",String.valueOf(resultJson1.get("fromGroup")));//微信号/群号
					map3.put("imageMd5","a95cca0de5fee24a744c431969ca71c1");//文本内容消息
					map3.put("imgSize","622917");//文本内容消息
					WKConfig.sendEmoji(map3,Authorization);
					return  ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
				case 2:
					Map<Object, Object> map4 =new HashMap<Object, Object>();
					map4.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
					map4.put("wcId",String.valueOf(resultJson1.get("fromGroup")));//微信号/群号
					map4.put("content","https://weikong-1301028514.cos.ap-shanghai.myqcloud.com//msgVoice/906a0afb-b6e3-41dc-b2ff-7745447f5852-1596523646688-500000.silk");//文本内容消息
					map4.put("length",1000);//文本内容消息
					WKConfig.sendVoice(map4,Authorization);
					
					Map<Object, Object> map5 =new HashMap<Object, Object>();
					map5.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
					map5.put("wcId",String.valueOf(resultJson1.get("fromGroup")));//微信号/群号
					map5.put("imageMd5","c2c430f6d6a63ba3cba1bdc58ea1458f");//文本内容消息
					map5.put("imgSize","1684340");//文本内容消息
					WKConfig.sendEmoji(map5,Authorization);
					return  ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
				case 3:
					Map<Object, Object> map6 =new HashMap<Object, Object>();
					map6.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
					map6.put("wcId",String.valueOf(resultJson1.get("fromGroup")));//微信号/群号
					map6.put("content","https://weikong-1301028514.cos.ap-shanghai.myqcloud.com//msgVoice/940be614-e28f-4b33-a268-838d9f305bea-1596523703663-600000.silk");//文本内容消息
					map6.put("length",1000);//文本内容消息
					WKConfig.sendVoice(map6,Authorization);
					
					Map<Object, Object> map7 =new HashMap<Object, Object>();
					map7.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
					map7.put("wcId",String.valueOf(resultJson1.get("fromGroup")));//微信号/群号
					map7.put("imageMd5","e5f171d55035fd749483aaa07d925e5c");//文本内容消息
					map7.put("imgSize","604598");//文本内容消息
					WKConfig.sendEmoji(map7,Authorization);
					return  ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
				case 4:
					Map<Object, Object> map8 =new HashMap<Object, Object>();
					map8.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
					map8.put("wcId",String.valueOf(resultJson1.get("fromGroup")));//微信号/群号
					map8.put("content","https://weikong-1301028514.cos.ap-shanghai.myqcloud.com//msgVoice/c3566c71-ff80-4471-8738-8f03175da0fd-1596523739349-500000.silk");//文本内容消息
					map8.put("length",1000);//文本内容消息
					WKConfig.sendVoice(map8,Authorization);
					
					Map<Object, Object> map9 =new HashMap<Object, Object>();
					map9.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
					map9.put("wcId",String.valueOf(resultJson1.get("fromGroup")));//微信号/群号
					map9.put("imageMd5","ce70689add5242941844b9a8d0229a52");//文本内容消息
					map9.put("imgSize","873159");//文本内容消息
					WKConfig.sendEmoji(map9,Authorization);
					return  ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
				case 5:
					Map<Object, Object> map11 =new HashMap<Object, Object>();
					map11.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
					map11.put("wcId",String.valueOf(resultJson1.get("fromGroup")));//微信号/群号
					map11.put("content","https://weikong-1301028514.cos.ap-shanghai.myqcloud.com//msgVoice/3be8ca70-fe8c-4143-a8f9-229e5785ecc0-1596523780143-700000.silk");//文本内容消息
					map11.put("length",1000);//文本内容消息
					WKConfig.sendVoice(map11,Authorization);
					
					Map<Object, Object> map12 =new HashMap<Object, Object>();
					map12.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
					map12.put("wcId",String.valueOf(resultJson1.get("fromGroup")));//微信号/群号
					map12.put("imageMd5","026ca90bdfcfcf18d3f60c8a4d4d32f6");//文本内容消息
					map12.put("imgSize","2972492");//文本内容消息
					WKConfig.sendEmoji(map12,Authorization);
					return  ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
				}
				logger.debug("ifreturn");
			}
			String chatNickName=null;
			logger.debug("继续执行"+content);
            if(!StringUtil.isNullOrEmpty(replaceAllUser)) {
				ChatRoommembers wxRoommembers=  chatRoommembersService.getMessChatRoomsByUserId(replaceAllUser);
				chatNickName=wxRoommembers.getChatNickName();
				if(content.substring(0,1).equals("@")&&replaceAllUser.equals(user)) {// 聊天
					Sum sumMess=sumService.getMessByid(1);//根据id查询出数量
					Sum sum=new Sum();
					sum.setSumId(1);
					if(sumMess.getCount()>=20) {
						sum.setCount(0);
						sumService.update(sum);//然后在修改数量
						Map<Object, Object> map =new HashMap<Object, Object>();
						map.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
						map.put("wcId",String.valueOf(resultJson1.get("fromGroup")));//微信号/群号
						map.put("content","群里呼叫"+chatNickName+"太频繁了,先休息两分钟噢");//文本内容消息
						WKConfig.sendText(map,Authorization);
						MessSend messSend=new MessSend();
						messSend.setWId(String.valueOf(resultJson1.get("wId")));
						messSend.setWcId(String.valueOf(resultJson1.get("fromGroup")));
						messSend.setContent("群里呼叫"+chatNickName+"太频繁了,先休息两分钟噢");
						messSend.setMessSendType(2);// 好友发的消息
						messSendService.save(messSend);
						ResponseBoxDto result = ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
						TimeUnit.MINUTES.sleep(2);
						return result;
					}else {
						sum.setCount(sumMess.getCount()+1);
						sumService.update(sum);//然后在修改数量
						String chatContent=content.substring(0,1)+chatNickName;
						if(content.equals(chatContent)) {
							Random random = new Random();
							int num = random.nextInt(6);
							switch (num) {
							case 0:
								Map<Object, Object> map =new HashMap<Object, Object>();
								map.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
								map.put("wcId",String.valueOf(resultJson1.get("fromGroup")));//微信号/群号
								map.put("content","亲亲在的，么么哒[亲亲]");//文本内容消息
								WKConfig.sendText(map,Authorization);
								MessSend messSend=new MessSend();
								messSend.setWId(String.valueOf(resultJson1.get("wId")));
								messSend.setWcId(String.valueOf(resultJson1.get("fromGroup")));
								messSend.setContent("亲亲在的，么么哒[亲亲]");
								messSend.setMessSendType(2);// 好友发的消息
								messSendService.save(messSend);
								ResponseBoxDto result = ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
								return result;
							case 1:
								Map<Object, Object> map1 =new HashMap<Object, Object>();
								map1.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
								map1.put("wcId",String.valueOf(resultJson1.get("fromGroup")));//微信号/群号
								map1.put("content","别总是艾特我，我怕你会爱上我[害羞]");//文本内容消息
								WKConfig.sendText(map1,Authorization);
								MessSend messSend1=new MessSend();
								messSend1.setWId(String.valueOf(resultJson1.get("wId")));
								messSend1.setWcId(String.valueOf(resultJson1.get("fromGroup")));
								messSend1.setContent("别总是艾特我，我怕你会爱上我[害羞]");
								messSend1.setMessSendType(2);// 好友发的消息
								messSendService.save(messSend1);
								return ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
							case 2:
								Map<Object, Object> map2 =new HashMap<Object, Object>();
								map2.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
								map2.put("wcId",String.valueOf(resultJson1.get("fromGroup")));//微信号/群号
								map2.put("content","在呢");//文本内容消息
								WKConfig.sendText(map2,Authorization);
								MessSend messSend2=new MessSend();
								messSend2.setWId(String.valueOf(resultJson1.get("wId")));
								messSend2.setWcId(String.valueOf(resultJson1.get("fromGroup")));
								messSend2.setContent("在呢");
								messSend2.setMessSendType(2);// 好友发的消息
								messSendService.save(messSend2);
								return ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
							case 3:
								Map<Object, Object> map3 =new HashMap<Object, Object>();
								map3.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
								map3.put("wcId",String.valueOf(resultJson1.get("fromGroup")));//微信号/群号
								map3.put("content","我爱你，没理由[色]");//文本内容消息
								WKConfig.sendText(map3,Authorization);
								MessSend messSend3=new MessSend();
								messSend3.setWId(String.valueOf(resultJson1.get("wId")));
								messSend3.setWcId(String.valueOf(resultJson1.get("fromGroup")));
								messSend3.setContent("我爱你，没理由[色]");
								messSend3.setMessSendType(2);// 好友发的消息
								messSendService.save(messSend3);
								return ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
							case 4:
								Map<Object, Object> map4 =new HashMap<Object, Object>();
								map4.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
								map4.put("wcId",String.valueOf(resultJson1.get("fromGroup")));//微信号/群号
								map4.put("content","在阿，一起来玩[跳跳]");//文本内容消息
								WKConfig.sendText(map4,Authorization);
								MessSend messSend4=new MessSend();
								messSend4.setWId(String.valueOf(resultJson1.get("wId")));
								messSend4.setWcId(String.valueOf(resultJson1.get("fromGroup")));
								messSend4.setContent("在阿，一起来玩[跳跳]");
								messSend4.setMessSendType(2);// 好友发的消息
								messSendService.save(messSend4);
								return ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
							case 5:
								Map<Object, Object> map5 =new HashMap<Object, Object>();
								map5.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
								map5.put("wcId",String.valueOf(resultJson1.get("fromGroup")));//微信号/群号
								map5.put("content","有空一起谈个恋爱，没空让我继续暗恋[嘿哈]");//文本内容消息
								WKConfig.sendText(map5,Authorization);
								MessSend messSend5=new MessSend();
								messSend5.setWId(String.valueOf(resultJson1.get("wId")));
								messSend5.setWcId(String.valueOf(resultJson1.get("fromGroup")));
								messSend5.setContent("有空一起谈个恋爱，没空让我继续暗恋[嘿哈]");
								messSend5.setMessSendType(2);// 好友发的消息
								messSendService.save(messSend5);
								return ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
							}
						}else {
							int receive=content.indexOf("?");
							String receString=content.substring(receive+1);
							JSONObject array = WKConfig.doGet(receString, null);
							String robotMessage = array.getString("content");
							String	robotMessagez=robotMessage.replaceAll("\\{br\\}", "\n");
							Map<Object, Object> map =new HashMap<Object, Object>();
							map.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
							map.put("wcId",String.valueOf(resultJson1.get("fromGroup")));//微信号/群号
							map.put("content",robotMessagez);//文本内容消息
							WKConfig.sendText(map,Authorization);
							MessSend messSend=new MessSend();
							messSend.setWId(String.valueOf(resultJson1.get("wId")));
							messSend.setWcId(String.valueOf(resultJson1.get("fromGroup")));
							messSend.setContent(robotMessagez);
							messSend.setMessSendType(2);// 好友发的消息
							messSendService.save(messSend);
							ResponseBoxDto result = ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
							return result;
						}
					}
					
				}
            }
            ChatRoommembers chat=chatRoommembersService.getMessChatRoomsByUserId(wcId);
            if(!ObjectUtil.isNullOrEmpty(chat)) {
            		if(content.equals(chat.getDisplayName())) {
						Random random = new Random();
						int num = random.nextInt(6);
						switch (num) {
						case 0:
							Map<Object, Object> map =new HashMap<Object, Object>();
							map.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
							map.put("wcId",String.valueOf(resultJson1.get("fromGroup")));//微信号/群号
							map.put("content","亲亲在的，么么哒[亲亲]");//文本内容消息
							WKConfig.sendText(map,Authorization);
							MessSend messSend=new MessSend();
							messSend.setWId(String.valueOf(resultJson1.get("wId")));
							messSend.setWcId(String.valueOf(resultJson1.get("fromGroup")));
							messSend.setContent("亲亲在的，么么哒[亲亲]");
							messSend.setMessSendType(2);// 好友发的消息
							messSendService.save(messSend);
							ResponseBoxDto result = ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
							return result;
						case 1:
							Map<Object, Object> map1 =new HashMap<Object, Object>();
							map1.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
							map1.put("wcId",String.valueOf(resultJson1.get("fromGroup")));//微信号/群号
							map1.put("content","别总是艾特我，我怕你会爱上我[害羞]");//文本内容消息
							WKConfig.sendText(map1,Authorization);
							MessSend messSend1=new MessSend();
							messSend1.setWId(String.valueOf(resultJson1.get("wId")));
							messSend1.setWcId(String.valueOf(resultJson1.get("fromGroup")));
							messSend1.setContent("别总是艾特我，我怕你会爱上我[害羞]");
							messSend1.setMessSendType(2);// 好友发的消息
							messSendService.save(messSend1);
							return ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
						case 2:
							Map<Object, Object> map2 =new HashMap<Object, Object>();
							map2.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
							map2.put("wcId",String.valueOf(resultJson1.get("fromGroup")));//微信号/群号
							map2.put("content","在呢");//文本内容消息
							WKConfig.sendText(map2,Authorization);
							MessSend messSend2=new MessSend();
							messSend2.setWId(String.valueOf(resultJson1.get("wId")));
							messSend2.setWcId(String.valueOf(resultJson1.get("fromGroup")));
							messSend2.setContent("在呢");
							messSend2.setMessSendType(2);// 好友发的消息
							messSendService.save(messSend2);
							return ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
						case 3:
							Map<Object, Object> map3 =new HashMap<Object, Object>();
							map3.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
							map3.put("wcId",String.valueOf(resultJson1.get("fromGroup")));//微信号/群号
							map3.put("content","我爱你，没理由[色]");//文本内容消息
							WKConfig.sendText(map3,Authorization);
							MessSend messSend3=new MessSend();
							messSend3.setWId(String.valueOf(resultJson1.get("wId")));
							messSend3.setWcId(String.valueOf(resultJson1.get("fromGroup")));
							messSend3.setContent("我爱你，没理由[色]");
							messSend3.setMessSendType(2);// 好友发的消息
							messSendService.save(messSend3);
							return ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
						case 4:
							Map<Object, Object> map4 =new HashMap<Object, Object>();
							map4.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
							map4.put("wcId",String.valueOf(resultJson1.get("fromGroup")));//微信号/群号
							map4.put("content","在阿，一起来玩[跳跳]");//文本内容消息
							WKConfig.sendText(map4,Authorization);
							MessSend messSend4=new MessSend();
							messSend4.setWId(String.valueOf(resultJson1.get("wId")));
							messSend4.setWcId(String.valueOf(resultJson1.get("fromGroup")));
							messSend4.setContent("在阿，一起来玩[跳跳]");
							messSend4.setMessSendType(2);// 好友发的消息
							messSendService.save(messSend4);
							return ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
						case 5:
							Map<Object, Object> map5 =new HashMap<Object, Object>();
							map5.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
							map5.put("wcId",String.valueOf(resultJson1.get("fromGroup")));//微信号/群号
							map5.put("content","有空一起谈个恋爱，没空让我继续暗恋[嘿哈]");//文本内容消息
							WKConfig.sendText(map5,Authorization);
							MessSend messSend5=new MessSend();
							messSend5.setWId(String.valueOf(resultJson1.get("wId")));
							messSend5.setWcId(String.valueOf(resultJson1.get("fromGroup")));
							messSend5.setContent("有空一起谈个恋爱，没空让我继续暗恋[嘿哈]");
							messSend5.setMessSendType(2);// 好友发的消息
							messSendService.save(messSend5);
							return ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
						}
					}else {
						String contengs=content.replace(chat.getDisplayName(), "");
						JSONObject array = WKConfig.doGet(contengs, null);
						String robotMessage = array.getString("content");
						String	robotMessagez=robotMessage.replaceAll("\\{br\\}", "\n");
						Map<Object, Object> map =new HashMap<Object, Object>();
						map.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
						map.put("wcId",String.valueOf(resultJson1.get("fromGroup")));//微信号/群号
						map.put("content",robotMessagez);//文本内容消息
						WKConfig.sendText(map,Authorization);
						MessSend messSend=new MessSend();
						messSend.setWId(String.valueOf(resultJson1.get("wId")));
						messSend.setWcId(String.valueOf(resultJson1.get("fromGroup")));
						messSend.setContent(robotMessagez);
						messSend.setMessSendType(2);// 好友发的消息
						messSendService.save(messSend);
						ResponseBoxDto result = ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
						return result;
					}
                }     
		}
		
		if(messageType==8) {//如果消息类型为语音消息
			JSONObject jsonVoice = new JSONObject();
			org.json.JSONObject object = XML.toJSONObject(content);		 	
		    String jsonData = object.get("msg").toString();//xml根节点
		    com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(jsonData);//转换成json对象
		    String message = jsonObject.getString("voicemsg");//取出对应的value
		    jsonVoice = JSONObject.fromObject(message);
			Map<Object, Object> map =new HashMap<Object, Object>();
			map.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
			map.put("msgId",String.valueOf(resultJson1.get("msgId")));//消息id
			map.put("length",String.valueOf(jsonVoice.get("length")));//语音的长度
			System.out.println(String.valueOf(jsonVoice.get("length")));
			map.put("bufId",String.valueOf(jsonVoice.get("bufid")));//xml中返回的bufId字段值
			map.put("fromUser",String.valueOf(resultJson1.get("fromUser")));//发送者
			JsonConfig jsonConfig = new JsonConfig();
			json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
			JSONObject resultJson =WKConfig.doPost("/getMsgVoice",json,request,Authorization);
			JSONObject resultJsonVoice = (JSONObject) resultJson.get("data");
			System.out.println(resultJsonVoice.get("url"));
	        String uploadPath = "C:\\Users\\Administrator\\Desktop\\music\\"; //最终图片上传的路径"
			UploadProjectMess.downloadFile(String.valueOf(resultJsonVoice.get("url")),uploadPath);
			String urlVoice=String.valueOf(resultJsonVoice.get("url"));
			String[] sourceStrArray=urlVoice.split("/");
	        String fileName = sourceStrArray[5];
			String uploadSilk=uploadPath+fileName;
			String filespcm=MusicUtil.processPcm(new File(uploadSilk));
			String fileWav = MusicUtil.coverMp(new File(filespcm));
			AipSpeech client = new AipSpeech("21651853", "wjfprNo1INQNgZwYsYV8q9Il", "Ir4Mgc4q1eUCvebY3ruKxt05r2OLWGYX");
	        client.setConnectionTimeoutInMillis(2000);
	        client.setSocketTimeoutInMillis(60000);
	        HashMap<String, Object> mapVoice =new HashMap<String, Object>();
	        mapVoice.put("dev_pid", 1537);
	        // 对语音二进制数据进行识别
	        org.json.JSONObject res = client.asr(fileWav, "wav", 16000, mapVoice);
	        logger.debug("dddd"+res.toString());
	        System.out.println(res);
	        byte[] data = Util.readFileByBytes(fileWav);     //readFileByBytes仅为获取二进制数据示例
	        org.json.JSONObject asrRes2 = client.asr(data, "wav", 16000, mapVoice);
	        System.out.println(asrRes2);
	        logger.debug("dddd"+asrRes2.toString());
			File filePcms = new File(filespcm);// 根据指定的文件名创建File对象
			File fileSilk=new File(uploadSilk);
	        String voiceContent=String.valueOf(asrRes2.get("result"));
	        String replaceAll=null;
	        if(!ObjectUtil.isNullOrEmpty(voiceContent)) {
	            String t = voiceContent.replaceAll("\\\"","");
	            replaceAll = t.replace("[", "").replace("]","");
	            System.out.println(replaceAll);
	        }
	        DownloadMess downloadMess=new DownloadMess();
			downloadMess.setDUrl(String.valueOf(resultJsonVoice.get("url")));
			downloadMess.setDType(2);
			downloadMessService.save(downloadMess);
	        if(replaceAll.equals("点歌")) {
				String cont="语音发送点歌+歌名可点歌";
				Map<Object, Object> mapVoiceMusic =new HashMap<Object, Object>();
				mapVoiceMusic.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
				mapVoiceMusic.put("wcId",String.valueOf(resultJson1.get("fromUser")));//微信号/群号
				mapVoiceMusic.put("content",cont);//文本内容消息
				WKConfig.sendText(mapVoiceMusic,Authorization);
				MessSend messSend=new MessSend();
				messSend.setWId(String.valueOf(resultJson1.get("wId")));
				messSend.setWcId(String.valueOf(resultJson1.get("fromUser")));
				messSend.setContent(cont);
				messSend.setMessSendType(1);// 好友发的消息
				messSendService.save(messSend);
				ResponseBoxDto resultVoiceMusic = ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
				return resultVoiceMusic;
			}else {
				String musicName=replaceAll.substring(2);
				Map<Object, Object> mapVoiceMusic =new HashMap<Object, Object>();
				mapVoiceMusic.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
				mapVoiceMusic.put("wcId",String.valueOf(resultJson1.get("fromUser")));//微信号/群号
				mapVoiceMusic.put("title",musicName);//文本内容消息
				mapVoiceMusic.put("url","https://y.music.163.com/m/song?id="+getSeachMusic(null,musicName));//文本内容消息
				mapVoiceMusic.put("description","");//文本内容消息
				mapVoiceMusic.put("thumbUrl","http://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg");//文本内容消息
				WKConfig.sendUrl(mapVoiceMusic,Authorization);
				MessSend messSend=new MessSend();
				messSend.setWId(String.valueOf(resultJson1.get("wId")));
				messSend.setWcId(String.valueOf(resultJson1.get("fromUser")));
				messSend.setContent(musicName);
				messSend.setMessSendType(1);// 好友发的消息
				messSendService.save(messSend);
				ResponseBoxDto resultVoiceMusic = ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
				return resultVoiceMusic;
			}
		}
		if(messageType==12) {//如果消息类型为语音消息
			JSONObject jsonVoice = new JSONObject();
			org.json.JSONObject object = XML.toJSONObject(content);		 	
		    String jsonData = object.get("msg").toString();//xml根节点
		    com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(jsonData);//转换成json对象
		    String message = jsonObject.getString("voicemsg");//取出对应的value
		    jsonVoice = JSONObject.fromObject(message);
			Map<Object, Object> map =new HashMap<Object, Object>();
			map.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
			map.put("msgId",String.valueOf(resultJson1.get("msgId")));//消息id
			map.put("length",String.valueOf(jsonVoice.get("length")));//语音的长度
			map.put("bufId",String.valueOf(jsonVoice.get("bufid")));//xml中返回的bufId字段值
			map.put("fromUser",String.valueOf(resultJson1.get("fromGroup")));//发送者
			JsonConfig jsonConfig = new JsonConfig();
			json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
			JSONObject resultJson =WKConfig.doPost("/getMsgVoice",json,request,Authorization);
			JSONObject resultJsonVoice = (JSONObject) resultJson.get("data");
			System.out.println(resultJsonVoice.get("url"));
			String uploadPath = "C:\\Users\\Administrator\\Desktop\\music\\"; //最终图片上传的路径"
			UploadProjectMess.downloadFile(String.valueOf(resultJsonVoice.get("url")),uploadPath);
			String urlVoice=String.valueOf(resultJsonVoice.get("url"));
			String[] sourceStrArray=urlVoice.split("/");
	        String fileName = sourceStrArray[5];
			String uploadSilk=uploadPath+fileName;
			String filespcm=MusicUtil.processPcm(new File(uploadSilk));
			String fileWav = MusicUtil.coverMp(new File(filespcm));
			AipSpeech client = new AipSpeech("21651853", "wjfprNo1INQNgZwYsYV8q9Il", "Ir4Mgc4q1eUCvebY3ruKxt05r2OLWGYX");
	        client.setConnectionTimeoutInMillis(2000);
	        client.setSocketTimeoutInMillis(60000);
	        HashMap<String, Object> mapVoice =new HashMap<String, Object>();
	        mapVoice.put("dev_pid", 1537);
	        // 对语音二进制数据进行识别
	        org.json.JSONObject res = client.asr(fileWav, "wav", 16000, mapVoice);
	        System.out.println(res);
	        logger.debug("dddd"+res.toString());
	        byte[] data = Util.readFileByBytes(fileWav);     //readFileByBytes仅为获取二进制数据示例
	        org.json.JSONObject asrRes2 = client.asr(data, "wav", 16000, mapVoice);
	        System.out.println(asrRes2);
	        logger.debug("dddd"+asrRes2.toString());
			File filePcms = new File(filespcm);// 根据指定的文件名创建File对象
			File fileSilk=new File(uploadSilk);
	        String voiceContent=String.valueOf(asrRes2.get("result"));
	        String replaceAll=null;
	        if(!ObjectUtil.isNullOrEmpty(voiceContent)) {
	            String t = voiceContent.replaceAll("\\\"","");
	            replaceAll = t.replace("[", "").replace("]","");
	            System.out.println(replaceAll);
	        }
			DownloadMess downloadMess=new DownloadMess();
			downloadMess.setDUrl(String.valueOf(resultJsonVoice.get("url")));
			downloadMess.setDType(2);
			downloadMessService.save(downloadMess);
			if(replaceAll.equals("点歌")||replaceAll.equals("听歌")) {
				String cont="语音发送点歌+歌名 或者听歌+歌名 可点歌";
				Map<Object, Object> mapVoiceMusic =new HashMap<Object, Object>();
				mapVoiceMusic.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
				mapVoiceMusic.put("wcId",String.valueOf(resultJson1.get("fromGroup")));//微信号/群号
				mapVoiceMusic.put("content",cont);//文本内容消息
				WKConfig.sendText(mapVoiceMusic,Authorization);
				MessSend messSend=new MessSend();
				messSend.setWId(String.valueOf(resultJson1.get("wId")));
				messSend.setWcId(String.valueOf(resultJson1.get("fromGroup")));
				messSend.setContent(cont);
				messSend.setMessSendType(2);// 好友发的消息
				messSendService.save(messSend);
				ResponseBoxDto resultVoiceMusic = ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
				return resultVoiceMusic;
			}else{
				if(replaceAll.substring(0,2).equals("点歌")||replaceAll.substring(0,2).equals("听歌")) {
					String musicName=replaceAll.substring(2);
					Map<Object, Object> mapVoiceMusic =new HashMap<Object, Object>();
					mapVoiceMusic.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
					mapVoiceMusic.put("wcId",String.valueOf(resultJson1.get("fromGroup")));//微信号/群号
					mapVoiceMusic.put("title",musicName);//文本内容消息
					mapVoiceMusic.put("url","https://y.music.163.com/m/song?id="+getSeachMusic(null,musicName));//文本内容消息
					mapVoiceMusic.put("description","");//文本内容消息
					mapVoiceMusic.put("thumbUrl","http://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg");//文本内容消息
					WKConfig.sendUrl(mapVoiceMusic,Authorization);
					MessSend messSend=new MessSend();
					messSend.setWId(String.valueOf(resultJson1.get("wId")));
					messSend.setWcId(String.valueOf(resultJson1.get("fromGroup")));
					messSend.setContent(musicName);
					messSend.setMessSendType(2);// 好友发的消息
					messSendService.save(messSend);
					ResponseBoxDto resultVoiceMusic = ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
					return resultVoiceMusic;
				}
			}
		}
		
		if(messageType==1) {//新人入群
			Map<Object, Object> map =new HashMap<Object, Object>();
			map.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
			map.put("wcId",String.valueOf(resultJson1.get("fromGroup")));//微信号/群号
			map.put("content","@新同志 欢迎入群");//文本内容消息
			map.put("at",joinUserName);//文本内容消息
			WKConfig.sendText(map,Authorization);
			Map<Object, Object> map1 =new HashMap<Object, Object>();
			map1.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
			map1.put("wcId",String.valueOf(resultJson1.get("fromGroup")));//微信号/群号
			map1.put("content","https://weikong-1301028514.cos.ap-shanghai.myqcloud.com//msgVoice/f78bbebf-2e18-4a2f-aa5b-dce1edfb58e6-1596093944267-300000.silk");//文本内容消息
			map1.put("length",1000);//文本内容消息
			WKConfig.sendVoice(map1,Authorization);
			Map<Object, Object> map2 =new HashMap<Object, Object>();
			map2.put("wId",String.valueOf(resultJson1.get("wId")));//微信实例ID
			map2.put("wcId",String.valueOf(resultJson1.get("fromGroup")));//微信号/群号
			map2.put("path","http://39.100.151.118/shareVideo/admin-manage/images/1.mp4");//文本内容消息
			map2.put("thumbPath","http://39.100.151.118/shareVideo/admin-manage/images/1.jpg");//文本内容消息
			WKConfig.sendVideo(map2,Authorization);
			return ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
		}
		return null;
	}
	
	public  String getSeachMusic(HttpServletRequest request,String musicName) {
		JSONObject jsonObject = new JSONObject();
		Map<Object, Object> map =new HashMap<Object, Object>();
		JsonConfig jsonConfig = new JsonConfig();
		jsonObject = JSONObject.fromObject(map, jsonConfig);// stringBuffer
		JSONObject resultJson =WKConfig.doPost1("http://musicapi.leanapp.cn/search?keywords="+musicName,jsonObject,request,null);
		JSONObject result = (JSONObject) resultJson.get("result");
		String sogs = result.getString("songs");
		List<JSONObject> datas = JSON.parseArray(sogs, JSONObject.class);
		 // 转换方法2
		return datas.get(0).get("id").toString();
	}
	

	public static void main(String [] args) throws UnsupportedEncodingException{
		JSONObject resultJson= WKConfig.doGet1("金牛", null);
		String name = resultJson.getString("newslist");
		net.sf.json.JSONArray jsonArray = resultJson.getJSONArray("newslist");
		System.out.println(jsonArray);
		JSONObject  jsonObject  =  (JSONObject) jsonArray.get(0);
		String gString =	jsonObject.get("title")+"\n"+jsonObject.get("grade")+"\n"+jsonObject.get("content");
		System.out.println(gString);
	}
}
