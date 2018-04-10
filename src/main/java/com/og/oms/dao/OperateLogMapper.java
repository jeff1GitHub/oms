package com.og.oms.dao;

import com.og.oms.model.OperateLog;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author jeff
 * @since 2017-11-29
 */
public interface OperateLogMapper extends BaseMapper<OperateLog> {

	/**
	 * 通过查询条件查询操作日志
	 * 
	 * @param map
	 * @return
	 */
	List<OperateLog> getOperateLogList(Map<String, Object> map);

}