package com.og.oms.service;

import com.og.oms.model.TaskAttachment;

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
public interface ITaskAttachmentService extends IService<TaskAttachment> {

	/**
	 * 根据任务ID添加附件内容
	 * 
	 * @param id
	 * @param fileNames
	 * @param filePaths
	 * @return
	 */
	boolean addTaskAttachmentByTaskId(Integer id, String fileNames, String filePaths);

	/**
	 * 根据任务ID获取对应的附件信息
	 * 
	 * @param id
	 * @return
	 */
	List<TaskAttachment> getTaskAttachmentByTaskId(Integer id);
	
}
