/**
 * @Copyright ®2018 gjmctp Co. Ltd. All rights reserved.<br/>
 * 项目名称 : 国金金属商品交易平台
 * 创建日期 : 2018年5月25日
 * 修改历史 : 
 *     1. [2018年5月25日]创建文件 by gjmctp
 */
package com.rk.util;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 字符串处理及转换工具类 【类或接口功能描述】
 * 
 * @author gjmctp
 * @date 2018年3月9日 上午10:48:24
 * @version V1.0
 */
public class StringUtil {
	/**
	 * 判断是否数字表示
	 */
	public static Pattern numericPattern = Pattern.compile("^[0-9\\-]+$");

	/**
	 * 判断是否数字
	 */
	public static Pattern numericStringPattern = Pattern.compile("^[0-9\\-\\-]+$");
	/**
	 * 判断是否浮点数字表示
	 */
	public static Pattern floatNumericPattern = Pattern.compile("^[0-9\\-\\.]+$");

	/**
	 * 判断是否纯字母组合
	 */
	public static Pattern abcPattern = Pattern.compile("^[a-z|A-Z]+$");
	/**
	 * 字符串中的空格、回车、换行符、制表符
	 */
	public static Pattern blankPattern = Pattern.compile("\\s*|\t|\r|\n");

	/**
	 * 非数字
	 */
	public static Pattern notNumberPattern = Pattern.compile("\\D+");

	/**
	 * 
	 */
	public static final String SPLIT_STR_PATTERN = ",|，|;|；|、|\\.|。|-|_|\\(|\\)|\\[|\\]|\\{|\\}|\\\\|/| |　|\"";

	public static Matcher matcher = null;

