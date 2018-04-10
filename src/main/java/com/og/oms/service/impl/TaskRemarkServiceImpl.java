package com.og.oms.service.impl;

import com.og.oms.model.TaskRemark;
import com.og.oms.dao.TaskRemarkMapper;
import com.og.oms.service.ITaskRemarkService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jeff
 * @since 2017-11-27
 */
@Service
public class TaskRemarkServiceImpl extends ServiceImpl<TaskRemarkMapper, TaskRemark> implements ITaskRemarkService {

	@Override
	public List<TaskRemark> getTaskRemarkByTaskId(Integer id) {
		EntityWrapper<TaskRemark> wrapper = new EntityWrapper<>();
		wrapper.eq("task_id", id);
		List<TaskRemark> taskRemarks = this.selectList(wrapper);
		return taskRemarks;
	}
}
