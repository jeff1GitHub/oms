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
 * 线路类型
 */
public enum LinkTypeEnum implements IEnum {
    QQ(2, "QQ"),
    EMAIL(3, "Email"),
    PHONE(4, "手机"),
    TEL(5, "座机"),
    WECHAT(6, "微信"),
    TG(7, "TG"),
    SKYPE(8, "skype"),
    WHATAPP(9, "whatapp"),;
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

    LinkTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
        this.del = 0;
    }

    LinkTypeEnum(Integer code, String name, Integer del) {
        this.code = code;
        this.name = name;
        this.del = del;
    }

    /**
     * 清空枚举
     */
    public static void clearEnum() {
        EnumUtil.clearEnum(LinkTypeEnum.class);
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
        EnumUtil.addEnum(LinkTypeEnum.class, String.valueOf(code), additionalTypes, additionalValues);
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
        for(LinkTypeEnum iEnum : LinkTypeEnum.values()) {
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
    
    public static String getNameByCode(Integer code) {
    	for(LinkTypeEnum tempEnum : LinkTypeEnum.values()) {
    		if(tempEnum.getCode().equals(code)) {
    			return tempEnum.getName();
    		}
    	}
    	 return "";
    }
}

