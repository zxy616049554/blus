package com.rk.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.bouncycastle.jce.provider.BrokenJCEBlockCipher.BrokePBEWithMD5AndDES;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.rk.entity.WxPlatform;
import com.rk.service.WxPlatformService;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class WKConfig {
	
	
	
//	@Autowired(required = true)
//	private static WxPlatformService wxPlatFormService;
//
//	public static List<WxPlatform> getPlatformk(){
//	  List<WxPlatform> wxPlatform = wxPlatFormService.findAll();
//	  System.out.println(wxPlatform);
//	  return wxPlatform;
//	}	
//	
//	
//	public static String account = getPlatformk().get(0).getPlatformAccount();
//	public static String password = getPlatformk().get(0).getPlatformPassword();
//	public static int type=2;
//	public static String Authorization = getPlatformk().get(0).getPlatformKey();
//	public static String httpUrl="http://192.168.40.14:18081/userInfo/webHttpTest";
	
	private static Logger log=org.slf4j.LoggerFactory.getLogger(WKConfig.class);
	
	public static JSONObject doPost(String method, JSONObject date, HttpServletRequest request,String Authorization) {
		HttpClient client = HttpClients.createDefault();
		// 将接口地址和接口方法拼接起来
		String url = "http://39.106.205.87:7415/" + method;
		HttpPost post = new HttpPost(url);
		JSONObject response = null;
		try {
			StringEntity s = new StringEntity(date.toString(),Charset.forName("UTF-8"));
			s.setContentEncoding("UTF-8");
			post.setEntity(s);
			post.setHeader("Accept", "application/json");
			post.addHeader("content-type", "application/json;charset=UTF-8");
			if(StringUtil.isNotEmpty(Authorization)) {
				post.addHeader("Authorization", Authorization);
			}
			// 调用Fa接口
			HttpResponse res = client.execute(post);
			String response1 = EntityUtils.toString(res.getEntity());
			System.out.println("************");
			System.out.println(response1);
			log.debug(response1);
		    response = JSONObject.fromObject(response1);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return response;
	}
	
	public static JSONObject doPost1(String method, JSONObject date, HttpServletRequest request,String Authorization) {
			HttpClient client = HttpClients.createDefault();
			// 将接口地址和接口方法拼接起来
			String url = method;
			HttpPost post = new HttpPost(url);
			JSONObject response = null;
			try {
				StringEntity s = new StringEntity(date.toString());
				s.setContentEncoding("UTF-8");
				s.setContentType("application/json");
				post.setEntity(s);
				post.addHeader("content-type", "application/json");
				if(StringUtil.isNotEmpty(Authorization)) {
					post.addHeader("Authorization", Authorization);
				}
				// 调用Fa接口
				HttpResponse res = client.execute(post);
				String response1 = EntityUtils.toString(res.getEntity());
				System.out.println("************");
				System.out.println(response1);
			    response = JSONObject.fromObject(response1);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			return response;
	}
	public static JSONObject doGet(String content, HttpServletRequest request) throws UnsupportedEncodingException {
		HttpClient client = HttpClients.createDefault();
		// 将接口地址和接口方法拼接起来
		String replyContent=URLEncoder.encode(content,"utf-8");
		String url = "http://api.qingyunke.com/api.php?key=free&appid=0&msg=" + replyContent;
		HttpGet post = new HttpGet(url);
		JSONObject response = null;
		try {
			post.setHeader("Accept", "application/json");
			post.addHeader("content-type", "application/json;charset=UTF-8");
			// 调用Fa接口
			HttpResponse res = client.execute(post);
			String response1 = EntityUtils.toString(res.getEntity());
			System.out.println("************");
			System.out.println(response1);
		    response = JSONObject.fromObject(response1);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return response;
	}
	public static JSONObject doGet1(String content, HttpServletRequest request) throws UnsupportedEncodingException {
		HttpClient client = HttpClients.createDefault();
		// 将接口地址和接口方法拼接起来
		String replyContent=URLEncoder.encode(content,"utf-8");
		String url = "http://api.tianapi.com/txapi/xingzuo/index?key=54e128e52659e5795fb21af3e8986684&me=" + replyContent;
		HttpGet post = new HttpGet(url);
		JSONObject response = null;
		try {
			post.setHeader("Accept", "application/json");
			post.addHeader("content-type", "application/json;charset=UTF-8");
			// 调用Fa接口
			HttpResponse res = client.execute(post);
			String response1 = EntityUtils.toString(res.getEntity());
			System.out.println("************");
			System.out.println(response1);
		    response = JSONObject.fromObject(response1);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return response;
	}
	public static void sendText(Map<Object,Object> map,String Authorization) {
		WKConfig.doPost("/sendText",JSONObject.fromObject(map, new JsonConfig()),null,Authorization);
	}
	public static void sendVoice(Map<Object,Object> map,String Authorization) {
		WKConfig.doPost("/sendVoice",JSONObject.fromObject(map, new JsonConfig()),null,Authorization);
	}
	public static void sendEmoji(Map<Object,Object> map,String Authorization) {
		WKConfig.doPost("/sendEmoji",JSONObject.fromObject(map, new JsonConfig()),null,Authorization);
	}
	public static void sendImage(Map<Object,Object> map,String Authorization) {
		WKConfig.doPost("/sendImage",JSONObject.fromObject(map, new JsonConfig()),null,Authorization);
	}
	public static void sendUrl(Map<Object,Object> map,String Authorization) {
		WKConfig.doPost("/sendUrl",JSONObject.fromObject(map, new JsonConfig()),null,Authorization);
	}
	public static void sendVideo(Map<Object,Object> map,String Authorization) {
		WKConfig.doPost("/sendVideo",JSONObject.fromObject(map, new JsonConfig()),null,Authorization);
	}
}
