package com.og.oms.service;

import com.og.oms.model.TaskRemark;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jeff
 * @since 2017-11-27
 */
public interface ITaskRemarkService extends IService<TaskRemark> {

	/**
	 * 根据任务ID获取备注信息
	 * 
	 * @param id
	 * @return
	 */
	List<TaskRemark> getTaskRemarkByTaskId(Integer id);
	
}
