package com.rk.util;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class BaseUtil {
	static Logger logger = LoggerFactory.getLogger(BaseUtil.class);
	
	/**
     * BASE64加密
     *
     * @param data 需要加密的数据
     * @return 加密之后的结果
     */
    public static String encryptBASE64(String data) {
        try {
            byte[] key = data.getBytes("utf-8");
            return (new BASE64Encoder()).encodeBuffer(key);
        } catch (Exception e) {
        	logger.info("base64加密抛出异常：" + e);
        }
        return null;
    }
 
 
    /**
     * BASE64解密
     *
     * @param data 需要解密的数据
     * @return 解密之后的数据
     */
    public static String decryptBASE64(String data) {
        try {
            byte[] key = (new BASE64Decoder()).decodeBuffer(data);
            return new String(key,"utf-8");
        } catch (IOException e) {
        	logger.info("base64解密抛出异常：" + e);
        }
        return null;
    }

}
