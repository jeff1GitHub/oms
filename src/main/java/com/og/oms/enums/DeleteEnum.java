package com.og.oms.enums;

import com.baomidou.mybatisplus.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

public enum DeleteEnum implements IEnum {
    NORMAL(0, "正常"),
    DELETE(1, "删除");

    /**
     * 标识
     */
    private Integer code;
    /**
     * 文字描述
     */
    private String name;

    DeleteEnum(Integer code, String name) {
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

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getText() {
        return name;
    }

    public void setText(String text) {
        this.name = text;
    }
}
