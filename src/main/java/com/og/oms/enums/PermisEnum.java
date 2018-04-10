package com.og.oms.enums;

import com.baomidou.mybatisplus.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

public enum PermisEnum implements IEnum {
    NORMAL(0, "其他");

    /**
     * 标识
     */
    private Integer code;
    /**
     * 文字描述
     */
    private String name;

    PermisEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public Serializable getValue() {
        return this.code;
    }

    @JsonValue
    public String getDesc() {
        return this.name;
    }
}
