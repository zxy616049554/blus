package com.rk.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rk.dto.ReplyMessageDTO;
import com.rk.dto.ReplyMessageRequestJson;
import com.rk.dto.SendMessDto;
import com.rk.entity.ReplyMessage;
import com.rk.entity.WxPlatform;
import com.rk.enums.ENUM_EXCEPTION_TYPE;
import com.rk.report.ResponseBoxDto;
import com.rk.service.ReplyMessageService;
import com.rk.service.WxPlatformService;
import com.rk.util.StringUtil;
import com.rk.util.WKConfig;

import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@RestController
@RequestMapping("/messagesSend")
public class MessagesSendController {

	@Autowired(required = true)
	private WxPlatformService wxPlatFormService;
	@Autowired
	private ReplyMessageService replyMessageService;
	
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

	@ApiOperation(value = "发送文本消息", notes = "发送文本消息")
	@RequestMapping(value = "/030001", method = RequestMethod.POST)
	public ResponseBoxDto getsendText(HttpServletRequest request,HttpSession session,@RequestBody SendMessDto sendMessDto) throws IOException {
		JSONObject json = new JSONObject();
		Map<Object, Object> map =new HashMap<Object, Object>();
		map.put("wId",sendMessDto.getwId());//微信实例ID
		map.put("wcId",sendMessDto.getWcId());//微信号/群号
		map.put("content",sendMessDto.getContent());//文本内容消息
		if(sendMessDto.getAt()!=null && sendMessDto.getAt()!=" ") {
		  map.put("at",sendMessDto.getAt()); //艾特 在群内艾特某人
		}
		JsonConfig jsonConfig = new JsonConfig();
		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
		JSONObject resultJson =WKConfig.doPost("/sendText",json,request,Authorization);
		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
		System.out.println(resultJson1.get("Authorization"));
		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
		return result;
	}
	
	@ApiOperation(value = "发送文件消息", notes = "发送文件消息")
	@RequestMapping(value = "/030002", method = RequestMethod.POST)
	public ResponseBoxDto getsendFile(HttpServletRequest request,HttpSession session) throws IOException {
		JSONObject json = new JSONObject();
		Map<Object, Object> map =new HashMap<Object, Object>();
		map.put("wId",session.getAttribute("wId"));//微信实例ID
		map.put("wcId",httpUrl);//好友号/群号
		map.put("path",httpUrl);//文件url链接
		map.put("fileName",httpUrl); //文件名
		JsonConfig jsonConfig = new JsonConfig();
		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
		JSONObject resultJson =WKConfig.doPost("/sendFile",json,request,Authorization);
		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
		System.out.println(resultJson1.get("Authorization"));
		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
		return result;
	}
	
	@ApiOperation(value = "发送图片消息", notes = "发送图片消息")
	@RequestMapping(value = "/030003", method = RequestMethod.POST)
	public ResponseBoxDto getsendImage(HttpServletRequest request,HttpSession session) throws IOException {
		JSONObject json = new JSONObject();
		Map<Object, Object> map =new HashMap<Object, Object>();
		map.put("wId",session.getAttribute("wId"));//微信实例ID
		map.put("wcId",httpUrl);//微信号/群号
		map.put("content",httpUrl);//图片url链接
		JsonConfig jsonConfig = new JsonConfig();
		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
		JSONObject resultJson =WKConfig.doPost("/sendImage",json,request,Authorization);
		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
		System.out.println(resultJson1.get("Authorization"));
		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
		return result;
	}
	
	@ApiOperation(value = "发送视频消息", notes = "发送视频消息")
	@RequestMapping(value = "/030004", method = RequestMethod.POST)
	public ResponseBoxDto getsendVideo(HttpServletRequest request,HttpSession session) throws IOException {
		JSONObject json = new JSONObject();
		Map<Object, Object> map =new HashMap<Object, Object>();
		map.put("wId",session.getAttribute("wId"));//微信实例ID
		map.put("wcId",httpUrl);//微信号/群号
		map.put("path",httpUrl);//视频url链接
		map.put("thumbPath",httpUrl);//视频封面url链接，可以自定义，也可以自己服务器获取视频首帧
		JsonConfig jsonConfig = new JsonConfig();
		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
		JSONObject resultJson =WKConfig.doPost("/sendVideo",json,request,Authorization);
		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
		System.out.println(resultJson1.get("Authorization"));
		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
		return result;
	}
	
	@ApiOperation(value = "发送语音消息", notes = "发送语音消息")
	@RequestMapping(value = "/030005", method = RequestMethod.POST)
	public ResponseBoxDto getsendVoice(HttpServletRequest request,HttpSession session) throws IOException {
		JSONObject json = new JSONObject();
		Map<Object, Object> map =new HashMap<Object, Object>();
		map.put("wId",session.getAttribute("wId"));//微信实例ID
		map.put("wcId",httpUrl);//单聊被发送人账号，群聊群账号
		map.put("content",httpUrl);//语音 silk格式
		map.put("length",httpUrl);//语音时长 xml数据中的voicelength字段
		JsonConfig jsonConfig = new JsonConfig();
		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
		JSONObject resultJson =WKConfig.doPost("/sendVoice",json,request,Authorization);
		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
		System.out.println(resultJson1.get("Authorization"));
		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
		return result;
	}
	
