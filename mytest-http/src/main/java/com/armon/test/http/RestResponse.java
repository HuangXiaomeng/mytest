package com.armon.test.http;

import java.util.HashMap;
import java.util.Map;

public class RestResponse {

    private Map<String, Object> dataMap = new HashMap<String, Object>();
    private Map<String, Object> paramsMap = new HashMap<String, Object>();

    private RestResponse() {
        dataMap.put("params", paramsMap);
    }

    public static RestResponse create() {
        return new RestResponse();
    }

    public void setRespCode(int code) {
        dataMap.put("respCode", code);
    }

    public void setSuccess(boolean value) {
        dataMap.put("success", value);
    }

    public void setWrongParams(boolean value) {
        dataMap.put("wrongParams", value);
    }

    public void setRespMsg(String msg) {
        dataMap.put("respMsg", msg);
    }

    public void addData(String key, Object value) {
        dataMap.put(key, value);
    }

    public void addParam(String key, Object value) {
        paramsMap.put(key, value);
    }

    public Map<String, Object> toMap() {
        return dataMap;
    }

}