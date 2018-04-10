package com.og.oms.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.og.oms.exception.OmsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.og.oms.annotation.PermisAnnotation;
import com.og.oms.common.WebSocketService;
import com.og.oms.enums.ResultCode;
import com.og.oms.enums.WorkStatusEnum;
import com.og.oms.model.Alias;
import com.og.oms.model.Station;
import com.og.oms.model.TaskAttachment;
import com.og.oms.model.TaskLog;
import com.og.oms.model.TaskRemark;
import com.og.oms.model.User;
import com.og.oms.model.Work;
import com.og.oms.service.IAliasService;
import com.og.oms.service.IStationService;
import com.og.oms.service.ITaskAttachmentService;
import com.og.oms.service.ITaskLogService;
import com.og.oms.service.ITaskRemarkService;
import com.og.oms.service.IUserService;
import com.og.oms.service.IWorkService;
import com.og.oms.utils.JsonResult;

/**
 * <p>
 * 工单前端控制器
 * </p>
 *
 * @author jeff
 * @since 2017-10-31
 */
@RestController
@SessionAttributes(names = "userId", types = Integer.class)
public class WorkController extends BaseController {

    @Autowired
    private IWorkService workService;

    @Autowired
    private IAliasService aliasService;

    @Autowired
    private IStationService stationService;
    
    @Autowired
    private ITaskLogService taskLogService;
    
    @Autowired
    private ITaskRemarkService taskRemarkService;
    
    @Autowired
    private IUserService userService;
    
    @Autowired
    private ITaskAttachmentService taskAttachmentService;
    
    @Autowired
    private WebSocketService webSocketService;

    /**
     * 获取工单列表
     *
     * @param workStatus
     * @param platform
     * @param faultType
     * @param level
     * @param search
     * @param startTime
     * @param endTime
     * @return
     */
    @RequestMapping(value = "work", method = RequestMethod.GET)
    public JsonResult getWorkList(Integer workStatus, Integer platform, Integer faultType, Integer level, Integer priority, String search, String startTime, String endTime,
    		@RequestParam(required = false, name = "pageSize") Integer pageSize,
            @RequestParam(required = false, name = "startIndex") Integer startIndex,
            @RequestParam(required = false, name = "pageIndex") Integer pageIndex) 
    {
        List<Work> list = this.workService.getWorkList(workStatus, platform, faultType, level, priority, search, startTime, endTime);
        List<Work> subList = null;
        
        int endIndex = startIndex + pageSize;
        int totalCount = 0;
        if(list != null && list.size() > 0) {
        	if(endIndex > list.size()) {
        		endIndex = list.size();
        	}
        	subList = list.subList(startIndex, endIndex);
        	totalCount = list.size();
        } else {
        	subList = list;
        }
        
        JsonResult jsonResult = new JsonResult(subList);
        jsonResult.getOthers().put("pageNum", startIndex/pageSize);
        jsonResult.getOthers().put("pageSize", pageSize);
        jsonResult.getOthers().put("totalCount", totalCount);
        
        return jsonResult;
    }

