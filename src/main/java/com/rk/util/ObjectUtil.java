/**
 * @Copyright ®2018 gjmctp Co. Ltd. All rights reserved.<br/>
 * 项目名称 : 国金金属商品交易平台
 * 创建日期 : 2018年5月25日
 * 修改历史 : 
 *     1. [2018年5月25日]创建文件 by gjmctp
 */
package com.rk.util;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rk.exception.BaseException;


public class ObjectUtil {

	private static final Logger log = LoggerFactory.getLogger(ObjectUtil.class);
	
    public static final BigDecimal ZERO = new BigDecimal("0");

    /** 
     * 判断对象或对象数组中每一个对象是否为空: 对象为null，字符序列长度为0，集合类、Map为empty 
     *  
     * @param obj 
     * @return 
     */
    @SuppressWarnings("rawtypes")
    public static boolean isNullOrEmpty(Object obj) {
        if (obj == null) {
            return true;
        }

        if (obj instanceof CharSequence) {
            return ((CharSequence) obj).length() == 0;
        }

        if (obj instanceof Collection) {
            return ((Collection) obj).isEmpty();
        }

        if (obj instanceof Map) {
            return ((Map) obj).isEmpty();
        }

        if (obj instanceof Object[]) {
            Object[] object = (Object[]) obj;
            if (object.length == 0) {
                return true;
            }
            boolean empty = true;
            for (int i = 0; i < object.length; i++) {
                if (!isNullOrEmpty(object[i])) {
                    empty = false;
                    break;
                }
            }
            return empty;
        }
        return false;
    }

    public static BigDecimal convertValue(BigDecimal decimal) {

        return decimal != null ? decimal : BigDecimal.valueOf(0);
    }

    public static Object getReflectValue(Object item, String name) {
        Method m;
        Object value = "";
        try {
            m = item.getClass().getMethod("get" + captureName(name));
            value = m.invoke(item);
        } catch (Exception e) {
        	log.error("系统异常，异常信息为：{}]",e.getMessage());
			throw new BaseException(e);
        }
        return value;
    }

    /**
     * 
    * @2018年2月20日
    * @user caoyk
    * @Title: captureName 
    * @Description: 将字符串name 转化为首字母大写
    * @param @param name
    * @param @return    设定文件 
    * @return String    返回类型 
    * @throws
     */
    public static String captureName(String name) {
        char[] cs = name.toCharArray();
        cs[0] -= 32;
        return String.valueOf(cs);
    }

    public static boolean isUpdateChnName(String basedField, Object target) {
        boolean flag = false;
        if (!ObjectUtil.isNullOrEmpty(basedField) && !ObjectUtil.getReflectValue(target, basedField).equals(ZERO)) {
            flag = true;
        }
        return flag;
    }

    /**
     * 将DTO中的值按照参数传递给Entity，要求两边的字段要一致
     * @param a  取值的对象
     * @param b  存放值的对象
     * return  Obejct 
     */
    @SuppressWarnings("rawtypes")
    public static Object syncDtoToEntity(Object a, Object b) {
        try {
            Class A = a.getClass();
            Class B = b.getClass();
            Field[] fa = A.getDeclaredFields();
            for (int i = 0; i < fa.length; i++) {
                try {
                    Field f1 = fa[i];
                    f1.setAccessible(true);
                    Object val = f1.get(a);
                    Field[] fb = B.getDeclaredFields();
                    for (int j = 0; j < fb.length; j++) {
                        Field f2 = fb[j];
                        if (f2.getName().equalsIgnoreCase(f1.getName())) {
                            f2.setAccessible(true);
                            f2.set(b, val);
                        }
                    }
                } catch (Exception ex) {
                    continue;
                }
            }
        } catch (Exception e) {
        	log.error("系统异常，异常信息为：{}]",e.getMessage());
            return null;
        }
        return b;
    }

    /** 
    * @Description: 后台运营
    * @Param: 
    * @return:
    */
    public static String toString(Object obj) {
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    public static Long toLong(Object obj) {
        if (obj == null) {
            return 0L;
        }
        if (obj instanceof Double || obj instanceof Float) {
           // return Long.valueOf(StringUtils.substringBefore(obj.toString(), "."));
            return Long.valueOf(StringUtil.subStringExe(obj.toString(),"",".",""));
        }
        if (obj instanceof Number) {
            return Long.valueOf(obj.toString());
        }
        if (obj instanceof String) {
            return Long.valueOf(obj.toString());
        } else {
            return 0L;
        }
    }

    public static Integer toInt(Object obj) {
        return toLong(obj).intValue();
    }
    

}
