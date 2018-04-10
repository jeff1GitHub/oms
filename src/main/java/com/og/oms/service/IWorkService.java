package com.og.oms.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.og.oms.enums.WorkStatusEnum;
import com.og.oms.model.User;
import com.og.oms.model.Work;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 工单服务类
 * </p>
 *
 * @author jeff
 * @since 2017-10-31
 */
public interface IWorkService extends IService<Work> {
    /**
     * 获取工单列表
     *
     * @return
     */
    List<Work> getWorkList(Integer workStatus, Integer platform, Integer faultType, Integer level, Integer priority, String search, String startTime, String endTime);

    Page<Work> getWorkList(Integer start, Integer length);

    /**
     * 获取工单信息
     *
     * @param id
     * @return
     */
    Work getWorkInfoById(Integer id);

    /**
     * 增加工单(客服)
     *
     * @param user
     * @param work
     * @return
     */
    boolean addWorkInfo(User user, Work work);

    /**
     * 修改工单
     *
     * @param user
     * @param work
     * @return
     */
    boolean updateWorkInfo(User user, Work work);

    /**
     * 接受任务(运维)
     *
     * @param user
     * @param workId
     * @return
     */
    boolean receiveWork(User user, Integer workId);

    /**
     * 完成任务(运维)
     *
     * @param user
     * @param workId
     * @param status
     * @param faultType 
     * @return
     */
    boolean finishWork(User user, Integer workId, Integer status, Integer faultType);

    /**
     * 确认完成任务(客服)
     *
     * @param user
     * @param workId
     * @param status
     * @return
     */
    boolean confirmWork(User user, Integer workId, Integer status);


    ///////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////统计方法//////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////


    /**
     * 机房统计
     *
     * @return
     */
    List<Map<String, Object>> serverRoomCount();

    /**
     * 平台统计
     *
     * @return
     */
    List<Map<String, Object>> platformCount();

    /**
     * 平台统计报表
     *
     * @param startTime
     * @param endTime
     * @return
     */
    List<Map<String, Object>> platformReport(String startTime, String endTime);

    /**
     * 站点统计报表
     *
     * @param startTime
     * @param endTime
     * @param platform
     * @return
     */
    List<Map<String, Object>> stationReport(String startTime, String endTime, Integer platform);

    /**
     * 别名统计报表
     *
     * @param startTime
     * @param endTime
     * @param platform
     * @param stationCode
     * @param aliasName
     * @return
     */
    List<Map<String, Object>> aliasReport(String startTime, String endTime, Integer platform, String stationCode, String aliasName);

	/**
	 * 根据状态获取条数
	 * 
	 * @param processed
	 * @return
	 */
	int countWorksByStatus(WorkStatusEnum processed);
}