    /**
     * 获取工单信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "work/{id}", method = RequestMethod.GET)
    public JsonResult getWorkInfoById(@PathVariable Integer id) {
    	Work work = this.workService.getWorkInfoById(id);
    	//获取任务操作日志
    	List<TaskLog> taskLogs = taskLogService.getTaskLogByWorkId(id);
    	if(taskLogs != null && taskLogs.size() > 0) {
    		work.setTaskLogs(taskLogs);
    	}
    	//获取任务备注
    	List<TaskRemark> taskRemarks = taskRemarkService.getTaskRemarkByTaskId(id);
    	if(taskRemarks != null && taskRemarks.size() > 0) {
    		work.setTaskRemarks(taskRemarks);
    	}
    	
    	//获取任务附件回显
    	List<TaskAttachment> taskAttachments = taskAttachmentService.getTaskAttachmentByTaskId(id);
    	if(taskAttachments != null && taskAttachments.size() > 0) {
			work.setTaskAttachments(taskAttachments);
    	}
    	
    	JsonResult jsonResult = new JsonResult(work);
    	
        return jsonResult;
    }
    
    /**
     * 增加工单
     *
     * @param userId
     * @param work
     * @param result
     * @return
     */
    @PermisAnnotation(id = 10101, name = "提交任务")
    @RequestMapping(value = "work", method = RequestMethod.PUT)
    public JsonResult addWorkInfo(@ModelAttribute("userId") Integer userId, @Valid Work work, BindingResult result, String info, 
    		String filePaths, String fileNames) {
        // 通过别名获取站点信息
        if(work.getAliasName() != null) {
            Alias alias = this.aliasService.getAliasByAlias(work.getAliasName());
            if(alias == null) {
                return new JsonResult(ResultCode.EXCEPTION, "别名不存在!");
            }
            
            if(alias.getStation() == null) {
            	return new JsonResult(ResultCode.EXCEPTION, "别名没有站点!");
            }
            // 获取站点信息
            Station station = this.stationService.getStationByCode(alias.getStation());
            // 站点编号
            work.setStationName(station.getCode());
            // 用户等级
            work.setLevel(station.getLevel());
            // 平台
            work.setPlatform(station.getPlatform());
        }
        // 工单状态
        work.setStatus(WorkStatusEnum.SUBMIT);

        JsonResult ret;
        if(workService.addWorkInfo(this.getUser(userId), work)) {
        	//写任务操作日志 add
        	taskLogService.addLog(this.getUser(userId), work.getId(), "add", 0);
        	// 保存附件所在位置和名称
        	taskAttachmentService.addTaskAttachmentByTaskId(work.getId(), fileNames, filePaths);
        	
        	//更新任务条数显示
        	getAndSendTaskCount();
        	
            ret = new JsonResult();
        } else {
            ret = new JsonResult(ResultCode.EXCEPTION);
        }
        return ret;
    }
    
    @RequestMapping(value = "getWorkCount", method = RequestMethod.GET)
    public JsonResult getWorkCount() {
    	getAndSendTaskCount();
    	return new JsonResult();
    }

    /**
     * 接受任务
     *
     * @param userId
     * @param workId
     * @return
     */
    @PermisAnnotation(id = 10201, name = "接受任务")
    @RequestMapping(value = "receiveWork/{workId}", method = RequestMethod.POST)
    public JsonResult receiveWork(@ModelAttribute("userId") Integer userId, @PathVariable Integer workId) {
        JsonResult ret;
        if(workService.receiveWork(this.getUser(userId), workId)) {
        	//写任务操作日志 accept
        	taskLogService.addLog(this.getUser(userId), workId, "accept", 0);
        	getAndSendTaskCount();
            ret = new JsonResult();
        } else {
            ret = new JsonResult(ResultCode.EXCEPTION);
        }
        return ret;
    }

    /**
     * 完成任务
     *
     * @param userId
     * @param workId
     * @param status     * @return
     */
    @PermisAnnotation(id = 10202, name = "完成任务")
    @RequestMapping(value = "finishWork/{workId}", method = RequestMethod.POST)
    public JsonResult finishWork(@ModelAttribute("userId") Integer userId, @PathVariable Integer workId, Integer faultType, Integer status) {
        if(status == null) {
            return new JsonResult(ResultCode.EXCEPTION);
        }

        JsonResult ret;
        if(workService.finishWork(this.getUser(userId), workId, status, faultType)) {
        	//写任务操作日志 finish
        	taskLogService.addLog(this.getUser(userId), workId, "finish", (status != null?status.intValue():0));
        	getAndSendTaskCount();
            ret = new JsonResult();
        } else {
            ret = new JsonResult(ResultCode.EXCEPTION);
        }
        return ret;
    }

