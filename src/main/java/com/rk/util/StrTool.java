
package com.rk.util;

public class StrTool {

    public static final String VISADATEDELIMITER = "-";

    /**
     * 获取子串在主串中出现第 n 次的位置
     * @param strMain String 主字符串
     * @param strSub String 子字符串
     * @param intTimes int 出现次数
     * @return int 位置值,如果子串在主串中没有出现指定次数,则返回-1
     */
    public static int getPos(String strMain, String strSub, int intTimes) {
        int intCounter = 0; // 循环记数
        int intPosition = 0; // 位置记录
        int intLength = strSub.length(); // 子串长度

        if (intTimes <= 0) {
            return -1;
        }
        while (intCounter < intTimes) {
            intPosition = strMain.indexOf(strSub, intPosition);
            if (intPosition == -1) {
                return -1;
            }
            intCounter++;
            intPosition += intLength;
        }
        return intPosition - intLength;
    }

    /**
     * 将输入的字符串进行转换，如果为空，则返回""，如果不空，则返回该字符串去掉前后空格
     * @param tStr 输入字符串
     * @return 如果为空，则返回""，如果不空，则返回该字符串去掉前后空格
     */
    public static String cTrim(String tStr) {
        String ttStr = "";
        if (tStr == null) {
            ttStr = "";
        } else {
            ttStr = tStr.trim();
        }
        return ttStr;
    }

}
