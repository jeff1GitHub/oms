package com.og.oms.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.og.oms.model.TaskLog;
import com.og.oms.model.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jeff
 * @since 2017-11-25
 */
public interface ITaskLogService extends IService<TaskLog> {

	/**
	 * 添加任务操作日志
	 * 
	 * @param user
	 * @param work
	 */
	void addLog(User user, Integer workId, String op, int status);

	/**
	 * 根据任务id获取任务的操作日志
	 * 
	 * @param id
	 */
	List<TaskLog> getTaskLogByWorkId(Integer id);
}