	public static boolean isNumeric(String str) {
		if (str == null) {
			return false;
		}
		int sz = str.length();
		for (int i = 0; i < sz; ++i) {
			if (!(Character.isDigit(str.charAt(i)))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 删除左右空格，如果为null 返回 ""
	 * 
	 * @param str
	 * @return
	 */
	public static String trim(String str) {
		if (str == null) {
			return "";
		}
		return str.trim();
	}

	/**
	 * 将object 转为 string value并去空格 若object为null返回空字串
	 * 
	 * @param value
	 * @return
	 */
	public static String getString(Object value) {
		if (value == null) {
			return "";
		}
		return String.valueOf(value).trim();
	}

	/**
	 * 判断是否数字表示
	 * 
	 * @param src 源字符串
	 * @return 是否数字的标志
	 */
	public static boolean isNumericString(String src) {
		boolean result = false;
		if (src != null && src.length() > 0) {
			Matcher m = numericStringPattern.matcher(src);
			if (m.find()) {
				result = true;
			}
		}
		return result;
	}

	public static String leftPad(String str, int size, char padChar) {
		if (str == null) {
			return null;
		}
		int pads = size - str.length();
		if (pads <= 0) {
			return str;
		}
		if (pads > 8192) {
			return leftPad(str, size, String.valueOf(padChar));
		}
		return padding(pads, padChar).concat(str);
	}

	private static String padding(int repeat, char padChar) throws IndexOutOfBoundsException {
		if (repeat < 0) {
			throw new IndexOutOfBoundsException("Cannot pad a negative amount: " + repeat);
		}
		char[] buf = new char[repeat];
		for (int i = 0; i < buf.length; ++i) {
			buf[i] = padChar;
		}
		return new String(buf);
	}

	public static String leftPad(String str, int size, String padStr) {
		if (str == null) {
			return null;
		}
		if (isEmpty(padStr)) {
			padStr = " ";
		}
		int padLen = padStr.length();
		int strLen = str.length();
		int pads = size - strLen;
		if (pads <= 0) {
			return str;
		}
		if ((padLen == 1) && (pads <= 8192)) {
			return leftPad(str, size, padStr.charAt(0));
		}

		if (pads == padLen) {
			return padStr.concat(str);
		}
		if (pads < padLen) {
			return padStr.substring(0, pads).concat(str);
		}
		char[] padding = new char[pads];
		char[] padChars = padStr.toCharArray();
		for (int i = 0; i < pads; ++i) {
			padding[i] = padChars[(i % padLen)];
		}
		return new String(padding).concat(str);
	}

	/**
	 * 判断是否纯字母组合
	 * 
	 * @param src 源字符串
	 * @return 是否纯字母组合的标志
	 */
	public static boolean isABC(String src) {
		boolean result = false;
		if (src != null && src.length() > 0) {
			Matcher m = abcPattern.matcher(src);
			if (m.find()) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * 判断是否浮点数字表示
	 * 
	 * @param src 源字符串
	 * @return 是否数字的标志
	 */
	public static boolean isFloatNumeric(String src) {
		boolean result = false;
		if (src != null && src.length() > 0) {
			Matcher m = floatNumericPattern.matcher(src);
			if (m.find()) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * 把string array or list用给定的符号symbol连接成一个字符串
	 * 
	 * @param array
	 * @param symbol
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String joinString(List array, String symbol) {
		String result = "";
		if (array != null) {
			for (int i = 0; i < array.size(); i++) {
				String temp = array.get(i).toString();
				if (temp != null && temp.trim().length() > 0) {
					result += (temp + symbol);
				}
			}
			if (result.length() > 1) {
				result = result.substring(0, result.length() - 1);
			}
		}
		return result;
	}

	/**
	 * 把string array or list用给定的符号symbol连接成一个字符串
	 * 
	 * @param array
	 * @param symbol
	 * @return
	 */
	public static String joinString(String[] array, String symbol) {
		String result = "";
		if (array != null) {
			for (int i = 0; i < array.length; i++) {
				String temp = array[i];
				if (temp != null && temp.trim().length() > 0) {
					result += (temp + symbol);
				}

			}
			if (result.length() > 1) {
				result = result.substring(0, result.length() - 1);
			}
		}
		return result;
	}

	/**
	 * 截取字符串 超出的字符用...代替
	 * 
	 * @param array
	 * @param symbol
	 * @return
	 */
	public static String subStringNotEncode(String subject, int size) {
		if (subject != null && subject.length() > size) {
			subject = subject.substring(0, size) + "...";
		}
		return subject;
	}

	/**
	 * 截取字符串 超出的字符用symbol代替
	 * 
	 * @param len    字符串长度 长度计量单位为一个GBK汉字 两个英文字母计算为一个单位长度
	 * @param str
	 * @param symbol
	 * @return
	 */
	public static String getLimitLengthString(String str, int len, String symbol) {
		int iLen = len * 2;
		int counterOfDoubleByte = 0;
		String strRet = "";
		try {
			if (str != null) {
				byte[] b = str.getBytes("GBK");
				if (b.length <= iLen) {
					return str;
				}
				for (int i = 0; i < iLen; i++) {
					if (b[i] < 0) {
						counterOfDoubleByte++;
					}
				}
				if (counterOfDoubleByte % 2 == 0) {
					strRet = new String(b, 0, iLen, "GBK") + symbol;
					return strRet;
				} else {
					strRet = new String(b, 0, iLen - 1, "GBK") + symbol;
					return strRet;
				}
			} else {
				return "";
			}
		} catch (Exception ex) {
			return str.substring(0, len);
		} finally {
			strRet = null;
		}
	}

	/**
	 * 截取字符串 超出的字符用symbol代替
	 * 
	 * @param len    字符串长度 长度计量单位为一个GBK汉字 两个英文字母计算为一个单位长度
	 * @param str
	 * @param symbol
	 * @return12
	 */
	public static String getLimitLengthString(String str, int len) {
		return getLimitLengthString(str, len, "...");
	}

	/**
	 * 
	 * 截取字符，不转码
	 * 
	 * @param subject
	 * @param size
	 * @return
	 */
	public static String subStrNotEncode(String subject, int size) {
		if (subject.length() > size) {
			subject = subject.substring(0, size);
		}
		return subject;
	}

	/**
	 * 取得字符串的实际长度（考虑了汉字的情况）
	 * 
	 * @param srcStr 源字符串
	 * @return 字符串的实际长度
	 */
	public static int getStringLen(String srcStr) {
		int result = 0;
		if (srcStr != null) {
			char[] theChars = srcStr.toCharArray();
			for (int i = 0; i < theChars.length; i++) {
				result += (theChars[i] <= 255) ? 1 : 2;
			}
		}
		return result;
	}

	/**
	 * 检查数据串中是否包含非法字符集
	 * 
	 * @param str
	 * @return [true]|[false] 包含|不包含
	 */
	public static boolean check(String str) {
		String sIllegal = "'\"";
		int len = sIllegal.length();
		if (null == str) {
			return false;
		}

		for (int i = 0; i < len; i++) {
			if (str.indexOf(sIllegal.charAt(i)) != -1) {
				return true;
			}
		}

		return false;
	}

	/***************************************************************************
	 * getHideEmailPrefix - 隐藏邮件地址前缀。
	 * 
	 * @param email - EMail邮箱地址 例如: linwenguo@koubei.com 等等...
	 * @return 返回已隐藏前缀邮件地址, 如 *********@koubei.com.
	 * @version 1.0 (2006.11.27) Wilson Lin
	 **************************************************************************/
	public static String getHideEmailPrefix(String email) {
		if (null != email) {
			int index = email.lastIndexOf('@');
			if (index > 0) {
				email = repeat("*", index).concat(email.substring(index));
			}
		}
		return email;
	}

	/***************************************************************************
	 * repeat - 通过源字符串重复生成N次组成新的字符串。
	 * 
	 * @param src - 源字符串 例如: 空格(" "), 星号("*"), "浙江" 等等...
	 * @param num - 重复生成次数
	 * @return 返回已生成的重复字符串
	 * @version 1.0 (2006.10.10) Wilson Lin
	 **************************************************************************/
	public static String repeat(String src, int num) {
		StringBuffer s = new StringBuffer();
		for (int i = 0; i < num; i++) {
			s.append(src);
		}
		return s.toString();
	}

	/**
	 * 根据指定的字符把源字符串分割成一个数组
	 * 
	 * @param src
	 * @return
	 */
	public static List<String> parseString2ListByCustomerPattern(String pattern, String src) {
		if (src == null) {
			return null;
		}
		List<String> list = new ArrayList<String>();
		String[] result = src.split(pattern);
		for (int i = 0; i < result.length; i++) {
			list.add(result[i]);
		}
		return list;
	}

	/**
	 * 根据指定的字符把源字符串分割成一个数组
	 * 
	 * @param src
	 * @return
	 */
	public static List<String> parseString2ListByPattern(String src) {
		String pattern = "，|,|、|。";
		return parseString2ListByCustomerPattern(pattern, src);
	}

	/**
	 * 格式化一个float
	 * 
	 * @param format 要格式化成的格式 such as #.00, #.#
	 */

	public static String formatFloat(float f, String format) {
		DecimalFormat df = new DecimalFormat(format);
		return df.format(f);
	}

	/**
	 * 判断是否是空字符串 null和"" 都返回 true
	 * 
	 * @author Robin Chang
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s) {
		if (s != null && !s.equals("")) {
			return false;
		}
		return true;
	}

	/**
	 * 判断是否是空字符串 null和"" null返回result,否则返回字符串
	 * 
	 * @param s
	 * @return
	 */
	public static String isEmpty(String s, String result) {
		if (s != null && !s.equals("")) {
			return s;
		}
		return result;
	}

	/**
	 * 自定义的分隔字符串函数 例如: 1,2,3 =>[1,2,3] 3个元素 ,2,3=>[,2,3] 3个元素 ,2,3,=>[,2,3,] 4个元素
	 * ,,,=>[,,,] 4个元素
	 * 
	 * 5.22算法修改，为提高速度不用正则表达式 两个间隔符,,返回""元素
	 * 
	 * @param split 分割字符 默认,
	 * @param src   输入字符串
	 * @return 分隔后的list
	 * @author Robin
	 */
	public static List<String> splitToList(String split, String src) {
		// 默认,
		String sp = ",";
		if (split != null && split.length() == 1) {
			sp = split;
		}
		List<String> r = new ArrayList<String>();
		int lastIndex = -1;
		int index = src.indexOf(sp);
		if (-1 == index && src != null) {
			r.add(src);
			return r;
		}
		while (index >= 0) {
			if (index > lastIndex) {
				r.add(src.substring(lastIndex + 1, index));
			} else {
				r.add("");
			}

			lastIndex = index;
			index = src.indexOf(sp, index + 1);
			if (index == -1) {
				r.add(src.substring(lastIndex + 1, src.length()));
			}
		}
		return r;
	}

	/**
	 * 把 名=值 参数表转换成字符串 (a=1,b=2 =>a=1&b=2)
	 * 
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String linkedHashMapToString(LinkedHashMap<String, String> map) {
		if (map != null && map.size() > 0) {
			String result = "";
			Iterator it = map.keySet().iterator();
			while (it.hasNext()) {
				String name = (String) it.next();
				String value = (String) map.get(name);
				result += (StringUtils.isEmpty(result)) ? "" : "&";
				result += String.format("%s=%s", name, value);
			}
			return result;
		}
		return null;
	}

	/**
	 * 解析字符串返回 名称=值的参数表 (a=1&b=2 => a=1,b=2)
	 * 
	 * @see test.koubei.util.StringUtilTest#testParseStr()
	 * @param str
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static LinkedHashMap<String, String> toLinkedHashMap(String str) {
		if (str != null && !str.equals("") && str.indexOf("=") > 0) {
			LinkedHashMap result = new LinkedHashMap();

			String name = null;
			String value = null;
			int i = 0;
			while (i < str.length()) {
				char c = str.charAt(i);
				switch (c) {
				case 61: // =
					value = "";
					break;
				case 38: // &
					if (name != null && value != null && !name.equals("")) {
						result.put(name, value);
					}
					name = null;
					value = null;
					break;
				default:
					if (value != null) {
						value = (value != null) ? (value + c) : "" + c;
					} else {
						name = (name != null) ? (name + c) : "" + c;
					}
				}
				i++;

			}

			if (name != null && value != null && !name.equals("")) {
				result.put(name, value);
			}

			return result;

		}
		return null;
	}

	/**
	 * 根据输入的多个解释和下标返回一个值
	 * 
	 * @param captions 例如:"无,爱干净,一般,比较乱"
	 * @param index    1
	 * @return 一般
	 */
	public static String getCaption(String captions, int index) {
		if (index > 0 && captions != null && !captions.equals("")) {
			String[] ss = captions.split(",");
			if (ss != null && ss.length > 0 && index < ss.length) {
				return ss[index];
			}
		}
		return null;
	}

	/**
	 * 数字转字符串,如果num<=0 则输出"";
	 * 
	 * @param num
	 * @return
	 */
	public static String numberToString(Object num) {
		if (num == null) {
			return null;
		} else if (num instanceof Integer && (Integer) num > 0) {
			return Integer.toString((Integer) num);
		} else if (num instanceof Long && (Long) num > 0) {
			return Long.toString((Long) num);
		} else if (num instanceof Float && (Float) num > 0) {
			return Float.toString((Float) num);
		} else if (num instanceof Double && (Double) num > 0) {
			return Double.toString((Double) num);
		} else {
			return "";
		}
	}

	/**
	 * 货币转字符串
	 * 
	 * @param money
	 * @param style 样式 [default]要格式化成的格式 such as #.00, #.#
	 * @return
	 */

	public static String moneyToString(Object money, String style) {
		if (money != null && style != null && (money instanceof Double || money instanceof Float)) {
			Double num = (Double) money;

			if (style.equalsIgnoreCase("default")) {
				// 缺省样式 0 不输出 ,如果没有输出小数位则不输出.0
				if (num == 0) {
					// 不输出0
					return "";
				} else if ((num * 10 % 10) == 0) {
					// 没有小数
					return Integer.toString((int) num.intValue());
				} else {
					// 有小数
					return num.toString();
				}

			} else {
				DecimalFormat df = new DecimalFormat(style);
				return df.format(num);
			}
		}
		return null;
	}

	/**
	 * 在sou中是否存在finds 如果指定的finds字符串有一个在sou中找到,返回true;
	 * 
	 * @param sou
	 * @param find
	 * @return
	 */
	public static boolean strPos(String sou, String... finds) {
		if (sou != null && finds != null && finds.length > 0) {
			for (int i = 0; i < finds.length; i++) {
				if (sou.indexOf(finds[i]) > -1) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean strPos(String sou, List<String> finds) {
		if (sou != null && finds != null && finds.size() > 0) {
			for (String s : finds) {
				if (sou.indexOf(s) > -1) {
					return true;
				}

			}
		}
		return false;
	}

	public static boolean strPos(String sou, String finds) {
		List<String> t = splitToList(",", finds);
		return strPos(sou, t);
	}

	/**
	 * 判断两个字符串是否相等 如果都为null则判断为相等,一个为null另一个not null则判断不相等 否则如果s1=s2则相等
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static boolean equals(String s1, String s2) {
		if (StringUtil.isEmpty(s1) && StringUtil.isEmpty(s2)) {
			return true;
		} else if (!StringUtil.isEmpty(s1) && !StringUtil.isEmpty(s2)) {
			return s1.equals(s2);
		}
		return false;
	}

	public static int toInt(String s) {
		if (s != null && !"".equals(s.trim())) {
			try {
				return Integer.parseInt(s);
			} catch (Exception e) {
				return 0;
			}
		}
		return 0;
	}

	public static double toDouble(String s) {
		if (s != null && !"".equals(s.trim())) {
			return Double.parseDouble(s);
		}
		return 0;
	}

	public static String simpleEncrypt(String str) {
		if (str != null && str.length() > 0) {
			// str = str.replaceAll("0","a");
			str = str.replaceAll("1", "b");
			// str = str.replaceAll("2","c");
			str = str.replaceAll("3", "d");
			// str = str.replaceAll("4","e");
			str = str.replaceAll("5", "f");
			str = str.replaceAll("6", "g");
			str = str.replaceAll("7", "h");
			str = str.replaceAll("8", "i");
			str = str.replaceAll("9", "j");
		}
		return str;

	}

	/**
	 * 过滤用户输入的URL地址（防治用户广告） 目前只针对以http或www开头的URL地址
	 * 本方法调用的正则表达式，不建议用在对性能严格的地方例如:循环及list页面等
	 * 
	 * @author fengliang
	 * @param str 需要处理的字符串
	 * @return 返回处理后的字符串
	 */
	public static String removeURL(String str) {
		if (str != null) {
			str = str.toLowerCase().replaceAll("(http|www|com|cn|org|\\.)+", "");
		}
		return str;
	}

	/**
	 * 随即生成指定位数的含数字验证码字符串
	 * 
	 * @author Peltason
	 * @date 2007-5-9
	 * @param bit 指定生成验证码位数
	 * @return String
	 */
	public static String numRandom(int bit) {
		if (bit == 0) {
			bit = 6; // 默认6位
		}

		String str = "";
		str = "0123456789";// 初始化种子
		return RandomStringUtils.random(bit, str);// 返回6位的字符串
	}

	/**
	 * 随即生成指定位数的含验证码字符串
	 * 
	 * @author Peltason
	 * 
	 * @date 2007-5-9
	 * @param bit 指定生成验证码位数
	 * @return String
	 */
	public static String random(int bit) {
		if (bit == 0) {
			bit = 6; // 默认6位
		}

		// 因为o和0,l和1很难区分,所以,去掉大小写的o和l
		String str = "";
		str = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz";// 初始化种子
		return RandomStringUtils.random(bit, str);// 返回6位的字符串
	}

	/**
	 * Wap页面的非法字符检查
	 * 
	 * @author hugh115
	 * @date 2007-06-29
	 * @param str
	 * @return
	 */
	public static String replaceWapStr(String str) {
		if (str != null) {
			str = str.replaceAll("<span class=\"keyword\">", "");
			str = str.replaceAll("</span>", "");
			str = str.replaceAll("<strong class=\"keyword\">", "");
			str = str.replaceAll("<strong>", "");
			str = str.replaceAll("</strong>", "");

			str = str.replace('$', '＄');

			str = str.replaceAll("&amp;", "＆");
			str = str.replace('&', '＆');

			str = str.replace('<', '＜');

			str = str.replace('>', '＞');

		}
		return str;
	}

	/**
	 * 字符串转float 如果异常返回0.00
	 * 
	 * @param s 输入的字符串
	 * @return 转换后的float
	 */
	public static Float toFloat(String s) {
		try {
			return Float.parseFloat(s);
		} catch (NumberFormatException e) {
			return new Float(0);
		}
	}

	/**
	 * 页面中去除字符串中的空格、回车、换行符、制表符
	 * 
	 * @author shazao
	 * @date 2007-08-17
	 * @param str
	 * @return
	 */
	public static String replaceBlank(String str) {
		if (str != null) {
			str = blankPattern.matcher(str).replaceAll("");
		}
		return str;
	}

	/**
	 * 
	 * 转换编码
	 * 
	 * @param s       源字符串
	 * @param fencode 源编码格式
	 * @param bencode 目标编码格式
	 * @return 目标编码
	 */
	public static String changCoding(String s, String fencode, String bencode) {
		String str;
		try {
			if (StringUtil.isNotEmpty(s)) {
				str = new String(s.getBytes(fencode), bencode);
			} else {
				str = "";
			}
			return str;
		} catch (UnsupportedEncodingException e) {
			return s;
		}
	}

	/**
	 * @param str
	 * @return
	 ************************************************************************* 
	 */
	public static String removeHTMLLableExe(String str) {
		str = stringReplace(str, ">\\s*<", "><");
		str = stringReplace(str, "&nbsp;", " ");// 替换空格
		str = stringReplace(str, "<br ?/?>", "\n");// 去<br><br />
		str = stringReplace(str, "<([^<>]+)>", "");// 去掉<>内的字符
		str = stringReplace(str, "\\s\\s\\s*", " ");// 将多个空白变成一个空格
		str = stringReplace(str, "^\\s*", "");// 去掉头的空白
		str = stringReplace(str, "\\s*$", "");// 去掉尾的空白
		str = stringReplace(str, " +", " ");
		return str;
	}

	/**
	 * 除去html标签
	 * 
	 * @param str 源字符串
	 * @return 目标字符串
	 */
	public static String removeHTMLLable(String str) {
		str = stringReplace(str, "\\s", "");// 去掉页面上看不到的字符
		str = stringReplace(str, "<br ?/?>", "\n");// 去<br><br />
		str = stringReplace(str, "<([^<>]+)>", "");// 去掉<>内的字符
		str = stringReplace(str, "&nbsp;", " ");// 替换空格
		str = stringReplace(str, "&(\\S)(\\S?)(\\S?)(\\S?);", "");// 去<br><br />
		return str;
	}

	/**
	 * 去掉HTML标签之外的字符串
	 * 
	 * @param str 源字符串
	 * @return 目标字符串
	 */
	public static String removeOutHTMLLable(String str) {
		str = stringReplace(str, ">([^<>]+)<", "><");
		str = stringReplace(str, "^([^<>]+)<", "<");
		str = stringReplace(str, ">([^<>]+)$", ">");
		return str;
	}

	/**
	 * 
	 * 字符串替换
	 * 
	 * @param str 源字符串
	 * @param sr  正则表达式样式
	 * @param sd  替换文本
	 * @return 结果串
	 */
	public static String stringReplace(String str, String sr, String sd) {
		String regEx = sr;
		Pattern p = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(str);
		str = m.replaceAll(sd);
		return str;
	}

	/**
	 * 
	 * 将html的省略写法替换成非省略写法
	 * 
	 * @param str html字符串
	 * @param pt  标签如table
	 * @return 结果串
	 */
	public static String fomateToFullForm(String str, String pt) {
		String regEx = "<" + pt + "\\s+([\\S&&[^<>]]*)/>";
		Pattern p = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(str);
		String[] sa = null;
		String sf = "";
		String sf2 = "";
		String sf3 = "";
		for (; m.find();) {
			sa = p.split(str);
			if (sa == null) {
				break;
			}
			sf = str.substring(sa[0].length(), str.indexOf("/>", sa[0].length()));
			sf2 = sf + "></" + pt + ">";
			sf3 = str.substring(sa[0].length() + sf.length() + 2);
			str = sa[0] + sf2 + sf3;
			sa = null;
		}
		return str;
	}

	/**
	 * 
	 * 得到字符串的子串位置序列
	 * 
	 * @param str 字符串
	 * @param sub 子串
	 * @param b   true子串前端,false子串后端
	 * @return 字符串的子串位置序列
	 */
	public static int[] getSubStringPos(String str, String sub, boolean b) {
		// int[] i = new int[(new Integer((str.length()-stringReplace( str , sub
		// , "" ).length())/sub.length())).intValue()] ;
		String[] sp = null;
		int l = sub.length();
		sp = splitString(str, sub);
		if (sp == null) {
			return null;
		}
		int[] ip = new int[sp.length - 1];
		for (int i = 0; i < sp.length - 1; i++) {
			ip[i] = sp[i].length() + l;
			if (i != 0) {
				ip[i] += ip[i - 1];
			}
		}
		if (b) {
			for (int j = 0; j < ip.length; j++) {
				ip[j] = ip[j] - l;
			}
		}
		return ip;
	}

	/**
	 * 
	 * 根据正则表达式分割字符串
	 * 
	 * @param str 源字符串
	 * @param ms  正则表达式
	 * @return 目标字符串组
	 */
	public static String[] splitString(String str, String ms) {
		String regEx = ms;
		Pattern p = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
		String[] sp = p.split(str);
		return sp;
	}

	/**
	 * 根据正则表达式提取字符串,相同的字符串只返回一个
	 * 
	 * @param str源字符串
	 * @param pattern 正则表达式
	 * @return 目标字符串数据组
	 ************************************************************************* 
	 */

	// ★传入一个字符串，把符合pattern格式的字符串放入字符串数组
	// java.util.regex是一个用正则表达式所订制的模式来对字符串进行匹配工作的类库包
	public static String[] getStringArrayByPattern(String str, String pattern) {
		Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
		Matcher matcher = p.matcher(str);
		// 范型
		Set<String> result = new HashSet<String>();// 目的是：相同的字符串只返回一个。。。 不重复元素
		// boolean find() 尝试在目标字符串里查找下一个匹配子串。
		while (matcher.find()) {
			for (int i = 0; i < matcher.groupCount(); i++) { // int groupCount()
																// 返回当前查找所获得的匹配组的数量。
				result.add(matcher.group(i));

			}
		}
		String[] resultStr = null;
		if (result.size() > 0) {
			resultStr = new String[result.size()];
			return result.toArray(resultStr);// 将Set result转化为String[] resultStr
		}
		return resultStr;

	}

	/**
	 * 得到第一个b,e之间的字符串,并返回e后的子串
	 * 
	 * @param s 源字符串
	 * @param b 标志开始
	 * @param e 标志结束
	 * @return b,e之间的字符串
	 */

	/*
	 * String aaa="abcdefgllhijklmn5465"; String[]
	 * bbb=StringProcessor.midString(aaa, "b","l"); //ab cdefg llhijklmn5465 // 元素0
	 * 元素1
	 */
	public static String[] midString(String s, String b, String e) {
		int i = s.indexOf(b) + b.length();
		int j = s.indexOf(e, i);
		String[] sa = new String[2];
		if (i < b.length() || j < i + 1 || i > j) {
			sa[1] = s;
			sa[0] = null;
			return sa;
		} else {
			sa[0] = s.substring(i, j);
			sa[1] = s.substring(j);
			return sa;
		}
	}

	/**
	 * 带有前一次替代序列的正则表达式替代
	 * 
	 * @param s
	 * @param pf
	 * @param pb
	 * @param start
	 * @return
	 */
	public static String stringReplace(String s, String pf, String pb, int start) {
		Pattern pattern_hand = Pattern.compile(pf);
		Matcher matcher_hand = pattern_hand.matcher(s);
		int gc = matcher_hand.groupCount();
		int pos = start;
		String sf1 = "";
		String sf2 = "";
		String sf3 = "";
		int if1 = 0;
		String strr = "";
		while (matcher_hand.find(pos)) {
			sf1 = matcher_hand.group();
			if1 = s.indexOf(sf1, pos);
			if (if1 >= pos) {
				strr += s.substring(pos, if1);
				pos = if1 + sf1.length();
				sf2 = pb;
				for (int i = 1; i <= gc; i++) {
					sf3 = "\\" + i;
					sf2 = replaceAll(sf2, sf3, matcher_hand.group(i));
				}
				strr += sf2;
			} else {
				return s;
			}
		}
		strr = s.substring(0, start) + strr;
		return strr;
	}

	/**
	 * 存文本替换
	 * 
	 * @param s  源字符串
	 * @param sf 子字符串
	 * @param sb 替换字符串
	 * @return 替换后的字符串
	 */
	public static String replaceAll(String s, String sf, String sb) {
		int i = 0, j = 0;
		int l = sf.length();
		boolean b = true;
		boolean o = true;
		String str = "";
		do {
			j = i;
			i = s.indexOf(sf, j);
			if (i > j) {
				str += s.substring(j, i);
				str += sb;
				i += l;
				o = false;
			} else {
				str += s.substring(j);
				b = false;
			}
		} while (b);
		if (o) {
			str = s;
		}
		return str;
	}

	// 字符串的替换
	public static String replace(String strSource, String strOld, String strNew) {
		if (strSource == null) {
			return null;
		}
		int i = 0;
		if ((i = strSource.indexOf(strOld, i)) >= 0) {
			char[] cSrc = strSource.toCharArray();
			char[] cTo = strNew.toCharArray();
			int len = strOld.length();
			StringBuffer buf = new StringBuffer(cSrc.length);
			buf.append(cSrc, 0, i).append(cTo);
			i += len;
			int j = i;
			while ((i = strSource.indexOf(strOld, i)) > 0) {
				buf.append(cSrc, j, i - j).append(cTo);
				i += len;
				j = i;
			}
			buf.append(cSrc, j, cSrc.length - j);
			return buf.toString();
		}
		return strSource;
	}

	/**
	 * 判断是否与给定字符串样式匹配
	 * 
	 * @param str     字符串
	 * @param pattern 正则表达式样式
	 * @return 是否匹配是true,否false
	 */
	public static boolean isMatch(String str, String pattern) {
		Pattern pattern_hand = Pattern.compile(pattern);
		Matcher matcher_hand = pattern_hand.matcher(str);
		boolean b = matcher_hand.matches();
		return b;
	}

	/**
	 * 截取字符串
	 * 
	 * @param s   源字符串
	 * @param jmp 跳过jmp
	 * @param sb  取在sb
	 * @param se  于se
	 * @return 之间的字符串
	 */
	public static String subStringExe(String s, String jmp, String sb, String se) {
		if (isEmpty(s)) {
			return "";
		}
		int i = s.indexOf(jmp);
		if (i >= 0 && i < s.length()) {
			s = s.substring(i + 1);
		}
		i = s.indexOf(sb);
		if (i >= 0 && i < s.length()) {
			s = s.substring(i + 1);
		}
		if (se == "") {
			return s;
		} else {
			i = s.indexOf(se);
			if (i >= 0 && i < s.length()) {
				s = s.substring(i + 1);
			}
			return s;
		}
	}

	/**
	 * 
	 * 方法功能描述
	 * 
	 * @param str 解码的内容
	 * @return经过解码的内容
	 */
	public static String getGBK(String str) {

		return transfer(str);
	}

	/**
	 * 
	 * 方法功能描述
	 * 
	 * @param str
	 * @return
	 */
	public static String transfer(String str) {
		Matcher m = numericPattern.matcher(str); // Pattern.compile("&#\\d+;")
		while (m.find()) {
			String old = m.group();
			str = str.replaceAll(old, getChar(old));
		}
		return str;
	}

	/**
	 * 
	 * 方法功能描述
	 * 
	 * @param str
	 * @return
	 */
	public static String getChar(String str) {
		String dest = str.substring(2, str.length() - 1);
		char ch = (char) Integer.parseInt(dest);
		return "" + ch;
	}

	/**
	 * 切割字符串 方法功能描述
	 * 
	 * @param str
	 * @return
	 */
	public static String subYhooString(String subject, int size) {
		subject = subject.substring(1, size);
		return subject;
	}

	/**
	 * 切割字符串 方法功能描述
	 * 
	 * @param str
	 * @return
	 */
	public static String subYhooStringDot(String subject, int size) {
		subject = subject.substring(1, size) + "...";
		return subject;
	}

	/**
	 * 泛型方法(通用)，把list转换成以“,”相隔的字符串 调用时注意类型初始化（申明类型） 如：List<Integer> intList = new
	 * ArrayList<Integer>(); 调用方法：StringUtil.listTtoString(intList);
	 * 效率：list中4条信息，1000000次调用时间为850ms左右
	 * 
	 * @param      <T> 泛型
	 * @param list list列表
	 * @return 以“,”相隔的字符串
	 */
	public static <T> String listTtoString(List<T> list) {
		if (list == null || list.size() < 1) {
			return "";
		}

		Iterator<T> i = list.iterator();
		if (!i.hasNext()) {
			return "";
		}

		StringBuilder sb = new StringBuilder();
		for (;;) {
			T e = i.next();
			sb.append(e);
			if (!i.hasNext()) {
				return sb.toString();
			}

			sb.append(",");
		}
	}

	/**
	 * 把整形数组转换成以“,”相隔的字符串
	 * 
	 * @param a 数组a
	 * @return 以“,”相隔的字符串
	 */
	public static String intArraytoString(int[] a) {
		if (a == null) {
			return "";
		}

		int iMax = a.length - 1;
		if (iMax == -1) {
			return "";
		}

		StringBuilder b = new StringBuilder();
		for (int i = 0;; i++) {
			b.append(a[i]);
			if (i == iMax) {
				return b.toString();
			}

			b.append(",");
		}
	}

	/**
	 * 判断文字内容重复
	 * 
	 */
	public static boolean isContentRepeat(String content) {
		int similarNum = 0;
		int forNum = 0;
		int subNum = 0;
		int thousandNum = 0;
		String startStr = "";
		String nextStr = "";
		boolean result = false;
		float endNum = (float) 0.0;
		if (content != null && content.length() > 0) {
			if (content.length() % 1000 > 0) {
				thousandNum = (int) Math.floor(content.length() / 1000) + 1;
			}

			else {
				thousandNum = (int) Math.floor(content.length() / 1000);
			}

			if (thousandNum < 3) {
				subNum = 100 * thousandNum;
			}

			else if (thousandNum < 6) {
				subNum = 200 * thousandNum;
			}

			else if (thousandNum < 9) {
				subNum = 300 * thousandNum;
			}

			else {
				subNum = 3000;
			}

			for (int j = 1; j < subNum; j++) {
				if (content.length() % j > 0) {
					forNum = (int) Math.floor(content.length() / j) + 1;
				}

				else {
					forNum = (int) Math.floor(content.length() / j);
				}

				if (result || j >= content.length()) {
					break;
				}

				else {
					for (int m = 0; m < forNum; m++) {
						if (m * j > content.length() || (m + 1) * j > content.length()
								|| (m + 2) * j > content.length()) {
							break;
						}

						startStr = content.substring(m * j, (m + 1) * j);
						nextStr = content.substring((m + 1) * j, (m + 2) * j);
						if (startStr.equals(nextStr)) {
							similarNum = similarNum + 1;
							endNum = (float) similarNum / forNum;
							if (endNum > 0.4) {
								result = true;
								break;
							}
						} else {
							similarNum = 0;
						}

					}
				}
			}
		}
		return result;
	}

	/**
	 * 判断对象是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(Object str) {
		boolean flag = true;
		if (str != null && !str.equals("")) {
			if (str.toString().length() > 0) {
				flag = true;
			}
		} else {
			flag = false;
		}
		return flag;
	}

	/**
	 * 全角字符变半角字符
	 * 
	 * @author shazao
	 * @date 2008-04-03
	 * @param str
	 * @return
	 */
	public static String full2Half(String str) {
		if (str == null || "".equals(str)) {
			return "";
		}

		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);

			if (c >= 65281 && c < 65373) {
				sb.append((char) (c - 65248));
			} else {
				sb.append(str.charAt(i));
			}

		}

		return sb.toString();
	}

	/**
	 * 全角括号转为半角
	 * 
	 * @author shazao
	 * @date 2007-11-29
	 * @param str
	 * @return
	 */
	public static String replaceBracketStr(String str) {
		if (str != null && str.length() > 0) {
			str = str.replaceAll("（", "(");
			str = str.replaceAll("）", ")");
		}
		return str;
	}

	/**
	 * 解析字符串返回map键值对(例：a=1&b=2 => a=1,b=2)
	 * 
	 * @param query   源参数字符串
	 * @param split1  键值对之间的分隔符（例：&）
	 * @param split2  key与value之间的分隔符（例：=）
	 * @param dupLink 重复参数名的参数值之间的连接符，连接后的字符串作为该参数的参数值，可为null
	 *                null：不允许重复参数名出现，则靠后的参数值会覆盖掉靠前的参数值。
	 * @return map
	 * @author sky
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map<String, String> parseQuery(String query, char split1, char split2, String dupLink) {
		if (!isEmpty(query) && query.indexOf(split2) > 0) {
			Map<String, String> result = new HashMap();

			String name = null;
			String value = null;
			String tempValue = "";
			int len = query.length();
			for (int i = 0; i < len; i++) {
				char c = query.charAt(i);
				if (c == split2) {
					value = "";
				} else if (c == split1) {
					if (!isEmpty(name) && value != null) {
						if (dupLink != null) {
							tempValue = result.get(name);
							if (tempValue != null) {
								value += dupLink + tempValue;
							}
						}
						result.put(name, value);
					}
					name = null;
					value = null;
				} else if (value != null) {
					value += c;
				} else {
					name = (name != null) ? (name + c) : "" + c;
				}
			}

			if (!isEmpty(name) && value != null) {
				if (dupLink != null) {
					tempValue = result.get(name);
					if (tempValue != null) {
						value += dupLink + tempValue;
					}
				}
				result.put(name, value);
			}

			return result;
		}
		return null;
	}

	/**
	 * 将list 用传入的分隔符组装为String
	 * 
	 * @param list
	 * @param slipStr
	 * @return String
	 */
	@SuppressWarnings("rawtypes")
	public static String listToStringSlipStr(List list, String slipStr) {
		StringBuffer returnStr = new StringBuffer();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				returnStr.append(list.get(i)).append(slipStr);
			}
		}
		if (returnStr.toString().length() > 0) {
			return returnStr.toString().substring(0, returnStr.toString().lastIndexOf(slipStr));
		} else {
			return "";
		}

	}

	/**
	 * 获取从start开始用*替换len个长度后的字符串
	 * 
	 * @param str   要替换的字符串
	 * @param start 开始位置
	 * @param len   长度
	 * @return 替换后的字符串
	 */
	public static String getMaskStr(String str, int start, int len) {
		if (StringUtil.isEmpty(str)) {
			return str;
		}
		if (str.length() < start) {
			return str;
		}

		// 获取*之前的字符串
		String ret = str.substring(0, start);

		// 获取最多能打的*个数
		int strLen = str.length();
		if (strLen < start + len) {
			len = strLen - start;
		}

		// 替换成*
		for (int i = 0; i < len; i++) {
			ret += "*";
		}

		// 加上*之后的字符串
		if (strLen > start + len) {
			ret += str.substring(start + len);
		}

		return ret;
	}

	/**
	 * 根据传入的分割符号,把传入的字符串分割为List字符串
	 * 
	 * @param slipStr 分隔的字符串
	 * @param src     字符串
	 * @return 列表
	 */
	public static List<String> stringToStringListBySlipStr(String slipStr, String src) {
		if (src == null) {
			return null;
		}
		List<String> list = new ArrayList<String>();
		String[] result = src.split(slipStr);
		for (int i = 0; i < result.length; i++) {
			list.add(result[i]);
		}
		return list;
	}

	/**
	 * 截取字符串
	 * 
	 * @param str  原始字符串
	 * @param len  要截取的长度
	 * @param tail 结束加上的后缀
	 * @return 截取后的字符串
	 */
	public static String getHtmlSubString(String str, int len, String tail) {
		if (str == null || str.length() <= len) {
			return str;
		}
		int length = str.length();
		char c = ' ';
		String tag = null;
		String name = null;
		int size = 0;
		String result = "";
		boolean isTag = false;
		List<String> tags = new ArrayList<String>();
		int i = 0;
		for (int end = 0, spanEnd = 0; i < length && len > 0; i++) {
			c = str.charAt(i);
			if (c == '<') {
				end = str.indexOf('>', i);
			}

			if (end > 0) {
				// 截取标签
				tag = str.substring(i, end + 1);
				int n = tag.length();
				if (tag.endsWith("/>")) {
					isTag = true;
				} else if (tag.startsWith("</")) { // 结束符
					name = tag.substring(2, end - i);
					size = tags.size() - 1;
					// 堆栈取出html开始标签
					if (size >= 0 && name.equals(tags.get(size))) {
						isTag = true;
						tags.remove(size);
					}
				} else { // 开始符
					spanEnd = tag.indexOf(' ', 0);
					spanEnd = spanEnd > 0 ? spanEnd : n;
					name = tag.substring(1, spanEnd);
					if (name.trim().length() > 0) {
						// 如果有结束符则为html标签
						spanEnd = str.indexOf("</" + name + ">", end);
						if (spanEnd > 0) {
							isTag = true;
							tags.add(name);
						}
					}
				}
				// 非html标签字符
				if (!isTag) {
					if (n >= len) {
						result += tag.substring(0, len);
						break;
					} else {
						len -= n;
					}
				}

				result += tag;
				isTag = false;
				i = end;
				end = 0;
			} else { // 非html标签字符
				len--;
				result += c;
			}
		}
		// 添加未结束的html标签
		for (String endTag : tags) {
			result += "</" + endTag + ">";
		}
		if (i < length) {
			result += tail;
		}
		return result;
	}

	public static String getProperty(String property) {
		if (property.contains("_")) {
			return property.replaceAll("_", "\\.");
		}
		return property;
	}

	// 判断一个字符串是否都为数字
	public boolean isDigit(String strNum) {
		return numericPattern.matcher((CharSequence) strNum).matches();
	}

	// 截取数字
	public String getNumbers(String content) {
		matcher = numericPattern.matcher(content);
		while (matcher.find()) {
			return matcher.group(0);
		}
		return "";
	}

	// 截取非数字
	public String splitNotNumber(String content) {
		matcher = notNumberPattern.matcher(content);
		while (matcher.find()) {
			return matcher.group(0);
		}
		return "";
	}

	/**
	 * 判断某个字符串是否存在于数组中
	 * 
	 * @param stringArray 原数组
	 * @param source      查找的字符串
	 * @return 是否找到
	 */
	public static boolean contains(String[] stringArray, String source) {
		// 转换为list
		List<String> tempList = Arrays.asList(stringArray);

		// 利用list的包含方法,进行判断
		if (tempList.contains(source)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 首字母大写
	 * 
	 * @param realName
	 * @return
	 */
	public static String firstUpperCase(String realName) {
		return StringUtils.replaceChars(realName, realName.substring(0, 1), realName.substring(0, 1).toUpperCase());
	}

	/**
	 * 首字母小写
	 * 
	 * @param realName
	 * @return
	 */
	public static String firstLowerCase(String realName) {
		return StringUtils.replaceChars(realName, realName.substring(0, 1), realName.substring(0, 1).toLowerCase());
	}

	/**
	 * 判断这个类是不是java自带的类
	 * 
	 * @param clazz
	 * @return
	 */
	public static boolean isJavaClass(Class<?> clazz) {
		boolean isBaseClass = false;
		if (clazz.isArray()) {
			isBaseClass = false;
		} else if (clazz.isPrimitive() || clazz.getPackage() == null || clazz.getPackage().getName().equals("java.lang")
				|| clazz.getPackage().getName().equals("java.math")
				|| clazz.getPackage().getName().equals("java.util")) {
			isBaseClass = true;
		}
		return isBaseClass;
	}

	public static boolean isBlank(String cDate) {
		if (cDate == null || "".equals(cDate.trim())) {
			return true;
		}
		return false;
	}

	/**
	 * 进行deciaml数据格式化操作，进行精确到一位小数且显示保留两位，不足补0
	 * 
	 * @param BigDecimal
	 * @return BigDecimal
	 */
	public static BigDecimal dicimalDataConverion(BigDecimal data) {
		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		if (data == null) {
			data = new BigDecimal("0.00");
		}
		// 保留到一位小数
		data = data.setScale(1, BigDecimal.ROUND_HALF_UP);
		// 格式化
		data = new BigDecimal(decimalFormat.format(data));
		return data;
	}

	/**
	 * 判断对象是否为空
	 * 
	 * @param obj
	 * @return
	 */
	public static String getObjectValue(Object obj) {
		return obj == null ? "" : obj.toString();
	}

	/**
	 * 进行deciaml数据格式化操作，进行精确到一位小数且显示保留1位，不足补0
	 * 
	 * @param BigDecimal
	 * @return BigDecimal
	 */
	public static BigDecimal dicimalDataConverion2(BigDecimal data) {
		DecimalFormat decimalFormat = new DecimalFormat("0.0");
		if (data == null) {
			data = new BigDecimal("0.0");
		}
		// 保留到一位小数
		data = data.setScale(1, BigDecimal.ROUND_HALF_UP);
		// 格式化
		data = new BigDecimal(decimalFormat.format(data));
		return data;
	}

	/**
	 * 字符串截取
	 * 
	 * @param str        要截取的字符串
	 * @param beginIndex 截取的起始索引
	 * @param cutLength  截取的字符串长度
	 * @return
	 */
	public static String subStringByLength(String str, int beginIndex, int cutLength) {
		if (isEmpty(str)) {
			return "";
		}
		int len = str.length();
		if (beginIndex + 1 > len || cutLength <= 0) {
			return "";
		}
		if (beginIndex < 0) {
			beginIndex = 0;
		}
		if (beginIndex + 1 + cutLength >= len) {
			return str.substring(beginIndex, len);
		} else {
			return str.substring(beginIndex, beginIndex + cutLength);
		}
	}

	/**
	 * 字符串是否为空，包括blank
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNullOrEmpty(String str) {
		return null != str && 0 != str.trim().length() ? false : true;
	}

	/**
	 * web为空时用/表示
	 * 
	 * @return
	 */
	public static String isWebBlank(String value) {
		if (StringUtil.isEmpty(value)) {
			return "/";
		}
		return value;
	}

	/**
	 * 获取6位随机字符串
	 * 
	 * @return
	 */
	public static String getlinkNo() {
		String linkNo = "";
		// 用字符数组的方式随机
		String model = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		char[] m = model.toCharArray();
		for (int j = 0; j < 6; j++) {
			char c = m[(int) (Math.random() * 36)];
			// 保证六位随机数之间没有重复的
			if (linkNo.contains(String.valueOf(c))) {
				j--;
				continue;
			}
			linkNo = linkNo + c;
		}
		return linkNo;
	}

	/**
	 * 生成商品订单号
	 */
	public static String getTradeNo() {
		// 生成订单号，生成预付款订单
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String newDate = sdf.format(new Date());
		String tradeNo = "";
		Random random = new Random();
		for (int i = 0; i < 3; i++) {
			tradeNo += random.nextInt(10);
		}
		return newDate+tradeNo;
	}
	/**
	 * 随机生成用户名
	 * @return
	 */
	public static String getName() {
		String[] names = { "赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈", "褚", "卫", "蒋", "沈", "韩", "杨", "朱", "秦",
				"尤", "许", "何", "吕", "施", "张", "孔", "曹", "严", "华", "金", "魏", "陶", "姜", "戚", "谢", "邹", "喻", "柏", "水",
				"窦", "章", "云", "苏", "潘", "葛", "奚", "范", "彭", "郎", "鲁", "韦", "昌", "马", "苗", "凤", "花", "方", "俞", "任",
				"袁", "柳", "酆", "鲍", "史", "唐", "费", "廉", "岑", "薛", "雷", "贺", "倪", "汤", "滕", "殷", "罗", "毕", "郝", "邬",
				"安", "常", "乐", "于", "时", "傅", "皮", "卞", "齐", "康", "伍", "余", "元", "卜", "顾", "孟", "平", "黄", "和", "穆",
				"萧", "尹", "姚", "邵", "湛", "汪", "祁", "毛", "禹", "狄", "米", "贝", "明", "臧", "计", "伏", "成", "戴", "谈", "宋",
				"茅", "庞", "熊", "纪", "舒", "屈", "项", "祝", "董", "梁", "杜", "阮", "蓝", "闵", "席", "季", "麻", "强", "贾", "路",
				"娄", "危", "江", "童", "颜", "郭", "梅", "盛", "林", "刁", "钟", "徐", "邱", "骆", "高", "夏", "蔡", "田", "樊", "胡",
				"凌", "霍", "虞", "万", "支", "柯", "昝", "管", "卢", "莫", "经", "房", "裘", "缪", "干", "解", "应", "宗", "丁", "宣",
				"贲", "邓", "郁", "单", "杭", "洪", "包", "诸", "左", "石", "崔", "吉", "钮", "龚", "程", "嵇", "邢", "滑", "裴", "陆",
				"荣", "翁", "荀", "羊", "于", "惠", "甄", "曲", "家", "封", "芮", "羿", "储", "靳", "汲", "邴", "糜", "松", "井", "段",
				"富", "巫", "乌", "焦", "巴", "弓", "牧", "隗", "山", "谷", "车", "侯", "宓", "蓬", "全", "郗", "班", "仰", "秋", "仲",
				"伊", "宫", "宁", "仇", "栾", "暴", "甘", "钭", "厉", "戎", "祖", "武", "符", "刘", "景", "詹", "束", "龙", "叶", "幸",
				"司", "韶", "郜", "黎", "蓟", "溥", "印", "宿", "白", "怀", "蒲", "邰", "从", "鄂", "索", "咸", "籍", "赖", "卓", "蔺",
				"屠", "蒙", "池", "乔", "阴", "郁", "胥", "能", "苍", "双", "闻", "莘", "党", "翟", "谭", "贡", "劳", "逄", "姬", "申",
				"扶", "堵", "冉", "宰", "郦", "雍", "却", "璩", "桑", "桂", "濮", "牛", "寿", "通", "边", "扈", "燕", "冀", "浦", "尚",
				"农", "温", "别", "庄", "晏", "柴", "瞿", "阎", "充", "慕", "连", "茹", "习", "宦", "艾", "鱼", "容", "向", "古", "易",
				"慎", "戈", "廖", "庾", "终", "暨", "居", "衡", "步", "都", "耿", "满", "弘", "匡", "国", "文", "寇", "广", "禄", "阙",
				"东", "欧", "殳", "沃", "利", "蔚", "越", "夔", "隆", "师", "巩", "厍", "聂", "晁", "勾", "敖", "融", "冷", "訾", "辛",
				"阚", "那", "简", "饶", "空", "曾", "毋", "沙", "乜", "养", "鞠", "须", "丰", "巢", "关", "蒯", "相", "查", "后", "荆",
				"红", "游", "郏", "竺", "权", "逯", "盖", "益", "桓", "公", "仉", "督", "岳", "帅", "缑", "亢", "况", "郈", "有", "琴",
				"归", "海", "晋", "楚", "闫", "法", "汝", "鄢", "涂", "钦", "商", "牟", "佘", "佴", "伯", "赏", "墨", "哈", "谯", "篁",
				"年", "爱", "阳", "佟", "言", "福", "南", "火", "铁", "迟", "漆", "官", "冼", "真", "展", "繁", "檀", "祭", "密", "敬",
				"揭", "舜", "楼", "疏", "冒", "浑", "挚", "胶", "随", "高", "皋", "原", "种", "练", "弥", "仓", "眭", "蹇", "覃", "阿",
				"门", "恽", "来", "綦", "召", "仪", "风", "介", "巨", "木", "京", "狐", "郇", "虎", "枚", "抗", "达", "杞", "苌", "折",
				"麦", "庆", "过", "竹", "端", "鲜", "皇", "亓", "老", "是", "秘", "畅", "邝", "还", "宾", "闾", "辜", "纵", "侴", "万俟",
				"司马", "上官", "欧阳", "夏侯", "诸葛", "闻人", "东方", "赫连", "皇甫", "羊舌", "尉迟", "公羊", "澹台", "公冶", "宗正", "濮阳",
				"淳于", "单于", "太叔", "申屠", "公孙", "仲孙", "轩辕", "令狐", "钟离", "宇文", "长孙", "慕容", "鲜于", "闾丘", "司徒", "司空",
				"兀官", "司寇", "南门", "呼延", "子车", "颛孙", "端木", "巫马", "公西", "漆雕", "车正", "壤驷", "公良", "拓跋", "夹谷", "宰父",
				"谷梁", "段干", "百里", "东郭", "微生", "梁丘", "左丘", "东门", "西门", "南宫", "第五", "公仪", "公乘", "太史", "仲长", "叔孙",
				"屈突", "尔朱", "东乡", "相里", "胡母", "司城", "张廖", "雍门", "毋丘", "贺兰", "綦毋", "屋庐", "独孤", "南郭", "北宫", "王孙" };

		Random random = new Random(System.currentTimeMillis());
		int index = random.nextInt(names.length - 1);
		// 数组中的姓
		String name = names[index].concat(names[index + 1]).concat(names[index + 2]).concat(names[index + 3])
				.concat(names[index + 4]).concat(names[index + 5]);
		return name;
	}
}
