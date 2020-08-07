package com.rk.util;

public class AlipayConfig {
	// 1.商户appid,使用商户自己的appid即可
    public static String APPID = "2021001156626192";
 
    //2.私钥 pkcs8格式的，与在支付宝存储的公钥对应
    public static String APP_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCJv+93aTqYzkI5feUpajU9bxgDBH2EmmQQBtbGc50L+IvxhSn8R2wokjKuVRQ39YfG6yA097yBwH9l8z2UB73dmo5yNVsQM50UOkI0SFb0vUlSk/UTDU9Y8D0EXP8n/oXvBbVZ9S4RO9mM2bDoszqIcJrVHIh7Fy/0Yhx5xFl+hAO0CbLuKE9Jk1/rmFRP/Zcb4v5P3UV4JLfliNw6jWjfE6ZX0eKgSIfqaRbUhHKOxcncN0fsNdpEwf3skLcM+EFvzuO42u5pkTKxtO+zcH8T5LYEsLiGSCq4KjbJ/Yu6TngWvlaIPYdR0eXA7jCyEuB7tYHg9YqYNerfuW+KdkX1AgMBAAECggEAdgNlEmWp2Q4keILeWNvwHuDVrogjTP7EqWJikQSt0Hqw0ktmzAcYp62RFAA54TNOjuIT5q0z5+nWe66n2/HB/WATl88UZE4DzjeXTpmHPggJEGFIPnlRuH2hmOJfi5Eoh73ZP+eo/wTKfjxmrwYWKJxoWhom52XXJMUFZmEM1zDZdGnuiJOrdFtli6riBETJEUbZCN4YEcd/SDH0n9sAf19qDCK4cT809lOL28gqEddKKxQt1eTFZGbm8+eayQ8sheo7DlP+UwZ52NBScRGDEkNpw8QG3AQQAZh8StDwWQcYXxCcT9YY4EIDq+SIq3gJare0lV9rtPNe4U3sWoUBZQKBgQDPMH+UYiTTZX0QXp99q61wM7I1JsRSR4C49kk/L3duLLMixY7iYay6iTVzVRPx+M07Ta3hIQY4f6Pb5oTQ4DfLuhfjKsRP2VZkGOCE8y+cJ5hv2yb8HLOpBWZLGnuuLDBTIzPEYc4mlFI0vVfLyVULd9hp9Trndw5VVskWK+8jMwKBgQCqM49ua/pYvfGB2bAC+jNW1x7OcRKP/GCjfvmq56kaMkZautCKzVlBiLiF9cs3zZqTxWiCjlCDcI5+n2Dqja1Ostyb1YHsXa4NcXntxRUHQPuvfCbe0DftPBbmFc6M3dVVHw1joxWTee2Xz0tJxpoEnxzZ/5lifQSte5EeAfNyNwKBgBTkzijXkOEkne0Vrb3Nn0ruW61SgH/PqCJ+67oMfl3E8jeydotL0FBrX9GGpeFD9uZhGgTXALqX3J1jbMdnpRjNZnVJLczjnPuxKosdvxJfR9cX4aGQ/2kvGza9r/VLjYAEz8ahGln3oSaiZYUug34JkvB0g4EuF2rxuoua2J3TAoGAH9WZBExJ/QM6AtGWrFfj9mAyMObRVAEeijYbuK2v4Wh55EqA6xVRloJeESBSLFPWj8EitRitUohg/qoM1JI/UjL+NF+Q94bmITmSVrjAICU1bjNIifBkJYlRtyNpCMwNNOij9njwdi8eE4EAUgJFmIuyN8wpAlTj5Rbxo601va0CgYBWm7JpVxkwNJ2uvtQ8WJtVbSpDi+KKPxObAJjHxl14nxH8FMthw+yotLHCi4U06gHEpguwk4t0u7YIoKy23RVNIln+TKuvYP8oCGyVdKUgqWGiBPjdSOAHEDcp/r4x7keACxOyxdNiOfXekTsEp7kFE/tqsCCTlLd+q6lZsSdiyg==";
 
    // 3.支付宝公钥，支付宝生成的公钥，切勿与商户公钥混淆
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAib/vd2k6mM5COX3lKWo1PW8YAwR9hJpkEAbWxnOdC/iL8YUp/EdsKJIyrlUUN/WHxusgNPe8gcB/ZfM9lAe93ZqOcjVbEDOdFDpCNEhW9L1JUpP1Ew1PWPA9BFz/J/6F7wW1WfUuETvZjNmw6LM6iHCa1RyIexcv9GIcecRZfoQDtAmy7ihPSZNf65hUT/2XG+L+T91FeCS35YjcOo1o3xOmV9HioEiH6mkW1IRyjsXJ3DdH7DXaRMH97JC3DPhBb87juNruaZEysbTvs3B/E+S2BLC4hkgquCo2yf2Luk54Fr5WiD2HUdHlwO4wshLge7WB4PWKmDXq37lvinZF9QIDAQAB";
 
    // 4.服务器异步通知页面路径 需http://或者https://格式的完整路径，必须外网可以正常访问，可以使用natapp进行外网映射
    public static String notify_url = "http://47.111.150.11:8080/shareVideo/goods/020021";
 
    //5.页面跳转同步通知页面路径 需http://或者https://格式的完整路径，必须外网可以正常访问 商户可以自定义同步跳转地址
    public static String return_url = "http://www.xxx.com/alipay/return_url.do";
 
    // 6.请求支付宝的网关地址,此处为沙箱测试地址，正式环境替换即可
    public static String URL = "https://openapi.alipaydev.com/gateway.do";
 
    // 7.编码
    public static String CHARSET = "UTF-8";
 
    // 8.返回格式
    public static String FORMAT = "json";
 
    // 9.加密类型
    public static String SIGNTYPE = "RSA2";
}
