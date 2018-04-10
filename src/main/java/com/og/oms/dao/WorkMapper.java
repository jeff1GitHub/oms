package com.og.oms.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.og.oms.model.Work;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 工单Mapper 接口
 * </p>
 *
 * @author jeff
 * @since 2017-10-31
 */
public interface WorkMapper extends BaseMapper<Work> {
    /**
     * 通过条件获取工单列表
     *
     * @param map
     * @return
     */
    List<Work> selectWorkList(@Param("params") Map<String, Object> map);


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
     * @return
     */
    List<Map<String, Object>> platformReport(@Param("params") Map<String, Object> params);

    /**
     * 站点统计报表
     *
     * @return
     */
    List<Map<String, Object>> stationReport(@Param("params") Map<String, Object> params);

    /**
     * 别名统计报表
     *
     * @return
     */
    List<Map<String, Object>> aliasReport(@Param("params") Map<String, Object> params);
}