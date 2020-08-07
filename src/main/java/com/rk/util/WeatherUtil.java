package com.rk.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSON;
import com.rk.dto.WeatherDto;


public class WeatherUtil {
    /**
     * 获取城市代码
     *
     * @param cityName
     * @return 城市代码
     */
    public static String getCityCode(String cityName) {
        // 定义城市代码
        String cityCode = "";
        try {
            // 定义url字符串
            String strUrl = "http://toy1.weather.com.cn/search?cityname=" + URLEncoder.encode(cityName, "utf-8");
            // 创建URL对象
            URL url = new URL(strUrl);
            // 利用URL对象获取HTTP连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 建立物理连接
            conn.connect();
            // 获取响应码
            int responseCode = conn.getResponseCode();
            // 判断连接是否成功
            if (responseCode == HttpURLConnection.HTTP_OK) { // 200
                // 获取字节输入流
                InputStream is = conn.getInputStream();
                // 定义字节数组用于保存字节输入流的全部内容
                byte[] buffer = new byte[is.available()];
                // 判断城市是否存在
                if (is.available() > 4) {
                    // 读取字节输入流全部信息，保存到buffer
                    is.read(buffer);
                    // 获取响应信息字符串
                    String response = new String(buffer, "utf-8");
                    // 截取出城市代码
                    cityCode = response.substring(10, 19);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
 
        // 返回城市代码
        return cityCode;
    }
 
    /**
     * 通过城市名获取天气信息
     *
     * @param cityName
     * @return 天气信息
     */
    public static HashMap<String, String> getWeatherInfo(String cityName) throws JSONException {
        // 定义天气信息哈希映射
        HashMap<String, String> weather = new HashMap<>();
 
        try {
            // 获取城市代码
            String cityCode = getCityCode(cityName);
            // 定义url字符串
            String strUrl="http://www.weather.com.cn/weather/"+cityCode+".shtml";
            // 创建URL对象
            URL url = new URL(strUrl);
            // 创建http连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // 建立连接
            connection.connect();
            // 获取响应码
            int code = connection.getResponseCode();
            // 判断是否连接成功
            if (code == 200) {
                // 获取字节输入流
                InputStream is = connection.getInputStream();
                // 创建字节数组用于保存响应信息
                byte[] buffer = new byte[is.available()];
                // 读取字节输入流全部信息
                is.read(buffer);
                // 获取响应信息字符串
                String response = new String(buffer, "utf-8");
                System.out.println(response);
//                Document doc = Jsoup.parse(response);
//                System.out.println(doc);
//                Element masthead = doc.select("div#7d").first();
//                System.out.println(masthead);
//                Elements mastheadinput=masthead.select("input#zs_7d_update_time");
//                System.out.println(mastheadinput);
//                Element mastheadul = doc.select("ul.t").first();
//                System.out.println(mastheadul);
//                Element mastheadp = mastheadul.select("p").first();
//                System.out.println(mastheadp);
//                Elements h1=masthead.select("h1");
//                System.out.println(h1);
                // 将json字符串转换成json对象
//                JSONObject weatherInfoJsonObj = new JSONObject(masthead);
//                System.out.println(weatherInfoJsonObj);
//                JSONObject cityInfoJsonObj = new JSONObject(weatherInfoJsonObj.getString("cityInfo"));
//                weather.put("城市：", cityInfoJsonObj.getString("parent") + " " + cityInfoJsonObj.getString("city") + "[" + cityCode + "]");
//                JSONArray forcastJsonArr = new JSONArray(new JSONObject(weatherInfoJsonObj.getString("data")).getString("forecast"));
//                weather.put("时间：", weatherInfoJsonObj.getString("time") + " " + forcastJsonArr.getJSONObject(0).getString("week"));
//                weather.put("温度：", forcastJsonArr.getJSONObject(0).getString("high") + " " + forcastJsonArr.getJSONObject(0).getString("low"));
//                weather.put("天气：", forcastJsonArr.getJSONObject(0).getString("type"));
//                HashMap<String, String> weathesr = getWeatherInfo(cityName);
//                for (String key: weathesr.keySet()) {
//                	System.out.println(key + weathesr.get(key));
//                }
            } else {
                weather.put("错误：", "[" + cityName + "]不存在！");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
 
        // 返回天气信息哈希映射
        return weather;
    }

    public static void main(String[] args) throws IOException, InterruptedException {    	
//    	System.out.println(getWeatherInfo("新疆"));
//    	String n="@.?广州天气";
//    	int wean=n.indexOf("天气");
//    	String subStr=n.substring(wean, wean+2);
//    	String a="<msgsource>\\n\\t<atuserlist>wxid_hg56woy7pobj22</atuserlist>\\n\\t<bizflag>0</bizflag>\\n\\t<silence>0<\\/silence>\\n\\t<membercount>10<\\/membercount>\\n<\\/msgsource>\\n";
//    	Document doc = Jsoup.parse(a.toString());//补全html
//    	String masthead = doc.select("atuserlist").first().text();//获取div id为7d的内容
//    	System.out.println(masthead);
//    	System.out.println(doc);
//    	read1("?天");
    	

	}
   
    /**
     * 获取返回数据中的一个属性示例,此处以获取今日温度为例
     * "temperature": "8℃~20℃"     今日温度
     * @param args
     * @return 
     * @throws IOException 
     */
    public static String GetTodayTemperatureByCity(String city) throws IOException {
    	String result="http://v.juhe.cn/weather/index?cityname="+city+"&key="+"e58a3b13189aedb8f7a6713bf1da1df3" ;
    	 URL url = new URL(result);
         // 创建http连接
         HttpURLConnection connection = (HttpURLConnection) url.openConnection();
         // 建立连接
         connection.connect();
         // 获取响应码
         int code = connection.getResponseCode();
         // 判断是否连接成功
         if (code == 200) {
             // 获取字节输入流
             InputStream is = connection.getInputStream();
             // 创建字节数组用于保存响应信息
             byte[] buffer = new byte[is.available()];
             // 读取字节输入流全部信息
             is.read(buffer);
             String response = new String(buffer, "utf-8");  
             if(response!=null){
            	 net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(response);  
            	 System.out.println(json);
	            /*获取返回状态码*/
	            result=json.getString("resultcode");
	            /*如果状态码是200说明返回数据成功*/
	            if(result!=null&&result.equals("200")){
	                result=json.getString("result");
	                //此时result中数据有多个key,可以对其key进行遍历,得到对个属性
	                json=net.sf.json.JSONObject.fromObject(result);
	                //今日温度对应的key是today
	                result=json.getString("future");
	                json=net.sf.json.JSONObject.fromObject(result);
	                Iterator iterator = json.keys();
			        while(iterator.hasNext()){
		                String key = (String) iterator.next();
		                System.out.println(key);
		                String value = json.getString(key);
		                System.out.println(value);
		                json=net.sf.json.JSONObject.fromObject(key);
		                Iterator iterators = json.keys();
				        while(iterators.hasNext()){
				        	 String keys = (String) iterator.next();
				             System.out.println(keys);
				             String values = json.getString(keys);
				             System.out.println(values);
				        }
		               
			        }
//	                System.out.println(json);
//	                result=json.getString("today");
//	                json=net.sf.json.JSONObject.fromObject(result);
	                //今日温度对应当key是temperature
//	                result=json.getString("temperature");
	                
	                return result;
	            }
            }
         }
        return result;
    }
    
    public static List<String> read1(String urlName) {
		URL url = null;
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		StringBuffer sb = new StringBuffer();
		String cityCode = getCityCode(urlName);
        // 定义url字符串
        String strUrl="http://www.weather.com.cn/weather/"+cityCode+".shtml";
		List<String> liStrings=new ArrayList<String>();
		try {
			url = new URL(strUrl);
			// 创建http连接
	         HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	         // 建立连接
	         connection.connect();
	         // 获取响应码
	         int code = connection.getResponseCode();
	         // 判断是否连接成功
			is = url.openStream();
			if(code==200) {
				isr = new InputStreamReader(is,"utf-8");
				br = new BufferedReader(isr);
				char[] c = new char[1024];
				while(br.read(c) != -1) {
					final String ss = new String(c);
					sb.append(ss);
				}
				Document doc = Jsoup.parse(sb.toString());//补全html
	            Element masthead = doc.select("div#7d").first();//获取div id为7d的内容
	            String mastheadinput=masthead.select("input#fc_24h_internal_update_time").val();//获取修改日期的值
	            System.out.println(mastheadinput);
	            Element mastheadul = doc.select("ul.t").first();//获取ul标签的内容
	            String dataYearMonth=mastheadinput.substring(0, 4)+"年"+mastheadinput.substring(4, 6)+"月";
	            Elements h1All=masthead.select("h1");
            	Elements mastheadpWeaAll = mastheadul.select("p.wea");
        		Elements mastheadpTemAll = mastheadul.select("p.tem");
    			Elements mastheadpwinAll = mastheadul.select("p.win");
	            for(int j=0;j<h1All.size();j++) {	
	            	String h1Data=h1All.get(j).text();
	            	String h1DataAll=h1Data.substring(0, 2);
	            	String spanAll=mastheadpTemAll.get(j).select("span").text();
	            	if(!StringUtil.isEmpty(spanAll)) {
	            		spanAll=spanAll+"-";
	            	}
	            	String all=dataYearMonth+h1DataAll+" 天气："+mastheadpWeaAll.get(j).text()+" 气温："+spanAll+mastheadpTemAll.get(j).select("i").text()+"风力"+mastheadpwinAll.get(j).select("i").text()+"\n";
	            	liStrings.add(all);
	            	System.out.println(all);
	            }
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return liStrings;
	}
}
