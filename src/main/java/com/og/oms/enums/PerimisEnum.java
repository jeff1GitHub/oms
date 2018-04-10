package com.og.oms.enums;

import com.baomidou.mybatisplus.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

public enum PerimisEnum implements IEnum {
    P1(0, "其他"),
    P2(0, "其他"),
    P3(0, "其他");

    /**
     * 标识
     */
    private Integer code;



    /**

     * 文字描述
     */
    private String name;

    PerimisEnum(Integer code, String name) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return name;
    }

    public void setText(String text) {
        this.name = text;
    }
}
