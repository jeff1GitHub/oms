package com.og.oms.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.og.oms.annotation.PermisAnnotation;
import com.og.oms.dao.OperateLogMapper;
import com.og.oms.enums.OperLogTypeEnum;
import com.og.oms.model.OperateLog;
import com.og.oms.model.User;
import com.og.oms.service.IOperateLogService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jeff
 * @since 2017-11-29
 */
@Service
public class OperateLogServiceImpl extends ServiceImpl<OperateLogMapper, OperateLog> implements IOperateLogService {

	@Override
	public boolean addOperateLog(User user, String methodName, PermisAnnotation permis, Object[] args) {
		
		// 不记录查询
		if(methodName != null && !methodName.startsWith("get")) {
			
			OperateLog operateLog = new OperateLog();
			operateLog.setUserId(user.getId());
			operateLog.setUserAccount(user.getAccount());
			operateLog.setCreateTime(new Date());
			operateLog.setContext("执行方法：" + methodName + " 内容 ：" + permis.name());
			//operateLog.setContext("执行方法：" + methodName + " 内容 ：" + permis.name() + " 参数：" + "\"" + Arrays.asList(args)+ "\"");
			
			if(methodName.startsWith("add")) {
				operateLog.setLogType(OperLogTypeEnum.ADD);
			} else if (methodName.startsWith("update")) {
				operateLog.setLogType(OperLogTypeEnum.UPDATE);
			} else if (methodName.startsWith("del")) {
				operateLog.setLogType(OperLogTypeEnum.DELETE);
			} else {
				operateLog.setLogType(OperLogTypeEnum.SEARCH);
			}
			this.insert(operateLog);
		}
		
		return false;
	}

	@Override
	public List<OperateLog> getOperateLogList(String account, Integer logType, String startDay, String endDay) {
		Map<String, Object> map = new HashMap<>();
		if(!StringUtils.isEmpty(account)) {
			map.put("account", account);
		}
		if(!StringUtils.isEmpty(startDay)) {
			map.put("startDay", startDay);
		}
		if(!StringUtils.isEmpty(endDay)) {
			map.put("endDay", endDay);
		}
		if(logType != null) {
			map.put("logType", logType);
		}
		return this.baseMapper.getOperateLogList(map);
	}
	
}
