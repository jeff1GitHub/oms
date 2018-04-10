package com.og.oms.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.og.oms.annotation.PermisAnnotation;
import com.og.oms.model.OperateLog;
import com.og.oms.service.IOperateLogService;
import com.og.oms.utils.JsonResult;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jeff
 * @since 2017-11-29
 */
@RestController
public class OperateLogController {
    @Autowired
    private IOperateLogService operateLogService;

    /**
     * 获取登录日志
     *
     * @param account
     * @return
     */
    @PermisAnnotation(id = 504, name = "登录日志-列表")
    @RequestMapping(value = "operLog", method = RequestMethod.GET)
    public JsonResult getOperateLogList(String account, Integer logType, String startDay, String endDay) {
    	List<OperateLog> operateLogList = operateLogService.getOperateLogList(account, logType, startDay, endDay);
        JsonResult result = new JsonResult(operateLogList);
        return result;
    }
}
