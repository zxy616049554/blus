package com.rk.enums;

public enum ENUM_EDSTATE_CODE {
	EFFECTIVE("E","有效记录"),
	INEFFECTIVE("D","无效记录");
    private final String code;
    private final String desc;

    ENUM_EDSTATE_CODE(String code, String desc) {
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
    public static String getEnumDescByKey(String key) {
        for (ENUM_EDSTATE_CODE enumItem : ENUM_EDSTATE_CODE.values()) {
            if (key.equals(enumItem.code())) {
                return enumItem.desc();
            }
        }
        return "";
    }
}
