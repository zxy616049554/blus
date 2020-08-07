/**
 * @Copyright ®2018 gjmetal Co. Ltd. All rights reserved.<br/>
 * 项目名称 : 国金大宗商品交易平台
 * 创建日期 : 2018年9月12日
 * 修改历史 : 
 *     1. [2018年9月12日]创建文件 by gjmetal
 */
package com.rk.enums;

/**  
 * 【通过返回信息，无具体信息时使用】
 * @author  wanglfsh
 * @date 2018年9月12日 下午3:37:06 
 * @version V1.0  
*/
public enum ENUM_RESULT {
    SUCCESS("01","交易成功"),FAIL("02","交易失败"),UNUSUAL("03","交易异常");
    private final String code;
    private final String desc;

    ENUM_RESULT(String code ,String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String code() {
        return code;
    }

    public String desc() {
        return desc;
    }
    /**
     * 根据key获得desc
     * @param key
     * @return
     */
    public static String getEnumDescByKey(String key){
        for(ENUM_RESULT enumItem:ENUM_RESULT.values()){
            if(key.equals(enumItem.code())){
                return enumItem.desc();
            }
        }
        return "";
    }
}
