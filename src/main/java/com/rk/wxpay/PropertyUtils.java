package com.rk.wxpay;

public class PropertyUtils {
	//#微信开放平台审核通过的应用APPID
	public static String unifiedorder = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	public static String orderquery = "https://api.mch.weixin.qq.com/pay/orderquery";
	public static String appid="wx040aed7a11a3ae05";
	//#商户号
	public static String mch_id="1537700541";
	//#AppSecret
	public static String AppSecret="969765d004d72e77c2d1499e90d90d63";
	//#API密钥
	public static String PrivateKey="izouyunjiazu2019izouyunjiazu2019";
	//#微信服务器主动通知商户服务器里指定的页面http/https路径。建议商户使用https  http://148.70.12.158:8080/shareVideo/goods/020012
	public static String tentpay_notify_url="http://148.70.12.158:80/shareVideo/goods/020012"; 
	//http://39.100.151.118/shareVideo/goods/020012
	//http://148.70.12.158:80/shareVideo/goods/020012
	//#本应用IP39.100.151.118/shareVideo
	public static String spbillCreateIp="148.70.12.158";
}
