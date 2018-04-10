package com.og.oms.service;

import com.baomidou.mybatisplus.service.IService;
import com.og.oms.model.Station;
import com.og.oms.model.User;

import java.util.List;

/**
 * <p>
 * 站点信息服务类接口
 * </p>
 *
 * @author jeff
 * @since 2017-11-04
 */
public interface IStationService extends IService<Station> {
    /**
     * 获取站点信息列表
     *
     * @param platform 平台
     * @param level 级别
     * @param type 类型
     * @param code 编号
     * @return
     */
    List<Station> getStationList(Integer platform, Integer level, Integer type, String code);

    /**
     * 通过id获取完整站点信息
     *
     * @param id
     * @return
     */
    Station getStationById(Integer id);

    /**
     * 通过code获取站点信息
     *
     * @param code
     * @return
     */
    Station getStationByCode(String code);

    /**
     * 获取简单的站点信息
     *
     * @param id
     * @return
     */
    Station getSimpleStationById(Integer id);

    /**
     * 增加站点信息
     *
     * @param user
     * @param station
     * @return
     */
    boolean addStation(User user, Station station);

    /**
     * 修改站点信息
     *
     * @param user
     * @param station
     * @return
     */
    boolean updateStation(User user, Station station);

    /**
     * 删除站点信息
     *
     * @param user
     * @param id
     * @return
     */
    boolean delStation(User user, Integer id);

	/**
	 * 启用禁用
	 * 
	 * @param user
	 * @param station
	 * @return
	 */
	boolean updateStationIsDisable(User user, Station station);


}
