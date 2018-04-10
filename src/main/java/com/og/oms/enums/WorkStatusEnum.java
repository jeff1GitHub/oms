package com.og.oms.enums;

import com.baomidou.mybatisplus.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import com.og.oms.utils.EnumUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum WorkStatusEnum implements IEnum {
    SUBMIT(1, "已提交", 0),
    PROCESSING(2, "处理中", 0),
    PROCESSED(3, "已处理", 0),
    NOT_RESOLVED(4, "未解决", 0),
    FINISHED(5, "已完成", 0),
    INVALID(6, "无效问题", 0);
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

    WorkStatusEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
        this.del = 0;
    }

    WorkStatusEnum(Integer code, String name, Integer del) {
        this.code = code;
        this.name = name;
        this.del = del;
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
        EnumUtil.addEnum(WorkStatusEnum.class, String.valueOf(code), additionalTypes, additionalValues);
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
        for(WorkStatusEnum iEnum : WorkStatusEnum.values()) {
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

