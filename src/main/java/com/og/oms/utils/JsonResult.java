package com.og.oms.utils;

import java.util.HashMap;
import java.util.Map;

import com.og.oms.enums.ResultCode;

/**
 * 返回页面对象
 */
public class JsonResult {

    private String code;
    private String message;
    private Object data;
    
    private Map<Object, Object> others = new HashMap<>();

    public Map<Object, Object> getOthers() {
		return others;
	}

	public void setOthers(Map<Object, Object> others) {
		this.others = others;
	}

	public JsonResult() {
        this.setCode(ResultCode.SUCCESS);
        this.setMessage("成功！");
    }

    public JsonResult(Object data) {
        this.setCode(ResultCode.SUCCESS);
        this.setMessage("成功！");
        this.data = data;
    }

    public JsonResult(ResultCode code) {
        this.setCode(code);
        this.setMessage(code.msg());
    }

    public JsonResult(ResultCode code, String message) {
        this.setCode(code);
        this.setMessage(message);
    }

    public JsonResult(ResultCode code, String message, Object data) {
        this.setCode(code);
        this.setMessage(message);
        this.setData(data);
    }

    public String getCode() {
        return code;
    }

    public void setCode(ResultCode code) {
        this.code = code.val();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

