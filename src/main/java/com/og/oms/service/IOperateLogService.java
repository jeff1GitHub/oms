package com.og.oms.service;

import com.og.oms.annotation.PermisAnnotation;
import com.og.oms.model.OperateLog;
import com.og.oms.model.User;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jeff
 * @since 2017-11-29
 */
public interface IOperateLogService extends IService<OperateLog> {

	/**
	 * 保存操作日志
	 * 
	 * @param user
	 * @param methodName
	 * @param permis
	 * @param args
	 * @return
	 */
	boolean addOperateLog(User user, String methodName, PermisAnnotation permis, Object[] args);

	/**
	 * 根据查询条件查询操作日志
	 * 
	 * @param account
	 * @param logType
	 * @param startDay
	 * @param endDay
	 * @return
	 */
	List<OperateLog> getOperateLogList(String account, Integer logType, String startDay, String endDay);
	
}
