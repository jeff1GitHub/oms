package com.og.oms.service.impl;

import com.og.oms.model.TaskAttachment;
import com.og.oms.dao.TaskAttachmentMapper;
import com.og.oms.service.ITaskAttachmentService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jeff
 * @since 2017-11-29
 */
@Service
public class TaskAttachmentServiceImpl extends ServiceImpl<TaskAttachmentMapper, TaskAttachment> implements ITaskAttachmentService {

	@Override
	public boolean addTaskAttachmentByTaskId(Integer id, String fileNames, String filePaths) {
		if(!StringUtils.isEmpty(fileNames)) {
			String[] fileNamess = fileNames.split(";");
			String[] filePathss = filePaths.split(";");
			List<TaskAttachment> taskAttachments = new ArrayList<>();
			TaskAttachment taskAttachment;
			for(int i = 0; i<fileNamess.length; i++) {
				taskAttachment = new TaskAttachment();
				taskAttachment.setTaskId(id);
				taskAttachment.setFileName(fileNamess[i]);
				taskAttachment.setUrl(filePathss[i]);
				taskAttachments.add(taskAttachment);
			}
			return this.insertBatch(taskAttachments);
		}
		return false;
	}

	@Override
	public List<TaskAttachment> getTaskAttachmentByTaskId(Integer id) {
		EntityWrapper<TaskAttachment> wrapper = new EntityWrapper<>();
		wrapper.eq("task_id", id);
		return this.selectList(wrapper);
	}
	
}
