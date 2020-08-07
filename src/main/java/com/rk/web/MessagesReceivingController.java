package com.rk.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rk.dto.MessReceiveDto;
import com.rk.entity.DownloadMess;
import com.rk.entity.MessReceiving;
import com.rk.entity.WxPlatform;
import com.rk.entity.WxUser;
import com.rk.enums.ENUM_EXCEPTION_TYPE;
import com.rk.report.ResponseBoxDto;
import com.rk.service.DownloadMessService;
import com.rk.service.MessReceivingService;
import com.rk.service.WxPlatformService;
import com.rk.util.FileUtils;
import com.rk.util.WKConfig;

import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@RestController
@RequestMapping("/messagesReceiving")
public class MessagesReceivingController {
	
	@Autowired(required = true)
	private WxPlatformService wxPlatFormService;
	@Autowired
	private DownloadMessService downloadMessService;
	@Autowired
	private MessReceivingService messReceivingService;
	
	public String account;
	public String password;
	public int type=2;
	public String Authorization;
	public String httpUrl;
	
	public List<WxPlatform> getPlatformk(){
	  List<WxPlatform> wxPlatform = wxPlatFormService.findAll();
	  account=wxPlatform.get(0).getPlatformAccount();
	  password=wxPlatform.get(0).getPlatformPassword();
	  Authorization=wxPlatform.get(0).getPlatformKey();
	  httpUrl=wxPlatform.get(0).getPlatformUrl();
	  return wxPlatform;
	}

	@ApiOperation(value = "设置消息接收地址", notes = "设置消息接收地址")
	@RequestMapping(value = "/020001", method = RequestMethod.POST)
	public ResponseBoxDto setHttpCallbackUrl(HttpServletRequest request) throws IOException {
		JSONObject json = new JSONObject();
		getPlatformk();
		Map<Object, Object> map =new HashMap<Object, Object>();
		map.put("httpUrl",httpUrl);//http回调地址
		JsonConfig jsonConfig = new JsonConfig();
		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
		JSONObject resultJson =WKConfig.doPost("/setHttpCallbackUrl",json,request,Authorization);
		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
		return result;
	}
	
	@ApiOperation(value = "取消消息接收", notes = "取消消息接收")
	@RequestMapping(value = "/020002", method = RequestMethod.POST)
	public ResponseBoxDto getCancelHttpCallbackUrl(HttpServletRequest request) throws IOException {
		JSONObject json = new JSONObject();
		Map<Object, Object> map =new HashMap<Object, Object>();
		JsonConfig jsonConfig = new JsonConfig();
		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
		JSONObject resultJson =WKConfig.doPost("/cancelHttpCallbackUrl",json,request,Authorization);
		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
		return result;
	}
	
	@ApiOperation(value = "下载文件", notes = "下载文件")
	@RequestMapping(value = "/020003", method = RequestMethod.POST)
	public ResponseBoxDto getMsgFile(HttpServletRequest request,HttpServletResponse response,HttpSession session,@RequestBody MessReceiveDto messReceiveDto) throws IOException {
		JSONObject json = new JSONObject();
		Map<Object, Object> map =new HashMap<Object, Object>();
		map.put("wId",messReceiveDto.getwId());//微信实例ID
		map.put("msgId",messReceiveDto.getDataMsgId());//消息id 
		map.put("from_user",messReceiveDto.getDataFromUser());//发送人的微信号
		map.put("to_user",messReceiveDto.getDataToUser());//接收人的微信号
		map.put("content",messReceiveDto.getDataContent());//收到的消息的xml数据
		JsonConfig jsonConfig = new JsonConfig();
		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
		JSONObject resultJson =WKConfig.doPost("/getMsgFile",json,request,Authorization);
		JSONObject resultJson1 = (JSONObject) resultJson.get("data"); 
		System.out.println(resultJson1.get("url"));
		DownloadMess downloadMess=new DownloadMess();
		downloadMess.setDUrl(String.valueOf(resultJson1.get("url")));
		downloadMess.setDType(1);
		downloadMessService.save(downloadMess);
		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
		return result;
	}
	
