package com.og.oms.enums;


import com.baomidou.mybatisplus.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 状态枚举
 */
public enum StatusType implements IEnum {
    YES(1, "是"),
    NO(0, "否");

    /**
     * 标识
     */
    private Integer code;

    /**
     * 文字描述
     */
    private String text;

    StatusType(Integer code, String text) {
        this.code = code;
        this.text = text;
    }

    public static Map<Integer, String> getSelect() {
        Map<Integer, String> map = new HashMap<>(StatusType.values().length);
        for(StatusType type : StatusType.values()) {
            map.put(type.code, type.text);
        }
        return map;
    }

    @Override
    public Serializable getValue() {
        return this.code;
    }

    @JsonValue
    public String getDesc() {
        return this.text;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    public static StatusType getStatusType(Integer code) {
    	for(StatusType tempEnum : StatusType.values()) {
    		if(tempEnum.getCode().equals(code)) {
    			return tempEnum;
    		}
    	}
    	 return null;
    }
    
}
