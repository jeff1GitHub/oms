package com.og.oms.enums;

import com.baomidou.mybatisplus.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import com.og.oms.utils.EnumUtil;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public enum ContractTypeEnum implements IEnum {
    NORMAL(0, "其他");

    /**
     * 标识
     */
    private Integer code;
    /**
     * 文字描述
     */
    private String name;

    /**
     * 删除标记
     */
    Integer del;



    ContractTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
        this.del = 0;
    }

    ContractTypeEnum(Integer code, String name, Integer del) {
        this.code = code;
        this.name = name;
        this.del = del;
    }

    /**
     * 清空枚举
     */
    public static void clearEnum() {
        EnumUtil.clearEnum(ContractTypeEnum.class);
    }

    /**
     * 增加一个枚举实例
     *
     * @param code
     * @param name
     */
    public static void addEnum(Integer code, String name) {
        addEnum(code, name, Integer.valueOf(0));
    }

    /**
     * 增加一个枚举实例
     *
     * @param code
     * @param name
     * @param del
     */
    public static void addEnum(Integer code, String name, Integer del) {
        Class[] additionalTypes = new Class[]{Integer.class, String.class, Integer.class};
        Object[] additionalValues = new Object[]{code, name, del};
        EnumUtil.addEnum(ContractTypeEnum.class, String.valueOf(code), additionalTypes, additionalValues);
    }

    @Override
    public Serializable getValue() {
        return this.code;
    }

    @JsonValue
    public Map<String, Object> getDesc() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", this.code);
        map.put("name", this.name);
        return map;
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
