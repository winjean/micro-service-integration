package com.winjean.common;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;
import org.apache.http.HttpStatus;

@Data
public class BaseResponse {

    //返回给调用服务的状态码
    public static final int INVOKE_SUCCESS_CODE = HttpStatus.SC_OK;

    public static final int OTHER_ERROR_CODE = 999;

    //返回给调用服务的消息
    public static final String INVOKE_SUCCESS = "invoke success";
    public static final String INVOKE_ERROR = "invoke error";

    @JacksonXmlProperty
    private int code;

    @JacksonXmlProperty
    private String msg;

    @JacksonXmlProperty
    private Object data;

    public static BaseResponse getSuccessResponse(){
        return getResponse(HttpStatus.SC_OK,INVOKE_SUCCESS);
    }

    public static BaseResponse getSuccessResponse(String msg){
        return getResponse(HttpStatus.SC_OK,msg);
    }

    public static BaseResponse getSuccessResponse(String msg, Object data){
        return getResponse(HttpStatus.SC_OK,msg,data);
    }

    public static BaseResponse getSuccessResponse(Object data){
        return getSuccessResponse(INVOKE_SUCCESS,data);
    }

    public static BaseResponse getFailureResponse(){
        return getResponse( HttpStatus.SC_INTERNAL_SERVER_ERROR, INVOKE_ERROR);
    }

    public static BaseResponse getFailureResponse(Object data){
        return getFailureResponse(INVOKE_ERROR, data);
    }

    public static BaseResponse getFailureResponse(String msg){
        return getFailureResponse(msg,null);
    }

    public static BaseResponse getFailureResponse(String msg, Object data){
        return getResponse( HttpStatus.SC_INTERNAL_SERVER_ERROR, msg, data);
    }

    public static BaseResponse getResponse(int code, String msg,Object data){
        BaseResponse response = new BaseResponse();
        response.setCode(code);
        response.setMsg(msg);
        if(data != null) response.setData(data);
        return response;
    }

    public static BaseResponse getResponse(int code, String msg){
        return getResponse(code, msg, null);
    }
}