	@ApiOperation(value = "下载语音", notes = "下载语音")
	@RequestMapping(value = "/020004", method = RequestMethod.POST)
	public ResponseBoxDto getMsgVoice(HttpServletRequest request,HttpServletResponse response,HttpSession session,@RequestBody MessReceiveDto messReceiveDto) throws IOException {
		JSONObject json = new JSONObject();
		Map<Object, Object> map =new HashMap<Object, Object>();
		map.put("wId",messReceiveDto.getwId());//微信实例ID
		map.put("msgId",messReceiveDto.getDataMsgId());//消息id
		map.put("length",messReceiveDto.getLength());//语音的长度
		map.put("bufId",messReceiveDto.getBufId());//xml中返回的bufId字段值
		map.put("fromUser",messReceiveDto.getDataFromUser());//发送者
		JsonConfig jsonConfig = new JsonConfig();
		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
		JSONObject resultJson =WKConfig.doPost("/getMsgVoice",json,request,Authorization);
		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
		System.out.println(resultJson1.get("url"));
		DownloadMess downloadMess=new DownloadMess();
		downloadMess.setDUrl(String.valueOf(resultJson1.get("url")));
		downloadMess.setDType(2);
		downloadMessService.save(downloadMess);
		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
		return result;
	}
	
	@ApiOperation(value = "下载视频", notes = "下载视频")
	@RequestMapping(value = "/020005", method = RequestMethod.POST)
	public ResponseBoxDto getMsgVideo(HttpServletRequest request,HttpServletResponse response,HttpSession session,@RequestBody MessReceiveDto messReceiveDto) throws IOException {
		JSONObject json = new JSONObject();
		Map<Object, Object> map =new HashMap<Object, Object>();
		map.put("wId",messReceiveDto.getwId());////微信实例ID
		map.put("msgId",messReceiveDto.getDataMsgId());//消息ID
		map.put("content",messReceiveDto.getDataContent());//收到的消息的xml数据
		JsonConfig jsonConfig = new JsonConfig();
		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
		JSONObject resultJson =WKConfig.doPost("/getMsgVideo",json,request,Authorization);
		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
		System.out.println(resultJson1.get("url"));
		DownloadMess downloadMess=new DownloadMess();
		downloadMess.setDUrl(String.valueOf(resultJson1.get("url")));
		downloadMess.setDType(3);
		downloadMessService.save(downloadMess);
		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
		return result;
	}
	
	@ApiOperation(value = "下载图片", notes = "下载图片")
	@RequestMapping(value = "/020006", method = RequestMethod.POST)
	public ResponseBoxDto getMsgImg(HttpServletRequest request,HttpServletResponse response,HttpSession session,@RequestBody MessReceiveDto messReceiveDto) throws IOException {
		JSONObject json = new JSONObject();
		Map<Object, Object> map =new HashMap<Object, Object>();
		map.put("wId",messReceiveDto.getwId());////微信实例ID
		map.put("msgId",messReceiveDto.getDataMsgId());//消息id
		map.put("fromUser",messReceiveDto.getDataFromUser());//发送人的微信号
		map.put("toUser",messReceiveDto.getDataToUser());//接收人的微信号
		map.put("content",messReceiveDto.getDataContent());//收到的消息的xml数据
		JsonConfig jsonConfig = new JsonConfig();
		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
		JSONObject resultJson =WKConfig.doPost("/getMsgImg",json,request,Authorization);
		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
		System.out.println(resultJson1.get("url"));
		DownloadMess downloadMess=new DownloadMess();
		downloadMess.setDUrl(String.valueOf(resultJson1.get("url")));
		downloadMess.setDType(4);
		downloadMessService.save(downloadMess);
		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
		return result;
	}
	
	@ApiOperation(value = "下载emoji", notes = "下载emoji")
	@RequestMapping(value = "/020007", method = RequestMethod.POST)
	public ResponseBoxDto getMsgEmoji(HttpServletRequest request,HttpServletResponse response,HttpSession session,@RequestBody MessReceiveDto messReceiveDto) throws IOException {
		JSONObject json = new JSONObject();
		Map<Object, Object> map =new HashMap<Object, Object>();
		map.put("wId",messReceiveDto.getwId());//微信实例id
		map.put("msgId",messReceiveDto.getDataMsgId());//消息id
		map.put("content",messReceiveDto.getDataContent());//收到emoji消息的xml数据
		JsonConfig jsonConfig = new JsonConfig();
		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
		JSONObject resultJson =WKConfig.doPost("/getMsgEmoji",json,request,Authorization);
		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
		System.out.println(resultJson1.get("url"));
		DownloadMess downloadMess=new DownloadMess();
		downloadMess.setDUrl(String.valueOf(resultJson1.get("url")));
		downloadMess.setDType(5);
		downloadMessService.save(downloadMess);
		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
		return result;
	}
	@ApiOperation(value = "获取聊天记录", notes = "获取聊天记录")
	@RequestMapping(value = "/020008", method = RequestMethod.POST)
	public ResponseBoxDto getAllReceMessage() {
		List<MessReceiving> list = messReceivingService.getGroupMessage();
		for (int i = 0; i < list.size(); i++) {
			
		}
		return ResponseBoxDto.setStatusEnum(list, ENUM_EXCEPTION_TYPE.SUCCESS);
	}
}
