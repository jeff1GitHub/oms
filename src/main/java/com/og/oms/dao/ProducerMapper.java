package com.og.oms.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.og.oms.model.Producer;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 供应商Mapper 接口
 * </p>
 *
 * @author jeff
 * @since 2017-11-02
 */
public interface ProducerMapper extends BaseMapper<Producer> {
    List<Map<String, Object>> getProducerList();
    
    Producer getProducerById(Integer id);
}