	@ApiOperation(value = "发送链接消息", notes = "发送链接消息")
	@RequestMapping(value = "/030006", method = RequestMethod.POST)
	public ResponseBoxDto getsendUrl(HttpServletRequest request,HttpSession session) throws IOException {
		JSONObject json = new JSONObject();
		Map<Object, Object> map =new HashMap<Object, Object>();
		map.put("wId",session.getAttribute("wId"));//微信实例ID
		map.put("wcId",httpUrl);//微信号/群号
		map.put("title",httpUrl);//标题
		map.put("url",httpUrl);//链接
		map.put("description",httpUrl);//描述
		map.put("thumbUrl",httpUrl);//图标url
		JsonConfig jsonConfig = new JsonConfig();
		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
		JSONObject resultJson =WKConfig.doPost("/sendUrl",json,request,Authorization);
		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
		System.out.println(resultJson1.get("Authorization"));
		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
		return result;
	}
	
	@ApiOperation(value = "发送名片消息", notes = "发送名片消息")
	@RequestMapping(value = "/030007", method = RequestMethod.POST)
	public ResponseBoxDto getsendNameCard(HttpServletRequest request,HttpSession session) throws IOException {
		JSONObject json = new JSONObject();
		Map<Object, Object> map =new HashMap<Object, Object>();
		map.put("wId",session.getAttribute("wId"));//微信实例ID
		map.put("wcId",httpUrl);//发送人微信号（群/好友）
		map.put("nameCardId",httpUrl);//要发送的名片微信号
		JsonConfig jsonConfig = new JsonConfig();
		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
		JSONObject resultJson =WKConfig.doPost("/sendNameCard",json,request,Authorization);
		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
		System.out.println(resultJson1.get("Authorization"));
		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
		return result;
	}
	
	@ApiOperation(value = "发送Emoji消息", notes = "发送Emoji消息")
	@RequestMapping(value = "/030008", method = RequestMethod.POST)
	public ResponseBoxDto getsendEmoji(HttpServletRequest request,HttpSession session) throws IOException {
		JSONObject json = new JSONObject();
		Map<Object, Object> map =new HashMap<Object, Object>();
		map.put("wId",session.getAttribute("wId"));//微信实例ID
		map.put("wcId",httpUrl);//微信号/群号
		map.put("imageMd5",httpUrl);//回调中xml中的md5字段值
		map.put("imgSize",httpUrl);//回调中xml中的len字段值
		JsonConfig jsonConfig = new JsonConfig();
		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
		JSONObject resultJson =WKConfig.doPost("/sendEmoji",json,request,Authorization);
		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
		System.out.println(resultJson1.get("Authorization"));
		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
		return result;
	}
	
	@ApiOperation(value = "发送小程序消息", notes = "发送小程序消息")
	@RequestMapping(value = "/030009", method = RequestMethod.POST)
	public ResponseBoxDto getsendApp(HttpServletRequest request,HttpSession session) throws IOException {
		JSONObject json = new JSONObject();
		Map<Object, Object> map =new HashMap<Object, Object>();
		map.put("wId",session.getAttribute("wId"));//微信实例ID
		map.put("wcId",httpUrl);//微信号/群号
		map.put("content",httpUrl);//小程序xml回调内容
		JsonConfig jsonConfig = new JsonConfig();
		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
		JSONObject resultJson =WKConfig.doPost("/sendApp",json,request,Authorization);
		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
		System.out.println(resultJson1.get("Authorization"));
		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
		return result;
	}
	
	@ApiOperation(value = "发送已经收到的图片消息", notes = "发送已经收到的图片消息")
	@RequestMapping(value = "/030010", method = RequestMethod.POST)
	public ResponseBoxDto getsendRecvImage(HttpServletRequest request,HttpSession session) throws IOException {
		JSONObject json = new JSONObject();
		Map<Object, Object> map =new HashMap<Object, Object>();
		map.put("wId",session.getAttribute("wId"));//微信实例ID
		map.put("wcId",httpUrl);//微信号/群号
		map.put("content",httpUrl);//xml图片内容 
		JsonConfig jsonConfig = new JsonConfig();
		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
		JSONObject resultJson =WKConfig.doPost("/sendRecvImage",json,request,Authorization);
		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
		System.out.println(resultJson1.get("Authorization"));
		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
		return result;
	}
	
