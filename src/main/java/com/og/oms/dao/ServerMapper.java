package com.og.oms.dao;

import com.og.oms.model.Server;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author jeff
 * @since 2017-11-04
 */
public interface ServerMapper extends BaseMapper<Server> {
	
	List<Server> getServerListForDropDown();

	List<Server> getServerList(Map<String, String> map);

}