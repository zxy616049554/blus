
package com.rk.report;

import java.io.Serializable;
import java.lang.reflect.Method;

import com.esotericsoftware.minlog.Log;
import com.rk.exception.BaseException;
import com.rk.util.StringUtil;



/**
 * 【报文返回封装类】
 * @author wanglf
 * @date 2018年9月26日 上午9:47:06
 * @version V1.0
 */
public class ResponseBoxDto implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -8060697625884165184L;

	/**
	 * 报文体信息
	 */
	private ResponseBodyDto responseBody;

	public ResponseBodyDto getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(ResponseBodyDto responseBody) {
		this.responseBody = responseBody;
	}

	@Override
	public String toString() {
		return "ResponseBoxDto [responseBody=" + responseBody + "]";
	}

	public static ResponseBoxDto succ() {
		ResponseBoxDto ret = new ResponseBoxDto();
		ResponseBodyDto respBody = new ResponseBodyDto();
		respBody.setData(null);
		respBody.setStatus(Status.succ());
		ret.setResponseBody(respBody);
		return ret;
	}
	
	public static ResponseBoxDto succ(Object data) {
		ResponseBoxDto ret = new ResponseBoxDto();
		ResponseBodyDto respBody = new ResponseBodyDto();
		respBody.setData(data);
		respBody.setStatus(Status.succ());
		ret.setResponseBody(respBody);
		return ret;
	}
	
	public static ResponseBoxDto fail() {
		ResponseBoxDto ret = new ResponseBoxDto();
		ResponseBodyDto respBody = new ResponseBodyDto();
		respBody.setStatus(Status.fail());
        respBody.setData(null);
		ret.setResponseBody(respBody);
		return ret;
	}
	
	public static ResponseBoxDto fail(Status status) {
		ResponseBoxDto ret = new ResponseBoxDto();
		ResponseBodyDto respBody = new ResponseBodyDto();
		respBody.setStatus(status);
        respBody.setData(null);
		ret.setResponseBody(respBody);
        return ret;
    }
	
	public static ResponseBoxDto fail(String statusCode, String statusMessage) {
		Status status = new Status();
        status.setStatusCode(statusCode);
        status.setStatusMessage(statusMessage);
        return ResponseBoxDto.fail(status);
    }
	
	public static ResponseBoxDto fail(BaseException exception) {
        Status status = new Status();
        status.setStatusCode(exception.getErrCode());
        status.setStatusMessage(exception.getErrMsg());
        return ResponseBoxDto.fail(status);
    }

	public static ResponseBoxDto unusual(String desc) {
		ResponseBoxDto ret = new ResponseBoxDto();
		ResponseBodyDto respBody = new ResponseBodyDto();
		respBody.setStatus(Status.unusual(desc));
        respBody.setData(null);
		ret.setResponseBody(respBody);
		return ret;
	}
	
	public static ResponseBoxDto unusual(Object data,String desc) {
        ResponseBoxDto ret = new ResponseBoxDto();
        ResponseBodyDto respBody = new ResponseBodyDto();
        respBody.setStatus(Status.unusual(desc));
        respBody.setData(null);
        ret.setResponseBody(respBody);
        return ret;
    }
	
	/**
	 *  传递枚举值显示提示信息
	 * @param data
	 * @param enumClass
	 * @return
	 */
    public static ResponseBoxDto setStatusEnum(Object data,Object enumClass) {
        ResponseBoxDto ret = new ResponseBoxDto();
        ResponseBodyDto respBody = new ResponseBodyDto();
        respBody.setData(data);
        Status status=new Status();
        String code="";
        String desc="";
        String[] result=ResponseBoxDto.getEumValue(enumClass);
        code = result[0];//编码
        desc = result[1];//描述
        if(StringUtil.isEmpty(code)||StringUtil.isEmpty(desc)) {
            respBody.setStatus(Status.fail());
            respBody.setData(null);
            ret.setResponseBody(respBody);
            return ret;
        }else {
            status.setStatusCode(code);
            status.setStatusMessage(desc);
            respBody.setStatus(status);
        }
        ret.setResponseBody(respBody);
        return ret;
    }
    
    
    /**
      * 解析枚举值
     * @param enumClass
     * @return
     */
    public static String[] getEumValue(Object enumClass) {
        Method codeMethod,descMethod;
        String[] result=new String[]{"",""};
        String methodName="";
        try {
            Method[] methods=enumClass.getClass().getMethods();
            for(int i=0;i<methods.length;i++) {
                methodName=methods[i].getName();
                if(methodName.equals("code")){
                    codeMethod = enumClass.getClass().getMethod(methodName);
                    result[0]=codeMethod.invoke(enumClass)==null?"":(String) codeMethod.invoke(enumClass);
                }else if(methodName.equals("message")||methodName.equals("desc")){
                    descMethod = enumClass.getClass().getMethod(methodName);
                    result[1]=descMethod.invoke(enumClass)==null?"":(String) descMethod.invoke(enumClass);
                }
            } 
        } catch (Exception e) {
           Log.error("[ResponseBoxDto.getEumValue]-["+enumClass+"枚举值不存在请重新输入]");
           return result;
        }
        return result;
    }
}
