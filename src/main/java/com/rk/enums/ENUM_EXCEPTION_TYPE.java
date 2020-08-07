package com.rk.enums;

import java.util.ArrayList;
import java.util.List;

/**  
 * 【统一异常处理枚举】
 * @author  wanglf
 * @date 2019年1月20日 下午3:09:31 
 * @version V1.0  
*/
public enum ENUM_EXCEPTION_TYPE {

    /**
     * 错误编码长度为6位
     * 示例：010502 
     *          01                      05          02
     * 服务级错误（01为系统级错误）    服务模块代码    具体错误代码
     * */
    /**  
     * 01 系统级别错误
     * 02 服务级别错误
     * 
     * */
    /**  
     * 服务模块代码:
     * 01 通用模块
     * */
    SUCCESS("000000","成功"),
    FAILURE("000001","失败"),
    REPEAT_REQUEST("010100","重复请求"),
    REMOTE_ERVICE_ERROR("010101","远程服务错误"),
    IP_LIMIT("010102","IP限制不能请求该资源"),
    PERMISSION_DENIED("010103","该资源需要appkey拥有授权"),
    PARAM_ERROR("010104","参数错误，请参考API文档"),
    PARAM_NULL_ERROR("010105","参数(%s)为空，请查验接口是否存在问题"),
    SYSTEM_IS_BUSY("010106","任务过多，系统繁忙"),
    JOB_EXPIRED("010107","任务超时"),
    RPC_ERROR("010108","RPC错误"),
    ILLEGAL_REQUEST("010109","非法请求"),
    INVALID_USER("010110","不合法的用户"),
    INSUFFICIENT_APP_PERMISSIONS("010111","应用的接口访问权限受限"),
    MISS_REQUIRED_PARAMETER("010112","缺失必选参数(%s)，请参考API文档"),
    PARAMETER_VALUE_INVALID("010113","参数值非法，需为(%s)，实际为(%s)，请参考API文档"),
    REQUEST_BODY_LENGTH_OVER_LIMIT("010114","请求长度超过限制"),
    REQUEST_API_NOT_FOUND("010115","接口不存在"),
    HTTP_METHOD_IS_NOT_SUPORTED("010116","请求的HTTPMETHOD不支持，请检查是否选择了正确的POST/GET方式"),
    IP_REQUESTS_OUT_OF_RATE_LIMIT("010117","IP请求频次超过上限"),
    USER_REQUESTS_OUT_OF_RATE_LIMIT("010118","用户请求频次超过上限"),
    USER_REQUESTS_FOR_OUT_OF_RATE_LIMIT("010119","用户请求特殊接口(%s)频次超过上限"),
    
    REDISCLUSTR_USE_FAIL("010120","redis集群操作数据发生异常"),
    REDISCLUSTR_SET_PRE_FAIL("010121","redis设置缓存发生异常"),
    REDISCLUSTR_SET_EXPIRE_FAIL("010122","redis设置缓存并且自己指定过期时间发生异常"),
    REDISCLUSTR_GET_EXPIRE_FAIL("010123","redis获取指定key的缓存发生异常"),
    REDISCLUSTR_DELETE_EXPIRE_FAIL("010124","redis删除指定key的缓存发生异常"),
    REDISCLUSTR_TTL_FAIL("010125","redis判断key多久过期发生异常"),
    REDISCLUSTR_GET_VALUE_FAIL("010126","redis判断缓存中是否有对应的value发生异常"),


    DATABASE_FAIL("010127","操作数据库发生异常"),
    QUERY_TABLE_FAIL("010128","数据查询不存在"),
    RABBITMQ_FAIL("010129","推送消息发生异常"),
    PROCESS_FAIL("010130","查无此流程"),
    NOT_SUPPOT_TYPE_FAIL("010131","不支持的类型"),
    USER_PHONECODE_NULL("020001","用户手机号或验证码有误"),
    
