package com.og.oms.enums;

import com.baomidou.mybatisplus.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import com.og.oms.utils.EnumUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 域名用途枚举类
 */
public enum DomainPurposeEnum implements IEnum {
    ;

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
    private Integer del;

    DomainPurposeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
        this.del = 0;
    }

    DomainPurposeEnum(Integer code, String name, Integer del) {
        this.code = code;
        this.name = name;
        this.del = del;
    }

    /**
     * 清空枚举
     */
    public static void clearEnum() {
        EnumUtil.clearEnum(DomainPurposeEnum.class);
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
        EnumUtil.addEnum(DomainPurposeEnum.class, String.valueOf(code), additionalTypes, additionalValues);
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

    /**
     * 获取键值对用于下拉列表使用
     *
     * @return
     */
    public static List<IEnum> getSelect() {
        List<IEnum> list = new ArrayList<>();
        for(DomainPurposeEnum iEnum : DomainPurposeEnum.values()) {
            if(iEnum.getDel() == 0) {
                list.add(iEnum);
            }
        }
        return list;
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

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }
}
