package com.og.oms.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.og.oms.dao.TaskLogMapper;
import com.og.oms.enums.WorkStatusEnum;
import com.og.oms.model.TaskLog;
import com.og.oms.model.User;
import com.og.oms.service.ITaskLogService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jeff
 * @since 2017-11-25
 */
@Service
public class TaskLogServiceImpl extends ServiceImpl<TaskLogMapper, TaskLog> implements ITaskLogService {

	@Override
	public void addLog(User user, Integer workId, String op, int status) {
		TaskLog taskLog = new TaskLog();
		taskLog.setTaskId(workId);
		if("add".equals(op)) {
			taskLog.setType(WorkStatusEnum.SUBMIT);
			taskLog.setMessage(user.getAccount() + "提交任务");
		} else if("accept".equals(op)) {
			taskLog.setType(WorkStatusEnum.PROCESSING);
			taskLog.setMessage(user.getAccount() + "接受任务");
		} else if("finish".equals(op)) {
			if(status==1) {
				taskLog.setType(WorkStatusEnum.PROCESSED);
				taskLog.setMessage(user.getAccount() + "完成任务，状态：已解决");
			} else {
				taskLog.setType(WorkStatusEnum.INVALID);
				taskLog.setMessage(user.getAccount() + "完成任务，状态：无效问题");
			}
			
		} else if("confirm".equals(op)) {
			if(status ==1) {
				taskLog.setType(WorkStatusEnum.FINISHED);
				taskLog.setMessage(user.getAccount() + "确认任务，状态：已完成");
			} else {
				taskLog.setType(WorkStatusEnum.NOT_RESOLVED);
				taskLog.setMessage(user.getAccount() + "确认任务，状态：未解决");
			}
		}
		taskLog.setCreateUser(user.getAccount());
		taskLog.setCreateTime(new Date());
		this.insert(taskLog);
	}

	@Override
	public List<TaskLog> getTaskLogByWorkId(Integer id) {
		EntityWrapper<TaskLog> wrapper = new EntityWrapper<>();
		wrapper.eq("task_id", id);
		
		List<TaskLog> taskLogs = this.selectList(wrapper);
		return taskLogs;
	}
	
}