	@ApiOperation(value = "发送已经收到的视频消息", notes = "发送已经收到的视频消息")
	@RequestMapping(value = "/030011", method = RequestMethod.POST)
	public ResponseBoxDto getsendRecvVideo(HttpServletRequest request,HttpSession session) throws IOException {
		JSONObject json = new JSONObject();
		Map<Object, Object> map =new HashMap<Object, Object>();
		map.put("wId",session.getAttribute("wId"));//微信实例ID
		map.put("wcId",httpUrl);//微信号/群号
		map.put("content",httpUrl);//xml视频内容 
		JsonConfig jsonConfig = new JsonConfig();
		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
		JSONObject resultJson =WKConfig.doPost("/sendRecvVideo",json,request,Authorization);
		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
		System.out.println(resultJson1.get("Authorization"));
		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
		return result;
	}
	
	@ApiOperation(value = "发送已经收到的文件消息", notes = "发送已经收到的文件消息")
	@RequestMapping(value = "/030012", method = RequestMethod.POST)
	public ResponseBoxDto getsendRecvFile(HttpServletRequest request,HttpSession session) throws IOException {
		JSONObject json = new JSONObject();
		Map<Object, Object> map =new HashMap<Object, Object>();
		map.put("wId",session.getAttribute("wId"));//微信实例ID
		map.put("wcId",httpUrl);//单聊被发送人账号 群聊群账号
		map.put("path",httpUrl);//文件url链接
		map.put("fileName", httpUrl);//文件名
		JsonConfig jsonConfig = new JsonConfig();
		json = JSONObject.fromObject(map, jsonConfig);// stringBuffer
		JSONObject resultJson =WKConfig.doPost("/sendFile",json,request,Authorization);
		JSONObject resultJson1 = (JSONObject) resultJson.get("data");
		System.out.println(resultJson1.get("Authorization"));
		ResponseBoxDto result = ResponseBoxDto.setStatusEnum(resultJson1, ENUM_EXCEPTION_TYPE.SUCCESS);
		return result;
	}
	@ApiOperation(value = "获取所有对应消息", notes = "获取所有对应消息")
	@RequestMapping(value = "/030013", method = RequestMethod.POST)
	public ResponseBoxDto getAllMessage() {
		List<ReplyMessage> list = replyMessageService.findAll();
		return ResponseBoxDto.setStatusEnum(list, ENUM_EXCEPTION_TYPE.SUCCESS);
	}
	@ApiOperation(value = "根据ID获取某条消息", notes = "根据ID获取某条消息")
	@RequestMapping(value = "/030014", method = RequestMethod.POST)
	public ResponseBoxDto getMessageById(@RequestBody ReplyMessageRequestJson json) {
		ReplyMessageDTO dto = json.getRequestBody();
		ReplyMessage dao = replyMessageService.get(dto.getReplyId());
		return ResponseBoxDto.setStatusEnum(dao, ENUM_EXCEPTION_TYPE.SUCCESS);
	}
	@ApiOperation(value = "根据ID修改消息", notes = "根据ID修改消息")
	@RequestMapping(value = "/030015", method = RequestMethod.POST)
	public ResponseBoxDto updateMessageById(@RequestBody ReplyMessageRequestJson json) {
//		try {
			ReplyMessageDTO dto = json.getRequestBody();
			ReplyMessage dao = new ReplyMessage();
			dao.setReplyId(dto.getReplyId());
			if(StringUtil.isNotEmpty(dto.getReceiveMess())) {
				dao.setReceiveMess(dto.getReceiveMess());
			}
			if(StringUtil.isNotEmpty(dto.getReplyMess())) {
				dao.setReplyMess(dto.getReplyMess());
			}
			replyMessageService.update(dao);
			return ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
//		} catch (Exception e) {
//			return ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.FAILURE);
//		}
	}
	@ApiOperation(value = "增加消息回復", notes = "增加消息回復")
	@RequestMapping(value = "/030016", method = RequestMethod.POST)
	public ResponseBoxDto saveMessage(@RequestBody ReplyMessageRequestJson json) {
		ReplyMessageDTO dto = json.getRequestBody();
		ReplyMessage dao = new ReplyMessage();
		dao.setReplyId(dto.getReplyId());
		if(StringUtil.isNotEmpty(dto.getReceiveMess())) {
			dao.setReceiveMess(dto.getReceiveMess());
		}
		if(StringUtil.isNotEmpty(dto.getReplyMess())) {
			dao.setReplyMess(dto.getReplyMess());
		}
		replyMessageService.save(dao);
		return ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
	}
	@ApiOperation(value = "根据ID删除某条消息", notes = "根据ID删除某条消息")
	@RequestMapping(value = "/030017", method = RequestMethod.POST)
	public ResponseBoxDto delete(@RequestBody ReplyMessageRequestJson json) {
		ReplyMessageDTO dto = json.getRequestBody();
		replyMessageService.delete(dto.getReplyId());
		return ResponseBoxDto.setStatusEnum(null, ENUM_EXCEPTION_TYPE.SUCCESS);
	}
}
