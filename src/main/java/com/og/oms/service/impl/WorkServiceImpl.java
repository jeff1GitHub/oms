package com.og.oms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.og.oms.dao.WorkMapper;
import com.og.oms.enums.FaultTypeEnum;
import com.og.oms.enums.WorkStatusEnum;
import com.og.oms.exception.OmsException;
import com.og.oms.model.User;
import com.og.oms.model.Work;
import com.og.oms.service.IWorkService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 工单服务实现类
 * </p>
 *
 * @author jeff
 * @since 2017-10-31
 */
@Service
public class WorkServiceImpl extends ServiceImpl<WorkMapper, Work> implements IWorkService {

    @Override
    public List<Work> getWorkList(Integer workStatus, Integer platform, Integer faultType, Integer level, Integer priority, String search, String startTime, String endTime) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", workStatus);
        map.put("platform", platform);
        map.put("faultType", faultType);
        map.put("level", level);
        map.put("priority", priority);
        map.put("search", search);
        map.put("startTime", startTime);
        map.put("endTime", endTime);

        return this.baseMapper.selectWorkList(map);
    }

    @Override
    public Page<Work> getWorkList(Integer start, Integer length) {
        EntityWrapper<Work> wrapper = new EntityWrapper<>();
        Page<Work> page = super.selectPage(new Page<>(start / length + 1, length), wrapper);
        return page;
    }

    @Override
    public Work getWorkInfoById(Integer id) {
        return this.selectById(id);
    }

    @Override
    public boolean addWorkInfo(User user, Work work) {
        work.setCreateTime(new Date());
        work.setCreateUser(user.getAccount());
        return this.insert(work);
    }

    @Override
    public boolean updateWorkInfo(User user, Work work) {
        work.setUpdateTime(new Date());
        work.setUpdateUser(user.getAccount());
        return this.updateById(work);
    }

    @Override
    public boolean receiveWork(User user, Integer workId) {
        // 获取任务
        Work work = this.getWorkInfoById(workId);
        if(work == null) {
            throw new OmsException("任务不存在!");
        }

        // 判断当前任务状态
        if(work.getStatus() != WorkStatusEnum.SUBMIT) {
            throw new OmsException("任务状态为" + work.getStatus().getName() + "!不能接受该任务");
        }

        // 修改任务状态
        work.setStatus(WorkStatusEnum.PROCESSING);
        work.setReceiveUser(user.getAccount());
        work.setReceiveTime(new Date());
        work.setUpdateUser(user.getAccount());
        work.setUpdateTime(new Date());

        return this.updateWorkInfo(user, work);
    }

    @Override
    public boolean finishWork(User user, Integer workId, Integer status, Integer faultType) {
        // 获取任务
        Work work = this.getWorkInfoById(workId);
        if(work == null) {
            throw new OmsException("任务不存在!");
        }

        // 判断当前任务状态
        if(work.getStatus() != WorkStatusEnum.PROCESSING) {
            throw new OmsException("任务状态为" + work.getStatus().getName() + "!不能接受该任务");
        }

        // 判断操作人
        if(!work.getReceiveUser().equals(user.getAccount())) {
            throw new OmsException("不能操作不是自己接受的任务!");
        }

        // 修改任务状态
        if(status == 1) {
        	work.setStatus(WorkStatusEnum.PROCESSED);
        } else if(status == 0) {
            work.setStatus(WorkStatusEnum.INVALID);
        } else {
            throw new OmsException("确认状态不正确");
        }
        work.setFaultType(FaultTypeEnum.getFaultTypeEnum(faultType));
        work.setFinishType(status);
        work.setFinishTime(new Date());
        work.setUpdateTime(new Date());
        work.setUpdateUser(user.getAccount());

        return this.updateWorkInfo(user, work);
    }

    @Override
    public boolean confirmWork(User user, Integer workId, Integer status) {
        // 获取任务
        Work work = this.getWorkInfoById(workId);
        if(work == null) {
            throw new OmsException("任务不存在!");
        }

        // 判断当前任务状态
        if(work.getStatus() != WorkStatusEnum.PROCESSED) {
            throw new OmsException("任务状态为" + work.getStatus().getName() + "!不能接受该任务");
        }

        // 判断操作人
        if(!work.getReceiveUser().equals(user.getAccount())) {
            throw new OmsException("不能操作不是自己接受的任务!");
        }

        // 修改任务状态
        if(status == 1) {
            work.setStatus(WorkStatusEnum.FINISHED);
        } else if(status == 0) {
            work.setStatus(WorkStatusEnum.NOT_RESOLVED);
        } else {
            throw new OmsException("确认状态不正确");
        }

        work.setUpdateTime(new Date());
        work.setUpdateUser(user.getAccount());

        return this.updateWorkInfo(user, work);
    }
    
	@Override
	public int countWorksByStatus(WorkStatusEnum processed) {
		EntityWrapper<Work> wrapper = new EntityWrapper<>();
		wrapper.eq("status", processed.getCode());
		return this.selectCount(wrapper);
	}

    ///////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////统计方法//////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public List<Map<String, Object>> serverRoomCount() {
        return this.baseMapper.serverRoomCount();
    }

    @Override
    public List<Map<String, Object>> platformCount() {
        return this.baseMapper.platformCount();
    }

    @Override
    public List<Map<String, Object>> platformReport(String startTime, String endTime) {
        Map<String, Object> params = new HashMap<>();
        params.put("startTime", startTime);
        params.put("endTime", endTime);
        return this.baseMapper.platformReport(params);
    }

    @Override
    public List<Map<String, Object>> stationReport(String startTime, String endTime, Integer platform) {
        Map<String, Object> params = new HashMap<>();
        params.put("startTime", startTime);
        params.put("endTime", endTime);
        params.put("platform", platform);
        return this.baseMapper.stationReport(params);
    }

    @Override
    public List<Map<String, Object>> aliasReport(String startTime, String endTime, Integer platform, String stationCode, String aliasName) {
        Map<String, Object> params = new HashMap<>();
        params.put("startTime", startTime);
        params.put("endTime", endTime);
        params.put("platform", platform);
        params.put("station", stationCode);
        params.put("alias", aliasName);
        return this.baseMapper.aliasReport(params);
    }

}
