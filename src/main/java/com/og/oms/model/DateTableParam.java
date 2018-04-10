package com.og.oms.model;

import com.baomidou.mybatisplus.plugins.Page;

public class DateTableParam<T> {
    /**
     * 初始分页起始点
     */
    private int iDisplayStart;
    /**
     * 更改初始页面长度
     */
    private int iDisplayLength;

    public Page<T> getPage(){
        Page<T> page = new Page<>();
        page.setCurrent(iDisplayStart);
        page.setSize(iDisplayLength);
        return page;
    }

    public int getiDisplayStart() {
        return iDisplayStart;
    }

    public void setiDisplayStart(int iDisplayStart) {
        this.iDisplayStart = iDisplayStart;
    }

    public int getiDisplayLength() {
        return iDisplayLength;
    }

    public void setiDisplayLength(int iDisplayLength) {
        this.iDisplayLength = iDisplayLength;
    }
}
