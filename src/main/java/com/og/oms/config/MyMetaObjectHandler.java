package com.og.oms.config;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/** mybatisplus自定义填充公共字段 ,即没有传的字段自动填充*/
@Component
public class MyMetaObjectHandler extends MetaObjectHandler  {
    //新增填充
    @Override
    public void insertFill(MetaObject metaObject) {
//        Object lastUpdateNameId = metaObject.getValue("lastUpdateNameId");
//        Object lastUpdateTime = metaObject.getValue("lastUpdateTime");
//        //获取当前登录用户
//        SysUser user = (SysUser)SecurityUtils.getSubject().getPrincipal();
//        if (null == lastUpdateNameId) {
//            metaObject.setValue("lastUpdateNameId", user.getId());
//        }
//        if (null == lastUpdateTime) {
//            metaObject.setValue("lastUpdateTime", new Date());
//        }
    }

    //更新填充
    @Override
    public void updateFill(MetaObject metaObject) {
        insertFill(metaObject);
    }
}