    USER_PWD_NULL("020002","用户密码为空"),
    USER_PAR_NULL("020003","用户参数有误"),
    USER_SIGN_TRUE("020004","用户当天已经签到"),
    USER_NO_REG("020005","用户没有注册"),
    USER_CODE_FAIL("020006","手机验证码有误"),
    USER_PHONE_NOTCORRECT("020007","手机号有误"),
    USER_NOT_LOGIN("020008","用户未登陆，请登录后重试"),
    USER_NOT_BANK("020009","银行名称，银行卡号，用户姓名或者开户行为空"),
    USER_IS_LOGIN("020010","注册成功，请登录"),
    USER_NOT_ADDRESS("020011","没有发货地址！"),
    USER_MONEY_EN("020012","用户走运值不足"),
    USER_GOOD_ONE("020013","此商品每人限购一个"),
    USER_USER_NOVIP("020014","用户不是会员，或者提现金额不够365"),
    USER_USER_NOCOLL("020015","用户没有收藏该商品"),
    USER_BIG_THREE("020016","用户当天分享超过三次，不再计算走运值"),
    USER_BIG_LUCK("020017","用户当天兑换步数最多为3万步"),
    USER_NOT_LUCKVALUE("020018","用户走运值不够！"),
    USER_ISHAVA_PARENT("020019","用户已经绑定过邀请码"),
    USER_VALUE_OUT("020020","今天视频奖励已经完成"),
    USER_MY_DEV("020021","不能填写自己的邀请码"),
    USER_WIT_MONEY("020022","您的余额有以前的任务金，实际可提现金额不足365"),
    USER_THREE("020023","非会员用户，只能免费兑换三次产品，期待您成为会员！"),
    USER_REDAY("020024","助力次数已用完！"),
    USER_NAME_PWD("020025","您没有登录权限或者账户名密码错误"),
    USER_ZIYONGJIN("020026","商品金额等于365,或者480才可以使用自佣金"),
    USER_ZIYONGJIN_NOTHING("020027","您的自用金小于商品金额"),
    USER_ORDER_DONT("020028","订单不合法，请核对订单信息！"),
    USER_ZIYONGJIN_ONEHUN("020029","自用金购买365已达到套数,请尝试购买480"),
    USER_ZIYONGJIN_FOREHUN("020030","自用金购买480已达到套数,请尝试购买365"),
    USER_NOT_COUNT("020031","抽奖次数已用完，观看视频后增加"),
    USER_IS_HAVE("020032","用户已经存在，请直接支付"),
    USER_NOT_DEFAULTADDRESS("020033","用户还没有默认地址"),
    USER_NOT_USERMONEYPWD("020034","支付密码为空"),
    USER_NOTTRUE_USERMONEYPWD("020035","支付密码错误"),
    USER_WZ_NOT("020036","文章类型为空"),
    USER_IS_BIND("020037","已经绑定过手机号，请直接登录！"),
    USER_CLUB_HAVA("020038","俱乐部名称已经存在，请重新填写信息！"),
    USER_GROUP_HAVA("020039","跑团名称已经存在，请重新填写信息！"),
    USER_CLUB_PWD("020040","俱乐部名称或密码错误请重新输入！"),
    USER_CLUB_PHONENOT("020041","手机号不存在绑定的俱乐部，请重新输入手机号"),
    USER_CLUB_MONEYNOT("020042","钱不够买货了"),
    USER_OLDPWD_NOT("020043","旧密码输入不正确"),
    USER_VERSION_NOT("020044","版本过低，请等待新版本"),
    USER_NOT_DATE("020045","只有每个月10号才可以提现自用金！"),
    USER_LOGIN_TIMEOUT("020046","登录超时"),
    USER_DJ_OUTPLAY("020047","没人每天3次哦"),
    ;

    /** 枚举值码 */
    private final String code;

    /** 枚举描述 */
    private final String message;

    /**
     * 构建一个 StatusEnum 。
     * 
     * @param code    枚举值码。
     * @param message 枚举描述。
     */
    private ENUM_EXCEPTION_TYPE(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 得到枚举值码。
     * 
     * @return 枚举值码。
     */
    public String getCode() {
        return code;
    }

    /**
     * 得到枚举描述。
     * 
     * @return 枚举描述。
     */
    public String getMessage() {
        return message;
    }

    /**
     * 得到枚举值码。
     * 
     * @return 枚举值码。
     */
    public String code() {
        return code;
    }

    /**
     * 得到枚举描述。
     * 
     * @return 枚举描述。
     */
    public String message() {
        return message;
    }

    /**
     * 通过枚举值码查找枚举值。
     * 
     * @param code 查找枚举值的枚举值码。
     * @return 枚举值码对应的枚举值。
     * @throws IllegalArgumentException 如果 code 没有对应的 StatusEnum 。
     */
    public static ENUM_EXCEPTION_TYPE findStatus(String code) {
        for (ENUM_EXCEPTION_TYPE status : values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        throw new IllegalArgumentException("ResultInfo StatusEnum not legal:" + code);
    }

    /**
     * 获取全部枚举值。
     *
     * @return 全部枚举值。
     */
    public static List<ENUM_EXCEPTION_TYPE> getAllStatus() {
        List<ENUM_EXCEPTION_TYPE> list = new ArrayList<ENUM_EXCEPTION_TYPE>();
        for (ENUM_EXCEPTION_TYPE status : values()) {
            list.add(status);
        }
        return list;
    }

    /**
     * 获取全部枚举值码。
     *
     * @return 全部枚举值码。
     */
    public static List<String> getAllStatusCode() {
        List<String> list = new ArrayList<String>();
        for (ENUM_EXCEPTION_TYPE status : values()) {
            list.add(status.code());
        }
        return list;
    }
    
    /**
     * 转义报错（用于批量处理的异常）
     * @param errCode
     * @param args
     * @return
     */
    public static String getMessageStr(String errCode, Object[] args) {
        String errMsg = ENUM_EXCEPTION_TYPE.findStatus(errCode).getMessage();
        errMsg = String.format(errMsg, args);
        return errMsg;
    }


    

}

