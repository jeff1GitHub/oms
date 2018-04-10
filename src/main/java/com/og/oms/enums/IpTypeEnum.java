package com.og.oms.enums;


import com.baomidou.mybatisplus.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 状态枚举
 */
public enum IpTypeEnum implements IEnum {
    INTER(1, "外网"),
    INTRA(0, "内网");

    /**
     * 标识
     */
    private Integer code;

    /**
     * 文字描述
     */
    private String text;

    IpTypeEnum(Integer code, String text) {
        this.code = code;
        this.text = text;
    }

    public static Map<Integer, String> getSelect() {
        Map<Integer, String> map = new HashMap<>(IpTypeEnum.values().length);
        for(IpTypeEnum type : IpTypeEnum.values()) {
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
}