    /**
     * 确认任务
     *
     * @param userId
     * @param workId
     * @param status     * @return
     */
    @PermisAnnotation(id = 10102, name = "确认任务")
    @RequestMapping(value = "confirmWork/{workId}", method = RequestMethod.POST)
    public JsonResult confirmWork(@ModelAttribute("userId") Integer userId, @PathVariable Integer workId, Integer status) {
        if(status == null) {
            return new JsonResult(ResultCode.EXCEPTION);
        }

        JsonResult ret;
        if(workService.confirmWork(this.getUser(userId), workId, status)) {
        	//写任务操作日志 confirm
        	taskLogService.addLog(this.getUser(userId), workId, "confirm", status);
        	getAndSendTaskCount();
            ret = new JsonResult();
        } else {
            ret = new JsonResult(ResultCode.EXCEPTION);
        }
        return ret;
    }

	/**
	 * 获取任务数量，通过webSocket推送到页面
	 */
	private void getAndSendTaskCount() {
		int csCount = workService.countWorksByStatus(WorkStatusEnum.PROCESSED);
		int opCount = workService.countWorksByStatus(WorkStatusEnum.SUBMIT);;
		webSocketService.sendMessage(csCount, opCount);
	}
	
    /**
     * 添加任务的备注
     * 
     * @param userId
     * @param id
     * @param remark
     * @return
     */
    @RequestMapping(value = "work/remark", method = RequestMethod.POST)
    public JsonResult addTaskRemark(@ModelAttribute("userId") Integer userId, Integer id, String remark) {
    	//获取当前操作用户
    	User user = userService.getUserById(userId);
    	
    	TaskRemark taskRemark = new TaskRemark();
    	//设置任务ID
    	taskRemark.setTaskId(id);
    	//设置操作用户名
    	taskRemark.setCreateUser(user.getAccount());
    	//设置操作时间
    	taskRemark.setCreateTime(new Date());
    	//设置备注内容
    	taskRemark.setRemark(remark);
    	
    	JsonResult ret;
    	if(taskRemarkService.insert(taskRemark)) {
            ret = new JsonResult();
        } else {
            ret = new JsonResult(ResultCode.EXCEPTION);
        }
    	return ret;
    }

    /**
     * ckeditor粘贴图片上传并返回访问路径(不限制图片大小)
     *
     * @param imgfile 图片文件
     * @return
     */
    @RequestMapping(value = "work/uploadImage")
    public Map<String, Object> uploadImage(@RequestParam("file") MultipartFile imgfile, HttpServletRequest req, @Value("${nas.home}")String nasHome) {
        Map<String, Object> ret = new HashMap<>();
        try {
            // 获取 文件后缀
            String fileSuffixes = imgfile.getContentType().split("/")[1];// data:image/png
            String originFileName = imgfile.getOriginalFilename();

            // 生成文件名称
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("HHmmssSSS");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
            Date time = cal.getTime();
            String filename = sdf.format(time);
            String subFoldName = sdf2.format(time);

            // 生成图片保存路径
//            String path = req.getSession().getServletContext().getRealPath("upload");
//            String filePath = path + "/" + filename + "." + fileSuffixes;
//            // 图片访问路径
//            String fileurl = "../upload/" + filename + "." + fileSuffixes;
            String separator = System.getProperty("file.separator");
            String filePath = nasHome + separator +"image"+ separator + subFoldName + separator + filename + "." + fileSuffixes;
            String dirPath = nasHome + separator +"image"+ separator + subFoldName;

            // 图片访问路径
            String fileurl =  "image"+ separator + subFoldName + separator + filename + "." + fileSuffixes;


            // 写文件到磁盘
            File newDir = new File(dirPath);
            if(!newDir.exists()) {
            	newDir.mkdirs();
            }
            File newFile = new File(filePath);
            imgfile.transferTo(newFile);

            // 返回url
            ret.put("fileName", filename + "." + fileSuffixes);
            ret.put("originFileName", originFileName);
            ret.put("url", fileurl);
            ret.put("uploaded", 1);
            ret.put("success", true);
        } catch(Exception e) {
            e.printStackTrace();
            throw new OmsException("上传图片失败!");
        }
        return ret;
    }
}
