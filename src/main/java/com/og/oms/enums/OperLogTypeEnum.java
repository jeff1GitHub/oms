package com.og.oms.enums;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

public enum OperLogTypeEnum implements IEnum {
	SEARCH(1,"查询"),
	ADD(2,"增加"),
	UPDATE(3,"修改"),
	DELETE(4,"删除");
	
	/**
     * 标识
     */
    private Integer code;

    /**
     * 文字描述
     */
    private String name;

    OperLogTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String text) {
        this.name = text;
    }
    
    public static OperLogTypeEnum getOperLogTypeEnum(Integer code) {
    	for(OperLogTypeEnum tempEnum : OperLogTypeEnum.values()) {
    		if(tempEnum.getCode().equals(code)) {
    			return tempEnum;
    		}
    	}
    	 return null;
    }
    
    /**
     * 获取键值对用于下拉列表使用
     *
     * @return
     */
    public static List<IEnum> getSelect() {
        List<IEnum> list = new ArrayList<>();
        for(OperLogTypeEnum iEnum : OperLogTypeEnum.values()) {
            list.add(iEnum);
        }
        return list;
    }

	@Override
	public Serializable getValue() {
		return this.code;
	}

    @JsonValue
    public Map<String, Object> getDesc() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        map.put("name", name);
        return map;
    }
}
